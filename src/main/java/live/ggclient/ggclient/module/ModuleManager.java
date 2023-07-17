package live.ggclient.ggclient.module;

import com.lukflug.module.*;
import com.lukflug.setting.KeybindSetting;
import live.ggclient.ggclient.module.MOVEMENT.Speed;
import live.ggclient.ggclient.module.MOVEMENT.Sprint;
import live.ggclient.ggclient.module.VISUAL.Fullbright;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

import static live.ggclient.ggclient.GGClient.clickGui;

public class ModuleManager {
    public ModuleManager() {
        Category.init();

        // MOVEMENT
        Category.MOVEMENT.modules.add(new Speed());
        Category.MOVEMENT.modules.add(new Sprint());

        // OTHER
        Category.OTHER.modules.add(new ClickGUIModule());
        Category.OTHER.modules.add(new HUDEditorModule());

        // VISUAL
        Category.VISUAL.modules.add(new Fullbright());

        // HUD
        Category.HUD.modules.add(new LogoModule());

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public ArrayList<Module> getAllModules() {
        ArrayList<Module> toReturn = new ArrayList<>();
        
        Category.MOVEMENT.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.COMBAT.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.HUD.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.OTHER.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.EXPLOITS.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.MISCELLANEOUS.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.VISUAL.getModules().forEach(module -> { toReturn.add((Module) module); });
        Category.WORLD.getModules().forEach(module -> { toReturn.add((Module) module); });

        return toReturn;
    }

    @SubscribeEvent
    public void onKeyInput (InputEvent.KeyInputEvent event) {
        getAllModules().forEach(module -> {
            if (Keyboard.isKeyDown(ClickGUIModule.keybind.getKey())) {
                clickGui.enterGUI();
                return;
            }
            if (Keyboard.isKeyDown(HUDEditorModule.keybind.getKey())) {
                clickGui.enterHUDEditor();
                return;
            }

            module.getSettings().forEach(setting -> {
                if (setting.getClass().equals(KeybindSetting.class)) {
                    KeybindSetting keybindSetting = (KeybindSetting) setting;

                    if (Keyboard.isKeyDown(keybindSetting.getKey()) && !Keyboard.isRepeatEvent()) {
                        module.isEnabled().toggle();
                    }
                }
            });
        });
    }
}
