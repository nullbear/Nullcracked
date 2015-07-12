package nullie.core.common.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import nullie.core.common.tools.AdvancedTool;
import net.minecraft.block.material.Material;

public class AdvancedToolShovel extends AdvancedTool{

    private static final Set EFFECTIVE_ON = Sets.newHashSet(new Material[] {Material.grass, Material.ground, Material.craftedSnow, Material.clay, Material.snow, Material.sand});

	protected AdvancedToolShovel(AdvancedToolMaterial materiala, AdvancedToolMaterial materialb) 
	{
		super(1.5F, 0.5F, 1, 3, materiala, materialb, EFFECTIVE_ON);
	}

}
