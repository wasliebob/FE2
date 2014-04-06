package forestryextras.helpers;

import java.util.HashMap;

public class ModifierHelper {
	
	public static String tagCompound = "Customizable";
	public static String typeDura = "Durability";
	public static String typeProd = "ProductionModifier";
	public static String grafterTypeColor = "GrafterColor";
	
	public static String frame = "Frame";
	public static String grafter = "Grafter";
	public static String scoop = "Scoop";
	
	public static HashMap<Integer, String> map = new HashMap<Integer, String>();
	public static HashMap<Integer, String> ItemType = new HashMap<Integer, String>();
	public static HashMap<Integer, Integer> dura = new HashMap<Integer, Integer>();
	public static HashMap<Integer, Float> prod = new HashMap<Integer, Float>();

	public static void addModifier(Integer item, String itemType, int durability, String type, float product)
	{
		map.put(item, type);
		ItemType.put(item, itemType);
		dura.put(item, durability);
		prod.put(item, product);
	}
	
	public static String getType(Integer stack)
	{
		return map.get(stack);
	}
	
	
	public static boolean isModifier(Integer stack)
	{
		return map.containsKey(stack);
	}
}
