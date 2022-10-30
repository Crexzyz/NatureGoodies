package crexzyzdev.ng;

import crexzyzdev.ng.items.MarijuanaSeeds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class NGFabric implements ModInitializer {

    public static final CreativeModeTab MOD_TAB = FabricItemGroupBuilder
            .create(new ResourceLocation(Constants.MOD_ID, "main"))
            .icon(() -> new ItemStack(Items.TALL_GRASS))
            .build();

    public static final MarijuanaSeeds MARIJUANA_SEEDS = new MarijuanaSeeds(new FabricItemSettings().group(MOD_TAB));

    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();
        
        // Some code like events require special initialization from the
        // loader specific code.
        ItemTooltipCallback.EVENT.register(CommonClass::onItemTooltip);

        Registry.register(Registry.ITEM, new ResourceLocation(Constants.MOD_ID, MarijuanaSeeds.NAME), MARIJUANA_SEEDS);
    }
}
