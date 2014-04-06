package forestryextras.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import forestry.apiculture.items.ItemBeeGE;
import forestryextras.blocks.itemblocks.FEItemBlockProducer;
import forestryextras.blocks.tiles.TileEntityProducer;
import forestryextras.main.Main;
import forestryextras.main.init.Items;
import forestryextras.main.init.Tabs;

public class FEBlockProducer extends BlockContainer{
    
	public FEBlockProducer(int id, String blockName) {
		super(id, Material.glass);
		setCreativeTab(Tabs.tabMain);
		
		setHardness(1.0F);
		setBlockBounds(0.2F, 0F, 0.2F, 0.8F, 1F, 0.8F);
		setUnlocalizedName(Main.alias.toLowerCase() + "." + "block" + "." + blockName.toLowerCase());

		GameRegistry.registerBlock(this, FEItemBlockProducer.class, blockName);
		init();
		}
	
	@SideOnly(Side.CLIENT)
	Icon top;
	@SideOnly(Side.CLIENT)
	Icon bottom;
	@SideOnly(Side.CLIENT)
	Icon side;
	
	public void init()
	{
		loadRecipes();
	}
	
	public void loadRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(this), new Object[]{
			"XIX",
			"XYX",
			"XXX",
			'X', Block.glass,
			'I', Block.woodSingleSlab,
			'Y', Items.witheriaIngot});
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
	{
		if(!world.isRemote){
		TileEntityProducer tile = (TileEntityProducer) world.getBlockTileEntity(x, y, z);
		
		if(!player.isSneaking()){
		if(player.getCurrentEquippedItem() != null){
		if(tile.getStackInSlot(0) == null)
		{
			if(player.getCurrentEquippedItem().getItem() instanceof ItemBeeGE){				
				tile.setInventorySlotContents(0,  player.getCurrentEquippedItem().copy());
				
				if(player.getCurrentEquippedItem().stackSize > 1)
					player.getCurrentEquippedItem().stackSize--;
					//player.setCurrentItemOrArmor(0, new ItemStack(player.getCurrentEquippedItem().getItem(), player.getCurrentEquippedItem().stackSize -1, 0));
				else
					player.setCurrentItemOrArmor(0, null);
				
				world.markBlockForUpdate(x, y, z);
			}
		}
		}
		}else{
			if(tile.getStackInSlot(0) != null){
	            dropItems(world, x, y, z);

			//dropBee(world, x, y, z, tile.getStackInSlot(0));
			tile.setInventorySlotContents(0, null);
			world.markBlockForUpdate(x, y, z);
			}
		}
		}

		return true;
	}


	 private void dropBee(World world, int x, int y, int z, ItemStack item){
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
	    			world.markBlockForUpdate(x, y, z);
	        }
	
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }


    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    /*
    @Override
    public int  getRenderType()
    {
    	return -1;
    }
    */
    
	@Override
    public void registerIcons(IconRegister ir) 
	{
		top = Block.planks.getBlockTextureFromSide(0);
		side = Block.glass.getBlockTextureFromSide(0);
		bottom = Block.planks.getBlockTextureFromSide(0);
        //blockIcon = IconHelper.forBlock(par1IconRegister, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int fside, int metadata) {
		if(fside == 0) {
			return bottom;
		} else if(fside == 1) {
			return top;
		} else {
			return side;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityProducer();
	}	
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
            dropItems(world, x, y, z);
            world.removeBlockTileEntity(x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z){
            Random rand = new Random();

            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if (!(tileEntity instanceof IInventory)) {
                    return;
            }
            IInventory inventory = (IInventory) tileEntity;

            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    ItemStack item = inventory.getStackInSlot(i);

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
            }
    }
}