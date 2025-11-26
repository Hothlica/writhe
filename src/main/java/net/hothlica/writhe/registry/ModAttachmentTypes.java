package net.hothlica.writhe.registry;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.impl.attachment.AttachmentRegistryImpl;
import net.hothlica.writhe.Writhe;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Consumer;

@SuppressWarnings("UnstableApiUsage")
public class ModAttachmentTypes {

    public static final AttachmentType<Integer> ROT_TICKS = register("rot_ticks", builder -> builder.initializer(() -> 0).persistent(Codecs.NONNEGATIVE_INT).syncWith(PacketCodecs.codec(Codecs.NONNEGATIVE_INT), AttachmentSyncPredicate.targetOnly()));

    private static <A> AttachmentType<A> register (String id, Consumer<AttachmentRegistry.Builder<A>> consumer) {
        AttachmentRegistry.Builder<A> builder = AttachmentRegistryImpl.builder();
        consumer.accept(builder);
        return builder.buildAndRegister(Writhe.id(id));
    }

    public static void init(){}
}
