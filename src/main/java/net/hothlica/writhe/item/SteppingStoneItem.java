package net.hothlica.writhe.item;

import net.hothlica.writhe.registry.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
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
        if (user.isFallFlying() || user.hasVehicle()) return TypedActionResult.fail(itemStack);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.STEPPING_STONE_USE, SoundCategory.NEUTRAL,0.8f,0.5f);
        user.getItemCooldownManager().set(this, 30);
        if (!world.isClient && !user.getAbilities().flying) {
            double yawRad = Math.toRadians(-user.getYaw());
            Vec3d look = new Vec3d(Math.sin(yawRad), 0, Math.cos(yawRad));
            //set y velocity to 0 if going down and add the velocity to both x and z
            Vec3d currVel = user.getVelocity();
            double addVel = 0;
            if (currVel.y < 0) {
                user.setVelocity(currVel.x, 0, currVel.z);
                addVel = Math.log(1 - currVel.y) * 1.5;
            }
            user.addVelocity(look.x * (0.9 + addVel), 0.6, look.z * (0.9 + addVel));
            user.velocityModified = true;
        }
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
