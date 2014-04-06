package forestryextras.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import forestryextras.blocks.containers.ContainerUpgrader;
import forestryextras.blocks.guis.GUIUpgrader;
import forestryextras.blocks.tiles.TileEntityUpgrader;


public class GUIHandler implements IGuiHandler {
        @Override
        public Object getServerGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z)
        {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(id == 0)
                	return new ContainerUpgrader((TileEntityUpgrader) tileEntity, player.inventory);
                else
                	return false;
        }


        @Override
        public Object getClientGuiElement(int id, EntityPlayer player, World world,
                        int x, int y, int z)
        {
                TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
                if(id == 0)
                	return new GUIUpgrader((TileEntityUpgrader) tileEntity, player.inventory);
                else
                	return false;
        }
}