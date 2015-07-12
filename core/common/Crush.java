package nullie.core.common;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Crushable
{
	private List<ItemStack> crushItems = new java.util.ArrayList<ItemStack>();
	
	private final void addCrushedItem(ItemStack item)
	{
		crushItems.add(item);
	}
	
	public final void dropItems(World worldIn, BlockPos pos, IBlockState state, int fortune, Block blockIn)
    {
		if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
        {
            java.util.List<ItemStack> items = getCrushDrops(worldIn, pos, state, fortune, blockIn);

            for (ItemStack item : items)
            {
                spawnAsEntity(worldIn, pos, item);
            }
        }
    }
	
	public List<ItemStack> getCrushDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune, Block blockIn)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        Random rand = world instanceof World ? ((World)world).rand : RANDOM;

        int count = quantityDropped(state, fortune, rand);
        for(int i = 0; i < count; i++)
        {
            Item item = this.getItemCrushed(state, rand, fortune, blockIn);
            if (item != null)
            {
                ret.add(new ItemStack(item, 1, this.damageDropped(state)));
            }
        }
        return ret;
    }

}
