package forestryextras.helpers.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import net.minecraft.client.Minecraft;
import forestryextras.main.Config;
import forestryextras.main.Main;

public class FileHelper {

	public static void init()
	{
		createItemList("itemList");
		createMutationList("mutationList");
		createFrameList("frameList");
//		createBeeProductList("beeProductionList");
	}
	
	public static void createItemList(String name)
	{
		File file = new File(Config.itemList + name + ".txt");
		try {
			file.createNewFile();
			PrintWriter writer = new PrintWriter(Config.itemList + name + ".txt", "UTF-8");
			
			for(int i = 0; i < list.size(); i++)
				if(list.get(i) != null)
					writer.println(list.get(i));

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
	}
	public static HashMap<Integer, String> list = new HashMap<Integer, String>();

	public static void createMutationList(String name)
	{
		File file = new File(Config.mutationList + name + ".txt");
		try{
			file.createNewFile();
			PrintWriter writer = new PrintWriter(Config.mutationList + name + ".txt", "UTF-8");
			
			for(int i = 0; i < result.size(); i++ )
				if(!parent1.get(i).contains("null") || !parent2.get(i).contains("null") || !result.get(i).contains("null") || !beeAuthor.get(i).contains("null"))
					writer.println(parent1.get(i) + " + " + parent2.get(i) + " = " + result.get(i) + "|| Author: " + beeAuthor.get(i));

			writer.close();
			
		}catch (IOException e){
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
	}
	public static HashMap<Integer, String> parent1 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> parent2 = new HashMap<Integer, String>();
	public static HashMap<Integer, String> result = new HashMap<Integer, String>();
	public static HashMap<Integer, String> beeAuthor = new HashMap<Integer, String>();
	
	public static List<String> readChangelog()
	{
		ArrayList<String> list = new ArrayList<String>();

		try{
			URL url = new URL("https://dl.dropboxusercontent.com/u/46500170/Site/" + "FE2" + "_" + Main.version + ".txt");

			Scanner scanner = new Scanner(url.openStream());
//			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    
//		    while (reader.readLine() != null) 
//				list.add(reader.readLine());
//			reader.close();
			
			while(scanner.hasNext()){
				list.add(scanner.nextLine());
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;

	}
	
	public static void createFrameList(String name)
	{
		File file = new File(Config.frameList + name + ".txt");
		try{
			file.createNewFile();
			PrintWriter writer = new PrintWriter(Config.frameList + name + ".txt", "UTF-8");
			
			for(int i = 0; i < frameName.size(); i++)
				writer.println(" |<b> Name</b> | " + frameName.get(i) + " |<b> Durability </b>| " + durability.get(i) + " |<b> Production </b>| " + prodMod.get(i) + " |<b> Decay </b>| " + decay.get(i) + " |<b> Flowering </b>| " + flowering.get(i) + " |<b> Lifespan </b>| " + lifeMod.get(i) + " |<b> Mutation </b>| " + mutMod.get(i) + " |<b> Territory </b>| " + terMod.get(i));
			
			writer.close();
			
		}catch (IOException e){
			e.printStackTrace();
			Minecraft.getMinecraft().shutdown();
		}
	}
	public static HashMap<Integer, String> frameName = new HashMap<Integer, String>();
	public static HashMap<Integer, Float> prodMod = new HashMap<Integer, Float>();
	public static HashMap<Integer, Float> decay = new HashMap<Integer, Float>();
	public static HashMap<Integer, Float> flowering = new HashMap<Integer, Float>();
	public static HashMap<Integer, Float> lifeMod = new HashMap<Integer, Float>();
	public static HashMap<Integer, Float> mutMod = new HashMap<Integer, Float>();
	public static HashMap<Integer, Float> terMod = new HashMap<Integer, Float>();
	public static HashMap<Integer, Integer> durability = new HashMap<Integer, Integer>();

}
