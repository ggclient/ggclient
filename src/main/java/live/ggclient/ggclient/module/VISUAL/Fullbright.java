package live.ggclient.ggclient.module.VISUAL;

import com.lukflug.module.Module;
import com.lukflug.setting.EnumSetting;
import com.lukflug.setting.KeybindSetting;
import net.minecraft.init.MobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.util.Objects;

public class Fullbright extends Module {

    public static final KeybindSetting keybindSetting = new KeybindSetting("Keybind", "keybind", "Keybind for module", ()->true, Keyboard.KEY_NONE);
    public static final EnumSetting modeSetting = new EnumSetting("Mode", "mode", "Mode for fullbright", ()->true, Modes.Potion, Modes.class);

    public Fullbright instance;
    public Minecraft mc = Minecraft.getMinecraft();

    enum Modes {
        Potion,
        Gamma
    }

    public Fullbright() {
        super("Fullbright", "Allows you to see in the dark.", ()->true, true);
        settings.add(keybindSetting);
        settings.add(modeSetting);

        instance = this;
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PotionEffect fullbrightEffect = new PotionEffect(Objects.requireNonNull(Potion.getPotionById(16)), 999999, 1);

        if (mc.player == null) { return; }

        if (!instance.isEnabled().isOn()) {
            if (mc.player.isPotionActive(fullbrightEffect.getPotion())) {
                mc.player.removePotionEffect(fullbrightEffect.getPotion());
            } else if (mc.gameSettings.gammaSetting > 1) {
                mc.gameSettings.gammaSetting = 1;
            }
        } else {
            if (modeSetting.getValue() == Modes.Potion) {
                if (mc.gameSettings.gammaSetting > 1) {
                    mc.gameSettings.gammaSetting = 1;
                }

                mc.player.addPotionEffect(fullbrightEffect);
            } else if (modeSetting.getValue() == Modes.Gamma) {
                if (mc.player.isPotionActive(fullbrightEffect.getPotion())) {
                    mc.player.removePotionEffect(fullbrightEffect.getPotion());
                }

                mc.gameSettings.gammaSetting = 100;
            }
        }
    }
}




