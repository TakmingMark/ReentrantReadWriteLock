package Pattern;
import javax.print.attribute.standard.RequestingUserName;

import Main.MainAcitivity;
import Tools.TextView;

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
		if(pattern.equals(TextView.ServerButtonName)) {
			patternView.close();
			mainAcitivity.initServer();
		}
		else if(pattern.equals(TextView.ClientButtonName)) {
			patternView.close();
			mainAcitivity.initClient();
		}
	}
}
