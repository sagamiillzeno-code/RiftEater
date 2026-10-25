
package com.rifteater;

import com.rifteater.proxy.CommonProxy;
import com.rifteater.registry.ModItems;
import com.rifteater.registry.ModEntities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RiftEater.MODID, name = RiftEater.NAME, version = RiftEater.VERSION,
     acceptedMinecraftVersions = "[1.12.2]")
public class RiftEater {
    public static final String MODID = "rifteater";
    public static final String NAME = "Rift Eater";
    public static final String VERSION = "1.0.0";

    public static final CreativeTabs TAB = new CreativeTabs("rifteater") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.SUMMONING_SIGIL);
        }
    };

    @SidedProxy(clientSide = "com.rifteater.proxy.ClientProxy",
                serverSide = "com.rifteater.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModEntities.init();
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
