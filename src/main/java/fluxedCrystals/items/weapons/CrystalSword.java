package fluxedCrystals.items.weapons;

import cofh.lib.util.helpers.MathHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import fluxedCrystals.FluxedCrystals;
import fluxedCrystals.reference.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class CrystalSword extends ItemSword
{

	public CrystalSword()
	{

		super(Material.Tools.crystal);

		this.setCreativeTab(FluxedCrystals.tab);

		setUnlocalizedName(Reference.LOWERCASE_MOD_ID + "." + Names.Items.CRYSTAL_SWORD);
		setTextureName(Reference.LOWERCASE_MOD_ID + ":" + Names.Items.CRYSTAL_SWORD);

	}

	private float getDamage(EntityLivingBase player)
	{

		float fallingMult = (player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater()
				&& !player.isPotionActive(Potion.blindness) && player.ridingEntity == null) ? 1.5F : 1.0F;

		float potionDamage = 1.0f;

		if (player.isPotionActive(Potion.damageBoost))
		{

			potionDamage += player.getActivePotionEffect(Potion.damageBoost).getAmplifier() * 1.3f;

		}

		return 8 * fallingMult * potionDamage;

	}

	@Override
	public boolean hitEntity(ItemStack itemStack, EntityLivingBase entity, EntityLivingBase player)
	{

		EntityPlayer entityPlayer = (EntityPlayer)player;

		itemStack.damageItem(1, player);

		entity.addPotionEffect(new PotionEffect(Potion.wither.getId(), 140, 10));

		entity.attackEntityFrom(DamageSource.causePlayerDamage(entityPlayer), getDamage(player));

		return true;

	}

	@Override
	public IIcon getIconIndex(ItemStack stack)
	{
		return getIcon(stack, 0);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass)
	{

		// TODO add second state icon here

		return this.itemIcon;

	}

	@Override
	public void registerIcons(IIconRegister iIconRegister)
	{

		super.registerIcons(iIconRegister);

	}

	@Override
	public Multimap getItemAttributeModifiers()
	{

		return HashMultimap.create();

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{

		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));

		return stack;

	}

	protected void pushEntityAway(Entity entity, Entity player)
	{

		double d0 = player.posX - entity.posX;
		double d1 = player.posZ - entity.posZ;
		double d2 = MathHelper.maxAbs(d0, d1);

		if (d2 >= 0.01D)
		{

			d2 = Math.sqrt(d2);
			d0 /= d2;
			d1 /= d2;

			double d3 = 1.0D / d2;

			if (d3 > 1.0D)
			{

				d3 = 1.0D;

			}

			d0 *= d3;
			d1 *= d3;
			d0 *= 0.2D;
			d1 *= 0.2D;
			d0 *= 1.0F - entity.entityCollisionReduction;
			d1 *= 1.0F - entity.entityCollisionReduction;

			entity.addVelocity(-d0, 0.0D, -d1);

		}

	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isCurrentItem) {

		if (entity instanceof EntityPlayer) {

			if (((EntityPlayer) entity).isBlocking()) {

				AxisAlignedBB axisalignedbb = entity.boundingBox.expand(2.0D, 1.0D, 2.0D);
				List<EntityMob> list = entity.worldObj.getEntitiesWithinAABB(EntityMob.class, axisalignedbb);

				for (Entity mob : list)
				{
					pushEntityAway(mob, entity);

					if (!((EntityLivingBase)mob).isPotionActive(Potion.wither.getId()))
					{

						((EntityLivingBase)mob).addPotionEffect(new PotionEffect(Potion.wither.getId(), 140, 10));

					}

				}

				return;

			}

		}

	}

}
