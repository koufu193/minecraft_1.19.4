package io.github.koufu193.network.packets.play;

import io.github.koufu193.network.PacketFormat;
import io.github.koufu193.network.data.DataTypes;
import io.github.koufu193.network.packets.AbstractPacket;
import org.jetbrains.annotations.NotNull;

public class ClientboundWorldTimePacket extends AbstractPacket {
    ClientboundWorldTimePacket(){}
    public ClientboundWorldTimePacket(long age,long time){
        fields(age,time);
    }
    @NotNull
    @Override
    public PacketFormat format() {
        return PacketFormat.of(DataTypes.Long,DataTypes.Long);
    }

    @Override
    public int packetId() {
        return 0x5e;
    }
}
