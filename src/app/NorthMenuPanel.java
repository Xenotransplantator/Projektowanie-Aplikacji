package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;


public class NorthMenuPanel implements ActionListener { //Górny Panel Z zaimplementowanym Action Listener'em
	JButton additionMenuButton, helpMenuButton, resetMenuButton,
			fillMenuButton, saveMenuButton, sumMenuButton,
			infoAboutAuthorButton, averageMenuButton, maxMenuButton,
			minMenuButton; //Dodanie Buttonów
	JMenuBar northMenuPanel, menuBar; // Dodanie Górnego panela i menuBar'a
	JMenu fileMenu, editMenu, windowMenu, calculationsMenu, helpMenu; // Dodanie JMenu
	JMenuItem additionMenuItem, resetMenuItem, saveMenuItem, fillMenuItem, 
			  sumMenuItem, averageMenuItem, maxMenuItem, minMenuItem,
			  zoomInMenuItem, helpMenuItem, infoAboutAuthorMenuItem,TestMenu; //JMenu itemy z Jmenu
	JToolBar myToolBar; // Dodanie JtoolBar'a
	
	NorthMenuPanel() {
		initComponents();
		createToolBar();
		setActionListeners();
		
		northMenuPanel.add(menuBar, BorderLayout.NORTH); //Dodanie menu bara na górze
		northMenuPanel.add(myToolBar, BorderLayout.SOUTH); //Dodanie Tool Bar'a na dole
	}
	private void initComponents() { // komponenty
		
		// main center panel
		northMenuPanel = new JMenuBar(); // stworzenie menu panela
		northMenuPanel.setLayout(new BorderLayout()); //ustawienie border layouta
		
		// menu bar menus and mnemonic
		menuBar = new JMenuBar(); //stworzenie menu Bar'a
		fileMenu = new JMenu("Plik"); //stworzenie nowego Jmenu o nazwie Plik
		calculationsMenu = new JMenu("Obliczenia"); //Stworzenie nowego menu Obliczenia
		windowMenu = new JMenu("Widok"); //stworzenie widoku
		helpMenu = new JMenu("Pomoc"); //stworzenie Pomoc
	
		
		// file menu items and mnemonic
		additionMenuItem = new JMenuItem("Dodaj liczbe", new ImageIcon("res/16x16/add.png")); //menuItem dodaj liczbê z ikon¹
		resetMenuItem = new JMenuItem("Wyzeruj", new ImageIcon("res/16x16/broom-icon-16.png")); //menuItem Wyzeruj macie¿ z image icon
		fillMenuItem = new JMenuItem("Wypelnij tabele", new ImageIcon("res/16x16/fill-icon-16.png"));//MenuItem wypelnij macierz
		saveMenuItem = new JMenuItem("Zapisz", new ImageIcon("res/16x16/save-icon-16.png")); //Menu Zapisz
		

		
		fileMenu.add(additionMenuItem); //dodanie menu itemu dodaj
		fileMenu.add(resetMenuItem); //dodanie menu resetu
		fileMenu.add(fillMenuItem); //
		fileMenu.add(saveMenuItem);
		
		
		
		
		// calculations menu items and mnemonic
		sumMenuItem = new JMenuItem("Suma", new ImageIcon("res/16x16/rsz_suma.png")); //Menu item suma 
		averageMenuItem = new JMenuItem("srednia", new ImageIcon("res/16x16/rsz_avarage.png")); //menu Item srednia
		maxMenuItem = new JMenuItem("Maksymalna wartosc", new ImageIcon("res/16x16/max-icon-16.png")); //menu Item Max
		minMenuItem = new JMenuItem("Minimalna wartosc", new ImageIcon("res/16x16/rsz_minimum.png")); // menu item minimm
		

		
		
		calculationsMenu.add(sumMenuItem);
		calculationsMenu.add(averageMenuItem);
		calculationsMenu.add(maxMenuItem);
		calculationsMenu.add(minMenuItem);
		
		// window menu items and mnemonic
		
		zoomInMenuItem = new JMenuItem("Powieksz okno", new ImageIcon("res/16x16/zoom-icon-16.png"));
		zoomInMenuItem.setMnemonic(KeyEvent.VK_Z);
		windowMenu.add(zoomInMenuItem);
		
		
		
		// help menu items and mnemonic
		helpMenuItem = new JMenuItem("Pomoc", new ImageIcon("res/16x16/rsz_help.png"));
		infoAboutAuthorMenuItem = new JMenuItem("Informacje o autorze", 
								  new ImageIcon("res/16x16/rsz_info.png"));
		//helpMenuItem.setMnemonic(KeyEvent.VK_M);
		//infoAboutAuthorMenuItem.setMnemonic(KeyEvent.VK_I);
		
		helpMenu.add(helpMenuItem);
		helpMenu.add(infoAboutAuthorMenuItem);
		
	
		// adding menus to menu bar
		menuBar.add(fileMenu);
		menuBar.add(calculationsMenu);
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);
		
		// creating tool bar (icon menu)
		myToolBar = new JToolBar();
		additionMenuButton = new JButton(new ImageIcon("res/24x24/add.png"));
		resetMenuButton = new JButton(new ImageIcon("res/24x24/broom-icon-24.png"));
		fillMenuButton = new JButton(new ImageIcon("res/24x24/fill-icon-24.png"));
		saveMenuButton = new JButton(new ImageIcon("res/24x24/save-icon-24.png"));
		sumMenuButton = new JButton(new ImageIcon("res/24x24/suma.png"));
		averageMenuButton = new JButton(new ImageIcon("res/24x24/avarage.png"));
		maxMenuButton = new JButton(new ImageIcon("res/24x24/max-icon-24.png"));
		minMenuButton = new JButton(new ImageIcon("res/24x24/minimum.png"));
		helpMenuButton = new JButton(new ImageIcon("res/24x24/help.png"));
		infoAboutAuthorButton = new JButton(new ImageIcon("res/24x24/info.png"));
		
		
		// opis itemów toolbar
		additionMenuButton.setToolTipText("Dodaj liczbe");
		resetMenuButton.setToolTipText("Wyzeruj");
		fillMenuButton.setToolTipText("WypeÅ‚nij");
		saveMenuButton.setToolTipText("Zapisz");
		sumMenuButton.setToolTipText("Suma");
		averageMenuButton.setToolTipText("Åšrednia");
		maxMenuButton.setToolTipText("Maksymalna wartoÅ›Ä‡");
		minMenuButton.setToolTipText("Minimalna wartoÅ›Ä‡");
		helpMenuButton.setToolTipText("Pomoc");
		infoAboutAuthorButton.setToolTipText("Informacje o autorze");
	}
	/*
	 * 	private method that creates toolbar
	 */
	private void createToolBar() {
		myToolBar.add(additionMenuButton);
		myToolBar.add(resetMenuButton);
		myToolBar.add(fillMenuButton);
		myToolBar.add(saveMenuButton);
		myToolBar.addSeparator();
		myToolBar.add(sumMenuButton);
		myToolBar.add(averageMenuButton);
		myToolBar.add(maxMenuButton);
		myToolBar.add(minMenuButton);
		myToolBar.addSeparator();
		myToolBar.add(helpMenuButton);
		myToolBar.add(infoAboutAuthorButton);
		myToolBar.setFloatable(false);
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
		
		// tool bar buttons (icon menu)
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
	@Override
	public void actionPerformed(ActionEvent e) {	}
	
	public JMenuBar getNorthMenuPanel() {return northMenuPanel;}
	
	public JButton getAdditionMenuButton() {return additionMenuButton;}
	public JButton getResetMenuButton() {return resetMenuButton;}
	public JButton getFillMenuButton() {return fillMenuButton;}
	public JButton getSaveMenuButton() {return saveMenuButton;}
	
	public JButton getSumMenuButton() {return sumMenuButton;}
	public JButton getAverageMenuButton() {return averageMenuButton;}
	public JButton getMaxMenuButton() {return maxMenuButton;}
	public JButton getMinMenuButton() {return minMenuButton;}
	
	public JButton getHelpMenuButton() {return helpMenuButton;}
	public JButton getInfoAboutAuthorButton() {return infoAboutAuthorButton;}
	
	public JMenuItem getAdditionMenuItem() {return additionMenuItem;}
	public JMenuItem getResetMenuItem() {return resetMenuItem;}
	public JMenuItem getFillMenuItem() {return fillMenuItem;}
	public JMenuItem getSaveMenuItem() {return saveMenuItem;}
	
	public JMenuItem getSumMenuItem() {return sumMenuItem;}
	public JMenuItem getAverageMenuItem() {return averageMenuItem;}
	public JMenuItem getMaxMenuItem() {return maxMenuItem;}
	public JMenuItem getMinMenuItem() {return minMenuItem;}
	
	public JMenuItem getZoomInMenuItem() {return zoomInMenuItem;}
	public JMenuItem getHelpMenuItem() {return helpMenuItem;}
	public JMenuItem getInfoAboutAuthorMenuItem() {return infoAboutAuthorMenuItem;}
}
