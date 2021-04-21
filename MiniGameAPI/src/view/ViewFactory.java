package view;

public interface ViewFactory {
	
	MessageView createMessageView();
	
	TitleView createTitleView();
	
	ActionBarView createActionBarView();
	
	MenuView createMenuView();
	
	MenuViewModel createMenuViewModel();
	
	TitleViewModel createTitleViewModel();
	
	MenuItem createMenuItem();
	
	MenuItem createMenuItem(String name, String icon, int slotIndex);
	
	ScoreView createScoreView();
	
	ScoreViewModel createScoreViewModel();
	
	SpawnItemView createSpawnItemView();
	
	SpawnItemViewModel createSpawnItemViewModel();

}
