
package com.rifteater.proxy;

import com.rifteater.client.RenderRiftEater;
import com.rifteater.entity.EntityRiftEater;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

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
}
