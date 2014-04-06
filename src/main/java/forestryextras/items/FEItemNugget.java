package forestryextras.items;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestryextras.helpers.NuggetHelper;
import forestryextras.main.Main;
import forestryextras.main.init.Tabs;

public class FEItemNugget extends Item{
	public FEItemNugget(int id) {
		super(id);
		setCreativeTab(Tabs.tabMain);
        setHasSubtypes(true);
        setUnlocalizedName(Main.alias.toLowerCase() + "." + "nugget");
		init();
	}
	
	//@Override
	//public String getItemDisplayName(ItemStack stack) {
	//	return "item.fe.nugget." + NuggetHelper.name.get(stack.getItemDamage()).toLowerCase() ;
	//}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String unlocalizedStackName = NuggetHelper.name.get(stack.getItemDamage());
		if (unlocalizedStackName != null)
			return "item.fe.nugget." + unlocalizedStackName.toLowerCase() ;
		return getUnlocalizedName();
	}
	
	public void init()
	{
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getSubItems(int id, CreativeTabs creativeTab, List list) {
		for(int i = 0; i < NuggetHelper.nuggets.size(); i++) {
			int meta = NuggetHelper.nuggets.get(i);
			list.add(new ItemStack(id, 1, meta));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{		
		if(NuggetHelper.color.containsKey(stack.getItemDamage())){
			return NuggetHelper.color.get(stack.getItemDamage());
		}else{
			return Color.white.getRGB();
		}
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
        itemIcon = ir.registerIcon(Main.modName.toLowerCase() + ":" + "nugget");
	}
}