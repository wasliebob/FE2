package forestryextras.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import cpw.mods.fml.common.registry.GameRegistry;
import forestryextras.main.init.Items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class NuggetHelper {
	public static void addNuggetToMap(int meta, String nuggetName, int nuggetColor, ItemStack outputItem)
	{
		nuggets.add(meta);
		name.put(meta, nuggetName);
		color.put(meta, nuggetColor);
		output.put(meta, outputItem);
	}
	
	public static void addRecipes()
	{
		addOreDictionary();
		for(int i = 0; i < nuggets.size(); i++)
		{
			int meta = nuggets.get(i);
			ItemStack outputStack = output.get(meta);
			
			if (outputStack != null) 
			{
				GameRegistry.addShapedRecipe(outputStack, new Object[]{
					"XXX",
					"XXX",
					"XXX",
					'X', new ItemStack(Items.nugget, 1, meta)});
				GameRegistry.addShapelessRecipe(new ItemStack(Items.nugget, 9, meta), outputStack);
			}
			
		}
			//RecipeManagers.centrifugeManager.addRecipe(10, new ItemStack(Bees.comb, 1, i), output.get(i));
	}
	
	public static void addOreDictionary()
	{
		for(int i = 0; i < nuggets.size(); i++)
		{
			int meta = nuggets.get(i);
			String nuggetName = name.get(meta);
			
			if (nuggetName != null)
			{
				OreDictionary.registerOre("nugget" + nuggetName, new ItemStack(Items.nugget, 1, meta));
			}
			
		}
			
	}
	
	public static ArrayList<Integer> nuggets = new ArrayList<Integer>();
	public static HashMap<Integer, String> name = new HashMap<Integer, String>();
	public static HashMap<Integer, Integer> color = new HashMap<Integer, Integer>();
	public static HashMap<Integer, ItemStack> output = new HashMap<Integer, ItemStack>();

}