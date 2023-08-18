package net.matees.settings;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public abstract class Setting<T> {

    private ItemStack menuItem;

    public abstract String getName();

    public abstract String getDescription();

    // Default
    public abstract T getSetting();

    public abstract void setSetting(T setting);

    public abstract Material getMenuItemMaterial();

    public abstract void setIntValue(int value);

    public abstract void setBooleanValue(boolean value);

    public abstract void setListValue(List value);

    public abstract void setStringValue(String value);

    public ItemStack getMenuItem() {
        ItemStack item = new ItemStack(this.getMenuItemMaterial(), 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ColorTranslator.translateColorCodes("&l&6") + this.getName());
        meta.setLore(List.of(
                ColorTranslator.translateColorCodes("Current: &2" + this.getSetting()),
                ColorTranslator.translateColorCodes("&o&7" + this.getDescription()),
                this.getSettingLore()));

        item.addUnsafeEnchantment(Enchantment.LUCK, 1);
        meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);
        return item;
    }

    public void setMenuItem(ItemStack menuItem) {
        this.menuItem = menuItem;
    }

    public abstract int getMenuItemSlot();

    public String getSettingLore() {
        if (this.getSetting() instanceof Integer) {
            return ColorTranslator.translateColorCodes("&2Increase (left)\n" +
                    "&4Decrease(right)");
        }

        if (this.getSetting() instanceof String) {
            return ColorTranslator.translateColorCodes("&3Click to edit");
        }

        if (this.getSetting() instanceof Boolean) {
            return ColorTranslator.translateColorCodes("&3Click to toggle");
        }

        if (this.getSetting() instanceof List) {
            return ColorTranslator.translateColorCodes("&3Click to view/edit");
        }

        return "";
    }

    public void handleItemClick(InventoryClickEvent event) {
        if (this.getSetting() instanceof Integer) {
            int current = (int) this.getSetting();
            if (event.isLeftClick() && current != 128) {
                this.setIntValue(current + 1);
            } else if (event.isRightClick() && current != 1) {
                this.setIntValue(current - 1);
            }

            return;
        }

        if (this.getSetting() instanceof Boolean) {
            if (!event.isLeftClick())
                return;
            boolean current = (boolean) this.getSetting();
            this.setBooleanValue(!current);

            return;
        }

        if (this.getSetting() instanceof String) {
            if (!event.isLeftClick())
                return;
            // Open sign, worry about this later
        }

        if (this.getSetting() instanceof List) {
            if (!event.isLeftClick())
                return;
            // Open another menu, with a list of "settings", heads, or whatever.
            // Worry about this later
        }
    }
}
