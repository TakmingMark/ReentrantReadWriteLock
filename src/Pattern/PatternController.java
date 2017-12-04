package Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		PatternAction patternAction=new PatternAction(patternModel);
		patternView.setClientButtonListner(patternAction);
		patternView.setServerButtonListener(patternAction);
	}
	
}
class PatternAction implements ActionListener{

	private PatternModel patternModel;
	
	public PatternAction(PatternModel patternModel) {
		this.patternModel=patternModel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		patternModel.conversionController(e.getActionCommand());
	}
	
}