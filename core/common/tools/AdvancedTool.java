package nullie.core.common.tools;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Set;

public class AdvancedTool extends Item
{
	private Set effectiveMaterials;
	protected float efficiencyOnProperMaterial = 4.0F;
	private float gashDamage = 0.0F;
	private float bashDamage = 1.0F;
	protected AdvancedToolMaterial toolMaterialA;
	protected AdvancedToolMaterial toolMaterialB;
	private int toolParts = 3;
	private int totalParts = 5;
	
	protected AdvancedTool(float bash, float gash, int parta, int partb, AdvancedToolMaterial materiala, AdvancedToolMaterial materialb, Set effectiveMaterials)
	{
		this.toolMaterialA = materiala;
		this.toolMaterialB = materialb;
		this.toolParts = parta;
		this.totalParts = partb;
		this.effectiveMaterials = effectiveMaterials;
		this.maxStackSize = 1;
		this.setMaxDamage(16*totalParts); // Tools should be able to take 16 damage for each part used in the recipe.
		this.efficiencyOnProperMaterial = materiala.getEfficiencyOnProperMaterial();
		this.gashDamage = gash;
		this.bashDamage = bash;
		this.setCreativeTab(CreativeTabs.tabTools);
	}
	
	public float getStrVsBlock(ItemStack stack, Block block)
    {
        return this.effectiveMaterials.contains(block.getMaterial()) ? this.efficiencyOnProperMaterial : 1.0F;
    }
	
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
		float averageHardness;
		averageHardness = ((toolMaterialA.getToolHardness()*toolParts)+(toolMaterialB.getToolHardness()*(totalParts-toolParts)))/totalParts;
		stack.damageItem(Math.round(5/averageHardness), attacker);
        return true;
    }
	
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
    {
        if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
        {
        	float averageHardness;
        	averageHardness = ((toolMaterialA.getToolHardness()*toolParts)+(toolMaterialB.getToolHardness()*(totalParts-toolParts)))/totalParts;
            stack.damageItem(1*Math.round((blockIn.getBlockHardness(worldIn, pos)/averageHardness)), playerIn);
        }

        return true;
    }
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }
	
	public AdvancedToolMaterial getToolMaterialA()
    {
        return this.toolMaterialA;
    }
	
	public AdvancedToolMaterial getToolMaterialB()
	{
		return this.toolMaterialB;
	}
	
	public int getToolParts()
	{
		return this.toolParts;
	}
	
	public int getTotalParts()
	{
		return this.totalParts;
	}
	
    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
    	int averageEnchantability;
    	averageEnchantability = ((toolMaterialA.getEnchantability()*toolParts)+(toolMaterialB.getEnchantability()*(totalParts-toolParts)))/totalParts;
        return averageEnchantability;
    }

    /**
     * Return the name for this tool's material.
     */
    public String getToolMaterialAName()
    {
        return this.toolMaterialA.toString();
    }
	
    public String getToolMaterialBName()
    {
        return this.toolMaterialB.toString();
    }
    
    /**
     * Return whether this item is repairable in an anvil.
     *  
     * @param toRepair The ItemStack to be repaired
     * @param repair The ItemStack that should repair this Item (leather for leather armor, etc.)
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.toolMaterialA.getRepairItemStack();
        if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }
      
	public static enum AdvancedToolMaterial
	{
		// Basics and Non-Metals
		WOOD(3.0F, 1.0F, 2), // Enchantable Weak Early Common Material
        STONE(4.0F, 1.5F, 1), // Weak Early Common Material
        QUARTZ(3.5F, 2.0F, 4), // Enchantable Early Common Material
        DIAMOND(10.0F, 4.0F, 4), // Enchantable and Extremely Durable
        EMERALD(8.0F, 4.0F, 6), // Extremely Enchantable and Durable
        OBSIDIAN(9.0F, 5.0F, 3), // Strongest and Most Efficient Common Material

        // Metals
        COPPER(6.0F, 4.5F, 2), // Balanced Basic Metal
        TIN(5.0F, 2.5F, 1), // Weakest Basic Metal
        ZINC(5.5F, 2.0F, 1), // Weaker Basic Metal
        IRON(6.5F, 4.0F, 1), // Basic Metal
        SILVER(4.0F, 6.0F, 4), // Secondmost Efficient Metal, Quite Enchantable
        GOLD(3.0F, 7.0F, 5), // Most Efficient Metal, Very Enchantable
        LEAD(5.5F, 4.0F, 1), // Weak Basic Metal
        TITANIUM(6.5F, 5.0F, 1), // Best Basic Mundane Metal
        
        // Alloys
        BRONZE(6.0F, 6.0F, 1), // Efficient Basic Alloy
        BRASS(6.5F, 5.5F, 1), // Tough Basic Alloy
        ELECTRUM(4.5F, 8.0F, 5), // Basic Magical Alloy
        REDALLOY(5.5F, 4.0F, 1), // Magical Iron
        REDSTEEL(7.5F, 8.0F, 3), // Strong Magical Steel
        SANGUINITE(4.5F, 9.0F, 8), // Efficient Magical Material
        CRYSTAL(6.5F, 6.0F, 8), // Durable Magical Material 
        DURANIUM(7.0F, 7.0F, 1), // Best Basic Mundane Alloy
        STEEL(7.5F, 8.0F, 2), // Advanced Alloy, Most Balanced.
        MOONSTEEL(5.5F, 8.0F, 4), // Advanced Magical Alloy
        DURASTEEL(8.0F, 8.0F, 1); // Strongest Mundane Material
        
        /** This affects how much damage the tool will take from uses, and is effectively its health. */
        private final float hardness;
		/** This affects how quickly the tool will mine, and is independent of health. A tool with low hardness but high efficiency will mine fast, but die quickly. */
		private final float efficiencyOnProperMaterial;
        /** This affects magic on the tools material. Magic-Themed materials will have more of this, Modern-Themed materials will have less of this. */
		private final int enchantability;
		/** The material used to repair it. */
        private ItemStack repairMaterial = null;
        
        private AdvancedToolMaterial(float hardness, float efficiency, int enchantability)
        {
            this.hardness = hardness;
            this.efficiencyOnProperMaterial = efficiency;
            this.enchantability = enchantability;
        }
        
        public float getEfficiencyOnProperMaterial()
        {
            return this.efficiencyOnProperMaterial;
        }
        
        public float getToolHardness()
        {
        	return this.hardness;
        }
        
        public int getEnchantability()
        {
            return this.enchantability;
        }
        
        public ItemStack getRepairItemStack()
        {
            	return this.repairMaterial;
        }

        public void setRepairItem(ItemStack stack)
        {
            this.repairMaterial = stack;
            return;
        }      
	}
	
	/*===================================== FORGE START =================================*/
    @Override
    public float getDigSpeed(ItemStack stack, net.minecraft.block.state.IBlockState state)
    {
        for (String type : getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state))
                return efficiencyOnProperMaterial;
        }
        return super.getDigSpeed(stack, state);
    }
    /*===================================== FORGE END =================================*/
    
}
