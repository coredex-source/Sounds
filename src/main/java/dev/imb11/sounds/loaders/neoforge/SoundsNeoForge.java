//? if neoforge {
/*package dev.imb11.sounds.loaders.neoforge;

import dev.imb11.sounds.SoundsClient;
import dev.imb11.sounds.config.SoundsConfig;
import dev.imb11.sounds.dynamic.SoundsReloadListener;
import dev.imb11.sounds.gui.SoundsConfigScreen;
import dev.imb11.sounds.sound.CustomSounds;
import dev.imb11.sounds.sound.events.PotionEventHelper;
import dev.imb11.sounds.util.ConfigSetters;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod("sounds")
public class SoundsNeoForge {
    private static final PotionEventHelper potionEventHelper = new PotionEventHelper();

    public SoundsNeoForge(IEventBus bus) {
        SoundsClient.init();

        CustomSounds.REGISTRY.register(bus);
        ModLoadingContext.get().getActiveContainer().registerExtensionPoint(IConfigScreenFactory.class, (client, parent) -> new SoundsConfigScreen(parent));
    }

    @EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        private static void clientTickEvent(ClientTickEvent.Pre event) {
            potionEventHelper.listenForEffectChanges(Minecraft.getInstance().level);
        }

        //? if >=1.21.5 {
        @SubscribeEvent
        private static void setupClientEvent(FMLClientSetupEvent event) {
            SoundsConfig.loadAll();
            ConfigSetters.init();
        }
        //?}

        @SubscribeEvent
        //? if <=1.21.3 {
        /^private static void registerResourceReloadListeners(net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent event) {
            event.registerReloadListener(new SoundsReloadListener());
        }
        ^///?} else {
        private static void registerResourceReloadListeners(net.neoforged.neoforge.client.event.AddClientReloadListenersEvent event) {
            event.addListener(SoundsClient.id("reload"), new SoundsReloadListener());
        }
        //?}
    }
}
*///?}
