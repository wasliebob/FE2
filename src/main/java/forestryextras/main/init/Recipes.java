package forestryextras.main.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import forestry.core.config.ForestryItem;
import forestryextras.helpers.util.FileHelper;

public class Recipes {
	
	public static void init()
	{
		initBlockRecipes();
		initItemRecipes();
		initMiscRecipes();
	}
	
	public static void initBlockRecipes()
	{
		initNormalBlockRecipes();
		initForestryBlockRecipes();
	}
	
	public static void initNormalBlockRecipes()
	{
		
	}
	
	public static void initForestryBlockRecipes()
	{
		
	}
	
	public static void initItemRecipes()
	{
		initNormalItemRecipes();
		initSpecialItemRecipes();
		initForestryItemRecipes();
	}
	
	public static void initNormalItemRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(Item.netherStar), new Object[]{
			"XXX",
			"XIX",
			"XXX",
			'X', Items.witheriaIngot,
			'I', Item.enderPearl});
		
		GameRegistry.addShapedRecipe(new ItemStack(Block.dragonEgg), new Object[]{
			"XXX",
			"XIX",
			"XXX",
			'X', Items.draconicIngot,
			'I', Item.netherStar});
	}
	
	public static void initForestryItemRecipes()
	{
		registerCarpenterRecipes(20, new FluidStack(FluidRegistry.LAVA, 1000), new ItemStack(Items.reinforcedIngot), new Object[]{
			"YYY",
			"XXX",
			"YYY",
			'Y', Block.obsidian,
			'X', Item.ingotIron});
		
		registerCarpenterRecipes(20, new FluidStack(FluidRegistry.LAVA, 1000), new ItemStack(Items.witheriaIngot), new Object[]{
			" X ",
			"ZYZ",
			" X ",
			'X', Item.blazeRod,
			'Y', Items.reinforcedIngot,
			'Z', new ItemStack(Block.skull, 1, 1)});
		
		registerCarpenterRecipes(20, new FluidStack(FluidRegistry.LAVA, 1000), new ItemStack(Items.draconicIngot), new Object[]{
			" X ",
			"ZYZ",
			" X ",
			'X', Item.enderPearl,
			'Y', Items.witheriaIngot,
			'Z', Item.eyeOfEnder});
		
//		registerSqueezerRecipes(10, new ItemStack(ForestryItem.beeDroneGE.item()), new FluidStack(FluidRegistry.getFluid("honey"), 100));
//		registerSqueezerRecipes(10, new ItemStack(ForestryItem.beePrincessGE.item()), new FluidStack(FluidRegistry.getFluid("honey"), 100));
//		registerSqueezerRecipes(10, new ItemStack(ForestryItem.beeQueenGE.item()), new FluidStack(FluidRegistry.getFluid("honey"), 100));

	}
	
	public static void initSpecialItemRecipes()
	{
//		GameRegistry.addShapelessRecipe(appendUpdateBook(), new ItemStack(Item.book), new ItemStack(Item.sugar));
	}
	
	public static ItemStack appendUpdateBook()
	{
		ItemStack book = new ItemStack(Item.writableBook);
		NBTTagList bookPages = new NBTTagList("pages");
		
		for(int i = 0; i < FileHelper.readChangelog().size(); i++)
			if(FileHelper.readChangelog().get(i) != null)
				bookPages.appendTag(new NBTTagString("1", FileHelper.readChangelog().get(i)));

		book.setTagInfo("pages", bookPages);
		book.setTagInfo("author", new NBTTagString("author", "Wasliebob"));
		book.setTagInfo("title", new NBTTagString("title", "Whats new in Forestry Extras 2"));
		book.itemID = Item.writtenBook.itemID;
		return book;
	}
	
	public static void initMiscRecipes()
	{
		
	}
	
	public static void registerSqueezerRecipes(int produceTime, ItemStack input, FluidStack output)
	{
		RecipeManagers.squeezerManager.addRecipe(produceTime, new ItemStack[]{input}, output);
	}
	
	public static void registerCarpenterRecipes(int produceTime, FluidStack fluid, ItemStack output, Object[] ingredients)
	{
		RecipeManagers.carpenterManager.addRecipe(produceTime, fluid, null, output, ingredients);
	}
}
