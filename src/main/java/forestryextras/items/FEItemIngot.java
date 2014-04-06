package forestryextras.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestryextras.helpers.util.FileHelper;
import forestryextras.main.Main;
import forestryextras.main.init.Tabs;

public class FEItemIngot extends Item{

	public FEItemIngot(int id, String itemName, int itemColor, String oreDictName) {
		super(id);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "item" + "." + itemName);
		setCreativeTab(Tabs.tabMain);
		name = itemName;
		color = itemColor;
		oreDict = oreDictName;
		init();
	}
	String name;
	String oreDict;
	int color;
	
	public void init()
	{
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre(oreDict, this);
		FileHelper.list.put(FileHelper.list.size(), this.getItemDisplayName(new ItemStack(this)));
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id)
	{
		return color;
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
        itemIcon = ir.registerIcon(Main.modName.toLowerCase() + ":" + "ingot");
	}
}
