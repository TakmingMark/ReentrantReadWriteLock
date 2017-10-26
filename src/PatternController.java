import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.text.ParagraphView;

import com.sun.corba.se.spi.oa.OADefault;

public class PatternController {
	MainAcitivity mainAcitivity;
	PatternView patternView;
	PatternModel patternModel;
	
	private PatternController(MainAcitivity mainAcitivity,PatternView patternView,PatternModel patternModel) {
		this.mainAcitivity=mainAcitivity;
		this.patternView=patternView;
		this.patternModel=patternModel;
		initPatternController();
	}
	
	public static PatternController getPatternControllerObject(MainAcitivity mainAcitivity,PatternView patternView,PatternModel patternModel) {
		return new PatternController(mainAcitivity,patternView,patternModel);
	}
	
	private void initPatternController() {
		PatternAction patternAction=new PatternAction(this);
		patternView.setClientButtonListner(patternAction);
		patternView.setServerButtonListener(patternAction);
	}
	
	public void conversionController(String pattern) {
		if(pattern.equals("Server")) {
			patternView.close();
			mainAcitivity.initServer();
		}
		else if(pattern.equals("Client")) {
			
		}
	}
}
class PatternAction implements ActionListener{

	PatternController patternController;
	public PatternAction(PatternController patternController) {
		this.patternController=patternController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		patternController.conversionController(e.getActionCommand());
	}
	
}