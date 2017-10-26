import javax.print.attribute.standard.RequestingUserName;

public class PatternModel {

	PatternView patternView;
	private PatternModel() {
		
	}
	
	public static PatternModel getPatternModelObject() {
		return new PatternModel();
	}
}
