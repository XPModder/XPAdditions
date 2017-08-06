package com.xpmodder.xpadditions.fluid;

import com.xpmodder.xpadditions.reference.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.StringUtils;

public class BlockFluidXPA extends BlockFluidClassic {

    protected final Fluid fluid;

    public BlockFluidXPA(Fluid fluid, Material material) {

        super(fluid, material);
        this.fluid = fluid;
        setNames(fluid);

    }

    public static BlockFluidXPA create(Fluid fluid, Material material){

        BlockFluidXPA res;

        res = new BlockFluidXPA(fluid, material);

        res.init();

        fluid.setBlock(res);

        return res;

    }

    static {
        MinecraftForge.EVENT_BUS.register(BlockFluidXPA.class);
    }

    protected void setNames(Fluid fluid) {
        setUnlocalizedName(fluid.getUnlocalizedName());
        setRegistryName("block_" + fluid.getName());
    }

    protected void init() {
        GameRegistry.register(this);
    }


}
