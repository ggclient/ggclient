package live.ggclient.ggclient.module.MOVEMENT;

import com.lukflug.module.Module;
import net.minecraftforge.common.MinecraftForge;

public class Jesus extends Module {
    public Jesus instance;

    public Jesus() {
        super("Jesus", "Allows you to walk on water", ()->true, true);

        instance = this;
        MinecraftForge.EVENT_BUS.register(instance);
    }
}
