package crexzyzdev.ng;

import crexzyzdev.ng.blocks.GreenieCropBlock;
import crexzyzdev.ng.items.GreenieSeeds;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

@Mod(Constants.MOD_ID)
public class NGForge {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);

    public static final RegistryObject<Block> GREENIE_CROP_BLOCK = registerBlock(
        GreenieCropBlock.NAME,
        () -> new GreenieCropBlock(
            BlockBehaviour.Properties.of(Material.PLANT).noOcclusion().noCollission().randomTicks().instabreak().sound(SoundType.CROP)
        ),
        null
    );

    public static final RegistryObject<Item> GREENIE_SEEDS = ITEMS.register(
        GreenieSeeds.NAME,
        () -> new GreenieSeeds(GREENIE_CROP_BLOCK.get(), new Item.Properties())
    );

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> result = BLOCKS.register(name, block);
        registerBlockItem(name, result, tab);
        return result;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(
            String name, RegistryObject<T> block, CreativeModeTab tab
    ) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public NGForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
    
        // Some code like events require special initialization from the
        // loader specific code.
        MinecraftForge.EVENT_BUS.addListener(this::onItemTooltip);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    // This method exists as a wrapper for the code in the Common project.
    // It takes Forge's event object and passes the parameters along to
    // the Common listener.
    private void onItemTooltip(ItemTooltipEvent event) {
        CommonClass.onItemTooltip(event.getItemStack(), event.getFlags(), event.getToolTip());
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(GREENIE_CROP_BLOCK.get(), RenderType.cutoutMipped());
        }

        @SubscribeEvent
        public static void onCreativeTabRegistry(CreativeModeTabEvent.Register event) {
            event.registerCreativeModeTab(new ResourceLocation(Constants.MOD_ID, "main"), builder -> {
                builder
                    .icon(() -> new ItemStack(Items.TALL_GRASS))
                    .displayItems((enabledFlags, populator, hasPermissions) -> {
                        populator.accept(new ItemStack(GREENIE_SEEDS.get()));
                    });
            });
        }
    }
}