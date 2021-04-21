package view;

public interface TitleViewModel {

	String getTitle();
	
	void setTitle(String title);
	
	String getSubtitle();
	
	void setSubtitle(String subtitle);
	
	void setFadeInTimeInSeconds(int fadeInTimeInSeconds);
	
	void setFadeOutTimeInSeconds(int fadeOutTimeInSeconds);
	
	void setStayTimeInSeconds(int stayTimeInSeconds);
	
	int getTitleFadeInTimeInSeconds();
	
	void setTitleFadeInTimeInSeconds(int titleFadeInTimeInSeconds);
	
	int getTitleStayTimeInSeconds();
	
	void setTitleStayTimeInSeconds(int titleStayTimeInSeconds);
	
	int getTitleFadeOutTimeInSeconds();
	
	void setTitleFadeOutTimeInSeconds(int titleFadeOutTimeInSeconds);
	
	int getSubtitleFadeInTimeInSeconds();
	
	void setSubtitleFadeInTimeInSeconds(int subTitleFadeInTimeInSeconds);
	
	int getSubtitleStayTimeInSeconds();
	
	void setSubtitleStayTimeInSeconds(int subTitleStayTimeInSeconds);
	
	int getSubtitleFadeOutTimeInSeconds();
	
	void setSubtitleFadeOutTimeInSeconds(int subtitleFadeOutTimeInSeconds);
	
	int getTitleFadeInTimeInTicks();
	
	int getTitleStayTimeInTicks();
	
	int getTitleFadeOutTimeInTicks();
	
	int getSubtitleFadeInTimeInTicks();
	
	int getSubtitleStayTimeInTicks();
	
	int getSubtitleFadeOutTimeInTicks();
	
}
