package crexzyzdev.ng;

import crexzyzdev.ng.blocks.GreenieCropBlock;
import crexzyzdev.ng.items.GreenieSeeds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;


public class NGFabric implements ModInitializer {

    public static final GreenieCropBlock GREENIE_CROP_BLOCK = new GreenieCropBlock(
            BlockBehaviour.Properties.of(Material.PLANT).noOcclusion().noCollission().randomTicks().instabreak()
                    .sound(SoundType.CROP)
    );

    public static final GreenieSeeds GREENIE_SEEDS = new GreenieSeeds(GREENIE_CROP_BLOCK, new FabricItemSettings());

    private static final CreativeModeTab MOD_TAB = FabricItemGroup.builder(
        new ResourceLocation(Constants.MOD_ID, "main")
    )
        .icon(() -> new ItemStack(GREENIE_SEEDS))
        .build();

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

        Registry.register(BuiltInRegistries.ITEM, GreenieSeeds.RESOURCE_LOCATION, GREENIE_SEEDS);
        Registry.register(BuiltInRegistries.BLOCK, GreenieCropBlock.RESOURCE_LOCATION, GREENIE_CROP_BLOCK);

        ItemGroupEvents.modifyEntriesEvent(MOD_TAB).register(content -> {
            content.prepend(GREENIE_SEEDS);
        });
    }
}
