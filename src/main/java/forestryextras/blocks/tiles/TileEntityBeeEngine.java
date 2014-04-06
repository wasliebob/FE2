package forestryextras.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import thaumcraftextras.helpers.MathHelper;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.tileentity.IEnergyInfo;
import forestry.apiculture.items.ItemBeeGE;
import forestryextras.helpers.util.EnergyHelper;
import forestryextras.main.Config;

public class TileEntityBeeEngine extends TileEntity implements ISidedInventory, IEnergyInfo, IEnergyHandler{
	public TileEntityBeeEngine()
	{
		ItemStacks = new ItemStack[1];
		chargeTime = MathHelper.secondToTick(2);
		waitTime = MathHelper.secondToTick(2);
		//turns = Config.beeEngineLifespan;
		//output = Config.beeEngineOutput;
		
		storage = new EnergyStorage(EnergyHelper.calculateEnergyStorage(2), 50, 50);
		storage.setMaxExtract(output);
		storage.setMaxReceive(output);
	}
	public ItemStack ItemStacks[];
	int chargeTime;
	int waitTime;
	int output;
	int turns;
	public EnergyStorage storage;

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


    /*
    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
    	if(turns == 0){
    		ItemStacks[0] = null;
    	}
    	
    	    	
    	if(getStackInSlot(0) != null){
    	if(chargeTime != 0)
    		chargeTime--;
    	
    	if(chargeTime == 0)
    	{
    		storage.modifyEnergyStored(+output);
    		turns--;
    		chargeTime = waitTime;
    	}
    	}
    	}
    }
	*/
    
    @Override
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
    		if(turns == 0){
    			ItemStacks[0] = null;
    		}
    	    	
    		if(getStackInSlot(0) != null && storage.getEnergyStored() + output <= storage.getMaxEnergyStored()){
    			if(turns != 0){
    				storage.modifyEnergyStored(+output);
    				turns--;
    			}
    		}
    	}
    }
    
	@Override
	public int getSizeInventory() {
		return ItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return ItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
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
	public ItemStack getStackInSlotOnClosing(int i) {
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
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		 ItemStacks[i] = itemstack;

	        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
	        {
	            itemstack.stackSize = getInventoryStackLimit();
	        } 
	}

	@Override
	public String getInvName() {
		return "mm.beeEngine";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		  if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
	         {
	                 return false;
	         }

	         return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	     }

	@Override
	public void openChest() {		
	}

	@Override
	public void closeChest() {		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		if(i == 0 && stack.getItem() instanceof ItemBeeGE)
			return true;
		else
			return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int slot) {
		return new int[0];
	}

	@Override
	public boolean canInsertItem(int i, ItemStack stack, int j) {
		if(i == 0 && stack.getItem() instanceof ItemBeeGE)
			return true;
		else
			return false;
		}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canInterface(ForgeDirection arg0) {
		return true;
	}

	@Override
	public int extractEnergy(ForgeDirection arg0, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection arg0) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection arg0) {
		return storage.getMaxEnergyStored();
	}

	@Override
	public int receiveEnergy(ForgeDirection arg0, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergy() {
		return storage.getEnergyStored();
	}

	@Override
	public int getEnergyPerTick() {
		return output;
	}

	@Override
	public int getMaxEnergy() {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyPerTick() {
		return output;
	}
}
