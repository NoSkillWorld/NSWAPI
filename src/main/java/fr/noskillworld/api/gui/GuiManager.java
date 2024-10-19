package fr.noskillworld.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GuiManager {

    public final Map<Class<? extends CustomInventory>, CustomInventory> registeredMenus;

    public GuiManager() {
        this.registeredMenus = new HashMap<>();
    }

    public void open(Player player, Class<? extends CustomInventory> gClass) {
        if (!registeredMenus.containsKey(gClass)) return;

        CustomInventory menu = registeredMenus.get(gClass);
        Inventory inv = Bukkit.createInventory(null, menu.getSlots(), menu.getName());
        inv.setContents(menu.getContents(player).get());
        player.openInventory(inv);
    }

    public void refresh(Player player, Class<? extends CustomInventory> gClass) {
        if (!registeredMenus.containsKey(gClass)) return;

        CustomInventory menu = registeredMenus.get(gClass);
        Inventory inv = player.getOpenInventory().getTopInventory();
        inv.setContents(menu.getContents(player).get());
    }

    public void addMenu(CustomInventory m) {
        registeredMenus.put(m.getClass(), m);
    }
}
