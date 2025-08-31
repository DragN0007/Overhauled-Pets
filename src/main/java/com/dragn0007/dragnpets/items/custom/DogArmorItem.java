package com.dragn0007.dragnpets.items.custom;

import com.dragn0007.dragnpets.PetsOverhaul;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class DogArmorItem extends Item {
   private final int protection;
   private final ResourceLocation texture;

   public DogArmorItem(int prot, String p_41365_, Properties p_41366_) {
      this(prot, new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog/armor/dog_armor_" + p_41365_ + ".png"), p_41366_);
   }

   public DogArmorItem(int prot, ResourceLocation p_41365_, Properties p_41366_) {
      super(p_41366_);
      this.protection = prot;
      this.texture = p_41365_;
   }

   public ResourceLocation getTexture() {
      return texture;
   }

   public int getProtection() {
      return this.protection;
   }
}
