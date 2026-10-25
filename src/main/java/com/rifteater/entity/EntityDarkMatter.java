
package com.rifteater.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityDarkMatter extends EntityThrowable {

    public EntityDarkMatter(World worldIn) {
        super(worldIn);
    }

    public EntityDarkMatter(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (world.isRemote) {
            return;
        }

        if (result.entityHit != null
            && result.entityHit != getThrower()
            && result.entityHit instanceof EntityLivingBase) {

            EntityLivingBase target =
                (EntityLivingBase) result.entityHit;

            target.attackEntityFrom(
                DamageSource.causeIndirectMagicDamage(this, getThrower()),
                12.0F
            );

            target.knockBack(
                this, 0.8F,
                posX - target.posX,
                posZ - target.posZ
            );
        }

        for (int i = 0; i < 24; i++) {
            double ox = (rand.nextDouble() - 0.5D) * 1.5D;
            double oy = rand.nextDouble() * 1.5D;
            double oz = (rand.nextDouble() - 0.5D) * 1.5D;

            world.spawnParticle(
                EnumParticleTypes.PORTAL,
                posX + ox, posY + oy, posZ + oz,
                0, 0.04D, 0
            );

            world.spawnParticle(
                EnumParticleTypes.SPELL_WITCH,
                posX + ox, posY + oy, posZ + oz,
                0, 0.02D, 0
            );
        }

        playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.8F, 0.5F);
        setDead();
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (world.isRemote && isEntityAlive()) {
            world.spawnParticle(
                EnumParticleTypes.PORTAL,
                posX, posY, posZ,
                (rand.nextDouble() - 0.5D) * 0.08D,
                (rand.nextDouble() - 0.5D) * 0.08D,
                (rand.nextDouble() - 0.5D) * 0.08D
            );
        }
    }
}
