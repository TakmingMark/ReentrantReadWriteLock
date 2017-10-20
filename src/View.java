import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import oracle.jrockit.jfr.JFR;

public class View {
	public JFrame jFrame;
	public JPanel jPanel;
	public JTextArea jTextArea;
	public JButton readButton,cancelReadButton,writeButton,cancelWriteButton;
	
	private View() {	
		initView();
	}
	
	public static View getViewObject() {
		return new View();
	}
	
	private void initView() {
		jFrame=new JFrame("ReentrantReakWriteLock");
		jPanel=new JPanel();
		jTextArea=new JTextArea();
		readButton=new JButton("Read");
		cancelReadButton=new JButton("CancelRead");
		writeButton=new JButton("Write");
		cancelWriteButton=new JButton("CancelWrite");
		
		jFrame.setLocation(300, 300);
		
		jPanel.setPreferredSize(new Dimension(350, 300));

		GroupLayout groupLayout=new GroupLayout(jPanel);
		jPanel.setLayout(groupLayout);
	
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup()
							.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
						                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(jTextArea,0,300,GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
						                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									)
							.addGroup(groupLayout.createSequentialGroup()
									 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
						                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
											.addComponent(readButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
											.addComponent(cancelReadButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
											)
									 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
						                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
											.addComponent(writeButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
											.addComponent(cancelWriteButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
											)
									 .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
						                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									)
							)
				);
		
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
		                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jTextArea,0,200,GroupLayout.PREFERRED_SIZE)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(readButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
							.addComponent(writeButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
							)
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup()
							.addComponent(cancelReadButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
							.addComponent(cancelWriteButton,0,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
							)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
		                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
