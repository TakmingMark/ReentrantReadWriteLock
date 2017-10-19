import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
		jFrame=new JFrame();
		jPanel=new JPanel();
		jTextArea=new JTextArea();
		readButton=new JButton("Read");
		cancelReadButton=new JButton("CancelRead");
		writeButton=new JButton("Write");
		cancelWriteButton=new JButton("CancelWrite");
		
		GroupLayout groupLayout=new GroupLayout(jPanel);
		jPanel.setLayout(groupLayout);
		
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup()
							.addComponent(jTextArea)
							.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
											.addComponent(readButton,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE,GroupLayout.PREFERRED_SIZE)
											.addComponent(cancelReadButton)
											)
									.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
											.addComponent(writeButton)
											.addComponent(cancelWriteButton)
											)
									)
							)	
				);
		
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
					.addComponent(jTextArea)
					.addGroup(groupLayout.createParallelGroup()
							.addComponent(readButton)
							.addComponent(writeButton)
							)
					.addGroup(groupLayout.createParallelGroup()
							.addComponent(cancelReadButton)
							.addComponent(cancelWriteButton)
							)
				);
		jPanel.setSize(new Dimension(300, 400));
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initView2() {
		jFrame=new JFrame();
		jPanel=new JPanel();
		jTextArea=new JTextArea();
		readButton=new JButton("Read");
		cancelReadButton=new JButton("CancelRead");
		writeButton=new JButton("Write");
		cancelWriteButton=new JButton("CancelWrite");
		
		GroupLayout groupLayout=new GroupLayout(jPanel);
		jPanel.setLayout(groupLayout);
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup()
				.addComponent(jTextArea)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(readButton)
						.addComponent(writeButton)
				)	
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createSequentialGroup()
				.addComponent(jTextArea)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(readButton)
						.addComponent(writeButton)
				)
		);
//		
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
