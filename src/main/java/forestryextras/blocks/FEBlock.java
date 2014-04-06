package forestryextras.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import forestryextras.main.Main;
import forestryextras.main.init.Tabs;

public class FEBlock extends Block {

	public FEBlock(int id, Material mat, String blockName, String textureName, boolean isIngotBlock, float lightValue, ItemStack craftMaterial) {
		super(id, mat);
		setCreativeTab(Tabs.tabMain);
		setLightValue(lightValue);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "block" + "." + blockName);
		setHardness(1.0F);
		
		name = blockName;
		isIBlock = isIngotBlock;
		lValue = lightValue;
		craftMat = craftMaterial;
		texture = textureName;
		
		init();
	}
	String name;
	String texture;
	float lValue;
	boolean isIBlock;
	ItemStack craftMat;
	
	public void init()
	{
		GameRegistry.registerBlock(this, name);
		
		if(isIBlock && craftMat != null)
			addRecipe();
	}
	
	public void addRecipe()
	{
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
		"XXX",
		"XXX",
		"XXX",
		'X', craftMat});
		
		GameRegistry.addShapelessRecipe(new ItemStack(craftMat.getItem().itemID, 9, 0), this);
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
        blockIcon = ir.registerIcon(Main.modName.toLowerCase() + ":" + texture);
	}	
}
