
package net.mcreator.tridentcraftingstructuresfabric.world.gen.feature;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.World;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.Heightmap;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Identifier;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.BlockMirror;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.Structure;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import java.util.Random;

public class TridentwardensstructureStructure {
	private static final Feature<DefaultFeatureConfig> feature = new Feature<DefaultFeatureConfig>(DefaultFeatureConfig.CODEC) {
		@Override
		public boolean generate(StructureWorldAccess worldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos pos,
				DefaultFeatureConfig config) {
			ServerWorld world = worldAccess.toServerWorld();
			int ci = (pos.getX() >> 4) << 4;
			int ck = (pos.getZ() >> 4) << 4;

			RegistryKey<World> dimensionType = world.getRegistryKey();
			boolean dimensionCriteria = false;
			if (dimensionType == World.OVERWORLD)
				dimensionCriteria = true;

			if (!dimensionCriteria)
				return false;
			if ((random.nextInt(1000000) + 1) <= 1000) {
				int count = random.nextInt(1) + 1;
				for (int a = 0; a < count; a++) {
					int i = ci + random.nextInt(16);
					int k = ck + random.nextInt(16);
					int j = worldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, i, k);

					j -= 1;

					BlockState blockAt = world.getBlockState(new BlockPos(i, j, k));
					boolean blockCriteria = false;
					if (blockAt.getBlock() == Blocks.SAND)
						blockCriteria = true;
					if (blockAt.getBlock() == Blocks.GRAVEL)
						blockCriteria = true;
					if (blockAt.getBlock() == Blocks.WATER)
						blockCriteria = true;
					if (!blockCriteria)
						continue;

					BlockPos spawnTo = new BlockPos(i + 0, j + 0, k + 0);

					int x = spawnTo.getX();
					int y = spawnTo.getY();
					int z = spawnTo.getZ();

					Structure structure = world.getStructureManager()
							.getStructureOrBlank(new Identifier("tridentcraftingstructuresfabric", "new_trident_wardens_structure_fabric"));
					if (structure == null)
						return false;

					BlockRotation rotation = BlockRotation.values()[random.nextInt(3)];
					BlockMirror mirror = BlockMirror.values()[random.nextInt(2)];
					structure.place(worldAccess, spawnTo, new StructurePlacementData().setRotation(rotation).setMirror(mirror).setRandom(random)
							.addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS).setChunkPosition(null).setIgnoreEntities(false),
							random);

				}
			}
			return true;
		}
	};
	private static final ConfiguredFeature<?, ?> configFeature = feature.configure(DefaultFeatureConfig.DEFAULT)
			.decorate(Decorator.NOPE.configure(DecoratorConfig.DEFAULT));

	public static void init() {
		Registry.register(Registry.FEATURE, new Identifier("tridentcraftingstructuresfabric", "tridentwardensstructure"), feature);
		RegistryKey<ConfiguredFeature<?, ?>> configFeatKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
				new Identifier("tridentcraftingstructuresfabric", "tridentwardensstructure"));
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, configFeatKey.getValue(), configFeature);

		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN,
				BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_WARM_OCEAN, BiomeKeys.FROZEN_OCEAN, BiomeKeys.LUKEWARM_OCEAN,
				BiomeKeys.OCEAN, BiomeKeys.WARM_OCEAN), GenerationStep.Feature.SURFACE_STRUCTURES, configFeatKey);
	}
}
