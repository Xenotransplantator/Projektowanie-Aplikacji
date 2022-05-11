package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class InfoAboutWindow extends JDialog { //O okienku zawieraj¹ce JDialog

	private static final int DEFAULT_WIDTH = 610;
	private static final int DEFAULT_HEIGHT = 400;
	
	private static final long serialVersionUID = 1L;
	private JLabel fishIcon, nameLabel, mailLabel, githubLabel,
				   phoneLabel, copyrightLabel, quoteLabel, titleLabel,
				   versionLabel; //Jlabel O
	private JPanel infoPanel; // Definicja JPanel
	private Font font, font2, font3, font4; //Definicja Font'ów
	private Dimension frameSize, screenSize;
					  Border blackline, empty; //rozmiar ramki, rozmiar ekranu i border czarnoliniowy pusty
	TitledBorder title; //tytu³ ramki
	
	
	InfoAboutWindow() {
		createFrame();
		initComponents();
		createInfoPanel();
		
		this.add(infoPanel, BorderLayout.CENTER); //info panel centralny
		this.add(fishIcon, BorderLayout.WEST); //ikona ryby po prawo
		this.add(copyrightLabel, BorderLayout.SOUTH); //prawo autorskie na dole
	}
	
	private void createFrame() { //utworz ramke
		frameSize = new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);		 //rozmiar ramki domylna szerokosc i wysokosc	
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();//pobranie rozdzielczosci pulpitu 
		if(frameSize.height > screenSize.height) frameSize.height = screenSize.height; // frame size mniejszy od ekranu wyrownanie
		if(frameSize.width > screenSize.width) frameSize.width = screenSize.width; // szerokosc od ekranu wyrowanie
		
		this.setTitle("Informacje o autorze"); //Informacje o autorze
		this.setIconImage(new ImageIcon("res/24x24/info-icon-24.png").getImage()); // Image Icon ustawienie
		this.setSize(frameSize); //ustawienie rozmiaru ramki
		this.setResizable(false); //nie resizable tzn. nie rozci¹galny
		this.setLocation((screenSize.width-frameSize.width)/2, (screenSize.height-frameSize.height)/2); //screen size szerokosc i wysokosc wyrowana do ekranu
		this.setLayout(new BorderLayout()); //definicja border layout
	}
	private void initComponents() { //komponenty
		
		// main info panel
		infoPanel = new JPanel(); //utworzenie info Panela
		
		// labels
		fishIcon = new JLabel(new ImageIcon("res/ripper.png")); // ikona ryby nowy label ikona ryby
		titleLabel = new JLabel("PROJEKTOWANIE APLIKACJI"); //tytul aplikacja XYZ
		versionLabel = new JLabel("version 1.0.6"); // nazwa wersji
		nameLabel = new JLabel("Piotr Sawala"); //nazwa autora
		mailLabel = new JLabel("adres @mail: p.sawala99@wp.pl"); //nazwa @mail
		phoneLabel = new JLabel("Phone: 123-456-789"); // Jlabel z numerem telefonu
	
		copyrightLabel = new JLabel("Copyright © 2021. All rights reserved"); //Prawa autorskie
	
		
		// borders
		empty = BorderFactory.createEmptyBorder(); // stworz pusta ramke
		title = BorderFactory.createTitledBorder(empty, "Jonathan Swift"); //pusta ramka z napisem Jonathan swift
		blackline = BorderFactory.createLineBorder(Color.black); //czarny border 
		title.setTitlePosition(TitledBorder.BOTTOM); //tytul pozycja dolna

		copyrightLabel.setBorder(blackline); //ustwienie lini carny
		
		// fonts
		font = new Font(Font.DIALOG, Font.CENTER_BASELINE, 20); //czcionka dialog na srodkowej lini o wielkosci 20px
		font2 = new Font(Font.DIALOG, Font.CENTER_BASELINE, 15);
		font3 = new Font(Font.SERIF, Font.ITALIC, 15);
		font4 = new Font(Font.DIALOG, Font.BOLD, 14); 
		
		titleLabel.setFont(font); //ustawienie pierwszego fontu do tytulu
		versionLabel.setFont(font4);
		nameLabel.setFont(font2);
		mailLabel.setFont(font2);
		phoneLabel.setFont(font2);
	
	}
	private void createInfoPanel() { //utworz panel informacyjny
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); 
		infoPanel.add(Box.createRigidArea(new Dimension(0,20)));
		infoPanel.add(titleLabel);
		infoPanel.add(Box.createRigidArea(new Dimension(0,10)));
		infoPanel.add(versionLabel);
		infoPanel.add(Box.createRigidArea(new Dimension(0,40)));
		infoPanel.add(nameLabel);
		infoPanel.add(Box.createRigidArea(new Dimension(0,15)));
		infoPanel.add(mailLabel);
		infoPanel.add(Box.createRigidArea(new Dimension(0,15)));
		infoPanel.add(phoneLabel);
	
	}
}