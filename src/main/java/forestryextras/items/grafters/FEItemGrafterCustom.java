package forestryextras.items.grafters;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.arboriculture.IToolGrafter;
import forestry.api.recipes.RecipeManagers;
import forestryextras.main.Main;
import forestryextras.main.init.Tabs;

public class FEItemGrafterCustom extends Item implements IToolGrafter{

	public FEItemGrafterCustom(int id, String itemName, int primaryColor, int secondaryColor, String oreDictName, int maxItemDamage, float saplingModifier, ItemStack mainMaterial, ItemStack handleMaterial, boolean isEasy, FluidStack recipeFluid, int creationTime) {
		super(id);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "grafter" + "." + itemName);
		setCreativeTab(Tabs.tabMain);
		setMaxDamage(maxItemDamage);
		setMaxStackSize(1);
		name = itemName;
		primColor = primaryColor;
		secColor = secondaryColor;
		oreDict = oreDictName;
		saplingMod = saplingModifier;
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
	float saplingMod;
	ItemStack mainMat;
	ItemStack handleMat;
	boolean easy;
	FluidStack recFluid;
	int createTime;
	Icon primary;
	Icon secondary;
	
	public void init()
	{
		initNBT();
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre(oreDict, this);
		recipe(easy);
	}
	
	public void initNBT()
	{
		ItemStack cItem = new ItemStack(this);
		NBTTagCompound tag = cItem.getTagCompound();
		if(tag == null)
		{
			tag = new NBTTagCompound("grafter");
			cItem.setTagCompound(tag);
			secColor = tag.getInteger("Color");
			//cItem.setItemName("Name" + tag.getInteger("Color"));
		}
		else
		{
			cItem.setTagCompound(tag);
			secColor = tag.getInteger("Color");
		}
	}
	
    public void recipe(boolean easy)
    {
    	if(easy == true){
    		GameRegistry.addShapedRecipe(new ItemStack(this, 1, getMaxDamage()), new Object[]{
    			"  X",
    			" I ",
    			"I  ",
    			'X', mainMat,
    			'I', handleMat});
    	}else{
    		RecipeManagers.carpenterManager.addRecipe(createTime, recFluid, null, new ItemStack(this, 1, getMaxDamage()), new Object[]{
    			"  X",
    			" I ",
    			"I  ",
    			'X', mainMat,
    			'I', handleMat});
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
        itemIcon = ir.registerIcon(Main.modName.toLowerCase() + ":" + "grafter");
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
		this.primary = ir.registerIcon(Main.modName.toLowerCase() + ":" + "grafter1");
		this.secondary = ir.registerIcon(Main.modName.toLowerCase() + ":" + "grafter2");
	}
	
	@Override
	public float getSaplingModifier(ItemStack arg0, World arg1,
			EntityPlayer arg2, int arg3, int arg4, int arg5) {
		return saplingMod;
	}
}