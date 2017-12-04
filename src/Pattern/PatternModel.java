package Pattern;
import Main.MainAcitivity;
import Tools.TextView;

public class PatternModel {
	private MainAcitivity mainAcitivity;
	private PatternView patternView;
	
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
