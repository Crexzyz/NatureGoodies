package crexzyzdev.ng;

import com.mojang.blaze3d.vertex.PoseStack;
import crexzyzdev.ng.blocks.MarijuanaCropBlock;
import crexzyzdev.ng.items.MarijuanaSeeds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class NGFabric implements ModInitializer {

    public static final CreativeModeTab MOD_TAB = FabricItemGroupBuilder
            .create(new ResourceLocation(Constants.MOD_ID, "main"))
            .icon(() -> new ItemStack(Items.TALL_GRASS))
            .build();

    public static final MarijuanaCropBlock MARIJUANA_CROP_BLOCK = new MarijuanaCropBlock(
            BlockBehaviour.Properties.of(Material.PLANT).noOcclusion().noCollission().randomTicks().instabreak()
                    .sound(SoundType.CROP)
    );

    public static final MarijuanaSeeds MARIJUANA_SEEDS = new MarijuanaSeeds(MARIJUANA_CROP_BLOCK, new FabricItemSettings().group(MOD_TAB));

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

        Registry.register(Registry.ITEM, MarijuanaSeeds.RESOURCE_LOCATION, MARIJUANA_SEEDS);
        Registry.register(Registry.BLOCK, MarijuanaCropBlock.RESOURCE_LOCATION, MARIJUANA_CROP_BLOCK);
    }
}
