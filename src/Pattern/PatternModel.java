package Pattern;
import javax.print.attribute.standard.RequestingUserName;

import Main.MainAcitivity;

public class PatternModel {

	MainAcitivity mainAcitivity;
	PatternView patternView;
	private PatternModel(MainAcitivity mainAcitivity,PatternView patternView) {
		this.mainAcitivity=mainAcitivity;
		this.patternView=patternView;
	}
	
	public static PatternModel getPatternModelObject(MainAcitivity mainAcitivity,PatternView patternView) {
		return new PatternModel(mainAcitivity,patternView);
	}
	
	public void conversionController(String pattern) {
		if(pattern.equals("Server")) {
			patternView.close();
			mainAcitivity.initServer();
		}
		else if(pattern.equals("Client")) {
			patternView.close();
			mainAcitivity.initClient();
		}
	}
}
