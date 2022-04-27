package com.easyshopper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("easyshopper")
public interface EasyOrdanConfig extends Config
{
    @ConfigItem(
            keyName = "Buy Type",
            name = "Buy option",
            description = "Switches between the buy options in Ordan's shop"
    )
    default EasyOrdanOverride buyType()
    {
        return EasyOrdanOverride.OFF;
    }
}
