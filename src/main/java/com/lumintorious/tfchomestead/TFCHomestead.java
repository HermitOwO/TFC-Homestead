package com.lumintorious.tfchomestead;

import com.lumintorious.tfchomestead.client.renderers.ClientEvents;
import com.lumintorious.tfchomestead.common.block.HomesteadBlocks;
import com.lumintorious.tfchomestead.common.drinks.HomesteadFluid;
import com.lumintorious.tfchomestead.common.item.HomesteadItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("tfchomestead")
public class TFCHomestead
{
    public static final String MOD_ID = "tfchomestead";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TFCHomestead()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        if(FMLEnvironment.dist == Dist.CLIENT) {
            ClientEvents.init();
        }

        MinecraftForge.EVENT_BUS.addListener(this::onDrink);

        HomesteadItems.ITEMS.register(eventBus);
        HomesteadFluid.FLUIDS.register(eventBus);
        HomesteadBlocks.BLOCKS.register(eventBus);
    }

    public void onDrink(LivingEntityUseItemEvent.Finish event) {
        if(event.getEntity() instanceof Player player) {
            ItemStack stack = event.getItem();
            stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(cap -> {
                HomesteadFluid.getAlcohol(cap).ifPresent(alcohol -> {
                    player.addEffect(alcohol.getEffectInstance());
                });
            });
        }
    }
}
