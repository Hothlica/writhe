package net.hothlica.writhe.item;

import net.hothlica.writhe.registry.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class BerryshardItem extends AliasedBlockItem {
    public BerryshardItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0f , 1.0f);
        if (!world.isClient && !user.isInCreativeMode()) user.setHealth(user.getHealth() / 2);
        user.animateDamage(user.getYaw());
        return itemStack;
    }
}
