//
// Decompiled by Procyon v0.5.30
//

package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.entities.EntityBoatThaumium;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBoatThaumium extends RenderBoat {
    private static final ResourceLocation boatTextures;

    protected ResourceLocation getEntityTexture(final EntityBoatThaumium p_110775_1_) {
        return RenderBoatThaumium.boatTextures;
    }

    protected ResourceLocation getEntityTexture(final Entity p_110775_1_) {
        return this.getEntityTexture((EntityBoatThaumium) p_110775_1_);
    }

    static {
        boatTextures = new ResourceLocation("thaumichorizons", "textures/entity/boatthaumium.png");
    }
}
