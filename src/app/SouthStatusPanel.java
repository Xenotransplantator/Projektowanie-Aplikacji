package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SouthStatusPanel implements ActionListener {
	
	private JPanel southStatusPanel;
	private JTextField /* textField1*/ textField2;
	private String infoText;
	
	SouthStatusPanel() {
		initComponents();
		setActionListeners();
	}
	private void initComponents() {	
		// main south panel
		southStatusPanel = new JPanel();
		
		southStatusPanel.setLayout(new BoxLayout(southStatusPanel,BoxLayout.X_AXIS));
		southStatusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
		
		textField2 = new JTextField(15);
		textField2.setText("Version 1.0.6");
		textField2.setSize(100, 5);
		textField2.setEditable(false);
		textField2.setHorizontalAlignment(JTextField.RIGHT);
		textField2.setBorder(BorderFactory.createEmptyBorder());
		
		southStatusPanel.add(Box.createRigidArea(new Dimension(5,20)));
		southStatusPanel.add(textField2);
		southStatusPanel.add(Box.createRigidArea(new Dimension(5,20)));
	}
	private void setActionListeners() {
	//	textField1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
	//	if(e.getSource().equals(textField1)) {
	//		textField1.setText(infoText);
		}
	//}
	public void setOption(String argument) {infoText = argument;}
	//public JTextField getTextField1() {return textField1;}
	public JPanel getSouthStatusPanel() {return southStatusPanel;}
}
