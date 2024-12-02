package com.dragn0007.dragnpets;

import com.dragn0007.dragnpets.entities.EntityTypes;
import com.dragn0007.dragnpets.gui.POMenuTypes;
import com.dragn0007.dragnpets.items.POItemGroupModifier;
import com.dragn0007.dragnpets.items.POItems;
import com.dragn0007.dragnpets.spawn.CreatureSpawnGeneration;
import com.dragn0007.dragnpets.util.PetsOverhaulCommonConfig;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.GeckoLib;


@Mod(PetsOverhaul.MODID)
public class PetsOverhaul
{
    public static final String MODID = "dragnpets";

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
    public static final RegistryObject<Codec<CreatureSpawnGeneration>> SPAWN_CODEC = BIOME_MODIFIER_SERIALIZERS.register("spawn_biome_modifier",
            () -> RecordCodecBuilder.create(builder ->
                    builder.group(Biome.LIST_CODEC.fieldOf("biomes").forGetter(CreatureSpawnGeneration::biomes)).apply(builder, CreatureSpawnGeneration::new)));

    public PetsOverhaul()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        POItems.register(eventBus);
        EntityTypes.ENTITY_TYPES.register(eventBus);
        POMenuTypes.register(eventBus);
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);

        GeckoLib.initialize();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, LivestockOverhaulClientConfig.SPEC, "livestock-overhaul-pets-client.toml");
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