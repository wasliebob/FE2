package forestryextras.blocks.itemblocks;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import forestryextras.main.Config;

public class FEItemBlockBeeEngine extends ItemBlock{

	public FEItemBlockBeeEngine(int id) {
		super(id);	
		text = new String[]{"This block will generate power out of bees.","You can get put a bee in the engine by right clicking", "with a bee in your hand."};
		//stats = new String[]{"Redstone Flux", Config.beeEngineOutput + " ", Config.beeEngineLifespan + " "};
		keys = new String[]{"Energy Type: ", "Energy Output: ", "Bee Lifespan: "};
		random = new String[]{"More Options", "Advanced Tooltips", "More Info", "Better Interaction"};
		rformat = new EnumChatFormatting[]{EnumChatFormatting.BOLD, EnumChatFormatting.UNDERLINE, EnumChatFormatting.OBFUSCATED, EnumChatFormatting.RESET};
	}
	String[] text;
	String[] keys;
	String[] stats;
	String[] random;
	EnumChatFormatting[] rformat;
	
	@Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) 
	{
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			if(text.length > 0){
				for(int i = 0;i < text.length; i++)
					list.add(EnumChatFormatting.GRAY + text[i]);
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)){
			if(stats.length > 0){
				for(int j = 0; j < stats.length; j++)
					list.add(EnumChatFormatting.RED + keys[j] + EnumChatFormatting.GRAY + stats[j]);
			}
		}else if(Keyboard.isKeyDown(Keyboard.KEY_TAB)){
			if(random.length > 0){
				for(int k = 0; k < random.length; k++)
					list.add(rformat[k] + random[k]);
			}
		}else{
			list.add(EnumChatFormatting.GREEN + "Press " + "Shift " + "for info.");
			list.add(EnumChatFormatting.RED + "Press " + "Control " + "for stats.");
		}
	}
}