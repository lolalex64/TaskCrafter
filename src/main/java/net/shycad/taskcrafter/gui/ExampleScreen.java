package net.shycad.taskcrafter.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.tooltip.Tooltip;

public class ExampleScreen extends Screen {


    public ExampleScreen() {
        super(Text.of("Checklist"));
        // Use Text.of() for text components
    }

    public ButtonWidget button1;
    public ButtonWidget button2;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("Button1"), button -> {
            System.out.println("You Clicked button1!");
        })
                .dimensions(width / 2 - 205, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
                .build();
        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
            System.out.println("You clicked button2!");
        })
                .dimensions(width / 2 + 5, 20, 200, 20)
                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
                .build();

        addDrawableChild(button1);
        addDrawableChild(button2);
    }


    public boolean shouldCloseOnEsc() {
        return true; // Allows closing screen with Escape key
    }
}
