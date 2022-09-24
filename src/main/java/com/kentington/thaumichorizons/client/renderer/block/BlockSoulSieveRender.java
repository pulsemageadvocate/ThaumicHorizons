//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.block;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockSoulSieveRender extends BlockRenderer implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(
            final Block block, final int metadata, final int modelID, final RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
        final TileSoulExtractor tc = new TileSoulExtractor();
        tc.blockMetadata = metadata;
        TileEntityRendererDispatcher.instance.renderTileEntityAt((TileEntity) tc, 0.0, 0.0, 0.0, 0.0f);
        GL11.glEnable(32826);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(
            final IBlockAccess world,
            final int x,
            final int y,
            final int z,
            final Block block,
            final int modelId,
            final RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(final int modelId) {
        return true;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockSoulSieveRI;
    }
}
