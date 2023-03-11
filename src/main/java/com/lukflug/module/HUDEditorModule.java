package com.lukflug.module;

import org.lwjgl.input.Keyboard;

import com.lukflug.setting.BooleanSetting;
import com.lukflug.setting.KeybindSetting;

public class HUDEditorModule extends Module {
    public static final KeybindSetting keybind = new KeybindSetting("Keybind","keybind","The key to toggle the module.",()->true,Keyboard.KEY_O);

    public HUDEditorModule() {
        super("HUDEditor","Module containing HUDEditor settings.",()->true,false);
        settings.add(keybind);
    }
}