package nullie.core.common.ores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import nullie.core.common.Nulltabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BasicOres extends Block
{  
	public BasicOres() {
		super(Material.rock);
		this.setCreativeTab(Nulltabs.tabOre);
	}

}
