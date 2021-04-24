package view.bukkit;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import minigame.view.ActionHandler;
import minigame.view.MenuItem;
import minigame.view.MenuView;
import minigame.view.MenuViewModel;

public class MenuViewBukkit implements MenuView {
	
	private UUID viewer;
	private Inventory inventory;
	private ActionHandler[] actionHandlers;
	
	@Override
	public void display(MenuViewModel menuViewModel) {
		Player bukkitPlayer = Bukkit.getPlayer(menuViewModel.getViewer());
		viewer = menuViewModel.getViewer();
		inventory = Bukkit.createInventory(null, menuViewModel.getNumberOfSlots(), menuViewModel.getTitle());
		actionHandlers = new ActionHandler[menuViewModel.getNumberOfSlots()];
		createItems(menuViewModel);
		bukkitPlayer.openInventory(inventory);
	}
	
	@Override
	public void hide() {
		Bukkit.getPlayer(viewer).closeInventory();
	}
	
	private void createItems(MenuViewModel menuViewModel) {
		for (MenuItem menuItem : menuViewModel.getMenuItems()) {
			int slotIndex = menuItem.getSlotIndex();
			inventory.setItem(slotIndex, createItemStackFromMenuItem(menuItem));
			actionHandlers[slotIndex] = menuItem.getActionHandler();
		}
	}
	
	private ItemStack createItemStackFromMenuItem(MenuItem menuItem) {
		ItemStack itemStack = new ItemStack(Material.matchMaterial(menuItem.getIcon()));
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(menuItem.getName());
		itemMeta.setLore(menuItem.getLore());
		itemStack.setAmount(menuItem.getAmount());
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
	public ActionHandler getClickHandler(int index) {
		return actionHandlers[index];
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public UUID getViewer() {
		return viewer;
	}
	
}
