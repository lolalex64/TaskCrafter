package net.shycad.taskcrafter.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
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
    private TextFieldWidget textField;

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

        textField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 2 - 10, 200, 20, Text.of("Enter text..."));
        textField.setEditable(true);
        textField.setDrawsBackground(true);
        addSelectableChild(textField);

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX,mouseY,delta);
        super.render(context, mouseX,mouseY, delta);
        textField.render(context, mouseX, mouseY, delta);

    }

    @Override
    public boolean charTyped(char chr, int keyCode) {
        return textField.charTyped(chr, keyCode) || super.charTyped(chr, keyCode);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return textField.keyPressed(keyCode, scanCode, modifiers) || super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (textField.mouseClicked(mouseX, mouseY, button)) {
            textField.setFocused(true);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true; // Allows closing screen with Escape key
    }
}
