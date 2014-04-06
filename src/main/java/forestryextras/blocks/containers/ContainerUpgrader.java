package forestryextras.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import forestryextras.blocks.tiles.TileEntityUpgrader;
import forestryextras.helpers.ModifierHelper;
import forestryextras.items.frames.FEItemFrameCustom;
import forestryextras.items.grafters.FEItemGrafterCustom;

public class ContainerUpgrader extends Container {
int x, y;
        
public static TileEntityUpgrader tile;
        
        public ContainerUpgrader(TileEntityUpgrader tileI, InventoryPlayer inventory) {
                super();
                this.tile = tileI;
                this.addSlotToContainer(new Slot(tile, 0, 56, 17));
                this.addSlotToContainer(new Slot(tile, 1, 56, 53));
                this.addSlotToContainer(new Slot(tile, 2, 116, 35));

                int i;

                for (i = 0; i < 3; ++i)
                {
                    for (int j = 0; j < 9; ++j)
                    {
                        this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                    }
                }

                for (i = 0; i < 9; ++i)
                {
                    this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
                }
        }
        
        @Override
        public boolean canInteractWith(EntityPlayer entityplayer) {
                return tile.isUseableByPlayer(entityplayer);
        }
        
        @Override
        public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
                ItemStack stack = null;
                Slot slotObject = (Slot) inventorySlots.get(slot);
                Slot slot0 = (Slot) inventorySlots.get(0);
                Slot slot1 = (Slot) inventorySlots.get(1);

                if (slotObject != null && slotObject.getHasStack())
                {
                        ItemStack stackInSlot = slotObject.getStack();
                        stack = stackInSlot.copy();

                        if (slot < 3)
                        {
                                if (!this.mergeItemStack(stackInSlot, 3, 37, true))
                                {
                                        return null;
                                }
                        }
                        else if(doesItemFitInSlot(stackInSlot) && !this.mergeItemStack(stackInSlot, getStartSlot(stackInSlot), getEndSlot(stackInSlot), false))
                        {
                                return null;
                        }
                        
                        if (stackInSlot.stackSize == 0)
                        {
                                slotObject.putStack(null);
                        }
                        else
                        {
                                slotObject.onSlotChanged();
                        }

                        if (stackInSlot.stackSize == stack.stackSize)
                        {
                                return null;
                        }
                        slotObject.onPickupFromSlot(player, stackInSlot);
                }
                return stack;
        }

        public int getStartSlot(ItemStack item)
        {
         if(item.getItem() instanceof FEItemFrameCustom || item.getItem() instanceof FEItemGrafterCustom)
        	 return 2;
         else if(ModifierHelper.isModifier(item.getItem().itemID))
        	 return 0;
         else
        	 return 0;
        }
        
        public int getEndSlot(ItemStack item)
        {
        if(item.getItem() instanceof FEItemFrameCustom || item.getItem() instanceof FEItemGrafterCustom)
        	return 3;
        else if(ModifierHelper.isModifier(item.getItem().itemID))
        	return 1;
        else
        	return 2;
        }
        
        public boolean doesItemFitInSlot(ItemStack item)
        {
         if(item.getItem() instanceof FEItemFrameCustom || item.getItem() instanceof FEItemGrafterCustom)
        	 return true;
         else if(ModifierHelper.isModifier(item.getItem().itemID))
        	 return true;
         else
        	 return false;
        }
}