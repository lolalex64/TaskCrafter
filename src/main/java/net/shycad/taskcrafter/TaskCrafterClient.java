package net.shycad.taskcrafter;

import net.fabricmc.api.ClientModInitializer;
import net.shycad.taskcrafter.KeyBinds.KeyBindings;

public class TaskCrafterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBindings.registerKeyBindings();
    }
}
