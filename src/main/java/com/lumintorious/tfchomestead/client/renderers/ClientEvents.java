package com.lumintorious.tfchomestead.client.renderers;

import com.lumintorious.tfchomestead.common.drinks.HomesteadFluid;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;


public class ClientEvents {
    public static void init() {
        MinecraftForge.EVENT_BUS.addListener(ClientEvents::addTooltipsToItem);
    }

    public static void addTooltipsToItem(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY).ifPresent(cap -> {
            FluidStack fluidStack = cap.getFluidInTank(0);
            if(!fluidStack.isEmpty()) {
                event.getToolTip().add(new TranslatableComponent(fluidStack.getTranslationKey()).withStyle(ChatFormatting.GRAY));
                var fluid = HomesteadFluid.AGED_ALCOHOL.inverse().keySet().stream().filter(f ->
                    f.getSource() == fluidStack.getFluid()
                ).findAny();
                fluid.ifPresent(f -> {
                    event.getToolTip().add(HomesteadFluid.AGED_ALCOHOL.inverse().get(f).getTooltip());
                });
            }
        });
    }
}
