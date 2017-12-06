package Pattern;

import Main.MainAcitivity;

public class PatternController {
	private MainAcitivity mainAcitivity;
	private PatternView patternView;
	private PatternModel patternModel;
	
	private PatternController(PatternView patternView,PatternModel patternModel) {
		this.patternView=patternView;
		this.patternModel=patternModel;
		initPatternController();
	}
	
	public static PatternController getPatternControllerObject(PatternView patternView,PatternModel patternModel) {
		return new PatternController(patternView,patternModel);
	}
	
	private void initPatternController() {
		patternView.getServerButton().addActionListener(e -> pressServerButton());
		patternView.getClientButton().addActionListener(e ->pressClientButton());
	}
	
	private void pressServerButton() {
		patternView.close();
		mainAcitivity.initServer();
	}
	
	private void pressClientButton() {
		patternView.close();
		mainAcitivity.initClient();
	}
}