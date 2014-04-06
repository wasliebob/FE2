package forestryextras.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestryextras.helpers.CombHelper;
import forestryextras.main.Main;
import forestryextras.main.init.Tabs;

public class FEItemComb extends Item{
	public FEItemComb(int id) {
		super(id);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "comb");
		setCreativeTab(Tabs.tabMain);
        setHasSubtypes(true);

		init();
	}
	Icon primary;
	Icon secondary;
	
	//@Override
	//public String getItemDisplayName(ItemStack stack) {
	//	return "item.fe.comb." + CombHelper.name.get(stack.getItemDamage()).toLowerCase() ;
	//}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String unlocalizedStackName = CombHelper.name.get(stack.getItemDamage());
		if (unlocalizedStackName != null)
			return "item.fe.comb." + unlocalizedStackName.toLowerCase() ;
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
		for(int i = 0; i < CombHelper.combs.size(); i++) {
			int meta = CombHelper.combs.get(i);
			list.add(new ItemStack(id, 1, meta));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamageForRenderPass(int meta, int renderPass) {
		if(renderPass > 0) {
			return this.primary;
		}
		return this.secondary;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getRenderPasses(int meta) {
		return 2;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int pass)
	{		
		if(pass > 0) 
		{
			return CombHelper.secondaryColor.get(stack.getItemDamage());
		}
			return CombHelper.primaryColor.get(stack.getItemDamage());
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
		this.primary = ir.registerIcon("forestry:beeCombs.0");
		this.secondary = ir.registerIcon("forestry:beeCombs.1");
	}
}