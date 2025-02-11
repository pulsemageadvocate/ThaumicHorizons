//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;

import com.kentington.thaumichorizons.common.entities.EntityWizardCow;

public class RenderWizardCow extends RenderCow {

    public static HashMap<String, AspectList> cowAspects;
    public static HashMap<String, NodeType> cowTypes;
    public static HashMap<String, NodeModifier> cowMods;

    public RenderWizardCow(final ModelBase p_i1253_1_, final float p_i1253_2_) {
        super(p_i1253_1_, p_i1253_2_);
    }

    public void doRender(final EntityWizardCow p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
        super.doRender((EntityLiving) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        if (RenderWizardCow.cowAspects.containsKey(p_76986_1_.getUniqueID().toString())) {
            this.renderMyNode(
                    p_76986_1_.posX,
                    p_76986_1_.posY + p_76986_1_.height / 2.0f,
                    p_76986_1_.posZ,
                    Minecraft.getMinecraft().renderViewEntity,
                    32.0,
                    true,
                    true,
                    1.0f,
                    p_76986_9_,
                    RenderWizardCow.cowAspects.get(p_76986_1_.getUniqueID().toString()),
                    RenderWizardCow.cowTypes.get(p_76986_1_.getUniqueID().toString()),
                    RenderWizardCow.cowMods.get(p_76986_1_.getUniqueID().toString()));
        }
        GL11.glDisable(3042);
    }

    public void renderMyNode(final double x, final double y, final double z, final EntityLivingBase viewer,
            final double viewDistance, final boolean visible, final boolean depthIgnore, final float size,
            final float partialTicks, final AspectList aspects, final NodeType type, final NodeModifier mod) {
        final long nt = System.nanoTime();
        UtilsFX.bindTexture(TileNodeRenderer.nodetex);
        final int frames = 32;
        if (aspects.size() > 0 && visible) {
            final double distance = viewer.getDistance(x, y, z);
            if (distance > viewDistance) {
                return;
            }
            float alpha = (float) ((viewDistance - distance) / viewDistance);
            if (mod != null) {
                switch (mod) {
                    case BRIGHT: {
                        alpha *= 1.5f;
                        break;
                    }
                    case PALE: {
                        alpha *= 0.66f;
                        break;
                    }
                    case FADING: {
                        alpha *= MathHelper.sin(viewer.ticksExisted / 3.0f) * 0.25f + 0.33f;
                        break;
                    }
                }
            }
            GL11.glPushMatrix();
            GL11.glAlphaFunc(516, 0.003921569f);
            GL11.glDepthMask(false);
            if (depthIgnore) {
                GL11.glDisable(2929);
            }
            GL11.glDisable(2884);
            final long time = nt / 5000000L;
            final float bscale = 0.25f;
            GL11.glPushMatrix();
            final float rad = 6.283186f;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
            int i = (int) ((nt / 40000000L + x) % frames);
            int count = 0;
            float scale = 0.0f;
            float angle = 0.0f;
            float average = 0.0f;
            for (final Aspect aspect : aspects.getAspects()) {
                if (aspect.getBlend() == 771) {
                    alpha *= 1.5;
                }
                average += aspects.getAmount(aspect);
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, aspect.getBlend());
                scale = MathHelper.sin(viewer.ticksExisted / (14.0f - count)) * bscale + bscale * 2.0f;
                scale = 0.2f + scale * (aspects.getAmount(aspect) / 50.0f);
                scale *= size;
                angle = time % (5000 + 500 * count) / (5000.0f + 500 * count) * rad;
                UtilsFX.renderFacingStrip(
                        x,
                        y,
                        z,
                        angle,
                        scale,
                        alpha / Math.max(1.0f, aspects.size() / 2.0f),
                        frames,
                        0,
                        i,
                        partialTicks,
                        aspect.getColor());
                GL11.glDisable(3042);
                GL11.glPopMatrix();
                ++count;
                if (aspect.getBlend() == 771) {
                    alpha /= 1.5;
                }
            }
            average /= aspects.size();
            GL11.glPushMatrix();
            GL11.glEnable(3042);
            i = (int) ((nt / 40000000L + x) % frames);
            scale = 0.1f + average / 150.0f;
            scale *= size;
            int strip = 1;
            switch (type) {
                case NORMAL: {
                    GL11.glBlendFunc(770, 1);
                    break;
                }
                case UNSTABLE: {
                    GL11.glBlendFunc(770, 1);
                    strip = 6;
                    angle = 0.0f;
                    break;
                }
                case DARK: {
                    GL11.glBlendFunc(770, 771);
                    strip = 2;
                    break;
                }
                case TAINTED: {
                    GL11.glBlendFunc(770, 771);
                    strip = 5;
                    break;
                }
                case HUNGRY: {
                    GL11.glBlendFunc(770, 1);
                    strip = 4;
                    break;
                }
                case PURE: {
                    scale *= 0.75f;
                    GL11.glBlendFunc(770, 1);
                    strip = 3;
                    break;
                }
            }
            GL11.glColor4f(1.0f, 0.0f, 1.0f, alpha);
            UtilsFX.renderFacingStrip(x, y, z, angle, scale, alpha, frames, strip, i, partialTicks, 16777215);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glEnable(2884);
            if (depthIgnore) {
                GL11.glEnable(2929);
            }
            GL11.glDepthMask(true);
            GL11.glAlphaFunc(516, 0.1f);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glAlphaFunc(516, 0.003921569f);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);
            GL11.glDepthMask(false);
            final int j = (int) ((nt / 40000000L + x) % frames);
            GL11.glColor4f(1.0f, 0.0f, 1.0f, 0.1f);
            UtilsFX.renderFacingStrip(x, y, z, 0.0f, 0.5f, 0.1f, frames, 1, j, partialTicks, 16777215);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1f);
            GL11.glPopMatrix();
        }
    }

    public void doRender(final Entity p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityWizardCow) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(final EntityLiving p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityWizardCow) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void doRender(final EntityLivingBase p_76986_1_, final double p_76986_2_, final double p_76986_4_,
            final double p_76986_6_, final float p_76986_8_, final float p_76986_9_) {
        this.doRender((EntityWizardCow) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    static {
        RenderWizardCow.cowAspects = new HashMap<String, AspectList>();
        RenderWizardCow.cowTypes = new HashMap<String, NodeType>();
        RenderWizardCow.cowMods = new HashMap<String, NodeModifier>();
    }
}
