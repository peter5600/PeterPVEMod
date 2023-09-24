package net.peter.peterpvemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LauncPadBlock extends Block {
    public LauncPadBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        player.sendSystemMessage(Component.literal("Hello"));
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState state, Entity entity){
        if (!entity.isCrouching()) {
            //Copied code from minecraft jump logic on liveable entity
            float f = entity.getYRot() * ((float)Math.PI / 180F);
            entity.setDeltaMovement(entity.getDeltaMovement().add((double)(-Mth.sin(f) * 5F), 2.5D, (double)(Mth.cos(f) * 5F)));

        }
        entity.fallDistance = 0;
        super.stepOn(level, blockPos, state, entity);
    }

}
