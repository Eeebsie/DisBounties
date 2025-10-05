package org.eeebsie.disbounties;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import java.sql.Connection;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(disbounties.MOD_ID)
public class disbounties {

       // Define mod id in a common place for everything to reference
       public static final String MOD_ID = "disbounties";
       // Directly reference a slf4j logger
       private static final Logger LOGGER = LogUtils.getLogger(); // Use this for  getting the logger instead of private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

       public disbounties() {
              System.out.println("⚙️ Initializing database...");
              Database.init();

              IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

              // Register the commonSetup method for modloading
              modEventBus.addListener(this::commonSetup);

              // Register ourselves for server and other game events we are interested in
              MinecraftForge.EVENT_BUS.register(this);

              // Register the item to a creative tab
              modEventBus.addListener(this::addCreative);

              // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
              ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

              LOGGER.info("🔹 Discord Quests Mod Loaded!");
       }

       private void commonSetup(final FMLCommonSetupEvent event) {

       }

       // Add the example block item to the building blocks tab
       private void addCreative(BuildCreativeModeTabContentsEvent event) {

       }

       // You can use SubscribeEvent and let the Event Bus discover methods to call
       @SubscribeEvent
       public void onServerStarting(ServerStartingEvent event) {

       }

       // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
       @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
       public static class ClientModEvents {

              @SubscribeEvent
              public static void onClientSetup(FMLClientSetupEvent event) {

              }
       }
}
