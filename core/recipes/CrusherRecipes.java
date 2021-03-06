package nullie.core.common.recipes;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class CrusherRecipes {
	private static final CrusherRecipes crushingBase = new CrusherRecipes();
	private Map crushingList = Maps.newHashMap();
	private Map experienceList = Maps.newHashMap();
	
	/**
     * Returns an instance of FurnaceRecipes.
     */
    public static CrusherRecipes instance()
    {
        return crushingBase;
    }
    
    private CrusherRecipes()
    {
        this.addCrushingRecipe();
    }
    
    public void addCrushingRecipe(ItemStack input, ItemStack stack, float experience)
    {
        this.crushingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }
    
    public java.util.List<ItemStack> getCrushingResult(ItemStack stack)
    {
        Iterator iterator = this.crushingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.compareItemStacks(stack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }
    
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
    
    public Map getCrushingList()
    {
        return this.crushingList;
    }
    
    public float getCrushingExperience(ItemStack stack)
    {
        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.compareItemStacks(stack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
    
}
