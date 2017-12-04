package Pattern;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import Tools.TextView;

public class PatternView {
	public JFrame frame;
	public JPanel panel;
	public JButton serverButton,clientButton;
	
	private PatternView() {	
		initView();
	}
	
	public static PatternView getPatternViewObject() {
		return new PatternView();
	}	
		
	private void initView() {
		frame=new JFrame(TextView.FrameName);
		panel=new JPanel();
		serverButton=new JButton(TextView.ServerButtonName);
		clientButton=new JButton(TextView.ClientButtonName);
		
		frame.setLocation(300, 300);
		
		panel.setPreferredSize(new Dimension(350, 300));
		
		GroupLayout groupLayout=new GroupLayout(panel);
		panel.setLayout(groupLayout);
	
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(serverButton,0,groupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(clientButton,0,groupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(serverButton,0,groupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
						.addComponent(clientButton,0,groupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public void setServerButtonListener(PatternAction severAction) {
		serverButton.addActionListener(severAction);
	}
	
	public void setClientButtonListner(PatternAction clientAction) {
		clientButton.addActionListener(clientAction);
	}
	
	public void close() {
		
		frame.setFocusable(false);
		frame.setVisible(false);
		frame.dispose();
	}
}
