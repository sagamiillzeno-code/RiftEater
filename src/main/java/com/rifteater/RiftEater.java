
package com.rifteater;

import com.rifteater.client.RiftEaterParticles;
import com.rifteater.proxy.CommonProxy;
import com.rifteater.registry.ModEntities;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = RiftEater.MODID,
    name = RiftEater.NAME,
    version = RiftEater.VERSION,
    acceptedMinecraftVersions = "[1.12.2]"
)
public class RiftEater {

    public static final String MODID = "rifteater";
    public static final String NAME = "Rift Eater";
    public static final String VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static RiftEater instance;

    public static final CreativeTabs TAB = new CreativeTabs("rifteater") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.ENDER_EYE);
        }
    };

    @SidedProxy(
        clientSide = "com.rifteater.proxy.ClientProxy",
        serverSide = "com.rifteater.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModEntities.init();
        proxy.preInit();
        MinecraftForge.EVENT_BUS.register(new RiftEaterParticles());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }
}
