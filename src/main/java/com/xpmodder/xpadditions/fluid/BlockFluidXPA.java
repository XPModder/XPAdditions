package com.xpmodder.xpadditions.fluid;


import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;


public class BlockFluidXPA extends FlowingFluidBlock {

    protected BlockFluidXPA(FlowingFluid p_i49014_1_, Properties p_i49014_2_) {
        super(p_i49014_1_, p_i49014_2_);
    }

    //TODO: remove if not required
    /*
    public static FluidStack stack;

    public BlockFluidXPA(Fluid fluid, Material material) {

        super(fluid, material);
        ModFluids.fluid_exp.setBlock(this);
        stack = new FluidStack(ModFluids.fluid_exp, Fluid.BUCKET_VOLUME);
        setCreativeTab(CreativeTabXPA.XPA_TAB);
        setUnlocalizedName("block_" + fluid.getName());
        setRegistryName("block_" + fluid.getName());

    }

    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {

        super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);

        if(entityIn instanceof EntityPlayer){

            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 10));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 5));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING));
            ((EntityPlayer) entityIn).addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1000, 5));

        }
        if(entityIn instanceof EntityLiving){

            ((EntityLiving) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 1000, 10));
            ((EntityLiving) entityIn).addPotionEffect(new PotionEffect(MobEffects.SPEED, 1000, 5));
            ((EntityLiving) entityIn).addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING));

        }

    }

    static {
        MinecraftForge.EVENT_BUS.register(BlockFluidXPA.class);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        Block block = ModFluids.block_exp;
        Item item = Item.getItemFromBlock(block);
        ModelBakery.registerItemVariants(item);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(Reference.MOD_ID + ":fluid", stack.getFluid().getName());
        ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
        ModelLoader.setCustomStateMapper(block, new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState bs) {
                return modelResourceLocation;
            }
        });
    }

     */

}
