
package com.rifteater.entity;

import com.rifteater.RiftEater;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class EntityRiftEater extends EntityMob {

    private int teleportCooldown = 100;
    private int rangedCooldown = 0;
    private int meleeCooldown = 0;
    private final Random riftRandom = new Random();

    public EntityRiftEater(World worldIn) {
        super(worldIn);
        setSize(1.35F, 3.1F);
        isImmuneToFire = true;
        experienceValue = 2450;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new BossCombatAI(this));
        this.targetTasks.addTask(
            1,
            new EntityAINearestAttackableTarget<EntityPlayer>(
                this, EntityPlayer.class, true
            )
        );
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
            .setBaseValue(750.0D);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)
            .setBaseValue(18.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED)
            .setBaseValue(0.32D);
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE)
            .setBaseValue(40.0D);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE)
            .setBaseValue(0.8D);

        setHealth(getMaxHealth());
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (world.isRemote) {
            return;
        }

        if (teleportCooldown > 0) teleportCooldown--;
        if (rangedCooldown > 0) rangedCooldown--;
        if (meleeCooldown > 0) meleeCooldown--;

        EntityLivingBase target = getAttackTarget();

        if (target != null && target.isEntityAlive()) {
            double distanceSq = getDistanceSq(target);

            if (teleportCooldown <= 0
                && (distanceSq > 100.0D
                || (getHealth() < getMaxHealth() * 0.35F
                && distanceSq < 36.0D))) {

                if (tryRiftStep(target)) {
                    teleportCooldown = 100;
                }
            }
        }
    }

    private boolean tryRiftStep(EntityLivingBase target) {
        for (int attempt = 0; attempt < 12; attempt++) {
            double angle;

            if (attempt < 4) {
                angle = Math.atan2(
                    target.posZ - posZ,
                    target.posX - posX
                ) + (riftRandom.nextDouble() - 0.5D);
            } else {
                angle = riftRandom.nextDouble() * Math.PI * 2.0D;
            }

            double distance = 5.0D + riftRandom.nextDouble() * 2.0D;
            double x = posX + Math.cos(angle) * distance;
            double z = posZ + Math.sin(angle) * distance;

            BlockPos feet = new BlockPos(x, posY, z);
            BlockPos ground = feet.down();

            if (!world.isBlockLoaded(feet)
                || !world.isBlockLoaded(feet.up())) {
                continue;
            }

            if (!world.isSideSolid(ground, EnumFacing.UP)
                || !world.isAirBlock(feet)
                || !world.isAirBlock(feet.up())
                || world.getBlockState(feet).getMaterial().isLiquid()
                || world.getBlockState(feet.up()).getMaterial().isLiquid()
                || world.getBlockState(ground).getMaterial().isLiquid()) {
                continue;
            }

            double oldX = posX;
            double oldY = posY;
            double oldZ = posZ;

            spawnRiftParticles(oldX, oldY, oldZ);
            setPositionAndUpdate(x, feet.getY(), z);

            if (!world.getCollisionBoxes(
                    this, getEntityBoundingBox()).isEmpty()
                || isInsideOfMaterial(
                    net.minecraft.block.material.Material.LAVA)) {
                setPositionAndUpdate(oldX, oldY, oldZ);
                continue;
            }

            spawnRiftParticles(posX, posY, posZ);
            playSound(
                net.minecraft.init.SoundEvents.ENTITY_ENDERMEN_TELEPORT,
                1.0F,
                0.65F
            );

            return true;
        }

        return false;
    }

    private void spawnRiftParticles(double x, double y, double z) {
        for (int i = 0; i < 28; i++) {
            double ox = (riftRandom.nextDouble() - 0.5D) * 1.8D;
            double oy = riftRandom.nextDouble() * 2.8D;
            double oz = (riftRandom.nextDouble() - 0.5D) * 1.8D;

            world.spawnParticle(
                EnumParticleTypes.PORTAL,
                x + ox, y + oy, z + oz,
                (riftRandom.nextDouble() - 0.5D) * 0.15D,
                riftRandom.nextDouble() * 0.15D,
                (riftRandom.nextDouble() - 0.5D) * 0.15D
            );

            world.spawnParticle(
                EnumParticleTypes.SPELL_WITCH,
                x + ox, y + oy, z + oz,
                0, 0, 0
            );
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (meleeCooldown > 0) {
            return false;
        }

        meleeCooldown = 22;
        boolean hit = super.attackEntityAsMob(entityIn);

        if (hit && entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase) entityIn).knockBack(
                this, 1.2F,
                posX - entityIn.posX,
                posZ - entityIn.posZ
            );

            for (int i = 0; i < 10; i++) {
                world.spawnParticle(
                    EnumParticleTypes.CRIT,
                    entityIn.posX,
                    entityIn.posY + 1.0D,
                    entityIn.posZ,
                    0, 0.1D, 0
                );
            }
        }

        return hit;
    }

    public boolean canShootDarkMatter() {
        return rangedCooldown <= 0;
    }

    public void shootDarkMatter(EntityLivingBase target) {
        if (world.isRemote || target == null || rangedCooldown > 0) {
            return;
        }

        EntityDarkMatter projectile = new EntityDarkMatter(world, this);

        double dx = target.posX - posX;
        double dy = target.getEntityBoundingBox().minY
            + target.height * 0.55D - projectile.posY;
        double dz = target.posZ - posZ;

        projectile.shoot(dx, dy, dz, 0.85F, 2.0F);
        world.spawnEntity(projectile);

        playSound(
            net.minecraft.init.SoundEvents.ENTITY_GHAST_SHOOT,
            1.0F,
            0.7F
        );

        rangedCooldown = 38;
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        dropItem(Items.DIAMOND, 4 + rand.nextInt(5));

        if (rand.nextFloat() < 0.70F) {
            entityDropItem(new ItemStack(Items.DIAMOND_SWORD), 0.0F);
        }

        if (rand.nextFloat() < 0.55F) {
            entityDropItem(new ItemStack(Items.DIAMOND_PICKAXE), 0.0F);
        }

        if (rand.nextFloat() < 0.50F) {
            entityDropItem(
                new ItemStack(Items.DIAMOND_CHESTPLATE), 0.0F
            );
        }

        int books = 2 + rand.nextInt(3);

        for (int i = 0; i < books; i++) {
            ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);

            net.minecraft.enchantment.EnchantmentHelper
                .addRandomEnchantment(
                    rand, book, 25 + rand.nextInt(25), true
                );

            entityDropItem(book, 0.0F);
        }
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    private static class BossCombatAI extends EntityAIBase {
        private final EntityRiftEater boss;
        private int pathRecalc;

        BossCombatAI(EntityRiftEater boss) {
            this.boss = boss;
            setMutexBits(3);
        }

        @Override
        public boolean shouldExecute() {
            return boss.getAttackTarget() != null
                && boss.getAttackTarget().isEntityAlive();
        }

        @Override
        public boolean shouldContinueExecuting() {
            return shouldExecute();
        }

        @Override
        public void updateTask() {
            EntityLivingBase target = boss.getAttackTarget();

            if (target == null) {
                return;
            }

            boss.getLookHelper().setLookPositionWithEntity(
                target, 30.0F, 30.0F
            );

            double distanceSq = boss.getDistanceSq(target);

            if (distanceSq > 16.0D) {
                if (pathRecalc-- <= 0) {
                    boss.getNavigator().tryMoveToEntityLiving(
                        target, 1.15D
                    );
                    pathRecalc = 10;
                }

                if (distanceSq < 400.0D && boss.canShootDarkMatter()) {
                    boss.shootDarkMatter(target);
                }
            } else {
                boss.getNavigator().clearPath();

                if (boss.getEntitySenses().canSee(target)) {
                    boss.attackEntityAsMob(target);
                }
            }
        }
    }
              }
