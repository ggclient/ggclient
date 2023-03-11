package live.ggclient.ggclient.module.MOVEMENT;

import com.lukflug.module.Module;
import com.lukflug.setting.DoubleSetting;
import com.lukflug.setting.KeybindSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

public class Speed extends Module {
    public static final DoubleSetting speedSetting = new DoubleSetting("Speed", "speed", "Multiplier of speed", ()->true, 0.1, 5, 1.0);
    public static final KeybindSetting keybindSetting = new KeybindSetting("Keybind", "keybind", "Keybind for module", ()->true, Keyboard.KEY_NONE);

    public Speed instance;

    public Minecraft mc = Minecraft.getMinecraft();

    public Speed() {
        super("Speed", "Makes you move faster", ()->true, true);

        settings.add(speedSetting);
        settings.add(keybindSetting);

        instance = this;
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(!instance.isEnabled().isOn()) { return; }

        if (mc.player.onGround && mc.player.moveForward > 0 && !mc.player.isInWater() && !mc.player.isInLava()) {
            float magic = 0.0174532920F;

            float yaw = mc.player.rotationYaw * magic;

            mc.player.setSprinting(true);
            mc.player.motionX -= MathHelper.sin(yaw) * (speedSetting.getValue() / 10);
            mc.player.motionZ += MathHelper.cos(yaw) * (speedSetting.getValue() / 10);
        }
    }
}
