package net.peter.peterpvemod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.peter.peterpvemod.PeterPVEMod;
import net.peter.peterpvemod.networking.packet.RegisterContractC2SPacket;

public class ModMessages {
    //Channel to communicate between client and server
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    //Each packet needs id, this makes sure they are unique.
    private static int id(){
        return packetId++;
    }

    public static void register(){
        //Create channel and lazy load static variable into INSTANCE variable
        SimpleChannel net = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(PeterPVEMod.MOD_ID, "messages"))
                .networkProtocolVersion((() -> "1.0")).clientAcceptedVersions(s -> true).serverAcceptedVersions(s -> true).simpleChannel();
        INSTANCE = net;

        //This long line below will register the packet and adds it to our networking system so that it can be used
        net.messageBuilder(RegisterContractC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER).decoder(RegisterContractC2SPacket::new).encoder(RegisterContractC2SPacket::toBytes).consumerMainThread(RegisterContractC2SPacket::handle).add();
    }

    //send message to server with INSTANCE that is registered.
    //MSG is a generic so this means that anything can be passed
    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    //Finds player and sends them message using instance created above
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
