package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class HelpWindow extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_WIDTH = 700;
	private static final int DEFAULT_HEIGHT = 600;
	private Dimension frameSize, screenSize;
	private JPanel helpPanel, northPanel, centerPanel, southPanel;
	private JLabel text1, text2, text3, text4, text9;
	
	HelpWindow() {
		initComponents();
		createFrame();
		
		this.add(helpPanel, BorderLayout.CENTER);
	}
	
	private void createFrame() {
		frameSize = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);			
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pobranie rozdzielczosci pulpitu 
		if(frameSize.height > screenSize.height) frameSize.height = screenSize.height;
		if(frameSize.width > screenSize.width) frameSize.width = screenSize.width;	
		this.setSize(frameSize);	
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		this.setTitle("Pomoc");
		this.setIconImage(new ImageIcon("res/24x24/help-icon-24.png").getImage());
	}
	private void initComponents() {
		northPanel = new JPanel();
		helpPanel = new JPanel();
		centerPanel = new JPanel();
		southPanel = new JPanel();
		
		text1 = new JLabel("Okno pomocy"); 
		text2 = new JLabel("1.Specyfikacja");
		text3 = new JLabel("2.Zastosowanie");
		text4 = new JLabel("3.Wykorzystane operacje");
		text9 = new JLabel("Pomoc Technczna:p.sawala99@wp.pl");
		
		text1.setFont(new Font(Font.DIALOG, Font.ITALIC, 28));
		text2.setFont(new Font(Font.DIALOG, Font.ITALIC, 24));
		text3.setFont(new Font(Font.DIALOG, Font.ITALIC, 24));
		text4.setFont(new Font(Font.DIALOG, Font.ITALIC, 24));
		
		
		northPanel.setLayout(new BorderLayout());
		northPanel.add(text1);
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(Box.createRigidArea(new Dimension(0,40)));
		centerPanel.add(text2);
		centerPanel.add(Box.createRigidArea(new Dimension(0,20)));
		centerPanel.add(text3);
		centerPanel.add(Box.createRigidArea(new Dimension(0,20)));
		centerPanel.add(text4);
		
		southPanel.add(text9);
		southPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		helpPanel.setLayout(new BorderLayout());
		helpPanel.add(northPanel, BorderLayout.NORTH);
		helpPanel.add(centerPanel, BorderLayout.CENTER);
		helpPanel.add(southPanel, BorderLayout.SOUTH);
		
	}
}
