
package com.rifteater.registry;

import com.rifteater.RiftEater;
import com.rifteater.entity.EntityDarkMatter;
import com.rifteater.entity.EntityRiftEater;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class ModEntities {

    private static final int RIFT_EATER_ID = 1;
    private static final int DARK_MATTER_ID = 2;

    private ModEntities() {
    }

    public static void init() {
        if (RiftEater.class == null
                || EntityRiftEater.class == null
                || EntityDarkMatter.class == null) {
            throw new IllegalStateException(
                "RiftEater: entity registration class is null"
            );
        }

        EntityRegistry.registerModEntity(
            new ResourceLocation("rifteater", "rift_eater"),
            EntityRiftEater.class,
            "rift_eater",
            RIFT_EATER_ID,
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
            DARK_MATTER_ID,
            RiftEater.instance,
            64,
            1,
            true
        );
    }
}
