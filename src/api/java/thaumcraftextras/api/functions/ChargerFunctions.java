package thaumcraftextras.api.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ChargerFunctions {

	public static Map<Item, Integer> isFuel = new HashMap<Item, Integer>();

	public static ArrayList<Item> isChargeAble = new ArrayList<Item>();
	public static ArrayList<ItemStack> isChargeAbleStack = new ArrayList<ItemStack>();

	
	public static void addChargeAble(Item item)
	{
		isChargeAbleStack.add(new ItemStack(item));
		isChargeAble.add(item);
	}
	
	public static void removeChargeAble(Item item)
	{
		isChargeAbleStack.remove(new ItemStack(item));
		isChargeAble.remove(item);
	}

	public static void addFuel(Item item, int energy)
	{
		isFuel.put(item, energy);
	}
	
	public static void removeFuel(Item item)
	{
		isFuel.remove(item);
	}
	
	public static Set<Item> getChargeAble()
	{
		return isFuel.keySet();
	}
}
