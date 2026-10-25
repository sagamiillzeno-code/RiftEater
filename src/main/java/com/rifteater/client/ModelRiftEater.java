
package com.rifteater.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRiftEater extends ModelBase {

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer core;
    private final ModelRenderer leftShoulder;
    private final ModelRenderer rightShoulder;
    private final ModelRenderer leftArm;
    private final ModelRenderer rightArm;
    private final ModelRenderer leftClaw;
    private final ModelRenderer rightClaw;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftFoot;
    private final ModelRenderer rightFoot;
    private final ModelRenderer leftHorn;
    private final ModelRenderer rightHorn;

    private final ModelRenderer spikeA;
    private final ModelRenderer spikeB;
    private final ModelRenderer spikeC;

    private final ModelRenderer tentacleL1;
    private final ModelRenderer tentacleL2;
    private final ModelRenderer tentacleL3;
    private final ModelRenderer tentacleR1;
    private final ModelRenderer tentacleR2;
    private final ModelRenderer tentacleR3;

    public ModelRiftEater() {
        textureWidth = 128;
        textureHeight = 128;

        body = new ModelRenderer(this, 0, 0);
        body.setRotationPoint(0F, 1F, 0F);
        body.addBox(-4F, -8F, -2.5F, 8, 12, 5);
        
        head = new ModelRenderer(this, 30, 0);
        head.setRotationPoint(0F, -7F, -0.3F);
        head.addBox(-2.5F, -4.5F, -2.5F, 5, 5, 5);
        body.addChild(head);

        core = new ModelRenderer(this, 54, 0);
        core.setRotationPoint(0F, -1.5F, -2.55F);
        core.addBox(-1.5F, -2F, -0.4F, 3, 4, 1);
        body.addChild(core);

        leftShoulder = new ModelRenderer(this, 64, 0);
        leftShoulder.setRotationPoint(3.5F, -6F, 0F);
        leftShoulder.addBox(-1F, -2F, -2F, 4, 5, 4);
        leftShoulder.rotateAngleZ = -0.25F;
        body.addChild(leftShoulder);

        rightShoulder = new ModelRenderer(this, 82, 0);
        rightShoulder.setRotationPoint(-3.5F, -6F, 0F);
        rightShoulder.addBox(-3F, -2F, -2F, 4, 5, 4);
        rightShoulder.rotateAngleZ = 0.25F;
        body.addChild(rightShoulder);

        leftArm = new ModelRenderer(this, 0, 22);
        leftArm.setRotationPoint(4F, -5F, 0F);
        leftArm.addBox(-1F, -1F, -1.5F, 2, 11, 3);
        body.addChild(leftArm);

        rightArm = new ModelRenderer(this, 12, 22);
        rightArm.setRotationPoint(-4F, -5F, 0F);
        rightArm.addBox(-1F, -1F, -1.5F, 2, 11, 3);
        body.addChild(rightArm);

        leftClaw = new ModelRenderer(this, 24, 22);
        leftClaw.setRotationPoint(0F, 9F, 0F);
        leftClaw.addBox(-0.5F, 0F, -1F, 1, 4, 1);
        leftArm.addChild(leftClaw);

        rightClaw = new ModelRenderer(this, 30, 22);
        rightClaw.setRotationPoint(0F, 9F, 0F);
        rightClaw.addBox(-0.5F, 0F, 0F, 1, 4, 1);
        rightArm.addChild(rightClaw);

        leftLeg = new ModelRenderer(this, 36, 22);
        leftLeg.setRotationPoint(2F, 3F, 0F);
        leftLeg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3);
        body.addChild(leftLeg);

        rightLeg = new ModelRenderer(this, 50, 22);
        rightLeg.setRotationPoint(-2F, 3F, 0F);
        rightLeg.addBox(-1.5F, 0F, -1.5F, 3, 11, 3);
        body.addChild(rightLeg);

        leftFoot = new ModelRenderer(this, 64, 22);
        leftFoot.setRotationPoint(0F, 9F, -0.2F);
        leftFoot.addBox(-1.5F, 0F, -2F, 3, 2, 4);
        leftLeg.addChild(leftFoot);

        rightFoot = new ModelRenderer(this, 80, 22);
        rightFoot.setRotationPoint(0F, 10F, -0.2F);
        rightFoot.addBox(-1.5F, 0F, -2F, 3, 2, 4);
        rightLeg.addChild(rightFoot);

        leftHorn = new ModelRenderer(this, 96, 22);
        leftHorn.setRotationPoint(1.5F, -3.5F, 0F);
        leftHorn.addBox(-0.5F, -7F, -0.5F, 1, 8, 1);
        leftHorn.rotateAngleZ = -0.5F;
        head.addChild(leftHorn);

        rightHorn = new ModelRenderer(this, 102, 22);
        rightHorn.setRotationPoint(-1.5F, -3.5F, 0F);
        rightHorn.addBox(-0.5F, -6F, -0.5F, 1, 7, 1);
        rightHorn.rotateAngleZ = 0.4F;
        head.addChild(rightHorn);

        spikeA = new ModelRenderer(this, 0, 43);
        spikeA.setRotationPoint(-2F, -5F, 1.5F);
        spikeA.addBox(-0.5F, -5F, 0F, 1, 6, 1);
        spikeA.rotateAngleZ = -0.3F;
        body.addChild(spikeA);

        spikeB = new ModelRenderer(this, 6, 43);
        spikeB.setRotationPoint(1F, -5F, 1.5F);
        spikeB.addBox(-0.5F, -6F, 0F, 1, 7, 1);
        spikeB.rotateAngleZ = 0.25F;
        body.addChild(spikeB);

        spikeC = new ModelRenderer(this, 12, 43);
        spikeC.setRotationPoint(0F, -3F, 1.5F);
        spikeC.addBox(-0.5F, -4F, 0F, 1, 4, 1);
        body.addChild(spikeC);

        tentacleL1 = new ModelRenderer(this, 20, 43);
        tentacleL1.setRotationPoint(3F, 1F, 1.5F);
        tentacleL1.addBox(-0.5F, 0F, 0F, 1, 5, 1);
        tentacleL1.rotateAngleZ = -0.25F;
        body.addChild(tentacleL1);

        tentacleL2 = new ModelRenderer(this, 26, 43);
        tentacleL2.setRotationPoint(0F, 4F, 0F);
        tentacleL2.addBox(0F, 0F, 0F, 1, 5, 1);
        tentacleL2.rotateAngleZ = -0.35F;
        tentacleL1.addChild(tentacleL2);

        tentacleL3 = new ModelRenderer(this, 32, 43);
        tentacleL3.setRotationPoint(0.5F, 4F, 0F);
        tentacleL3.addBox(0F, 0F, 0F, 1, 4, 1);
        tentacleL3.rotateAngleZ = -0.4F;
        tentacleL2.addChild(tentacleL3);

        tentacleR1 = new ModelRenderer(this, 38, 43);
        tentacleR1.setRotationPoint(-3F, 1F, 1.5F);
        tentacleR1.addBox(-0.5F, 0F, 0F, 1, 5, 1);
        tentacleR1.rotateAngleZ = 0.25F;
        body.addChild(tentacleR1);

        tentacleR2 = new ModelRenderer(this, 44, 43);
        tentacleR2.setRotationPoint(0F, 4F, 0F);
        tentacleR2.addBox(-1F, 0F, 0F, 1, 5, 1);
        tentacleR2.rotateAngleZ = 0.35F;
        tentacleR1.addChild(tentacleR2);

        tentacleR3 = new ModelRenderer(this, 50, 43);
        tentacleR3.setRotationPoint(-0.5F, 4F, 0F);
        tentacleR3.addBox(-1F, 0F, 0F, 1, 4, 1);
        tentacleR3.rotateAngleZ = 0.4F;
        tentacleR2.addChild(tentacleR3);
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

        core.rotateAngleX =
                (float) Math.sin(ageInTicks * 0.12F) * 0.08F;

        tentacleL1.rotateAngleZ =
                -0.25F + (float) Math.sin(ageInTicks * 0.10F) * 0.08F;
        tentacleR1.rotateAngleZ =
                0.25F + (float) Math.sin(ageInTicks * 0.10F + 1.5F) * 0.08F;
    }
}
