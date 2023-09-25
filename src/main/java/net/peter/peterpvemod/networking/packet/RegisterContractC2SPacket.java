package net.peter.peterpvemod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.peter.peterpvemod.contract.Contract;
import net.peter.peterpvemod.contract.PlayerContractProvider;

import java.util.function.Supplier;

public class RegisterContractC2SPacket {

    public RegisterContractC2SPacket(){

    }

    //If we wanna send additional data to the server then save inside buffer else use normal constructor.
    public RegisterContractC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        //Context just like GoLang its just what is currently happening it contains stuff like the player that sent packet etc.
        NetworkEvent.Context context = supplier.get();

        //only runs on server
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.sendSystemMessage(Component.literal("Hello test"));
//            player.getCapability(PlayerContractProvider.PLAYER_CONTRACTS).ifPresent(contractHandler -> {
//                //Check if contract is on server.
//                Contract contract = contractHandler.getContracts();
//                //If yes Message Contract
//                if(contract.getContractName().isEmpty()){
//                    contractHandler.addContract();
//                }
//                player.sendSystemMessage(Component.literal(contract.getContractString()));
//            });



        });
        return true;
    }

}
