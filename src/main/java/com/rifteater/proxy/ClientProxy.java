
package com.rifteater.proxy;

import com.rifteater.client.RenderRiftEater;
import com.rifteater.entity.EntityRiftEater;
import com.rifteater.registry.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();

        RenderingRegistry.registerEntityRenderingHandler(
            EntityRiftEater.class,
            RenderRiftEater::new
        );
    }

    @Override
    public void init() {
        super.init();
    }

    @SubscribeEvent
    public void registerItemModels(ModelRegistryEvent event) {
        registerItemModel(ModItems.SUMMONING_SIGIL);
        registerItemModel(ModItems.DARK_MATTER);
    }

    private void registerItemModel(Item item) {
        if (item == null || item.getRegistryName() == null) {
            return;
        }

        ModelLoader.setCustomModelResourceLocation(
            item,
            0,
            new ModelResourceLocation(
                item.getRegistryName(),
                "inventory"
            )
        );
    }
}
