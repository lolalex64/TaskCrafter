package net.shycad.taskcrafter.KeyBinds;

import net.shycad.taskcrafter.gui.ExampleScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static KeyBinding openExampleScreenKey;

    public static void registerKeyBindings() {
        openExampleScreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.taskcrafter.open_example_screen", // The translation key of the key binding's name
                GLFW.GLFW_KEY_K, // The keycode of the key
                "category.taskcrafter.keys" // The translation key of the key binding's category
        ));

        // Register the key press event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openExampleScreenKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new ExampleScreen());
                }
            }
        });
    }
}
