import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class View {
	public JFrame jFrame;
	public JPanel jPanel;
	public JTextArea jTextArea;
	public JButton ReadButton,cancelReadButton,writeButton,cancelWriteButton;
	
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
		ReadButton=new JButton("Read");
		cancelReadButton=new JButton("CancelRead");
		writeButton=new JButton("Write");
		cancelWriteButton=new JButton("CancelWrite");
		
		GroupLayout groupLayout=new GroupLayout(jPanel);
		jPanel.setLayout(groupLayout);
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(groupLayout.createParallelGroup())
				.addComponent(jTextArea,100,200,300)
				.addComponent(component)
				);
	}
}
