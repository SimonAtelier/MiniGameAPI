package view.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import minigame.view.ActionBarView;
import minigame.view.DefaultMenuItem;
import minigame.view.DefaultMenuViewModel;
import minigame.view.DefaultScoreViewModel;
import minigame.view.DefaultSpawnItemViewModel;
import minigame.view.DefaultTitleViewModel;
import minigame.view.MenuItem;
import minigame.view.MenuView;
import minigame.view.MenuViewModel;
import minigame.view.MessageView;
import minigame.view.ScoreView;
import minigame.view.ScoreViewModel;
import minigame.view.SpawnItemView;
import minigame.view.SpawnItemViewModel;
import minigame.view.TitleView;
import minigame.view.TitleViewModel;
import minigame.view.ViewFactory;

public class ViewFactoryBukkit implements ViewFactory {

	private String prefix;
	private JavaPlugin plugin;
	
	public ViewFactoryBukkit(JavaPlugin plugin, String prefix) {
		this.plugin = plugin;
		this.prefix = prefix;
	}
	
	@Override
	public MessageView createMessageView() {
		MessageViewBukkit messageView = new MessageViewBukkit();
		messageView.setPrefix(prefix);
		return messageView;
	}

	@Override
	public TitleView createTitleView() {
		return new TitleViewBukkit();
	}

	@Override
	public ActionBarView createActionBarView() {
		return new ActionBarViewBukkit();
	}

	@Override
	public MenuView createMenuView() {
		MenuViewBukkit menuView = new MenuViewBukkit();
		MenuController menuController = new MenuController(menuView);
		Bukkit.getServer().getPluginManager().registerEvents(menuController, plugin);
		return menuView;
	}

	@Override
	public MenuItem createMenuItem(String name, String icon, int slotIndex) {
		MenuItem menuItem = createMenuItem();
		menuItem.setName(name);
		menuItem.setIcon(icon);
		menuItem.setSlotIndex(slotIndex);
		return menuItem;
	}

	@Override
	public MenuViewModel createMenuViewModel() {
		return new DefaultMenuViewModel();
	}

	@Override
	public TitleViewModel createTitleViewModel() {
		return new DefaultTitleViewModel();
	}

	@Override
	public MenuItem createMenuItem() {
		return new DefaultMenuItem();
	}

	@Override
	public ScoreView createScoreView() {
		return new ScoreViewBukkit();
	}

	@Override
	public ScoreViewModel createScoreViewModel() {
		return new DefaultScoreViewModel();
	}

	@Override
	public SpawnItemView createSpawnItemView() {
		return new SpawnItemViewBukkit();
	}

	@Override
	public SpawnItemViewModel createSpawnItemViewModel() {
		return new DefaultSpawnItemViewModel();
	}

}
