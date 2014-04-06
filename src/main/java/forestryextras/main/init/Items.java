package forestryextras.main.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import forestryextras.helpers.ModifierHelper;
import forestryextras.helpers.NuggetHelper;
import forestryextras.items.FEItemFrame;
import forestryextras.items.FEItemGrafter;
import forestryextras.items.FEItemIngot;
import forestryextras.items.FEItemNugget;
import forestryextras.items.FEItemScoop;
import forestryextras.items.FEItemStick;
import forestryextras.main.Config;

public class Items {

	public static void init()
	{
		initItems();
		initModifiers();
		initFrames();
		initScoops();
		initGrafters();
		initModSupportItems();
		initModSupportFrames();
		initWorldGen();
		initNuggets();
	}
	
	public static void initItems()
	{
		draconicIngot = new FEItemIngot(Config.draconicIngotId, "draconicIngot", 0xFF0000, "ingotDraconic");
		reinforcedIngot = new FEItemIngot(Config.reinforcedIngotId, "reinforcedIngot", 0x999999, "ingotReinforced");
		witheriaIngot = new FEItemIngot(Config.witheriaIngotId, "witheriaIngot", 0x333333, "ingotWitheria");
		alfiumIngot = new FEItemIngot(Config.alfiumIngotId, "alfiumIngot", 0x663366, "ingotAlfium");
		ryuIngot = new FEItemIngot(Config.ryuIngotId, "ryuIngot", 0x990000, "ingotRyu");

		coalStick = new FEItemStick(Config.coalStickId, "coalStick", 0x000000, "stickCoal", new ItemStack(Item.coal));
		diamondStick = new FEItemStick(Config.diamondStickId, "diamondStick", 0x99FFFF, "stickDiamond", new ItemStack(Item.diamond));
		draconicStick = new FEItemStick(Config.draconicStickId, "draconicStick", 0xFF0000, "stickDraconic", new ItemStack(Items.draconicIngot));
		emeraldStick = new FEItemStick(Config.emeraldStickId, "emeraldStick", 0x99FF33, "stickEmerald", new ItemStack(Item.emerald));
		goldStick = new FEItemStick(Config.goldStickId, "goldStick", 0xFFFF66, "stickGold", new ItemStack(Item.ingotGold));
		ironStick = new FEItemStick(Config.ironStickId, "ironStick", 0xFFFFCC, "stickIron", new ItemStack(Item.ingotIron));
		mutationStick = new FEItemStick(Config.mutationStickId, "mutationStick", 0x99CC00, "stickMutation", new ItemStack(Item.netherStar));
		obsidianStick = new FEItemStick(Config.obsidianStickId, "obsidianStick", 0x999966, "stickObsidian", new ItemStack(Block.obsidian));
		reinforcedStick = new FEItemStick(Config.reinforcedStickId, "reinforcedStick", 0xCCCC99, "stickReinforced", new ItemStack(Items.reinforcedIngot));
		alfiumStick = new FEItemStick(Config.alfiumStickId, "alfiumStick", 0x663366, "stickAlfium", new ItemStack(Items.alfiumIngot));
		witheriaStick = new FEItemStick(Config.witheriaStickId, "witheriaStick", 0x333333, "stickWitheria", new ItemStack(Items.witheriaIngot));

	}
	public static FEItemIngot draconicIngot;
	public static FEItemIngot reinforcedIngot;
	public static FEItemIngot alfiumIngot;
	public static FEItemIngot ryuIngot;
	public static FEItemIngot witheriaIngot;

	public static FEItemStick coalStick;
	public static FEItemStick diamondStick;
	public static FEItemStick draconicStick;
	public static FEItemStick emeraldStick;
	public static FEItemStick goldStick;
	public static FEItemStick ironStick;
	public static FEItemStick mutationStick;
	public static FEItemStick obsidianStick;
	public static FEItemStick reinforcedStick;
	public static FEItemStick alfiumStick;
	public static FEItemStick witheriaStick;

	public static void initFrames()
	{
		coalFrame = new FEItemFrame(Config.coalFrameId, 100, false, false, false, false, 1.0F, 1.0F, 1.1F, 1.0F, 1.0F, 1.0F, "frameCoal", "frameCoal", "frame", 0x000000, new ItemStack(Item.silk), new ItemStack(Items.coalStick), true, null, 0);
		ironFrame = new FEItemFrame(Config.ironFrameId, 200, false, false, false, false, 1.0F, 1.0F, 1.2F, 1.0F, 1.0F, 1.0F, "frameIron", "frameIron", "frame", 0xFFFFCC, new ItemStack(Item.silk), new ItemStack(Items.ironStick), false, new FluidStack(FluidRegistry.LAVA, 10), 10);
    	goldFrame = new FEItemFrame(Config.goldFrameId, 150, false, false, false, false, 1.0F, 1.0F, 1.3F, 1.0F, 1.0F, 1.0F, "frameGold", "frameGold", "frame", 0xFFFF66, new ItemStack(Item.silk), new ItemStack(Items.goldStick), false, new FluidStack(FluidRegistry.LAVA, 10), 10);
		diamondFrame = new FEItemFrame(Config.diamondFrameId, 250, false, false, false, false, 1.0F, 1.0F, 1.4F, 1.0F, 1.0F, 1.0F, "frameDiamond", "frameDiamond", "frame", 0xCCFFFF, new ItemStack(Item.silk), new ItemStack(Items.diamondStick), false, new FluidStack(FluidRegistry.LAVA, 10), 12);
		emeraldFrame = new FEItemFrame(Config.emeraldFrameId, 300, false, false, false, false, 1.0F, 1.0F, 1.5F, 1.0F, 1.0F, 1.0F, "frameEmerald", "frameEmerald", "frame", 0x99FF66, new ItemStack(Item.silk), new ItemStack(Items.emeraldStick), false, new FluidStack(FluidRegistry.LAVA, 10), 14);
		reinforcedFrame = new FEItemFrame(Config.reinforcedFrameId, 500, false, false, false, false, 1.0F, 1.0F, 1.4F, 1.0F, 1.0F, 1.0F, "frameReinforced", "frameReinforced", "frame", 0xCCCC99, new ItemStack(Item.silk), new ItemStack(Items.reinforcedStick), false, new FluidStack(FluidRegistry.LAVA, 15), 20);
		witheriaFrame = new FEItemFrame(Config.witheriaFrameId, 750, false, false, false, false, 1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 1.0F, "frameWitheria", "frameWitheria", "frame", 0x333333, new ItemStack(Item.blazePowder), new ItemStack(Items.witheriaStick), false, new FluidStack(FluidRegistry.LAVA, 15), 20);
		draconicFrame = new FEItemFrame(Config.draconicFrameId, 1000, false, false, false, false, 1.0F, 1.0F, 2.5F, 1.0F, 1.0F, 1.0F, "frameDraconic", "frameDraconic", "frame", 0xFF0000, new ItemStack(Item.silk), new ItemStack(Items.draconicStick), false, new FluidStack(FluidRegistry.LAVA, 100), 100);
		mutationFrame = new FEItemFrame(Config.mutationFrameId, 25, false, false, false, false, 1.0F, 1.0F, 1.0F, 1.0F, 10.0F, 1.0F, "frameMutation", "frameMutation", "frame", 0x99CC00, new ItemStack(Item.enderPearl), new ItemStack(Items.mutationStick), true, null, 0);
		alfiumFrame = new FEItemFrame(Config.alfiumFrameId, 750, false, false, false, false, 1.0F, 1.0F, 1.5F, 1.0F, 1.0F, 1.0F, "frameAlfium", "frameAlfium", "frame", 0x663366, new ItemStack(Item.silk), new ItemStack(Items.alfiumStick), false, new FluidStack(FluidRegistry.LAVA, 15), 20);
		//customFrame = new FEItemFrameCustom(Config.upgradeFrameId, 10, false, false, false, false, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, "frameCustom", "frameCustom", "frame", 0x663366, new ItemStack(Item.ingotGold), new ItemStack(Items.emeraldStick), true, null, 0);
	}
	public static FEItemFrame coalFrame;
	public static FEItemFrame ironFrame;
	public static FEItemFrame goldFrame;
	public static FEItemFrame diamondFrame;
	public static FEItemFrame emeraldFrame;
	public static FEItemFrame reinforcedFrame;
	public static FEItemFrame draconicFrame;
	public static FEItemFrame mutationFrame;
	public static FEItemFrame obsidianFrame;
	public static FEItemFrame alfiumFrame;
	public static FEItemFrame witheriaFrame;

	//public static FEItemFrameCustom customFrame;
	
	public static void initScoops()
	{
		reinforcedScoop = new FEItemScoop(Config.reinforcedScoopId, "scoopReinforced", 0xFFFFFF, 0x999999, "scoopReinforced", 300, new ItemStack(Items.reinforcedIngot), new ItemStack(Items.reinforcedStick), true, null, 0);
		draconicScoop = new FEItemScoop(Config.draconicScoopId, "scoopDraconic", 0xFFFFFF, 0xFF0000, "scoopDraconic", 700, new ItemStack(Items.draconicIngot), new ItemStack(Items.draconicStick), true, null, 0);

	}
	public static FEItemScoop reinforcedScoop;
	public static FEItemScoop draconicScoop;

	public static void initGrafters()
	{
		reinforcedGrafter = new FEItemGrafter(Config.reinforcedGrafterId, "grafterReinforced", 0x996633, 0x999999, "grafterReinforced", 300, 1.5F, new ItemStack(Items.reinforcedIngot), new ItemStack(Items.reinforcedStick), true, null, 0);
		draconicGrafter = new FEItemGrafter(Config.draconicGrafterId, "grafterDraconic", 0x996633, 0xFF0000, "grafterDraconic", 700, 2.0F, new ItemStack(Items.draconicIngot), new ItemStack(Items.draconicStick), true, null, 0);
//		customGrafter = new FEItemGrafterCustom(Config.customGrafterId, "grafterCustom", 0x996633, 0xFF0000, "grafterCustom", 700, 2.0F, new ItemStack(Items.witheriaIngot), new ItemStack(Items.witheriaStick), true, null, 0);
	}
	public static FEItemGrafter draconicGrafter;
	public static FEItemGrafter reinforcedGrafter;
//	public static FEItemGrafterCustom customGrafter;

	public static void initModSupportFrames()
	{
		if(OreDictionary.getOres("ingotAlumite").size() > 0)
			alumiteFrame = new FEItemFrame(Config.alumiteFrameId, 500, false, false, false, false, 1.0F, 1.0F, 1.5F, 1.0F, 1.0F, 1.0F, "frameAlumite", "frameAlumite", "frame", 0xFF33FF, new ItemStack(Item.silk), new ItemStack(Items.alumiteStick), true, null, 0);

		if(OreDictionary.getOres("ingotArdite").size() > 0)
			arditeFrame = new FEItemFrame(Config.arditeFrameId, 750, false, false, false, false, 1.0F, 1.0F, 1.6F, 1.0F, 1.0F, 1.0F, "frameArdite", "frameArdite", "frame", 0xCC6633, new ItemStack(Item.silk), new ItemStack(Items.arditeStick), true, null, 0);

		if(OreDictionary.getOres("ingotBronze").size() > 0)
			bronzeFrame = new FEItemFrame(Config.bronzeFrameId, 450, false, false, false, false, 1.0F, 1.0F, 1.2F, 1.0F, 1.0F, 1.0F, "frameBronze", "frameBronze", "frame", 0x996633, new ItemStack(Item.silk), new ItemStack(Items.bronzeStick), true, null, 0);

		if(OreDictionary.getOres("ingotCobalt").size() > 0)
			cobaltFrame = new FEItemFrame(Config.cobaltFrameId, 750, false, false, false, false, 1.0F, 1.0F, 1.7F, 1.0F, 1.0F, 1.0F, "frameCobalt", "frameCobalt", "frame", 0x0099CC, new ItemStack(Item.silk), new ItemStack(Items.cobaltStick), true, null, 0);

		if(OreDictionary.getOres("ingotCopper").size() > 0)
			copperFrame = new FEItemFrame(Config.copperFrameId, 225, false, false, false, false, 1.0F, 1.0F, 1.1F, 1.0F, 1.0F, 1.0F, "frameCopper", "frameCopper", "frame", 0xFFCC33, new ItemStack(Item.silk), new ItemStack(Items.copperStick), true, null, 0);

		if(OreDictionary.getOres("ingotLead").size() > 0)
			leadFrame = new FEItemFrame(Config.leadFrameId, 400, false, false, false, false, 1.0F, 1.0F, 1.2F, 1.0F, 1.0F, 1.0F, "frameLead", "frameLead", "frame", 0x9999CC, new ItemStack(Item.silk), new ItemStack(Items.leadStick), true, null, 0);
		
		if(OreDictionary.getOres("ingotManyullyn").size() > 0)
			manyullynFrame = new FEItemFrame(Config.manyullynFrameId, 1000, false, false, false, false, 1.0F, 1.0F, 2.0F, 1.0F, 1.0F, 1.0F, "frameManyullyn", "frameManyullyn", "frame", 0x9966FF, new ItemStack(Item.silk), new ItemStack(Items.manyullynStick), true, null, 0);

		if(OreDictionary.getOres("ingotSilver").size() > 0)
			silverFrame = new FEItemFrame(Config.silverFrameId, 400, false, false, false, false, 1.0F, 1.0F, 1.2F, 1.0F, 1.0F, 1.0F, "frameSilver", "frameSilver", "frame", 0xFFFFCC, new ItemStack(Item.silk), new ItemStack(Items.silverStick), true, null, 0);

		if(OreDictionary.getOres("ingotSteel").size() > 0)
			steelFrame = new FEItemFrame(Config.steelFrameId, 400, false, false, false, false, 1.0F, 1.0F, 1.2F, 1.0F, 1.0F, 1.0F, "frameSteel", "frameSteel", "frame", 0x999966, new ItemStack(Item.silk), new ItemStack(Items.steelStick), true, null, 0);
		
		if(OreDictionary.getOres("ingotTin").size() > 0)
			tinFrame = new FEItemFrame(Config.tinFrameId, 230, false, false, false, false, 1.0F, 1.0F, 1.1F, 1.0F, 1.0F, 1.0F, "frameTin", "frameTin", "frame", 0xFFFFCC, new ItemStack(Item.silk), new ItemStack(Items.tinStick), true, null, 0);
		
		if(OreDictionary.getOres("ingotPokefennium").size() > 0)
			pokefenniumFrame = new FEItemFrame(Config.pokefenniumFrameId, 800, false, false, false, false, 1.0F, 1.0F, 1.8F, 1.0F, 1.0F, 1.0F, "framePokefennium", "framePokefennium", "frame", 0x99CC99, new ItemStack(Item.silk), new ItemStack(Items.pokefenniumStick), true, null, 0);
		
		if(OreDictionary.getOres("ingotFairy").size() > 0)
			fairyFrame = new FEItemFrame(Config.fairyFrameId, 800, false, false, false, false, 1.0F, 1.0F, 1.7F, 1.0F, 1.0F, 1.0F, "frameFairy", "frameFairy", "frame", 0xFF99FF, new ItemStack(Item.silk), new ItemStack(Items.fairyStick), true, null, 0);
		
		if(OreDictionary.getOres("ingotThaumium").size() > 0)
			thaumiumFrame = new FEItemFrame(Config.thaumiumFrameId, 600, false, false, false, false, 1.0F, 1.0F, 1.4F, 1.0F, 1.0F, 1.0F, "frameThaumium", "frameThaumium", "frame", 0x9966FF, new ItemStack(Item.silk), new ItemStack(Items.thaumiumStick), true, null, 0);

		if(OreDictionary.getOres("ingotDarkThaumium").size() > 0)
			darkThaumiumFrame = new FEItemFrame(Config.darkThaumiumFrameId, 1000, false, false, false, false, 1.0F, 1.0F, 1.9F, 1.0F, 1.0F, 1.0F, "frameDarkThaumium", "frameDarkThaumium", "frame", 0x993399, new ItemStack(Item.silk), new ItemStack(Items.darkThaumiumStick), true, null, 0);
		
		if(OreDictionary.getOres("ingotVoid").size() > 0)
			voidFrame = new FEItemFrame(Config.voidFrameId, 1000, false, false, false, false, 1.0F, 1.0F, 1.9F, 1.0F, 1.0F, 1.0F, "frameVoid", "frameVoid", "frame", 0x9900CC, new ItemStack(Item.silk), new ItemStack(Items.voidStick), true, null, 0);
	}
	public static FEItemFrame alumiteFrame;
	public static FEItemFrame arditeFrame;
	public static FEItemFrame bronzeFrame;
	public static FEItemFrame cobaltFrame;
	public static FEItemFrame copperFrame;
	public static FEItemFrame leadFrame;
	public static FEItemFrame manyullynFrame;
	public static FEItemFrame silverFrame;
	public static FEItemFrame steelFrame;
	public static FEItemFrame tinFrame;
	public static FEItemFrame pokefenniumFrame;
	public static FEItemFrame fairyFrame;
	public static FEItemFrame thaumiumFrame;
	public static FEItemFrame darkThaumiumFrame;
	public static FEItemFrame voidFrame;

	public static void initModSupportItems()
	{
		if(OreDictionary.getOres("ingotAlumite").size() > 0)
			alumiteStick = new FEItemStick(Config.alumiteStickId, "alumiteStick", 0xFF33FF, "stickAlumite", "ingotAlumite");

		if(OreDictionary.getOres("ingotArdite").size() > 0)
			arditeStick = new FEItemStick(Config.arditeStickId, "arditeStick", 0xCC6633, "stickArdite", "ingotArdite");

		if(OreDictionary.getOres("ingotBronze").size() > 0)
			bronzeStick = new FEItemStick(Config.bronzeStickId, "bronzeStick", 0x996633, "stickBronze", "ingotBronze");

		if(OreDictionary.getOres("ingotCobalt").size() > 0)
			cobaltStick = new FEItemStick(Config.cobaltStickId, "cobaltStick", 0x0099CC, "stickCobalt", "ingotCobalt");

		if(OreDictionary.getOres("ingotCopper").size() > 0)
			copperStick = new FEItemStick(Config.copperStickId, "copperStick", 0xFFCC33, "stickCopper", "ingotCopper");

		if(OreDictionary.getOres("ingotLead").size() > 0)
			leadStick = new FEItemStick(Config.leadStickId, "leadStick", 0x9999CC, "stickLead", "ingotLead");

		if(OreDictionary.getOres("ingotManyullyn").size() > 0)
			manyullynStick = new FEItemStick(Config.manyullynStickId, "manyullynStick", 0x9966FF, "stickManyullyn", "ingotManyullyn");

		if(OreDictionary.getOres("ingotSilver").size() > 0)
			silverStick = new FEItemStick(Config.silverStickId, "silverStick", 0xFFFFCC, "stickSilver", "ingotSilver");

		if(OreDictionary.getOres("ingotSteel").size() > 0)
			steelStick = new FEItemStick(Config.steelStickId, "steelStick", 0x999966, "stickSteel", "ingotSteel");
		
		if(OreDictionary.getOres("ingotTin").size() > 0)
			tinStick = new FEItemStick(Config.tinStickId, "tinStick", 0xFFFFCC, "stickTin", "ingotTin");
		
		if(OreDictionary.getOres("ingotPokefennium").size() > 0)
			pokefenniumStick = new FEItemStick(Config.pokefenniumStickId, "pokefenniumStick", 0x99CC99, "stickPokefennium", "ingotPokefennium");
		
		if(OreDictionary.getOres("ingotFairy").size() > 0)
			fairyStick = new FEItemStick(Config.fairyStickId, "fairyStick", 0xFF99FF, "stickFairy", "ingotFairy");
		
		if(OreDictionary.getOres("ingotThaumium").size() > 0)
			thaumiumStick = new FEItemStick(Config.thaumiumStickId, "thaumiumStick", 0x9966FF, "stickThaumium", "ingotThaumium");
		
		if(OreDictionary.getOres("ingotDarkThaumium").size() > 0)
			darkThaumiumStick = new FEItemStick(Config.darkThaumiumStickId, "darkThaumiumStick", 0x993399, "stickDarkThaumium", "ingotDarkThaumium");
		
		if(OreDictionary.getOres("ingotVoid").size() > 0)
			voidStick = new FEItemStick(Config.voidStickId, "voidStick", 0x9900CC, "stickVoid", "ingotVoid");
		
	}
	public static FEItemStick alumiteStick;
	public static FEItemStick arditeStick;
	public static FEItemStick bronzeStick;
	public static FEItemStick cobaltStick;
	public static FEItemStick copperStick;
	public static FEItemStick leadStick;
	public static FEItemStick manyullynStick;
	public static FEItemStick silverStick;
	public static FEItemStick steelStick;
	public static FEItemStick tinStick;
	public static FEItemStick pokefenniumStick;
	public static FEItemStick fairyStick;
	public static FEItemStick thaumiumStick;
	public static FEItemStick darkThaumiumStick;
	public static FEItemStick voidStick;

	public static void initWorldGen()
	{	
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(Items.alfiumIngot), 0, 3, 0));	
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(new WeightedRandomChestContent(new ItemStack(Items.ryuIngot), 0, 3, 0));	
	}
	
	public static void initNuggets()
	{
		NuggetHelper.addNuggetToMap(0, "Draconic", 0xFF0000, new ItemStack(Items.draconicIngot));
		NuggetHelper.addNuggetToMap(1, "Reinforced", 0xCCCC99, new ItemStack(Items.reinforcedIngot));

		if(Loader.isModLoaded("Thaumcraft") && OreDictionary.getOres("ingotThaumium").size() > 0){
		NuggetHelper.addNuggetToMap(2, "Thaumium", 0x9966FF, OreDictionary.getOres("ingotThaumium").get(0));}
		
		if(Loader.isModLoaded("ThaumcraftExtras") && OreDictionary.getOres("ingotDarkThaumium").size() > 0){
		NuggetHelper.addNuggetToMap(3, "Dark Thaumium", 0x993399, OreDictionary.getOres("ingotDarkThaumium").get(0));}

		if(Loader.isModLoaded("ExtraTiC") && OreDictionary.getOres("ingotFairy").size() > 0){
		NuggetHelper.addNuggetToMap(4, "Fairy", 0xFF33FF, OreDictionary.getOres("ingotFairy").get(0));}
		
		if(Loader.isModLoaded("ExtraTiC") && OreDictionary.getOres("ingotPokefennium").size() > 0){
		NuggetHelper.addNuggetToMap(5, "Pokefennium", 0x9999FF, OreDictionary.getOres("ingotPokefennium").get(0));}

		if(Loader.isModLoaded("EnderIO") && OreDictionary.getOres("ingotConductiveIron").size() > 0){
		NuggetHelper.addNuggetToMap(6, "Conductive Iron", 0x999966, OreDictionary.getOres("ingotConductiveIron").get(0));}
		
		if(Loader.isModLoaded("EnderIO") && OreDictionary.getOres("ingotElectricalSteel").size() > 0){
		NuggetHelper.addNuggetToMap(7, "Electrical Steel", 0x999966, OreDictionary.getOres("ingotElectricalSteel").get(0));}
		
		if(Loader.isModLoaded("EnderIO") && OreDictionary.getOres("ingotEnergeticAlloy").size() > 0){
		NuggetHelper.addNuggetToMap(8, "Energetic Alloy", 0x99FF66, OreDictionary.getOres("ingotEnergeticAlloy").get(0));}
		
		if(Loader.isModLoaded("EnderIO") && OreDictionary.getOres("ingotVibrantAlloy").size() > 0){
		NuggetHelper.addNuggetToMap(9, "Vibrant Alloy", 0xFF9900, OreDictionary.getOres("ingotVibrantAlloy").get(0));}
		
		if(Loader.isModLoaded("EnderIO") && OreDictionary.getOres("ingotPulsatingIron").size() > 0){
		NuggetHelper.addNuggetToMap(10, "Pulsating Iron", 0xFF9900, OreDictionary.getOres("ingotPulsatingIron").get(0));}
		
		//if(Loader.isModLoaded("EnderIO") && Bees.doesModItemExist("EnderIO", "electricalSteel")){
		//NuggetHelper.addNuggetToMap(7, "ElectricSteel", 0xCCCC99, GameRegistry.findItemStack("EnderIO", "electricalSteel", 1));}
		
		nugget = new FEItemNugget(Config.nuggetId);
		NuggetHelper.addRecipes();
	}
	public static FEItemNugget nugget;
	
	public static void initModifiers()
	{
		ModifierHelper.addModifier(Block.obsidian.blockID, ModifierHelper.frame, 10, ModifierHelper.typeDura, 0F);
		
		ModifierHelper.addModifier(Item.ingotGold.itemID, ModifierHelper.frame, 0, ModifierHelper.typeProd, 0.2F);
	
		ModifierHelper.addModifier(Item.dyePowder.itemID, ModifierHelper.grafter, 0, ModifierHelper.grafterTypeColor, 0.0F);
	}
}
