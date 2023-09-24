package net.peter.peterpvemod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CheckContractC2SPacket {

    public CheckContractC2SPacket(){

    }

    //If we wanna send additional data to the server then save inside buffer else use normal constructor.
    public CheckContractC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        //Context just like GoLang its just what is currently happening it contains stuff like the player that sent packet etc.
        NetworkEvent.Context context = supplier.get();

        //only runs on server
        context.enqueueWork(() -> {

            //Check if contract is on server.
            //If No message please register for contract

            //If yes and completed drop reward

            //If yes and not completed re print contract


        });
        return true;
    }

}
