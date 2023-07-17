package com.jetpacker06.allthecogs.registrate;

import com.jetpacker06.allthecogs.content.block.cogwheel.CogwheelType;
import com.jetpacker06.allthecogs.content.block.cogwheel.Props;

public class ATCCogwheelTypes {

    public static CogwheelType MEAT;
    public static CogwheelType CHEESE;
    public static CogwheelType HAY;
    public static CogwheelType SLIME;
    public static CogwheelType DIRT;
    public static CogwheelType IRON;

    public static void registerCogwheelTypes() {;
        SLIME = new CogwheelType("slime", Props.slime);
        DIRT = new CogwheelType("dirt", new CogwheelType.CogProperties(Props.dirt).mineableShovel().stress(2));
        HAY = new CogwheelType("hay", new CogwheelType.CogProperties(Props.hay).mineableHoe().stressSmall(2).stressLarge(4));
    }
}
