package nullie.core.common.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import nullie.core.common.tools.AdvancedTool;
import net.minecraft.block.material.Material;

public class AdvancedToolScythe extends AdvancedTool{

    private static final Set EFFECTIVE_ON = Sets.newHashSet(new Material[] {Material.web, Material.leaves, Material.plants, Material.vine, Material.cloth, Material.carpet, Material.cactus});

	protected AdvancedToolScythe(AdvancedToolMaterial materiala, AdvancedToolMaterial materialb) 
	{
		super(0.0F, 1.0F, 4, 6, materiala, materialb, EFFECTIVE_ON);
	}

}
