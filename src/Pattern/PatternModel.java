package Pattern;
import Main.MainAcitivity;
import Tools.TextContent;

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
}
