
package com.rifteater.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRiftEater extends ModelBase {

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer core;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftHorn;
    private final ModelRenderer rightHorn;
    private final ModelRenderer backSpike1;
    private final ModelRenderer backSpike2;
    private final ModelRenderer tentacleLeft;
    private final ModelRenderer tentacleRight;

    public ModelRiftEater() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this, 32, 0);
        body.setRotationPoint(0F, -2F, 0F);
        body.addBox(-4F, -7F, -2.5F, 8, 12, 5, 0F);

        head = new ModelRenderer(this, 0, 0);
        head.setRotationPoint(0F, -7F, 0F);
        head.addBox(-3F, -5F, -3F, 6, 6, 6, 0F);
        body.addChild(head);

        core = new ModelRenderer(this, 72, 0);
        core.setRotationPoint(0F, -1F, -2.6F);
        core.addBox(-1.5F, -2F, -0.5F, 3, 4, 1, 0F);
        body.addChild(core);

        leftArm = new ModelRenderer(this, 0, 32);
        leftArm.setRotationPoint(4F, -5F, 0F);
        leftArm.addBox(-1F, -1F, -1.5F, 2, 13, 3, 0F);
        body.addChild(leftArm);

        rightArm = new ModelRenderer(this, 20, 32);
        rightArm.setRotationPoint(-4F, -5F, 0F);
        rightArm.addBox(-1F, -1F, -1.5F, 2, 11, 3, 0F);
        body.addChild(rightArm);

        leftLeg = new ModelRenderer(this, 40, 32);
        leftLeg.setRotationPoint(2F, 4F, 0F);
        leftLeg.addBox(-1.5F, 0F, -1.5F, 3, 12, 3, 0F);
        body.addChild(leftLeg);

        rightLeg = new ModelRenderer(this, 60, 32);
        rightLeg.setRotationPoint(-2F, 4F, 0F);
        rightLeg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3, 0F);
        body.addChild(rightLeg);

        leftHorn = new ModelRenderer(this, 84, 24);
        leftHorn.setRotationPoint(2F, -4F, 0F);
        leftHorn.addBox(-0.5F, -7F, -0.5F, 1, 8, 1, 0F);
        leftHorn.rotateAngleZ = -0.45F;
        head.addChild(leftHorn);

        rightHorn = new ModelRenderer(this, 100, 24);
        rightHorn.setRotationPoint(-2F, -4F, 0F);
        rightHorn.addBox(-0.5F, -5F, -0.5F, 1, 6, 1, 0F);
        rightHorn.rotateAngleZ = 0.35F;
        head.addChild(rightHorn);

        backSpike1 = new ModelRenderer(this, 84, 44);
        backSpike1.setRotationPoint(2F, -5F, 1.5F);
        backSpike1.addBox(-0.5F, -5F, -0.5F, 1, 6, 1, 0F);
        backSpike1.rotateAngleX = -0.4F;
        backSpike1.rotateAngleZ = -0.5F;
        body.addChild(backSpike1);

        backSpike2 = new ModelRenderer(this, 100, 44);
        backSpike2.setRotationPoint(-2F, -2F, 1.5F);
        backSpike2.addBox(-0.5F, -4F, -0.5F, 1, 5, 1, 0F);
        backSpike2.rotateAngleX = -0.5F;
        backSpike2.rotateAngleZ = 0.45F;
        body.addChild(backSpike2);

        tentacleLeft = new ModelRenderer(this, 0, 72);
        tentacleLeft.setRotationPoint(3F, 2F, 1F);
        tentacleLeft.addBox(-0.5F, 0F, -0.5F, 1, 10, 1, 0F);
        tentacleLeft.rotateAngleX = -0.35F;
        tentacleLeft.rotateAngleZ = -0.6F;
        body.addChild(tentacleLeft);

        tentacleRight = new ModelRenderer(this, 16, 72);
        tentacleRight.setRotationPoint(-3F, 1F, 1F);
        tentacleRight.addBox(-0.5F, 0F, -0.5F, 1, 9, 1, 0F);
        tentacleRight.rotateAngleX = -0.25F;
        tentacleRight.rotateAngleZ = 0.55F;
        body.addChild(tentacleRight);
    }

    @Override
    public void render(Entity entity, float limbSwing,
                       float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        setRotationAngles(limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale, entity);
        body.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing,
                                  float limbSwingAmount,
                                  float ageInTicks,
                                  float netHeadYaw,
                                  float headPitch,
                                  float scaleFactor,
                                  Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount,
                ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;

        leftArm.rotateAngleX =
                (float) Math.sin(ageInTicks * 0.08F) * 0.12F;
        rightArm.rotateAngleX =
                (float) Math.sin(ageInTicks * 0.08F + Math.PI) * 0.12F;

        tentacleLeft.rotateAngleZ =
                -0.6F + (float) Math.sin(ageInTicks * 0.10F) * 0.12F;
        tentacleRight.rotateAngleZ =
                0.55F + (float) Math.sin(ageInTicks * 0.10F + 1.5F) * 0.12F;

        core.rotateAngleX =
                (float) Math.sin(ageInTicks * 0.12F) * 0.08F;
    }
}
