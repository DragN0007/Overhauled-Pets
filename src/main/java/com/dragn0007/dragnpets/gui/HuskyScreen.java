package com.dragn0007.dragnpets.gui;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.dog.husky.Husky;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class HuskyScreen extends AbstractContainerScreen<HuskyMenu> {
    public static final ResourceLocation HUSKY_INVENTORY_LOCATION = new ResourceLocation(PetsOverhaul.MODID, "textures/gui/husky.png");
    public final Husky husky;

    public HuskyScreen(HuskyMenu huskyMenu, Inventory inventory, Component component) {
        super(huskyMenu, inventory, component);
        this.husky = huskyMenu.husky;
    }

    public void renderBg(GuiGraphics graphics, float p_282998_, int p_282929_, int p_283133_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, HUSKY_INVENTORY_LOCATION);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(HUSKY_INVENTORY_LOCATION, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (this.husky.isChested()) {
            graphics.blit(HUSKY_INVENTORY_LOCATION, x + 57, y + 17, 0, this.imageHeight, 145, 54);
        }
    }

    @Override
    public void render(GuiGraphics p_281697_, int p_282103_, int p_283529_, float p_283079_) {
        this.renderBackground(p_281697_);
        super.render(p_281697_, p_282103_, p_283529_, p_283079_);
        this.renderTooltip(p_281697_, p_282103_, p_283529_);
    }
}

