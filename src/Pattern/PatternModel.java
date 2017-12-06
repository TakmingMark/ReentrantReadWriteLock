package Pattern;
import Main.MainAcitivity;
import Tools.TextContent;

public class PatternModel {
	private PatternController patternController;
	
	private PatternModel(PatternController patternController) {
		this.patternController=patternController;
	}
	
	public static PatternModel getPatternModelObject(PatternController patternController) {
		return new PatternModel(patternController);
	}
}
