package net.peter.peterpvemod.block;

import com.mojang.blaze3d.shaders.Uniform;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.peter.peterpvemod.PeterPVEMod;
import net.peter.peterpvemod.item.ModCreativeModTab;
import net.peter.peterpvemod.item.ModItem;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PeterPVEMod.MOD_ID);

    //Then after making registerblock I can register the block and it will register itself and an item along side it also DropExperience block takes a range and drops experience between that
    public static final RegistryObject<Block> REINFORCED_CONCRETE = registerBlock("reinforced_concrete",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).explosionResistance(999).strength(90f).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModTab.PETER_PVE_MOD_TAB);

    public static final RegistryObject<Block> CAMO_BLOCK = registerBlock("camo", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(20f).noCollission()), ModCreativeModTab.PETER_PVE_MOD_TAB);

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    //Generic below says whatever is passed just needs to extend Block and then register the block.
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);

        return toReturn;
    }

    //Just registers the block as an item because minecraft requires blocks be items when they are picked up etc
    public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
