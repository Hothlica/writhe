package net.hothlica.writhe.registry;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.fabricmc.fabric.impl.attachment.AttachmentRegistryImpl;
import net.hothlica.writhe.Writhe;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.function.Consumer;

public class ModAttachmentTypes {

    public static final AttachmentType<Integer> ROT_TICKS = register("rot_ticks", 0, builder -> builder.persistent(Codecs.NONNEGATIVE_INT).syncWith(PacketCodecs.codec(Codecs.NONNEGATIVE_INT), AttachmentSyncPredicate.targetOnly()));

    private static <A> AttachmentType<A> register (String id, A defaultData, Consumer<AttachmentRegistry.Builder<A>> consumer) {
        AttachmentRegistry.Builder<A> builder = AttachmentRegistryImpl.builder();
        builder.initializer(() -> defaultData);
        consumer.accept(builder);
        return builder.buildAndRegister(Writhe.id(id));
    }

    public static void init(){}
}
