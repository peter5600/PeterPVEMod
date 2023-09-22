package net.peter.peterpvemod.item.Custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RadioItem extends Item {
    public RadioItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        //Make sure this happens on the server side not client side
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            outputMessage(player);
            player.getCooldowns().addCooldown(this, 60);//3 second cool down
        }

        return super.use(level, player, hand);
    }

    private void outputMessage(Player player){
        player.sendSystemMessage(Component.literal("A contract was made to do x to y for " + getRandomNumber() + " z's"));
    }

    //1 - 10
    private int getRandomNumber(){
        return RandomSource.createNewThreadLocalInstance().nextInt(10) + 1;
    }
}
