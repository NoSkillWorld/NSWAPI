package fr.noskillworld.api.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

/**
 * Represents a GUI with a custom name & items
 */
public interface CustomInventory {

    /**
     * Returns the name of the GUI
     *
     * @return The name of the GUI
     */
    String getName();

    /**
     * Returns the number of rows of the GUI
     *
     * @return The number of rows of the GUI
     */
    int getRows();

    /**
     * Returns the number of slots of the GUI
     * Depends on the number of row set (number_of_rows x 9)
     *
     * @return The number of slots of the GUI
     */
    default int getSlots() {
        return getRows() * 9;
    }

    /**
     * Get all the items the CustomInventory for a player
     *
     * @param player The player that has opened the inventory
     * @return The items in the inventory
     */
    Supplier<ItemStack[]> getContents(Player player);

    /**
     * Triggered on player inventory click.<br>
     * You will need to create an InventoryClickEvent listener
     * if you're using Spigot plugin implementation.
     *
     * @param player      The player that has clicked
     * @param inventory   The inventory clicked
     * @param clickedItem The item that have been clicked
     * @param slot        The id of the slot that have been clicked
     * @param isLeftClick True if the player have left-clicked, false if not
     * @see GuiManager
     */
    void onClick(Player player, Inventory inventory, ItemStack clickedItem, int slot, boolean isLeftClick);
}
