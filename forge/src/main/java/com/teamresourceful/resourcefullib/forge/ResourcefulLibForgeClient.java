package com.teamresourceful.resourcefullib.forge;

import com.teamresourceful.resourcefullib.client.highlights.HighlightHandler;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ResourcefulLibForgeClient {

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ResourcefulLibForgeClient::onClientReloadListeners);
    }

    public static void onClientReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(new HighlightHandler());
    }

}
