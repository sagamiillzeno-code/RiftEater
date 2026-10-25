
package com.rifteater.registry;

import com.rifteater.RiftEater;
import com.rifteater.entity.EntityDarkMatter;
import com.rifteater.entity.EntityRiftEater;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public final class ModEntities {
    private static int nextId = 1;

    private ModEntities() {}

    public static void init() {
        EntityRegistry.registerModEntity(
            new ResourceLocation(RiftEater.MODID, "rift_eater"),
            EntityRiftEater.class,
            "rift_eater",
            nextId++,
            RiftEater.class,
            96,
            3,
            true,
            0x17121F,
            0x8D35D6
        );

        EntityRegistry.registerModEntity(
            new ResourceLocation(RiftEater.MODID, "dark_matter"),
            EntityDarkMatter.class,
            "dark_matter",
            nextId++,
            RiftEater.class,
            64,
            1,
            true
        );
    }
}
