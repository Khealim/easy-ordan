package com.easyshopper;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class easyordantest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EasyOrdan.class);
		RuneLite.main(args);
	}
}