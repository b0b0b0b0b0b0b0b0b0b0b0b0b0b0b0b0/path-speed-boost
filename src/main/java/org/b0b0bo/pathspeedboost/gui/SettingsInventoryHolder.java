package org.b0b0bo.pathspeedboost.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SettingsInventoryHolder implements InventoryHolder {

    private Inventory inventory;

    public SettingsInventoryHolder() {

    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
