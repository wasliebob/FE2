package forestryextras.main.init.intergration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import forestryextras.main.init.Blocks;
import forestryextras.main.init.Items;



public class Thaumcraft {

	public static void init()
	{
		initOreClusters();
		initMaterialAspects();
	}
	
	public static void preInit()
	{
		initOreDictRegisters();
	}

	public static void initOreDictRegisters()
	{
		OreDictionary.registerOre("woodSilverwood", new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1));
		OreDictionary.registerOre("woodGreatwood", new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0));
		OreDictionary.registerOre("saplingGreatwood", new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0));
		OreDictionary.registerOre("saplingSilverwood", new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1));
	}
	
	public static void initOreClusters()
	{

	}
	
	public static void initMaterialAspects()
	{
		ThaumcraftApi.registerObjectTag(Items.draconicIngot.itemID, 0, new AspectList().add(Aspect.MAGIC, 1).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(Items.reinforcedIngot.itemID, 0, new AspectList().add(Aspect.ORDER, 1).add(Aspect.METAL, 1));
		ThaumcraftApi.registerObjectTag(Blocks.draconicBlock.blockID, 0, new AspectList().add(Aspect.MAGIC, 9).add(Aspect.METAL, 9));
		ThaumcraftApi.registerObjectTag(Blocks.reinforcedBlock.blockID, 0, new AspectList().add(Aspect.ORDER, 9).add(Aspect.METAL, 9));
	}
}
