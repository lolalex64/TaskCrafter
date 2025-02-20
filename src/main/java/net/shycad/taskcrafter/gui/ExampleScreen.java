package net.shycad.taskcrafter.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.client.gui.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExampleScreen extends Screen {


    public ExampleScreen() {
        super(Text.of("Checklist"));
        items = new ArrayList<>();
        buttons = new HashMap<>();
    }

    public ButtonWidget button1;
    private TextFieldWidget textField;
    private List<String>  items;
    private Map<String, ButtonWidget> buttons;
    private boolean buttonClicked;

    @Override
    protected void init() {
        button1 = ButtonWidget.builder(Text.literal("ADD"), button -> {
                    String text = textField.getText();
                    if (!text.isEmpty()) {
                        items.add(text);
                        textField.setText("");
                        buttonClicked = true;
                        createRemoveButtonForItem(text);
                    }
                })
                .dimensions(10, 40, 50 ,20)
                .tooltip(Tooltip.of(Text.literal("")))
                .build();


        addDrawableChild(button1);


        textField = new TextFieldWidget(this.textRenderer, 10, 10, 100, 20, Text.of("Enter text"));
        textField.setEditable(true);
        textField.setDrawsBackground(true);
        addSelectableChild(textField);

       for (String item: items) {
           createRemoveButtonForItem(item);
       }

    }

    private void createRemoveButtonForItem(String item) {
            ButtonWidget removeButton = ButtonWidget.builder(Text.literal("X"), button -> {
                        items.remove(item);
                        removeButtonFromScreen(item);
                        buttonClicked = true; // Set flag to true to trigger re-rendering
                    })
                    .dimensions(this.width / 2 + 60, 0, 20, 20) // Initial position, will be updated in render method
                    .tooltip(Tooltip.of(Text.literal("Remove")))
                    .build();

            buttons.put(item, removeButton);
            addDrawableChild(removeButton);

    }

    private void removeButtonFromScreen(String item) {
        ButtonWidget removeButton = buttons.remove(item);
        if (removeButton != null) {
          this.remove(removeButton);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX,mouseY,delta);
        super.render(context, mouseX,mouseY, delta);
        textField.render(context, mouseX, mouseY, delta);

        int y = 20;
        int buttonWidth = 20;
        int buttonMargin = 5;
        int itemspacing = 30;

        for (String item : items) {

            int textWidth = this.textRenderer.getWidth(item);
            int x = (this.width - textWidth - buttonWidth - buttonMargin) / 2;

            y += 10; // Adjust the y position for the next item

            ButtonWidget removeButton = buttons.get(item);
            if (removeButton != null) {
                removeButton.setPosition(x - buttonWidth - buttonMargin, y - 5);
                removeButton.render(context, mouseX, mouseY, delta);
            }

            context.drawTextWithShadow(this.textRenderer, item, x, y, 0xFFFFFF);

            y += itemspacing;// Adjust the y position for the next item
        }

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

    @Override
    public void tick() {
        super.tick();
        // If the button was clicked, clear the focus and reset the flag
        if (buttonClicked) {
            setFocused(null);
            buttonClicked = false;
        }
    }
}
