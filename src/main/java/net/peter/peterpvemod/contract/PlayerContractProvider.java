package net.peter.peterpvemod.contract;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerContractProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerContract> PLAYER_CONTRACTS = CapabilityManager.get(new CapabilityToken<PlayerContract>() {});

    private PlayerContract contract = null;

    private final LazyOptional<PlayerContract> optional = LazyOptional.of(this::createPlayerContract);

    private PlayerContract createPlayerContract() {
        if(this.contract == null){
            this.contract = new PlayerContract();
        }
        return this.contract;
    }

    //if exists return else return empty version of class
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == PLAYER_CONTRACTS){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerContract().convertContractToBytesAndSaveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerContract().loadNBTBytesIntoContract(nbt);
    }
}
