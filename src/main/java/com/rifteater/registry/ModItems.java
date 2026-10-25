
package com.rifteater.registry;

import com.rifteater.RiftEater;
import com.rifteater.item.ItemDarkMatter;
import com.rifteater.item.ItemSummoningSigil;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod.EventBusSubscriber(modid = RiftEater.MODID)
@ObjectHolder(RiftEater.MODID)
public final class ModItems {

    public static final Item SUMMONING_SIGIL = null;
    public static final Item DARK_MATTER = null;

    private ModItems() {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Item sigil = new ItemSummoningSigil();
        sigil.setRegistryName(RiftEater.MODID, "summoning_sigil");
        sigil.setTranslationKey("rifteater.summoning_sigil");
        sigil.setCreativeTab(RiftEater.TAB);
        event.getRegistry().register(sigil);

        Item darkMatter = new ItemDarkMatter();
        darkMatter.setRegistryName(RiftEater.MODID, "dark_matter");
        darkMatter.setTranslationKey("rifteater.dark_matter");
        darkMatter.setCreativeTab(RiftEater.TAB);
        event.getRegistry().register(darkMatter);
    }
}
