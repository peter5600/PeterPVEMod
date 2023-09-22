package net.peter.peterpvemod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.peter.peterpvemod.PeterPVEMod;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PeterPVEMod.MOD_ID);


    //item name is lowercase only no spaces
    //After I create an anon func that returns an item which takes properties which uses builder pattern to specify the properties.
    public static final RegistryObject<Item> RADIO = ITEMS.register("radio", () -> new Item(new Item.Properties().stacksTo(1).tab(ModCreativeModTab.PETER_PVE_MOD_TAB)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
