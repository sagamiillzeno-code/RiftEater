
package com.rifteater.registry;

import com.rifteater.RiftEater;
import com.rifteater.entity.EntityDarkMatter;
import com.rifteater.entity.EntityRiftEater;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class ModEntities {

    private ModEntities() {
    }

    public static void init() {

        if (RiftEater.instance == null) {
            throw new IllegalStateException(
                "RiftEater instance is not initialized"
            );
        }

        EntityRegistry.registerModEntity(
            new ResourceLocation("rifteater", "rift_eater"),
            EntityRiftEater.class,
            "rift_eater",
            1,
            RiftEater.instance,
            96,
            3,
            true,
            0x17121F,
            0x8D35D6
        );

        EntityRegistry.registerModEntity(
            new ResourceLocation("rifteater", "dark_matter"),
            EntityDarkMatter.class,
            "dark_matter",
            2,
            RiftEater.instance,
            64,
            1,
            true
        );
    }
}
