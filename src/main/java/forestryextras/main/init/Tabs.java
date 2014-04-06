package forestryextras.main.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Tabs {

	public static void init()
	{
        LanguageRegistry.instance().addStringLocalization("itemGroup.tabForestryExtras", "en_US", "Forestry Extras 2");
	}
	
    public static CreativeTabs tabMain = new CreativeTabs("tabForestryExtras") {
        public ItemStack getIconItemStack() {
               return new ItemStack(Items.draconicIngot, 1, 0);}};  
               
}
