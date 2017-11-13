import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

public class PatternView {
	public JFrame jFrame;
	public JPanel jPanel;
	public JButton serverButton,clientButton;
	
	private PatternView() {	
		initView();
	}
	
	public static PatternView getPatternViewObject() {
		return new PatternView();
	}	
		
	private void initView() {
		jFrame=new JFrame("Pattern Option");
		jPanel=new JPanel();
		serverButton=new JButton("Server");
		clientButton=new JButton("Client");
		
		jFrame.setLocation(300, 300);
		
		jPanel.setPreferredSize(new Dimension(350, 300));
		
		GroupLayout groupLayout=new GroupLayout(jPanel);
		jPanel.setLayout(groupLayout);
	
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
		
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setServerButtonListener(PatternAction severAction) {
		serverButton.addActionListener(severAction);
	}
	
	public void setClientButtonListner(PatternAction clientAction) {
		clientButton.addActionListener(clientAction);
	}
	
	public void close() {
		
		jFrame.setFocusable(false);
		jFrame.setVisible(false);
		jFrame.dispose();
	}
}
