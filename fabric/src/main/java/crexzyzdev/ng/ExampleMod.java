package crexzyzdev.ng;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;

public class ExampleMod implements ModInitializer {

    public static final MyItem MY_ITEM = new MyItem(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));

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

        Registry.register(Registry.ITEM, "nature-goodies:my_item", MY_ITEM);
    }
}
