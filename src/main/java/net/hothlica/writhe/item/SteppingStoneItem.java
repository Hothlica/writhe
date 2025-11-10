package net.hothlica.writhe.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SteppingStoneItem extends Item {
    public SteppingStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(this, 30);
        if (!world.isClient) {
            Vec3d look = user.getRotationVec(1.0F);
            //set y velocity to 0 if going down
            Vec3d currVel = user.getVelocity();
            if (currVel.y < 0) {
                user.setVelocity(currVel.x, 0, currVel.z);
            }
            user.addVelocity(look.x, 1, look.z);
            user.velocityModified = true;

        }
        user.playSound(SoundEvents.BLOCK_CONDUIT_DEACTIVATE, 0.8f,-1f);
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
