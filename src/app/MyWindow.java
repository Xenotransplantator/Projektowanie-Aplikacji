package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;

import org.apache.log4j.BasicConfigurator;
import org.freixas.jcalendar.JCalendarCombo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.JTaskPane;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.JTipOfTheDay;
import com.l2fprod.common.swing.tips.DefaultTip;
import com.l2fprod.common.swing.tips.DefaultTipModel;
import org.apache.log4j.*;
//import com.toedter.calendar.JCalendar;
//import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooser;

/**
 * 
 * 
 * @author Piotr
 *
 */

public class MyWindow extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static int DEFAULT_WIDTH = 850;
	private static int DEFAULT_HEIGHT = 570;
	private NorthMenuPanel nMP;
	private CenterAppPanel cAP;
	private SouthStatusPanel sSP;
	private Dimension frameSize, screenSize;
	private JButton additionCenterButton, additionMenuButton, sumMenuButton,
					calculateCenterButton, resetCenterButton, resetMenuButton, fillCenterButton,
					fillMenuButton, saveCenterButton, saveMenuButton, averageMenuButton, 
					maxMenuButton, minMenuButton, helpMenuButton, infoAboutAuthorButton;
	private JMenuItem additionMenuItem, resetMenuItem, fillMenuItem, saveMenuItem,
					  sumMenuItem, averageMenuItem, maxMenuItem, minMenuItem, zoomInMenuItem, helpMenuItem,
					  infoAboutAuthorMenuItem;
	private String chosenOption;
	private JTextField textField1;
	
	
	
	MyWindow() {
		
		
	
		
		
	 DefaultTipModel tipModel = new DefaultTipModel();
		tipModel.add(new DefaultTip("Tip1", "Quick and dirty zeby pisac, trzeba pisac."));
	tipModel.add(new DefaultTip("Tip2","Jezeli nauczysz sie pracowac z gitem, to nigdy nie bedziesz sobie wyobrazal pracy bez niego "));
		tipModel.add(new DefaultTip ("Tip3","Jak robisz kanapke to najpierw kladz szynke a potem ser bo jak kanapka spadnie to zawsze mozna podniesc szynke"));
		
		JTipOfTheDay porady = new JTipOfTheDay(tipModel);
		porady.showDialog(this);
		
	
		
	
	
		

		
		
		
  JOutlookBar outlookBar = new JOutlookBar();
		outlookBar.setBounds(0, 69, 67, 315);
		getContentPane().add(outlookBar);
		
		
	
	
		createFrame(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLayoutPanels();
		setGettersNames();
		setActionListeners();
	}
	private void createFrame(int defaultWidth, int defaultHeight) {
		frameSize = new Dimension(defaultWidth,defaultHeight);			
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pobranie rozdzielczosci pulpitu 
		if(frameSize.height > screenSize.height) frameSize.height = screenSize.height;
		if(frameSize.width > screenSize.width) frameSize.width = screenSize.width;
		
		this.setSize(frameSize);	
		//this.setMinimumSize(new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT));
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Aplikacja made by Piotr Sawala v1.0.6");
	//	this.setIconImage(new ImageIcon("res/mainapp-icon-72.png").getImage());
	}
	private void setLayoutPanels() {
		nMP = new NorthMenuPanel();
		cAP = new CenterAppPanel();
		sSP = new SouthStatusPanel();
		
		this.add(nMP.getNorthMenuPanel(), BorderLayout.NORTH);
		this.add(cAP.getCenterAppPanel(), BorderLayout.CENTER);
		this.add(sSP.getSouthStatusPanel(), BorderLayout.SOUTH);
	}
	private void setGettersNames() {
		//menu items
		additionMenuItem = nMP.getAdditionMenuItem();
		resetMenuItem = nMP.getResetMenuItem();
		fillMenuItem = nMP.getFillMenuItem();
		saveMenuItem = nMP.getSaveMenuItem();
		
		sumMenuItem = nMP.getSumMenuItem();
		averageMenuItem = nMP.getAverageMenuItem();
		maxMenuItem = nMP.getMaxMenuItem();
		minMenuItem = nMP.getMinMenuItem();
		
		zoomInMenuItem = nMP.getZoomInMenuItem();
		
		helpMenuItem = nMP.getHelpMenuItem();
		infoAboutAuthorMenuItem = nMP.getInfoAboutAuthorMenuItem();
		
		// center buttons
		additionCenterButton = cAP.getAdditionCenterButton();
		resetCenterButton = cAP.getResetCenterButton();
		fillCenterButton = cAP.getFillCenterButton();
		saveCenterButton = cAP.getSaveCenterButton();
		calculateCenterButton = cAP.getCalculateCenterButton();
		
		// icon menu buttons
		additionMenuButton = nMP.getAdditionMenuButton();
		resetMenuButton = nMP.getResetMenuButton();
		fillMenuButton = nMP.getFillMenuButton();
		saveMenuButton = nMP.getSaveMenuButton();
		
		sumMenuButton = nMP.getSumMenuButton();
		averageMenuButton = nMP.getAverageMenuButton();
		maxMenuButton = nMP.getMaxMenuButton();
		minMenuButton = nMP.getMinMenuButton();	
		
		helpMenuButton = nMP.getHelpMenuButton();
		infoAboutAuthorButton = nMP.getInfoAboutAuthorButton();
		
//table
//		cAP.getTable();
	}	
	private void setActionListeners() {
		
		// menu bar items
		additionMenuItem.addActionListener(this);
		resetMenuItem.addActionListener(this);
		fillMenuItem.addActionListener(this);
		saveMenuItem.addActionListener(this);
		
		sumMenuItem.addActionListener(this);
		averageMenuItem.addActionListener(this);
		maxMenuItem.addActionListener(this);
		minMenuItem.addActionListener(this);
		
		zoomInMenuItem.addActionListener(this);
		
		helpMenuItem.addActionListener(this);
		
		infoAboutAuthorMenuItem.addActionListener(this);
		
		// center buttons
		additionCenterButton.addActionListener(this);
		fillCenterButton.addActionListener(this);
		resetCenterButton.addActionListener(this);
		saveCenterButton.addActionListener(this);
		calculateCenterButton.addActionListener(this);
		
		// icon menu buttons
		additionMenuButton.addActionListener(this);
		resetMenuButton.addActionListener(this);
		fillMenuButton.addActionListener(this);
		saveMenuButton.addActionListener(this);
		
		sumMenuButton.addActionListener(this);
		averageMenuButton.addActionListener(this);
		maxMenuButton.addActionListener(this);
		minMenuButton.addActionListener(this);
		
		helpMenuButton.addActionListener(this);
		
		infoAboutAuthorButton.addActionListener(this);
		
	
	}
	private void setSouthInfoPanel(ActionEvent e, String infoText) {
		sSP.setOption(infoText);
		e.setSource(textField1);
		sSP.actionPerformed(e);
	} 
	private void setButtonsActions(ActionEvent e, JButton centralButton, String infoText) {
		setSouthInfoPanel(e, infoText);
		e.setSource(centralButton);
		cAP.actionPerformed(e);
	}
	private void setComboBoxAction(ActionEvent e, JButton centralButton, String infoText) {
		setSouthInfoPanel(e, infoText);
		cAP.setComboBoxUsage(false);
		cAP.setChosenOption(infoText);
		e.setSource(centralButton);
		cAP.actionPerformed(e);
		cAP.setComboBoxUsage(true);
	}
	
	
	
	
	
	
	
	
	/*
	 * 	public method that redirects every action from NorthMenuPanel, SouthStatusPanel to CenterAppPanel
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(additionMenuButton) || e.getSource().equals(additionMenuItem) || 
			e.getSource().equals(additionCenterButton)) {
			setButtonsActions(e, additionCenterButton, "Dodawanie");
		}
		if (e.getSource().equals(resetMenuButton) || e.getSource().equals(resetMenuItem) || 
			e.getSource().equals(resetCenterButton)) {
			setButtonsActions(e, resetCenterButton, "Wyzerowanie");
		}
		if (e.getSource().equals(fillMenuButton) || e.getSource().equals(fillMenuItem) || 
			e.getSource().equals(fillCenterButton)) {
			setButtonsActions(e, fillCenterButton, "Wype³nienie");
		}
		if (e.getSource().equals(saveMenuButton) || e.getSource().equals(saveMenuItem) || 
			e.getSource().equals(saveCenterButton)) {
			setButtonsActions(e, saveCenterButton, "Zapis");
		}
		if (e.getSource().equals(sumMenuButton) || e.getSource().equals(averageMenuButton) || 
			e.getSource().equals(maxMenuButton)	|| e.getSource().equals(minMenuButton) ||
			e.getSource().equals(sumMenuItem) || e.getSource().equals(averageMenuItem) || 
			e.getSource().equals(maxMenuItem)	|| e.getSource().equals(minMenuItem)) {
			if(e.getSource().equals(sumMenuButton) || e.getSource().equals(sumMenuItem)) {
				chosenOption = "Suma";
			}
			else if(e.getSource().equals(averageMenuButton) || e.getSource().equals(averageMenuItem)) {
				chosenOption = "œrednia";
			}
			else if(e.getSource().equals(maxMenuButton) || e.getSource().equals(maxMenuItem)) {
				chosenOption = "Maksymalna wartoœæ";
			}
			else {
				chosenOption = "Minimalna wartoœæ";
			}
			setComboBoxAction(e, calculateCenterButton, chosenOption);
			}
		if (e.getSource().equals(zoomInMenuItem)) {	
			setSouthInfoPanel(e, "Powiêkszanie okna");
			DEFAULT_WIDTH += 50;
			DEFAULT_HEIGHT += 50;
			createFrame(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		}
		if (e.getSource().equals(helpMenuButton) || e.getSource().equals(helpMenuItem)) {
			setSouthInfoPanel(e, "Okno pomocy");
			HelpWindow helpWindow = new HelpWindow();
			helpWindow.setVisible(true);
		}
		if (e.getSource().equals(infoAboutAuthorButton) || e.getSource().equals(infoAboutAuthorMenuItem)) {
			setSouthInfoPanel(e, "Informacje o autorze");
			InfoAboutWindow infoWindow = new InfoAboutWindow();
			infoWindow.setVisible(true);
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			MyWindow myFrame = new MyWindow();
			myFrame.setVisible(true);
		});
	}
}