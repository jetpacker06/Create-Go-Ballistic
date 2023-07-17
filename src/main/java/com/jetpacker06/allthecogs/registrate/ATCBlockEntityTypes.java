package com.jetpacker06.allthecogs.registrate;

import com.jetpacker06.allthecogs.content.block.cogwheel.CustomCogwheelBlock;
import com.jetpacker06.allthecogs.content.block.cogwheel.CustomCogwheelBlockEntity;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;

import java.util.ArrayList;

public class ATCBlockEntityTypes {

    public static BlockEntityEntry<CustomCogwheelBlockEntity> CUSTOM_COGWHEEL;

    public static ArrayList<BlockEntry<? extends CustomCogwheelBlock>> customCogwheels = new ArrayList<>();

    public static void registerBlockEntities(CreateRegistrate REGISTRATE) {
        var customCogwheel = REGISTRATE.blockEntity("custom_cogwheel", CustomCogwheelBlockEntity::new)
                .instance(() -> SingleRotatingInstance::new);
        for (BlockEntry<? extends CustomCogwheelBlock> block : customCogwheels) customCogwheel.validBlock(block);
        CUSTOM_COGWHEEL = customCogwheel.register();
    }
}
