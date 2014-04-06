package forestryextras.main.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.recipes.RecipeManagers;
import forestryextras.blocks.FEBlock;
import forestryextras.blocks.FEBlockProducer;
import forestryextras.main.Config;

public class Blocks {

	public static void init()
	{
		draconicBlock = new FEBlock(Config.draconicBlockId, Material.iron, "draconicBlock", "draconicblock", true, 1.0F, new ItemStack(Items.draconicIngot));
		reinforcedBlock = new FEBlock(Config.reinforcedBlockId, Material.iron, "reinforcedBlock", "reinforcedblock", true, 1.0F, new ItemStack(Items.reinforcedIngot));
		draconicOre = new FEBlock(Config.draconicOreId, Material.iron, "draconicOre", "draconicore", false, 0F, null);
		producer = new FEBlockProducer(Config.producerId, "Beeducer");
		
//		upgrader = new FEBlockUpgrader(Config.upgraderId, "Upgrader");
		GameRegistry.registerWorldGenerator(new WorldGen());
		
		smelting();
	}
	
	public static void smelting()
	{
		GameRegistry.addSmelting(draconicOre.blockID, new ItemStack(Items.draconicIngot), 0.0F);
	}
	
	public static void crafting()
	{
		RecipeManagers.carpenterManager.addRecipe(20, new FluidStack(FluidRegistry.LAVA, 20), null, new ItemStack(Items.reinforcedIngot), new Object[]{
			"IZ",
			"XY",
			'X', Item.diamond,
			'I', Item.ingotGold,
			'Y', Item.ingotIron,
			'Z', Block.obsidian});
	}
	
	public static FEBlock draconicBlock;
	public static FEBlock reinforcedBlock;
	public static FEBlock draconicOre;
	public static FEBlockProducer producer;

//	public static FEBlockUpgrader upgrader;
}
