package nullie.core.common.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import nullie.core.common.tools.AdvancedTool;
import net.minecraft.block.material.Material;

public class AdvancedToolPickaxe extends AdvancedTool{

    private static final Set EFFECTIVE_ON = Sets.newHashSet(new Material[] {Material.coral, Material.packedIce, Material.ice, Material.rock, Material.iron, Material.glass});

	protected AdvancedToolPickaxe(AdvancedToolMaterial materiala, AdvancedToolMaterial materialb) 
	{
		super(0.5F, 0.5F, 3, 5, materiala, materialb, EFFECTIVE_ON);
	}

}
