package live.ggclient.ggclient.module.VISUAL;

import com.lukflug.module.Module;
import com.lukflug.setting.KeybindSetting;
import com.lukflug.setting.EnumSetting;
import net.minecraft.init.MobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

enum type{
    POTION,
    GAMMA
}

public class fullbright extends Module {
    public static final KeybindSetting keybindSetting = new KeybindSetting("Keybind", "keybind", "Keybind for module", ()->true, Keyboard.KEY_NONE);
//    private static final Enum type = live.ggclient.ggclient.module.VISUAL.fullbright.type;
//    public static final EnumSetting enumSetting = new EnumSetting("Type", "type", "Ded", ()->true, type, type.getClass());
    public live.ggclient.ggclient.module.VISUAL.fullbright instance;
    public Minecraft mc = Minecraft.getMinecraft();
    // previous brightness info
    private float previousBright;
    private int previousNightVision;


    public fullbright() {
        super("Fullbright", "Allows you to see in the dark.", ()->true, true);
        settings.add(keybindSetting);
        settings.add(enumSetting);

        instance = this;
        MinecraftForge.EVENT_BUS.register(instance);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(instance.isEnabled().isOn()) {
            mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION.setPotionName("FullBright"), 80950, 1, false, false));

        } else if (!instance.isEnabled().isOn()) {
            mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }
    }

}




