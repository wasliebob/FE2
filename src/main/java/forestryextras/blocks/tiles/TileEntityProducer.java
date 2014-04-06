package forestryextras.blocks.tiles;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraftextras.helpers.MathHelper;
import forestry.core.config.ForestryItem;

public class TileEntityProducer extends TileEntity implements ISidedInventory{
	public TileEntityProducer()
	{
		ItemStacks = new ItemStack[1];
		chargeTime = MathHelper.secondToTick(5);
		waitTime = MathHelper.secondToTick(5);
		waiterTime = MathHelper.secondToTick(5);
		turns = 32;
	}
	public ItemStack ItemStacks[];
	int chargeTime;
	int waitTime;
	int waiterTime;

	int turns;
	

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
    public void updateEntity() 
    {
    	if(!worldObj.isRemote){
    	if(turns == 0){
    		ItemStacks[0] = null;
			worldObj.markBlockForUpdate(xCoord, yCoord, yCoord);
    	}
    	
    	if(waiterTime != 0)
    		waiterTime--;
    	
    	if(waiterTime == 0)
			worldObj.markBlockForUpdate(xCoord, yCoord, yCoord);
    	
    	if(getStackInSlot(0) != null){
    	if(chargeTime != 0)
    		chargeTime--;
    	
    	if(chargeTime == 0)
    	{
    			ItemStack stack = new ItemStack(ForestryItem.beeComb.item());
    			dropItems(worldObj, this.xCoord, this.yCoord, this.zCoord, stack);
    			turns--;
    			chargeTime = waitTime;
    			worldObj.markBlockForUpdate(xCoord, yCoord, yCoord);
    	}
    	}
    	}
    }
    
    private void dropItems(World world, int x, int y, int z, ItemStack item){
    	Random rand = new Random();
                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                        if (item.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
    			//worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
		return "mm.producer";
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return false;
	}
}
