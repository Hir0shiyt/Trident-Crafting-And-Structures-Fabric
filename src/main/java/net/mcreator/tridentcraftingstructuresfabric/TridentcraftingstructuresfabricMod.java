/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package net.mcreator.tridentcraftingstructuresfabric;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;

import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.TridentspherefortressStructure;
import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.TridentForgottenstructureStructure;
import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.SuperGiganticTridentStructure;
import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.Special200downloadsstructureStructure;
import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.MutatedTridentForgottenStructureStructure;
import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.MutatedSuperGiganticTridentStructure;
import net.mcreator.tridentcraftingstructuresfabric.world.gen.feature.MutatedSpecial15KStructureStructure;
import net.mcreator.tridentcraftingstructuresfabric.item.TridentstickItem;
import net.mcreator.tridentcraftingstructuresfabric.item.TridentTopItem;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.api.ModInitializer;

public class TridentcraftingstructuresfabricMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Item Tridentstick_ITEM = Registry.register(Registry.ITEM, id("tridentstick"), new TridentstickItem());
	public static final Item TridentTop_ITEM = Registry.register(Registry.ITEM, id("trident_top"), new TridentTopItem());
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing TridentcraftingstructuresfabricMod");
		TridentForgottenstructureStructure.init();
		SuperGiganticTridentStructure.init();
		Special200downloadsstructureStructure.init();
		TridentspherefortressStructure.init();
		MutatedTridentForgottenStructureStructure.init();
		MutatedSuperGiganticTridentStructure.init();
		MutatedSpecial15KStructureStructure.init();
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
		});
	}

	public static final Identifier id(String s) {
		return new Identifier("tridentcraftingstructuresfabric", s);
	}
}
