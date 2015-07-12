package nullie.core.common.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import nullie.core.common.tools.AdvancedTool;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class AdvancedToolHammer extends AdvancedTool{

    private static final Set EFFECTIVE_ON = Sets.newHashSet(new Material[] {Material.coral, Material.packedIce, Material.ice, Material.rock, Material.iron, Material.glass});

	protected AdvancedToolHammer(AdvancedToolMaterial materiala, AdvancedToolMaterial materialb) 
	{
		super(1.0F, 0.0F, 3, 5, materiala, materialb, EFFECTIVE_ON);
	}
	
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
    {
		if (player.capabilities.isCreativeMode)
            return false;
		if (hammerCrush(itemstack, pos, player))
            return true;
		return false;
    }
	
	public boolean hammerCrush(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		World world = player.worldObj;
		Block block = world.getBlockState(pos).getBlock();
		
		if (block == null)
            return false;
		
		if (!EFFECTIVE_ON.contains(block.getMaterial()) && !(block.getMaterial().isToolNotRequired()))
			return false;
		
		Item item = Item.getItemFromBlock(block);
		
		if (item == null)
            return false;
		
		ItemStack result = FurnaceRecipes.smelting().getSmeltingResult(new ItemStack(block));
		
	}
}
