package nullie.core.common.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import nullie.core.common.tools.AdvancedTool;
import net.minecraft.block.material.Material;

public class AdvancedToolQuarterstaff extends AdvancedTool{

    private static final Set EFFECTIVE_ON = Sets.newHashSet(new Material[] {});

	protected AdvancedToolQuarterstaff(AdvancedToolMaterial materiala, AdvancedToolMaterial materialb) 
	{
		super(1.0F, 0.0F, 3, 1, materiala, materialb, EFFECTIVE_ON);
	}

}
