package live.ggclient.ggclient.module.MOVEMENT;

import com.lukflug.module.Module;
import com.lukflug.setting.BooleanSetting;
import com.lukflug.setting.EnumSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {
    public static final EnumSetting<Modes> modeSetting = new EnumSetting<Modes>("Mode", "mode", "Mode for sprint", ()->true, Modes.Normal, Modes.class);
    public static final BooleanSetting instantSetting = new BooleanSetting("Instant", "instant", "Allows you sprint instantly (Omni broken)", ()->true, false);

    public Sprint instance;

    public Minecraft mc = Minecraft.getMinecraft();

    public Sprint() {
        super("Sprint", "Auto sprint", ()->true, true);

        settings.add(modeSetting);
        settings.add(instantSetting);

        instance = this;
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(!instance.isEnabled().isOn()) { return; }

        if (!mc.player.isInWater() && !mc.player.isInLava()) {
            if (modeSetting.getValue() == Modes.Normal) {
                if (instantSetting.isOn()) {
                    mc.player.setSprinting(true);
                } else {
                    if (mc.player.moveForward > 0) {
                        mc.player.setSprinting(true);
                    }
                }
            } else if (modeSetting.getValue() == Modes.Omni) {
                return;
                // TODO Implement omni sprint

            }
        }
    }

    enum Modes {
        Normal,
        Omni
    }
}
