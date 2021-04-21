package view.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import view.ActionBarView;
import view.DefaultMenuItem;
import view.DefaultMenuViewModel;
import view.DefaultScoreViewModel;
import view.DefaultSpawnItemViewModel;
import view.DefaultTitleViewModel;
import view.MenuItem;
import view.MenuView;
import view.MenuViewModel;
import view.MessageView;
import view.ScoreView;
import view.ScoreViewModel;
import view.SpawnItemView;
import view.SpawnItemViewModel;
import view.TitleView;
import view.TitleViewModel;
import view.ViewFactory;

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
