package forestryextras.blocks.tiles;

import java.awt.Color;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import thaumcraftextras.api.functions.ChargerFunctions;
import thaumcraftextras.api.functions.ExchangerFunctions;
import cpw.mods.fml.common.network.PacketDispatcher;
import forestryextras.helpers.ModifierHelper;
import forestryextras.items.frames.FEItemFrameCustom;
import forestryextras.items.grafters.FEItemGrafterCustom;

public class TileEntityUpgrader extends TileEntity implements ISidedInventory
{
    
	ItemStack ItemStacks[]; 
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};
    
    public TileEntityUpgrader()
    {
    	ItemStacks = new ItemStack[3];
    }
    
    @Override
    public void updateEntity()
    {
    	if(!this.worldObj.isRemote)
    	{
    		if(getStackInSlot(0) != null && getStackInSlot(2) != null)
    		{    	
    			ItemStack cItem = getStackInSlot(2);
    			ItemStack mod = getStackInSlot(0);
    			NBTTagCompound tag = cItem.getTagCompound();
    			if(ModifierHelper.isModifier(mod.getItem().itemID) && ModifierHelper.ItemType.get(mod.getItem().itemID) == ModifierHelper.frame && cItem.getItem() instanceof FEItemFrameCustom)
    			{		
    				
				}
    			else if(ModifierHelper.isModifier(mod.getItem().itemID) && ModifierHelper.ItemType.get(mod.getItem().itemID) == ModifierHelper.grafter && cItem.getItem() instanceof FEItemGrafterCustom)
    			{
    				if(getStackInSlot(0).getItem() instanceof ItemDye)
    				{
    					if(tag == null)
    					{
    						tag = new NBTTagCompound("grafter");
    						tag.setInteger("Color", getColor(getStackInSlot(0).getItemDamage()));
    						cItem.setTagCompound(tag);
    						cItem.setItemName("Name" + tag.getInteger("Color"));
    					}
    					else
    					{
    						tag.setInteger("Color", getColor(getStackInSlot(0).getItemDamage()));
    						cItem.setTagCompound(tag);
    					}
    				}else{
    					
    				}
    			}
    		}
    	}
    }
    
    public int getColor(int meta)
    {
    	switch(meta)
    	{
    	case 0: return Color.black.getRGB();
    	case 1: return Color.red.getRGB();
    	case 2: return Color.green.getRGB();
    	case 3: return 0x996633;
    	case 4: return Color.blue.getRGB();
    	default: return Color.black.getRGB();
    	}
    }
    
	@Override
	public int getSizeInventory() {
		return ItemStacks.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) 
	{
		   if(i > ItemStacks.length)
	            return ItemStacks[0];
	            else
	            	return ItemStacks[i];
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) 
	{	
        ItemStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }        
	}
	
	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if (ItemStacks[i] != null)
        {

            if (ItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = ItemStacks[i];
                ItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                ItemStack itemstack1 = ItemStacks[i].splitStack(j);

                if (ItemStacks[i].stackSize == 0)
                {
                    ItemStacks[i] = null;
                }

                return itemstack1;
            }
        }
        else
        {
            return null;
        }
	}

    @Override
    public ItemStack getStackInSlotOnClosing(int i)
{
        if (ItemStacks[i] != null)
        {
            ItemStack itemstack = ItemStacks[i];
            ItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public String getInvName() {
		return "fe2.upgrader";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	

	@Override
	public void openChest() 
	{
		
	}

	@Override
	public void closeChest() 
	{
		
	}
	

	
	 @Override
     public void readFromNBT(NBTTagCompound tagCompound) {
             super.readFromNBT(tagCompound);
            
             NBTTagList tagList = tagCompound.getTagList("Inventory");
             for (int i = 0; i < tagList.tagCount(); i++) {
                     NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
                     byte slot = tag.getByte("Slot");
                     if (slot >= 0 && slot < ItemStacks.length) {
                             ItemStacks[slot] = ItemStack.loadItemStackFromNBT(tag);
                     }
             }
     }

     @Override
     public void writeToNBT(NBTTagCompound tagCompound) {
             super.writeToNBT(tagCompound);
                            
             NBTTagList itemList = new NBTTagList();
             for (int i = 0; i < ItemStacks.length; i++) {
                     ItemStack stack = ItemStacks[i];
                     if (stack != null) {
                             NBTTagCompound tag = new NBTTagCompound();
                             tag.setByte("Slot", (byte) i);
                             stack.writeToNBT(tag);
                             itemList.appendTag(tag);
                     }
             }
             tagCompound.setTag("Inventory", itemList);
     }
     
     @Override
     public Packet getDescriptionPacket() {
             NBTTagCompound nbttagcompound = new NBTTagCompound();
             writeToNBT(nbttagcompound);
             return new Packet132TileEntityData(xCoord, yCoord, zCoord, -999, nbttagcompound);
     }

     @Override
     public void onDataPacket(INetworkManager manager, Packet132TileEntityData packet) {
             super.onDataPacket(manager, packet);
             readFromNBT(packet.data);
     }


     @Override
     public void onInventoryChanged() 
     {
             super.onInventoryChanged();
             if(!worldObj.isRemote) 
             {
                     PacketDispatcher.sendPacketToAllInDimension(getDescriptionPacket(), worldObj.provider.dimensionId);
             }
     }
     
 	@Override
 	public boolean isUseableByPlayer(EntityPlayer player) 
 	{
         if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
         {
                 return false;
         }

         return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
 	}
 	

	  
	  public boolean hashmapContains(ItemStack item)
	  {
		  return ModifierHelper.isModifier(item.getItem().itemID);
	  }

	  public String getType(ItemStack item)
	  {
		  return ModifierHelper.getType(item.getItem().itemID);
	  }
	  
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int[]{0, 1, 2};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) 
	{
		if(i == 0 && j == 4)
			return itemstack != null && ExchangerFunctions.canExchange.containsKey(itemstack.getItem());
		else if(i == 1 && j == 4)
			return itemstack != null && ChargerFunctions.isChargeAble.contains(itemstack.getItem());
		else 
			return false;
	}

	/**
	 * 0 = Bottom
	 * 1 = Top
	 * 2 = Back
	 * 3 = Front
	 * 4 = Left
	 * 5 = Right
	 */
	
	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j)
	{
		if(i == 1 && j ==  5 && itemstack.getItem().getDamage(getStackInSlot(1)) == getStackInSlot(1).getItem().getMaxDamage())
			return true;
		if(i == 2 && j ==  5)
			return true;
		else
			return false;
	}
}