package io.github.koufu193.core.game.world.chunk.block.converters;

import io.github.koufu193.core.game.data.Identifier;
import io.github.koufu193.core.game.data.block.BlockMeta;
import io.github.koufu193.core.game.data.Material;
import org.jglrxavpok.hephaistos.mca.BlockState;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.HashMap;

public class DefaultBlockMetaConverter implements IBlockMetaConverter<BlockMeta> {
    private final HashMap<Identifier,BlockState> cache=new HashMap<>();

    @Override
    public BlockMeta convert(BlockState state) {
        Material material=Material.fromId(state.component1());
        return new BlockMeta() {
            @Override
            public Material material() {
                return material;
            }

            @Override
            public NBTCompound toCompound() {
                return null;
            }
        };
    }

    @Override
    public BlockState convert(BlockMeta block) {
        return new BlockState(block.material().id().toString());
    }
}
