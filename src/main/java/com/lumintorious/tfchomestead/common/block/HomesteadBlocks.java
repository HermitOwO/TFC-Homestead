package com.lumintorious.tfchomestead.common.block;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import com.lumintorious.tfchomestead.TFCHomestead;
import com.lumintorious.tfchomestead.common.drinks.AgedAlcohol;
import com.lumintorious.tfchomestead.common.drinks.HomesteadFluid;
import com.lumintorious.tfchomestead.common.item.HomesteadItems;
import javax.annotation.Nullable;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.registry.RegistrationHelpers;

public class HomesteadBlocks
{
    public static DeferredRegister<Block> BLOCKS =
        DeferredRegister.create(ForgeRegistries.BLOCKS, TFCHomestead.MOD_ID);

    public static final Map<AgedAlcohol, RegistryObject<LiquidBlock>> AGED_ALCOHOL = Helpers.mapOfKeys(AgedAlcohol.class, fluid ->
        register("fluid/" + fluid.getId(), () -> new LiquidBlock(HomesteadFluid.AGED_ALCOHOL.get(fluid).source(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()))
    );

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier)
    {
        return register(name, blockSupplier, (Function<T, ? extends BlockItem>) null);
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockSupplier, @Nullable Function<T, ? extends BlockItem> blockItemFactory)
    {
        return RegistrationHelpers.registerBlock(BLOCKS, HomesteadItems.ITEMS, name, blockSupplier, blockItemFactory);
    }
}
