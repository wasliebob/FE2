package forestryextras.items.frames;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IHiveFrame;
import forestry.api.recipes.RecipeManagers;
import forestryextras.helpers.ModifierHelper;
import forestryextras.main.Main;

public final class FEItemFrameCustom extends Item implements IHiveFrame {

    public FEItemFrameCustom(int id, int durability, boolean isHelish, boolean isSimulated, boolean isSelfLighted, boolean isSealed,
    		float frameDecay, float floweringMod, float productionMod, float lifespanMod,
    		float mutationMod, float territoryMod, String itemName, String oreDictName, String textureName, int frameColor,
    		ItemStack bindingMaterial, ItemStack frameMaterial, boolean easyRecipe, FluidStack recipeFluid, int creationTime)
    {
        super(id);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "frame" + "." + itemName);
		setMaxStackSize(1);
        
		stack = new ItemStack(this);

		initNBT();
		
		//uses = tag.getCompoundTag(ModifierHelper.tagCompound).getInteger(ModifierHelper.typeDura);
		//productionmodifier = tag.getCompoundTag(ModifierHelper.tagCompound).getFloat(ModifierHelper.typeProd);

		//if(tag != null && tag.getInteger(ModifierHelper.typeDura) != 0)
			//uses = tag.getInteger(ModifierHelper.typeProd);
		//else
			//uses = durability;
		
		uses = durability;
		productionmodifier = productionMod;
		helish = isHelish;
        simulated = isSimulated;
        selflighted = isSelfLighted;
        sealed = isSealed;
        decay = frameDecay;
        flowering = floweringMod;
		productionmodifier = productionMod;
        lifespanmodifier = lifespanMod;
        mutationmodifier = mutationMod;
        territorymodifier = territoryMod;   
       
        setMaxDamage(uses);


        name = itemName;
        oreDict = oreDictName;
        texture = textureName;
        color = frameColor;
        
        frameMat = frameMaterial;
        bindingMat = bindingMaterial;
        easyRec = easyRecipe;
        recFluid = recipeFluid;
        createTime = creationTime;
        init();
    }
    boolean helish;
    String HELISH = "Helish";
    boolean simulated;
    String SIMULATED = "Simulated";
    boolean selflighted;
    String SELFLIGHTED = "Selflighted";
    boolean sealed;
    String SEALED = "Sealed";
    float decay;
    String DECAY = "Decay";
    float flowering;
    String FLOWERING = "Flowering";
    float productionmodifier;
    String PRODMOD = ModifierHelper.typeProd;
    float lifespanmodifier;
    String LIFEMOD = "LifespanModifier";
    float mutationmodifier;
    String MUTMOD = "MutationModifier";
    float territorymodifier;
    String TERMOD = "TerritoryModifier";
	String name;
	String oreDict;
	int color;
    String texture;
    ItemStack bindingMat;
    ItemStack frameMat;
    boolean easyRec;
    FluidStack recFluid;
    int createTime;
    int uses;
    String USES = "USES";
    ItemStack stack;
    
	public void initNBT()
	{
		ItemStack stack = new ItemStack(this);
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound(ModifierHelper.tagCompound));
		}
		stack.getTagCompound().setTag(ModifierHelper.tagCompound, new NBTTagCompound());
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setInteger(USES, 0);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setBoolean(HELISH, false);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setBoolean(SIMULATED, false);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setBoolean(SELFLIGHTED, false);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setBoolean(SEALED, false);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setFloat(DECAY, 1.0F);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setFloat(FLOWERING, 1.0F);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setFloat(PRODMOD, 1.0F);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setFloat(LIFEMOD, 1.0F);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setFloat(MUTMOD, 1.0F);
		stack.getTagCompound().getCompoundTag(ModifierHelper.tagCompound).setFloat(TERMOD, 1.0F);
		//NBTTagCompound compound = new NBTTagCompound(ModifierHelper.tagCompound);
		
		/*
		tag.setInteger(USES, uses);
		tag.setBoolean(HELISH, helish);
		tag.setBoolean(SIMULATED, simulated);
		tag.setBoolean(SELFLIGHTED, selflighted);
		tag.setBoolean(SEALED, sealed);
		tag.setFloat(DECAY, decay);
		tag.setFloat(FLOWERING, flowering);
		tag.setFloat(PRODMOD, productionmodifier);
		tag.setFloat(LIFEMOD, lifespanmodifier);
		tag.setFloat(MUTMOD, mutationmodifier);
		tag.setFloat(TERMOD, territorymodifier);
		*/
		/*
		compound.setTag(ModifierHelper.tagCompound, new NBTTagCompound());
		compound.getCompoundTag(ModifierHelper.tagCompound).setInteger(USES, 0);
		compound.getCompoundTag(ModifierHelper.tagCompound).setBoolean(HELISH, false);
		compound.getCompoundTag(ModifierHelper.tagCompound).setBoolean(SIMULATED, false);
		compound.getCompoundTag(ModifierHelper.tagCompound).setBoolean(SELFLIGHTED, false);
		compound.getCompoundTag(ModifierHelper.tagCompound).setBoolean(SEALED, false);
		compound.getCompoundTag(ModifierHelper.tagCompound).setFloat(DECAY, 1.0F);
		compound.getCompoundTag(ModifierHelper.tagCompound).setFloat(FLOWERING, 1.0F);
		compound.getCompoundTag(ModifierHelper.tagCompound).setFloat(PRODMOD, 1.0F);
		compound.getCompoundTag(ModifierHelper.tagCompound).setFloat(LIFEMOD, 1.0F);
		compound.getCompoundTag(ModifierHelper.tagCompound).setFloat(MUTMOD, 1.0F);
		compound.getCompoundTag(ModifierHelper.tagCompound).setFloat(TERMOD, 1.0F);
		
		uses = compound.getCompoundTag(ModifierHelper.tagCompound).getInteger(USES);
		*/
	}
    public void init()
    {
    	recipe(easyRec);
		
		GameRegistry.registerItem(this, this.getUnlocalizedName());
		OreDictionary.registerOre(oreDict, this); 
	}
    
    public void recipe(boolean easy)
    {
    	if(easy == true){
    		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
    			"XXX",
    			"XIX",
    			"XXX",
    			'X', frameMat,
    			'I', bindingMat});
    	}else{
    		RecipeManagers.carpenterManager.addRecipe(createTime, recFluid, null, new ItemStack(this), new Object[]{
    			"XXX",
    			"XIX",
    			"XXX",
    			'X', frameMat,
    			'I', bindingMat});
    	}
    }
    
	@Override
	public float getTerritoryModifier(IBeeGenome genome, float currentModifier) {
		return territorymodifier;
	}

	@Override
	public float getMutationModifier(IBeeGenome genome, IBeeGenome mate,
			float currentModifier) {
		return mutationmodifier;
	}

	@Override
	public float getLifespanModifier(IBeeGenome genome, IBeeGenome mate,
			float currentModifier) {
		return lifespanmodifier;
	}

	@Override
	public float getProductionModifier(IBeeGenome genome, float currentModifier) {
			return productionmodifier;
	}

	@Override
	public float getFloweringModifier(IBeeGenome genome, float currentModifier) {
		return flowering;
	}

	@Override
	public float getGeneticDecay(IBeeGenome genome, float currentModifier) {
		return decay;
	}

	@Override
	public boolean isSealed() {
		return sealed;
	}

	@Override
	public boolean isSelfLighted() {
		return selflighted;
	}

	@Override
	public boolean isSunlightSimulated() {
		return simulated;
	}

	@Override
	public boolean isHellish() {
		return helish;
	}

	@Override
	public ItemStack frameUsed(IBeeHousing housing, ItemStack frame,
			IBee queen, int wear) {
		 frame.setItemDamage(frame.getItemDamage() + wear);
	        if(frame.getItemDamage() >= frame.getMaxDamage())
	            return null;
	        else
	            return frame;
	}
	
	@Override
    public void registerIcons(IconRegister ir) 
	{
        itemIcon = ir.registerIcon(Main.modName.toLowerCase() + ":" + texture);
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int id)
	{
		return color;
	}
	
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean id)
	{	
		list.add(EnumChatFormatting.RED + "Durability: " + EnumChatFormatting.GRAY + uses);
		list.add(EnumChatFormatting.RED + "isHelish: " + EnumChatFormatting.GRAY + helish);
		list.add(EnumChatFormatting.RED + "isSimulated: " + EnumChatFormatting.GRAY + simulated);
		list.add(EnumChatFormatting.RED + "isSelflighted: " + EnumChatFormatting.GRAY +  selflighted);
		list.add(EnumChatFormatting.RED + "isSealed: " + EnumChatFormatting.GRAY + sealed);
		list.add(EnumChatFormatting.RED + "Decay: " + EnumChatFormatting.GRAY +  decay * 100 + "%");
		list.add(EnumChatFormatting.RED + "Flowering: " + EnumChatFormatting.GRAY + flowering*100 + "%");
		list.add(EnumChatFormatting.RED + "Production Modifier: " + EnumChatFormatting.GRAY +  productionmodifier*100 + "%");
		list.add(EnumChatFormatting.RED + "Lifespan Modifier: " + EnumChatFormatting.GRAY +  lifespanmodifier*100 + "%");
		list.add(EnumChatFormatting.RED + "Mutation Modifier: " + EnumChatFormatting.GRAY + mutationmodifier*100 + "%");
		list.add(EnumChatFormatting.RED + "Territory Modifier: " + EnumChatFormatting.GRAY + territorymodifier*100 + "%");
	}
	
	@Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
	{
		initNBT();
	}

}