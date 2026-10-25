
package com.rifteater.client;

import com.rifteater.RiftEater;
import com.rifteater.entity.EntityRiftEater;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRiftEater extends RenderLiving<EntityRiftEater> {

    private static final ResourceLocation TEXTURE =
        new ResourceLocation(
            RiftEater.MODID,
            "textures/entity/rift_eater.png"
        );

    public RenderRiftEater(RenderManager manager) {
        super(manager, new ModelBiped(0.0F), 0.8F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRiftEater entity) {
        return TEXTURE;
    }

    @Override
    protected void applyRotations(
        EntityRiftEater entity,
        float ageInTicks,
        float rotationYaw,
        float partialTicks
    ) {
        super.applyRotations(
            entity, ageInTicks, rotationYaw, partialTicks
        );
    }
}
