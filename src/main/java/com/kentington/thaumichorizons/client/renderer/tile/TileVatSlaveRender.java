//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import thaumcraft.client.lib.UtilsFX;

import com.kentington.thaumichorizons.common.tiles.TileVat;

public class TileVatSlaveRender extends TileEntitySpecialRenderer {

    ModelBiped corpse;
    static String tx1;
    static String tx2;
    EntityItem stack;

    public TileVatSlaveRender() {
        this.corpse = new ModelBiped();
        this.stack = null;
    }

    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float f) {
        if (te.getBlockMetadata() == 0 && te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord + 1, te.zCoord) == 7) {
            final TileVat tco = (TileVat) te.getWorldObj().getTileEntity(te.xCoord, te.yCoord + 1, te.zCoord);
            GL11.glPushMatrix();
            if (tco.getEntityContained() != null && !(tco.getEntityContained() instanceof EntityPlayer)) {
                if (tco.mode == 1) {
                    tco.getClass();
                    final float n = 800 - tco.progress;
                    tco.getClass();
                    final float scale = n / 800.0f;
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, scale);
                    final Entity p_147936_1_ = (Entity) tco.getEntityContained();
                    final double d0 = p_147936_1_.lastTickPosX + (p_147936_1_.posX - p_147936_1_.lastTickPosX) * f;
                    final double d2 = p_147936_1_.lastTickPosY + (p_147936_1_.posY - p_147936_1_.lastTickPosY) * f;
                    final double d3 = p_147936_1_.lastTickPosZ + (p_147936_1_.posZ - p_147936_1_.lastTickPosZ) * f;
                    final float f2 = p_147936_1_.prevRotationYaw
                            + (p_147936_1_.rotationYaw - p_147936_1_.prevRotationYaw) * f;
                    final RenderManager instance = RenderManager.instance;
                    final Entity entity = p_147936_1_;
                    final double n2 = d0;
                    final RenderManager instance2 = RenderManager.instance;
                    final double n3 = n2 - RenderManager.renderPosX;
                    final double n4 = d2;
                    final RenderManager instance3 = RenderManager.instance;
                    final double n5 = n4 - RenderManager.renderPosY;
                    final double n6 = d3;
                    final RenderManager instance4 = RenderManager.instance;
                    instance.func_147939_a(entity, n3, n5, n6 - RenderManager.renderPosZ, f2, f, false);
                } else {
                    GL11.glTranslatef(
                            0.0f,
                            0.1f * (float) Math.cos(Math.toRadians(Minecraft.getMinecraft().thePlayer.ticksExisted)),
                            0.0f);
                    RenderManager.instance.renderEntitySimple((Entity) tco.getEntityContained(), f);
                }
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                GL11.glDisable(3042);
            } else if (tco.mode == 3) {
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glTranslatef(
                        (float) (-x) - 0.5f,
                        (float) (-y) - 1.5f
                                + 0.1f * (float) Math
                                        .cos(Math.toRadians(Minecraft.getMinecraft().thePlayer.ticksExisted)),
                        (float) z + 0.5f);
                UtilsFX.bindTexture("thaumichorizons", TileVatSlaveRender.tx1);
                this.corpse.render((Entity) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
            } else if (tco.mode == 4 || (tco.mode == 2 && tco.recipeType == 1)) {
                GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                GL11.glTranslatef(
                        (float) (-x) - 0.5f,
                        (float) (-y) - 1.5f
                                + 0.1f * (float) Math
                                        .cos(Math.toRadians(Minecraft.getMinecraft().thePlayer.ticksExisted)),
                        (float) z + 0.5f);
                UtilsFX.bindTexture("thaumichorizons", TileVatSlaveRender.tx2);
                this.corpse.render((Entity) null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f);
            } else if (tco.sample != null) {
                if (this.stack == null || tco.sample.getItem() != this.stack.getEntityItem().getItem()) {
                    this.stack = new EntityItem(
                            tco.getWorldObj(),
                            tco.xCoord + 0.5,
                            tco.yCoord - 1.0,
                            tco.zCoord + 0.5,
                            tco.sample);
                }
                GL11.glTranslatef(
                        0.0f,
                        0.1f * (float) Math.cos(Math.toRadians(Minecraft.getMinecraft().thePlayer.ticksExisted)),
                        0.0f);
                RenderManager.instance.renderEntitySimple((Entity) this.stack, f);
            }
            GL11.glEnable(32826);
            GL11.glPopMatrix();
        }
    }

    static {
        TileVatSlaveRender.tx1 = "textures/models/corpseeffigy.png";
        TileVatSlaveRender.tx2 = "textures/models/corpseeffigyrevived.png";
    }
}
