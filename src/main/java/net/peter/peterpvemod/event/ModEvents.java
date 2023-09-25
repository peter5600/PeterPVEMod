package net.peter.peterpvemod.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.peter.peterpvemod.PeterPVEMod;
import net.peter.peterpvemod.contract.PlayerContract;
import net.peter.peterpvemod.contract.PlayerContractProvider;

@Mod.EventBusSubscriber(modid = PeterPVEMod.MOD_ID)
public class ModEvents {

    //When a player capability is added if its a player then add the capability that I made
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player){
            if(!event.getObject().getCapability(PlayerContractProvider.PLAYER_CONTRACTS).isPresent()){
                event.addCapability(new ResourceLocation(PeterPVEMod.MOD_ID, "properties"), new PlayerContractProvider());
            }
        }
    }

    //When player dies capability is lost so get it from old store and move to new store
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()){
            event.getOriginal().getCapability(PlayerContractProvider.PLAYER_CONTRACTS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerContractProvider.PLAYER_CONTRACTS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    //When the capability is registered register then capability
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerContract.class);
    }

    //i don't need this but you could use this to run a tick on the server and update stuff when the server ticks
//    @SubscribeEvent
//    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
//        if(event.side == LogicalSide.SERVER){
//
//        }
//    }

}
