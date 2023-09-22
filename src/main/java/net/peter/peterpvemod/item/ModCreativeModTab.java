package net.peter.peterpvemod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTab {
    //Make tab give name and return the item that i want to be the icon for the tab
    //To make another just duplicate this the name passed here is the ID not the display name
    public static final CreativeModeTab PETER_PVE_MOD_TAB = new CreativeModeTab("peterpvemodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.RADIO.get());
        }
    };
}
