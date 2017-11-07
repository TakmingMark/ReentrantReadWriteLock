import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;

public class ServerView{
	private JFrame jFrame;
	private JPanel jPanel;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;
	
	private ServerView() {	
		initView();
	}
	
	public static ServerView getServerViewObject() {
		return new ServerView();
	}	
		
	private void initView() {
		jFrame=new JFrame("Server");
		jPanel=new JPanel();
		jTextArea=new JTextArea(13,23);
		jScrollPane=new JScrollPane(jTextArea);

		jPanel.setPreferredSize(new Dimension(350, 300));
		
		jTextArea.setFont(jTextArea.getFont().deriveFont(16f));
		jTextArea.setEditable(false);
		jFrame.setLocation(300, 300);
		
		GroupLayout groupLayout=new GroupLayout(jPanel);
		jPanel.setLayout(groupLayout);
	
		groupLayout.setHorizontalGroup(
				groupLayout.createSequentialGroup()
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jScrollPane,0,groupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		
		groupLayout.setVerticalGroup(
				groupLayout.createSequentialGroup()
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup()
						.addComponent(jScrollPane,0,groupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
	                     GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		
		jFrame.add(jPanel);
		jFrame.pack();
		jFrame.setFocusable(true);
		jFrame.setEnabled(true);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jTextArea.setText("sadfsdf");
	}
	
	public void close() {
		jFrame.setVisible(false);
		jFrame.dispose();
	}

	public void updatejTextArea(String inputMsg) {
		jTextArea.append(inputMsg);
	}
}
