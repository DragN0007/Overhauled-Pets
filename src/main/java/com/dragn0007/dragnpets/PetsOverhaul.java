package com.dragn0007.dragnpets;

import com.dragn0007.dragnpets.entities.POEntityTypes;
import com.dragn0007.dragnpets.gui.POMenuTypes;
import com.dragn0007.dragnpets.items.POItemGroupModifier;
import com.dragn0007.dragnpets.items.POItems;
import com.dragn0007.dragnpets.util.PetsOverhaulClientConfig;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;


@Mod(PetsOverhaul.MODID)
public class PetsOverhaul
{
    public static final String MODID = "dragnpets";

    public PetsOverhaul()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        POItems.register(eventBus);
        POItemGroupModifier.register(eventBus);
        POEntityTypes.ENTITY_TYPES.register(eventBus);
        POMenuTypes.register(eventBus);

        GeckoLib.initialize();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, PetsOverhaulClientConfig.SPEC, "livestock-overhaul-pets-client.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, PetsOverhaulCommonConfig.SPEC, "livestock-overhaul-pets-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final EntityDataSerializer<ResourceLocation> RESOURCE_LOCATION = new EntityDataSerializer<>() {
        @Override
        public void write(FriendlyByteBuf buf, ResourceLocation resourceLocation) {
            buf.writeResourceLocation(resourceLocation);
        }

        @Override
        public ResourceLocation read(FriendlyByteBuf buf) {
            return buf.readResourceLocation();
        }

        @Override
        public ResourceLocation copy(ResourceLocation resourceLocation) {
            return resourceLocation;
        }
    };

    static {
        EntityDataSerializers.registerSerializer(RESOURCE_LOCATION);
    }
}