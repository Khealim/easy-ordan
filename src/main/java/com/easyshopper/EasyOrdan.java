package com.easyshopper;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ClientTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.SessionOpen;
import net.runelite.client.config.ConfigManager;

import java.util.Objects;

@Slf4j
@PluginDescriptor(
	name = "Easy Shopper",
	description = "Bypass shift button while in shop interface to buy items",
	tags = {"swap","swapper","menu","entry","menu entry swapper","shop","blast furnace","left click"}
)
public class EasyOrdan extends Plugin
{
	int bf = 7757; //bf
	@Inject
	private Client client;

	@Inject
	private EasyOrdanConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Easy shopper started");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Easy shopper ended");
	}

	@Subscribe
	public void onClientTick(ClientTick event)
	{
		if (client.getGameState() != GameState.LOGGED_IN || client.isMenuOpen() || client.isKeyPressed(KeyCode.KC_SHIFT))
		{
			return;
		}

		boolean atBF = false;

		WorldPoint playerLoc = Objects.requireNonNull(client.getLocalPlayer()).getWorldLocation();

		if (bf == playerLoc.getRegionID()) {
			atBF = true;
		}

		if (atBF) {
			MenuEntry[] menuEntries = client.getMenuEntries();
			int emptyIdx = -1;
			int topIdx = menuEntries.length - 1;
			for (int i = 0; i < topIdx; i++) {

				if (Text.removeTags(menuEntries[i].getOption()).equals(config.buyType())) {
					emptyIdx = i;
					break;
				}
			}
			if (emptyIdx == -1) {
				return;
			}

			MenuEntry entry1 = menuEntries[emptyIdx];
			MenuEntry entry2 = menuEntries[topIdx];

			menuEntries[emptyIdx] = entry2;
			menuEntries[topIdx] = entry1;

			client.setMenuEntries(menuEntries);
		}

	}

	@Provides
	EasyOrdanConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(EasyOrdanConfig.class);
	}
}
