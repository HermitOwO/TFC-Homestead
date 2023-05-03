package com.lumintorious.tfchomestead.common.item;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;
import com.lumintorious.tfchomestead.TFCHomestead;
import com.lumintorious.tfchomestead.common.drinks.AgedAlcohol;
import com.lumintorious.tfchomestead.common.drinks.HomesteadFluid;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.dries007.tfc.util.Helpers;

import static net.dries007.tfc.common.TFCItemGroup.*;

public class HomesteadItems {
    public static DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, TFCHomestead.MOD_ID);

    public static final Map<AgedAlcohol, RegistryObject<BucketItem>> FLUID_BUCKETS = Helpers.mapOfKeys(AgedAlcohol.class, fluid ->
        register("bucket/aged_" + fluid.name(), () -> new BucketItem(HomesteadFluid.AGED_ALCOHOL.get(fluid).source(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(MISC)))
    );

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item)
    {
        return ITEMS.register(name.toLowerCase(Locale.ROOT), item);
    }
}