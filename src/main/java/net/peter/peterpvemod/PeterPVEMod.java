package net.peter.peterpvemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.peter.peterpvemod.block.ModBlocks;
import net.peter.peterpvemod.item.ModItem;
import net.peter.peterpvemod.networking.ModMessages;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PeterPVEMod.MOD_ID)
public class PeterPVEMod
{
    public static final String MOD_ID = "peterpvemod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public PeterPVEMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Call my class to register my items.
        ModItem.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            //Not sure why but this must be at the top of enqueue work
            ModMessages.register();
        });
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
