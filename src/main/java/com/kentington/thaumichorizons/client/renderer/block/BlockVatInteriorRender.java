//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import thaumcraft.client.renderers.block.BlockRenderer;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockVatInteriorRender extends BlockRenderer implements ISimpleBlockRenderingHandler {

    public void renderInventoryBlock(final Block block, final int metadata, final int modelId,
            final RenderBlocks renderer) {}

    public boolean renderWorldBlock(final IBlockAccess world, final int x, final int y, final int z, final Block block,
            final int modelId, final RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(final int modelId) {
        return false;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockVatInteriorRI;
    }
}
