package live.ggclient.ggclient;

import live.ggclient.ggclient.module.ModuleManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import com.lukflug.ClickGUI;

@Mod(modid = GGClient.MODID, name = GGClient.NAME, version = GGClient.VERSION)
public class GGClient {
    public static final String MODID = "ggclient";
    public static final String NAME = "GG Client";
    public static final String VERSION = "0.1.0";

    public static Logger logger;
    public static ClickGUI clickGui;
    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }
    @EventHandler
    public void init (FMLInitializationEvent event) {
        ModuleManager mm = new ModuleManager();
        clickGui = new ClickGUI();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRender (RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) clickGui.render();
    }
}
