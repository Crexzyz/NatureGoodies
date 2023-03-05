package crexzyzdev.ng.items;

import crexzyzdev.ng.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class GreenieSeeds extends ItemNameBlockItem {
    public static final String NAME = "greenie_seeds";

    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(Constants.MOD_ID, NAME);

    public GreenieSeeds(Block aliasedBlock, Properties properties) {
        super(aliasedBlock, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.playSound(SoundEvents.ARROW_HIT);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

}
