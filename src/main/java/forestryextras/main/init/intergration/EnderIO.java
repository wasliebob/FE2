package forestryextras.main.init.intergration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import crazypants.enderio.ModObject;
import crazypants.enderio.material.Alloy;

public class EnderIO {

	public static void init()
	{
		initOreDictRegisters();
	}
	
	public static void initOreDictRegisters()
	{
		/** In case they are using an older version then latest (as of 28-3-2014)*/
	    OreDictionary.registerOre("ingotConductiveIron", new ItemStack(ModObject.itemAlloy.actualId, 1, Alloy.CONDUCTIVE_IRON.ordinal()));
	    OreDictionary.registerOre("ingotElectricalSteel", new ItemStack(ModObject.itemAlloy.actualId, 1, Alloy.ELECTRICAL_STEEL.ordinal()));
	    OreDictionary.registerOre("ingotEnergeticAlloy", new ItemStack(ModObject.itemAlloy.actualId, 1, Alloy.ENERGETIC_ALLOY.ordinal()));
	    OreDictionary.registerOre("ingotVibrantAlloy", new ItemStack(ModObject.itemAlloy.actualId, 1, Alloy.PHASED_GOLD.ordinal()));
	    OreDictionary.registerOre("ingotPulsatingIron", new ItemStack(ModObject.itemAlloy.actualId, 1, Alloy.PHASED_IRON.ordinal()));

	}
}
