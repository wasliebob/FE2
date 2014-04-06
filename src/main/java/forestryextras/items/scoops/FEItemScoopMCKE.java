package forestryextras.items.scoops;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraftextras.api.energy.IToolMCKE;
import thaumcraftextras.api.functions.ChargerFunctions;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.core.IToolScoop;
import forestry.api.recipes.RecipeManagers;
import forestryextras.main.Main;
import forestryextras.main.init.Tabs;

public class FEItemScoopMCKE extends Item implements IToolScoop, IToolMCKE{

	public FEItemScoopMCKE(int id, String itemName, int primaryColor, int secondaryColor, String oreDictName, int maxItemDamage, ItemStack mainMaterial, ItemStack handleMaterial, boolean isEasy, FluidStack recipeFluid, int creationTime) {
		super(id);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "scoop" + "." + itemName);
		setCreativeTab(Tabs.tabMain);
		setMaxDamage(maxItemDamage);
		setMaxStackSize(1);
		name = itemName;
		primColor = primaryColor;
		secColor = secondaryColor;
		oreDict = oreDictName;
		mainMat = mainMaterial;
		handleMat = handleMaterial;
		easy = isEasy;
		recFluid = recipeFluid;
		createTime = creationTime;
		init();
	}
	String name;
	String oreDict;
	int primColor;
	int secColor;
	ItemStack mainMat;
	ItemStack handleMat;
	boolean easy;
	FluidStack recFluid;
	int createTime;
	Icon primary;
	Icon secondary;
	public void init()
	{
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre(oreDict, this);
        MinecraftForge.setToolClass(this, "scoop", 7);
		ChargerFunctions.addChargeAble(this);

		recipe(easy);
	}
	
    public void recipe(boolean easy)
    {
    	if(easy == true){
    		GameRegistry.addShapedRecipe(new ItemStack(this, 1, getMaxDamage()), new Object[]{
    			"XIX",
    			"XXX",
    			" X ",
    			'X', handleMat,
    			'I', mainMat});
    	}else{
    		RecipeManagers.carpenterManager.addRecipe(createTime, recFluid, null, new ItemStack(this, 1, getMaxDamage()), new Object[]{
    			"XIX",
    			"XXX",
    			" X ",
    			'X', handleMat,
    			'I', mainMat});
    	}
    }
    
    /*
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id)
	{
		return color;
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
        itemIcon = ir.registerIcon(Main.modName.toLowerCase() + ":" + "scoop");
	}
	*/
    
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
			return secColor;
		}
			return primColor;
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
		this.primary = ir.registerIcon(Main.modName.toLowerCase() + ":" + "scoop1");
		this.secondary = ir.registerIcon(Main.modName.toLowerCase() + ":" + "scoop2");
	}

	@Override
	public int getAmountOfEnergy(ItemStack stack) {
		return getDamage(stack);
	}

	@Override
	public void getCostForMode(ItemStack stack, int cost) {
	}

	@Override
	public void setAmountOfEnergy(ItemStack stack, int amount) {
		stack.setItemDamage(amount);
	}

	@Override
	public void setCostForMode(ItemStack arg0, int arg1, int arg2) {
		
	}
}