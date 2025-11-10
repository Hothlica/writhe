package net.hothlica.writhe.item;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class BerryshardItem extends AliasedBlockItem {
    public BerryshardItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (!user.isInCreativeMode()) {
            if (!world.isClient) {
                user.setHealth(user.getHealth() / 2);
            }
            user.playSound(SoundEvents.ENTITY_PLAYER_HURT);
        }
        return itemStack;
    }
}
