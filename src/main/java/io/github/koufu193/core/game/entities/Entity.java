package io.github.koufu193.core.game.entities;

import io.github.koufu193.core.game.data.Location;
import io.github.koufu193.core.game.entities.interfaces.IEntity;
import io.github.koufu193.core.game.world.World;
import io.github.koufu193.server.MinecraftServer;
import io.github.koufu193.util.NBTUtils;
import org.jetbrains.annotations.NotNull;
import org.jglrxavpok.hephaistos.nbt.NBT;
import org.jglrxavpok.hephaistos.nbt.NBTByte;
import org.jglrxavpok.hephaistos.nbt.mutable.MutableNBTCompound;

import java.util.Objects;
import java.util.UUID;

public class Entity implements IEntity {
    protected final MinecraftServer server;
    protected final int entityId;
    protected final UUID uniqueId;
    protected final MutableNBTCompound nbt;
    protected String customName;
    protected boolean customNameVisitable;
    protected Location location;
    protected boolean onGround;

    public Entity(MinecraftServer server, int entityId, MutableNBTCompound nbt, World world) {
        this.server = server;
        this.entityId = entityId;
        this.customName = nbt.getString("CustomName");
        this.nbt = nbt;
        this.customNameVisitable = Objects.requireNonNullElse(nbt.getBoolean("CustomNameVisible"), false);
        this.uniqueId = NBTUtils.convertIntsNBTToUUID(Objects.requireNonNull(nbt.getIntArray("UUID")));
        Location rotation = NBTUtils.convertFloatRotationToLocation(Objects.requireNonNull(nbt.getList("Rotation")));
        this.location = NBTUtils.convertDoublePositionToLocation(Objects.requireNonNull(nbt.getList("Pos")))
                .yaw(rotation.yaw()).pitch(rotation.pitch())
                .world(world);
        this.onGround=((NBTByte)nbt.getOrPut("OnGround",()->NBT.Boolean(false))).asBoolean();
    }

    @Override
    public int entityId() {
        return this.entityId;
    }

    @Override
    public UUID uniqueId() {
        return this.uniqueId;
    }


    @Override
    public String customName() {
        return this.customName;
    }

    public void customName(String customName) {
        this.customName = customName;
        if (customName == null || "".equals(customName)) {
            this.nbt.remove("CustomName");
        } else this.nbt.setString("CustomName", this.customName);
    }

    @Override
    public void customNameVisitable(boolean visitable) {
        this.customNameVisitable = visitable;
        this.nbt.set("CustomNameVisible", NBT.Boolean(visitable));
    }
    @Override
    public boolean onGround() {
        return this.onGround;
    }

    @Override
    public MutableNBTCompound createMutableNBT() {
        return this.nbt;
    }

    @Override
    public Location location() {
        return this.location.clone();
    }

    @Override
    public boolean customNameVisitable() {
        return this.customNameVisitable;
    }

    @Override
    public void teleport(@NotNull Location location) {
    }
    public void onGround(boolean onGround){
        this.onGround=onGround;
    }
    public void location(@NotNull Location location,boolean onGround){
        this.location=location;
        this.onGround=onGround;
    }
    public void location(@NotNull Location location){
        this.location(location,this.onGround);
    }
}
