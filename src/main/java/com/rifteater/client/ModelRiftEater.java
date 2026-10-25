
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
    private final ModelRenderer backSpikes;
    private final ModelRenderer leftTentacle;
    private final ModelRenderer rightTentacle;

    public ModelRiftEater() {
        textureWidth = 128;
        textureHeight = 128;

        // Основной корпус: вытянутый, суженный силуэт.
        body = new ModelRenderer(this, 32, 0);
        body.setRotationPoint(0F, 1F, 0F);
        body.addBox(-4F, -8F, -2.5F, 8, 12, 5, 0F);

        // Голова немного утоплена в плечи.
        head = new ModelRenderer(this, 0, 0);
        head.setRotationPoint(0F, -7F, -0.3F);
        head.addBox(-2.5F, -4.5F, -2.5F, 5, 5, 5, 0F);
        body.addChild(head);

        // Энергетическое ядро на груди.
        core = new ModelRenderer(this, 72, 0);
        core.setRotationPoint(0F, -1.5F, -2.55F);
        core.addBox(-1.5F, -2F, -0.4F, 3, 4, 1, 0F);
        body.addChild(core);

        // Наклонённые массивные наплечники.
        leftShoulder = new ModelRenderer(this, 84, 44);
        leftShoulder.setRotationPoint(3.5F, -6F, 0F);
        leftShoulder.addBox(-1F, -2F, -2F, 4, 5, 4, 0F);
        leftShoulder.rotateAngleZ = -0.25F;
        body.addChild(leftShoulder);

        rightShoulder = new ModelRenderer(this, 100, 44);
        rightShoulder.setRotationPoint(-3.5F, -6F, 0F);
        rightShoulder.addBox(-3F, -2F, -2F, 4, 5, 4, 0F);
        rightShoulder.rotateAngleZ = 0.25F;
        body.addChild(rightShoulder);

        // Длинные руки.
        leftArm = new ModelRenderer(this, 0, 32);
        leftArm.setRotationPoint(4F, -5F, 0F);
        leftArm.addBox(-1F, -1F, -1.5F, 2, 11, 3, 0F);
        body.addChild(leftArm);

        rightArm = new ModelRenderer(this, 20, 32);
        rightArm.setRotationPoint(-4F, -5F, 0F);
        rightArm.addBox(-1F, -1F, -1.5F, 2, 11, 3, 0F);
        body.addChild(rightArm);

        // Когти на концах рук.
        leftClaw = new ModelRenderer(this, 84, 24);
        leftClaw.setRotationPoint(0F, 9F, 0F);
        leftClaw.addBox(-0.5F, 0F, -1F, 1, 4, 1, 0F);
        leftClaw.rotateAngleX = -0.25F;
        leftArm.addChild(leftClaw);

        rightClaw = new ModelRenderer(this, 100, 24);
        rightClaw.setRotationPoint(0F, 9F, 0F);
        rightClaw.addBox(-0.5F, 0F, 0F, 1, 4, 1, 0F);
        rightClaw.rotateAngleX = 0.25F;
        rightArm.addChild(rightClaw);

        // Ноги разной длины для асимметричного силуэта.
        leftLeg = new ModelRenderer(this, 40, 32);
        leftLeg.setRotationPoint(2F, 3F, 0F);
        leftLeg.addBox(-1.5F, 0F, -1.5F, 3, 10, 3, 0F);
        body.addChild(leftLeg);

        rightLeg = new ModelRenderer(this, 60, 32);
        rightLeg.setRotationPoint(-2F, 3F, 0F);
        rightLeg.addBox(-1.5F, 0F, -1.5F, 3, 11, 3, 0F);
        body.addChild(rightLeg);

        // Ступни с выступающими носками.
        leftFoot = new ModelRenderer(this, 0, 72);
        leftFoot.setRotationPoint(0F, 9F, -0.2F);
        leftFoot.addBox(-1.5F, 0F, -2F, 3, 2, 4, 0F);
        leftLeg.addChild(leftFoot);

        rightFoot = new ModelRenderer(this, 16, 72);
        rightFoot.setRotationPoint(0F, 10F, -0.2F);
        rightFoot.addBox(-1.5F, 0F, -2F, 3, 2, 4, 0F);
        rightLeg.addChild(rightFoot);

        // Два длинных рога.
        leftHorn = new ModelRenderer(this, 84, 24);
        leftHorn.setRotationPoint(1.5F, -3.5F, 0F);
        leftHorn.addBox(-0.5F, -7F, -0.5F, 1, 8, 1, 0F);
        leftHorn.rotateAngleZ = -0.5F;
        head.addChild(leftHorn);

        rightHorn = new ModelRenderer(this, 100, 24);
        rightHorn.setRotationPoint(-1.5F, -3.5F, 0F);
        rightHorn.addBox(-0.5F, -6F, -0.5F, 1, 7, 1, 0F);
        rightHorn.rotateAngleZ = 0.4F;
        head.addChild(rightHorn);

        // Несколько выступающих шипов на спине.
        backSpikes = new ModelRenderer(this, 84, 44);
        backSpikes.setRotationPoint(0F, -5F, 1.5F);
        backSpikes.addBox(-2F, -5F, 0F, 1, 6, 1, 0F);
        backSpikes.addBox(1F, -6F, 0F, 1, 7, 1, 0F);
        backSpikes.addBox(-0.5F, -3F, 0F, 1, 4, 1, 0F);
        backSpikes.rotateAngleX = -0.25F;
        body.addChild(backSpikes);

        // Длинные изогнутые отростки позади корпуса.
        leftTentacle = new ModelRenderer(this, 0, 72);
        leftTentacle.setRotationPoint(3F, 1F, 1.5F);
        leftTentacle.addBox(-0.5F, 0F, 0F, 1, 5, 1, 0F);
        leftTentacle.addBox(0.5F, 4F, 0F, 1, 5, 1, 0F);
        leftTentacle.addBox(1.5F, 8F, 0F, 1, 4, 1, 0F);
        leftTentacle.rotateAngleZ = -0.35F;
        body.addChild(leftTentacle);

        rightTentacle = new ModelRenderer(this, 16, 72);
        rightTentacle.setRotationPoint(-3F, 1F, 1.5F);
        rightTentacle.addBox(-0.5F, 0F, 0F, 1, 5, 1, 0F);
        rightTentacle.addBox(-1.5F, 4F, 0F, 1, 5, 1, 0F);
        rightTentacle.addBox(-2.5F, 8F, 0F, 1, 4, 1, 0F);
        rightTentacle.rotateAngleZ = 0.35F;
        body.addChild(rightTentacle);
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

        leftShoulder.rotateAngleZ =
                -0.25F + (float) Math.sin(ageInTicks * 0.06F) * 0.04F;
        rightShoulder.rotateAngleZ =
                0.25F - (float) Math.sin(ageInTicks * 0.06F) * 0.04F;

        leftTentacle.rotateAngleZ =
                -0.35F + (float) Math.sin(ageInTicks * 0.10F) * 0.12F;
        rightTentacle.rotateAngleZ =
                0.35F + (float) Math.sin(ageInTicks * 0.10F + 1.5F) * 0.12F;

        core.rotateAngleX =
                (float) Math.sin(ageInTicks * 0.12F) * 0.08F;
    }
}
