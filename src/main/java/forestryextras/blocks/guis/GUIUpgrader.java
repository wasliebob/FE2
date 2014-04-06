package forestryextras.blocks.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import forestryextras.blocks.containers.ContainerUpgrader;
import forestryextras.blocks.tiles.TileEntityUpgrader;


public class GUIUpgrader extends GuiContainer {

        private static final ResourceLocation gui = new ResourceLocation("forestryextras:textures/gui/upgrader.png");
        int x, y;
        
        TileEntityUpgrader tile;

        public GUIUpgrader(TileEntityUpgrader tileI, InventoryPlayer inventory) {
                super(new ContainerUpgrader(tileI, inventory));
                this.tile = tileI;
        }
        
        @Override
        protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(gui);
            drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
            GL11.glColor3f(1F, 1F, 1F);
        }
        
        @Override
        public void initGui() {
                super.initGui();
                x = (width - xSize) / 2;
                y = (height - ySize) / 2;
        }
    
}
