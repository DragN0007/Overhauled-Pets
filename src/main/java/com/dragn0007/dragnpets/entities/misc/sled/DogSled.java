package com.dragn0007.dragnpets.entities.misc.sled;

import com.dragn0007.dragnpets.PetsOverhaul;
import com.dragn0007.dragnpets.entities.dog.Husky;
import com.dragn0007.dragnpets.entities.dog.HuskyCollarLayer;
import com.dragn0007.dragnpets.entities.dog.ODog;
import com.dragn0007.dragnpets.items.POItems;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DogSled extends Entity implements ContainerListener {

    public static final EntityDataAccessor<Float> HEALTH = SynchedEntityData.defineId(DogSled.class, EntityDataSerializers.FLOAT);
    public SimpleContainer inventory;
    public LazyOptional<?> itemHandler;
    private int lerpSteps;
    private float targetRotation = 0;
    private float currentRotation = 0;
    public int forwardMotion = 1;

    public int driveTick = 0;
    public float lastDrivePartialTick = 0;
    public Vec3 lastClientPos = Vec3.ZERO;
    private double targetX;
    private double targetY;
    private double targetZ;
    private float targetYRot;

    public static final float FRICTION = 0.7f;
    public static final float GRAVITY = 0.08f;

    public DogSled(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.createInventory();
        this.noCulling = true;
    }

    public static final float MAX_HEALTH = 16f;
    public static final int MAX_DOGS = 6;
    int DOGS_HITCHED = 0;

    public void createInventory() {
        this.inventory = new SimpleContainer(36);
        this.inventory.addListener(this);
        this.itemHandler = LazyOptional.of(() -> new InvWrapper(this.inventory));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(this.isAlive() && cap == ForgeCapabilities.ITEM_HANDLER && this.itemHandler != null && this.isAlive()) {
            return itemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if(this.itemHandler != null) {
            LazyOptional<?> oldHandler = this.itemHandler;
            this.itemHandler = null;
            oldHandler.invalidate();
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    public Vec3 calcOffset(double x, double y, double z) {
        double rad = this.getYRot() * Math.PI / 180;

        double xOffset = this.position().x + (x * Math.cos(rad) - z * Math.sin(rad));
        double yOffset = this.position().y + y;
        double zOffset = this.position().z + (x * Math.sin(rad) + z * Math.cos(rad));

        return new Vec3(xOffset, yOffset, zOffset);
    }

    @Override
    protected boolean canAddPassenger(Entity entity) {
        if (this.DOGS_HITCHED >= 4) {
            return this.getPassengers().size() < 2;
        } else {
            return this.getPassengers().size() < 1;
        }
    }

    @Override
    public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
        int i = this.getPassengers().indexOf(entity);
        switch (i) {
            case 0:
                entity.setPos(this.calcOffset(0.0, 0.0, -1.5));
                break;
            case 1:
                entity.setPos(this.calcOffset(0.5, -0.5, 0));
                break;
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.lastClientPos = this.position();

        if(this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;

            Vec3 velocity = this.getDeltaMovement();
            double dx = velocity.x * FRICTION;
            if(Math.abs(dx) < 0.001) dx = 0;

            double dz = velocity.z * FRICTION;
            if(Math.abs(dz) < 0.001) dz = 0;

            double dy = velocity.y + (this.onGround() ? 0 : -GRAVITY);

            this.setDeltaMovement(dx, dy, dz);

            if(this.lastClientPos.x != this.position().x || this.lastClientPos.y != this.position().y || this.lastClientPos.z != this.position().z) {
                this.syncPacketPositionCodec(this.position().x, this.position().y, this.position().z);
            }
        } else {
            this.setDeltaMovement(0, 0, 0);
        }

        if (this.lerpSteps > 0) {
            double x = this.getX() + (this.targetX - this.getX()) / this.lerpSteps;
            double y = this.getY() + (this.targetY - this.getY()) / this.lerpSteps;
            double z = this.getZ() + (this.targetZ - this.getZ()) / this.lerpSteps;

            float yRot = this.getYRot() + (this.targetYRot - this.getYRot()) / this.lerpSteps;

            this.setPos(x, y, z);
            this.setYRot(yRot);
            this.lerpSteps--;
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float damage) {
        if(!this.level().isClientSide && !this.isRemoved()) {
            this.markHurt();
            this.gameEvent(GameEvent.ENTITY_DAMAGE);
            float health = this.entityData.get(HEALTH) - damage;
            this.entityData.set(HEALTH, health);

            if(health < 0) {
                Containers.dropContents(this.level(), this, this.inventory);
                this.spawnAtLocation(POItems.DOG_SLED.get());
                this.kill();
            }
        }
        return true;
    }

    @Override
    public float getStepHeight() {
        return 1;
    }

    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) this.getFirstPassenger();
    }

    public Husky husky;

	public boolean isHitched() {
		return this.husky != null;
	}

	public void hitchToHusky(Husky husky) {
		this.husky = husky;
		this.DOGS_HITCHED++;
	}

	public void unHitch() {
		this.husky = null;
		this.DOGS_HITCHED--;
	}

    @Override
    @NotNull
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if(husky.isTame() && husky.isOwnedBy(player) && !husky.isOrderedToSit()) {

        }

        if(player.isShiftKeyDown()) {
            if(!this.level().isClientSide) {
                NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider((containerId, inventory, serverPlayer) -> {
                    return new ChestMenu(MenuType.GENERIC_9x4, containerId, inventory, this.inventory, 4);
                }, this.getDisplayName()));
            }
        } else if(!this.level().isClientSide && this.canAddPassenger(player)){
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        }
        return super.interact(player, hand);
    }

    public ResourceLocation getTextureLocation() {
        return new ResourceLocation(PetsOverhaul.MODID, "textures/entity/dog_sled.png");
    }

    @Override
    protected void defineSynchedData () {
        this.entityData.define(HEALTH, MAX_HEALTH);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.entityData.set(HEALTH, compoundTag.getFloat("Health"));

        this.createInventory();
        ListTag listTag = compoundTag.getList("Items", Tag.TAG_COMPOUND);
        for(int i = 0; i < listTag.size(); i++) {
            CompoundTag tag = listTag.getCompound(i);
            int j = tag.getByte("Slot") & 255;
            if(j < this.inventory.getContainerSize()) {
                this.inventory.setItem(j, ItemStack.of(tag));
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putFloat("Health", this.entityData.get(HEALTH));

        ListTag listTag = new ListTag();
        for(int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack itemStack = this.inventory.getItem(i);
            if(!itemStack.isEmpty()) {
                CompoundTag tag = new CompoundTag();
                tag.putByte("Slot", (byte)i);
                itemStack.save(tag);
                listTag.add(tag);
            }
        }
        compoundTag.put("Items", listTag);
    }


    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    public void containerChanged(Container container) {

    }
}
