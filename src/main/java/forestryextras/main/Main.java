package forestryextras.main;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import forestryextras.handlers.GUIHandler;
import forestryextras.helpers.util.FileHelper;
import forestryextras.main.init.Bees;
import forestryextras.main.init.Blocks;
import forestryextras.main.init.Items;
import forestryextras.main.init.Recipes;
import forestryextras.main.init.Tabs;
import forestryextras.main.init.intergration.IntergrationLoader;

@Mod(modid = "ForestryExtras", name = "ForestryExtras", version = "1.2.2" ,dependencies = "required-after:Forestry;after:Thaumcraft;after:ThaumcraftExtras;after:ExtraTiC;after:EnderIO; after:ThermalExpansion")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Main {
    @SidedProxy(clientSide = "forestryextras.client.ClientProxy", serverSide = "forestryextras.main.CommonProxy")
    public static CommonProxy proxy;
 
    @Instance("ForestryExtras")
    public static Main instance;
    public static String version = "1.2.2";
    public static String modName = "ForestryExtras";
    public static String alias = "FE";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		Config config = new Config();
		Config.loadConfig(event);
		
		File file = new File(Config.itemList);
		if(!file.exists()){
			file.mkdir();
		}
    }

    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.load();
		IntergrationLoader.preFullInit();
    	Items.init();
    	Blocks.init();
    	IntergrationLoader.init();
    	Bees.init();
    	Tabs.init();
    	initTiles();
		FileHelper.init();
    	NetworkRegistry.instance().registerGuiHandler(this, new GUIHandler());
    	initEvents();
    	Recipes.init();
    }
    
    public void initEvents()
    {
        MinecraftForge.EVENT_BUS.register(this); 
    }
    
    public void initTiles()
    {
        GameRegistry.registerTileEntity(forestryextras.blocks.tiles.TileEntityProducer.class, "10001");
        GameRegistry.registerTileEntity(forestryextras.blocks.tiles.TileEntityUpgrader.class, "10002");
        GameRegistry.registerTileEntity(forestryextras.blocks.tiles.TileEntityBeeEngine.class, "10003");
    }
    
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent evt)
    {

    }
    
    public void initMisc(FMLPreInitializationEvent event)
    {
		if(event.getSide() == Side.CLIENT){
			IntergrationLoader.initRequired("ThermalExpansion");
		}
    }
}