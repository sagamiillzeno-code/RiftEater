
package com.rifteater.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 3D-модель босса Разломный Пожиратель.
 * Minecraft Forge 1.12.2
 */
public class ModelRiftEater extends ModelBase {

    private final ModelRenderer body;
    private final ModelRenderer head;
    private final ModelRenderer core;
    private final ModelRenderer[] spikes = new ModelRenderer[6];
    private final ModelRenderer[][] tentacles =
            new ModelRenderer[8][3];

    public ModelRiftEater() {
        textureWidth = 128;
        textureHeight = 128;

        // Основное тело: вытянутое, с неровными каменными слоями.
        body = new ModelRenderer(this, 0, 0);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addBox(-5.0F, 0.0F, -3.5F,
                10, 10, 7, 0.0F);

        // Верхняя часть тела.
        head = new ModelRenderer(this, 36, 0);
        head.setRotationPoint(0.0F, 1.0F, -1.0F);
        head.addBox(-3.5F, -6.0F, -3.0F,
                7, 7, 6, 0.0F);

        // Кристаллическое ядро разлома.
        core = new ModelRenderer(this, 0, 30);
        core.setRotationPoint(0.0F, 5.0F, -4.0F);
        core.addBox(-2.5F, -3.0F, -1.0F,
                5, 6, 2, 0.0F);

        // Неодинаковые шипы на спине и плечах.
        for (int i = 0; i < spikes.length; i++) {
            spikes[i] = new ModelRenderer(this,
                    0 + (i % 3) * 12, 42 + (i / 3) * 12);

            float x = (i % 2 == 0) ? -3.0F : 3.0F;
            float z = (i < 2) ? 1.0F : 2.5F;
            float y = (i < 2) ? 1.0F : 4.0F;

            spikes[i].setRotationPoint(x, y, z);
            spikes[i].addBox(-1.0F, -5.0F, -1.0F,
                    2, 5, 2, 0.0F);

            spikes[i].rotateAngleZ =
                    (x < 0.0F) ? -0.25F : 0.25F;
            spikes[i].rotateAngleX =
                    (i % 2 == 0) ? -0.20F : 0.20F;
        }

        // Восемь щупалец, по три сегмента каждое.
        for (int i = 0; i < tentacles.length; i++) {
            boolean left = i < 4;
            int row = i % 4;

            float side = left ? -1.0F : 1.0F;
            float x = side * (4.0F + (row % 2) * 1.5F);
            float y = 3.0F + (row % 3);
            float z = -1.0F + row * 1.5F;

            for (int j = 0; j < 3; j++) {
                ModelRenderer segment = new ModelRenderer(
                        this, 48 + j * 12, 32 + (i % 4) * 8);

                segment.setRotationPoint(
                        j == 0 ? x : side * 2.0F,
                        j == 0 ? y : 3.0F,
                        j == 0 ? z : 0.0F);

                float width = j == 0 ? 3.0F
                        : (j == 1 ? 2.0F : 1.2F);

                segment.addBox(
                        -width / 2.0F,
                        0.0F,
                        -width / 2.0F,
                        (int) width + 1,
                        5 - j,
                        (int) width + 1,
                        0.0F);

                segment.rotateAngleZ =
                        side * (0.55F + row * 0.12F);
                segment.rotateAngleX =
                        -0.15F + row * 0.12F;

                tentacles[i][j] = segment;
            }
        }
    }

    @Override
    public void render(
            Entity entity,
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            float scale) {

        setRotationAngles(
                limbSwing,
                limbSwingAmount,
                ageInTicks,
                netHeadYaw,
                headPitch,
                scale,
                entity);

        body.render(scale);
        head.render(scale);
        core.render(scale);

        for (ModelRenderer spike : spikes) {
            spike.render(scale);
        }

        for (ModelRenderer[] tentacle : tentacles) {
            for (ModelRenderer segment : tentacle) {
                segment.render(scale);
            }
        }
    }

    @Override
    public void setRotationAngles(
            float limbSwing,
            float limbSwingAmount,
            float ageInTicks,
            float netHeadYaw,
            float headPitch,
            float scaleFactor,
            Entity entity) {

        super.setRotationAngles(
                limbSwing,
                limbSwingAmount,
                ageInTicks,
                netHeadYaw,
                headPitch,
                scaleFactor,
                entity);

        // Лёгкое покачивание головы и щупалец.
        head.rotateAngleY =
                netHeadYaw * 0.017453292F * 0.25F;
        head.rotateAngleX =
                headPitch * 0.017453292F * 0.25F;

        for (int i = 0; i < tentacles.length; i++) {
            float phase = ageInTicks * 0.08F + i * 0.9F;

            tentacles[i][0].rotateAngleX =
                    -0.15F + (float) Math.sin(phase) * 0.12F;

            tentacles[i][1].rotateAngleX =
                    0.10F + (float) Math.sin(phase + 0.8F) * 0.18F;

            tentacles[i][2].rotateAngleX =
                    0.15F + (float) Math.sin(phase + 1.6F) * 0.22F;
        }
    }
}
