
package com.rifteater.client;

import com.rifteater.entity.EntityRiftEater;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRiftEater extends RenderLiving<EntityRiftEater> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(
                    "rifteater",
                    "textures/entity/rift_eater.png"
            );

    public RenderRiftEater(RenderManager manager) {
        super(manager, new ModelRiftEater(), 0.8F);
    }

    @Override
    protected ResourceLocation getEntityTexture(
            EntityRiftEater entity
    ) {
        return TEXTURE;
    }

    @Override
    protected void preRenderCallback(
            EntityRiftEater entity,
            float partialTickTime
    ) {
        // Увеличиваем босса, сохраняя пропорции модели.
        GlStateManager.scale(1.25F, 1.25F, 1.25F);
    }
}
