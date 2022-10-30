package crexzyzdev.ng.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MarijuanaSeeds extends Item {

    public static final String NAME = "marijuana_seeds";

    public MarijuanaSeeds(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.playSound(SoundEvents.ANVIL_FALL);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

}
