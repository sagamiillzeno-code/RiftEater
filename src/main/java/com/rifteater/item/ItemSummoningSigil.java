
package com.rifteater.item;

import com.rifteater.RiftEater;
import com.rifteater.entity.EntityRiftEater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSummoningSigil extends Item {

    public ItemSummoningSigil() {
        setMaxStackSize(1);
        setCreativeTab(RiftEater.TAB);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(
        World world,
        EntityPlayer player,
        EnumHand hand
    ) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote) {
            BlockPos pos = player.getPosition().offset(player.getHorizontalFacing(), 3);

            EntityRiftEater boss = new EntityRiftEater(world);
            boss.setPosition(
                pos.getX() + 0.5D,
                pos.getY(),
                pos.getZ() + 0.5D
            );

            if (world.getCollisionBoxes(boss, boss.getEntityBoundingBox()).isEmpty()
                && world.isBlockLoaded(pos)
                && world.isBlockLoaded(pos.up())
                && world.isAirBlock(pos)
                && world.isAirBlock(pos.up())
                && !world.getBlockState(pos.down()).getMaterial().isLiquid()) {

                world.spawnEntity(boss);

                world.playSound(
                    null,
                    pos,
                    SoundEvents.ENTITY_ENDERMEN_TELEPORT,
                    SoundCategory.HOSTILE,
                    1.0F,
                    0.5F
                );

                if (!player.capabilities.isCreativeMode) {
                    stack.shrink(1);
                }
            }
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
