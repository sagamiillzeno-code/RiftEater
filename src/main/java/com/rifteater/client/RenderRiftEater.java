
package com.rifteater.client;

import com.rifteater.entity.EntityRiftEater;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Random;

public class RiftEaterParticles {

    private final Random random = new Random();

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        if (event.world.isRemote) {
            Minecraft minecraft = Minecraft.getMinecraft();

            if (minecraft.world != event.world || minecraft.player == null) {
                return;
            }

            for (EntityRiftEater boss : event.world.getEntities(
                    EntityRiftEater.class,
                    entity -> entity.isEntityAlive()
            )) {
                if (minecraft.player.getDistanceSq(boss) > 32.0D * 32.0D) {
                    continue;
                }

                if (random.nextInt(3) != 0) {
                    continue;
                }

                double angle = random.nextDouble() * Math.PI * 2.0D;
                double radius = 0.8D + random.nextDouble() * 0.8D;

                double x = boss.posX + Math.cos(angle) * radius;
                double y = boss.posY + random.nextDouble() * boss.height;
                double z = boss.posZ + Math.sin(angle) * radius;

                event.world.spawnParticle(
                        EnumParticleTypes.PORTAL,
                        x, y, z,
                        (random.nextDouble() - 0.5D) * 0.08D,
                        random.nextDouble() * 0.08D,
                        (random.nextDouble() - 0.5D) * 0.08D
                );
            }
        }
    }
}
