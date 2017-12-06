package Pattern;

import Main.MainAcitivity;

public class PatternController {
	private MainAcitivity mainAcitivity;
	private PatternView patternView;
	private PatternModel patternModel;

	private PatternController() {
	}
	
	public static PatternController getPatternControllerObject() {
		return new PatternController();
	}
	
	public void initPatternController() {
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

	public void setMainAcitivity(MainAcitivity mainAcitivity) {
		this.mainAcitivity = mainAcitivity;
	}

	public void setPatternView(PatternView patternView) {
		this.patternView = patternView;
	}

	public void setPatternModel(PatternModel patternModel) {
		this.patternModel = patternModel;
	}
}