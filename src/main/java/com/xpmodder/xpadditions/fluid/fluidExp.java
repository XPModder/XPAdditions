package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class fluidExp extends Fluid {

    public fluidExp() {

        super("xpjuice", new ResourceLocation(Reference.MOD_ID, "fluids/xpjuice_still"), new ResourceLocation(Reference.MOD_ID, "fluids/xpjuice_flow"));
        setViscosity(10);
        setDensity(30);
        setUnlocalizedName("xpjuice");

    }

}
