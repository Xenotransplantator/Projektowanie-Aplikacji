package app;

import java.util.*;
import org.apache.log4j.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.freixas.jcalendar.JCalendarCombo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.l2fprod.common.swing.JOutlookBar;
import com.toedter.calendar.JCalendar;
import java.util.Calendar;

public class CenterAppPanel implements ActionListener {
	private CellConstraints cc;
	private DefaultTableCellRenderer renderer;
	private File saveFile;
	private FormLayout formLayout;
	private JButton additionCenterButton, calculateCenterButton, fillCenterButton,
					resetCenterButton, saveCenterButton, Wykres, Kalendarz;
	private JComboBox<String> comboBox;
	private JLabel calculations, columnNumberLabel, enterNumberLabel, rowNumberLabel;
	private JPanel borderPanel, centerAppPanel, container, resultPanel;
	private JSpinner columnSpinner, rowSpinner;
	public JTable table;
	private JTextArea resultArea;
	private JTextField enterNumberField;
	private Long max, min, result;
	private Long[][] tableData;
	private PrintWriter saveWriter;
	private SpinnerListModel columnsModel, rowModel;
	private String chosenOption;
	private String[] columnNames, options;
	private TitledBorder title;
	private boolean comboBoxUsage;
	private Border raisedetched, resultBorder;
	private List<Integer> oneFiveArray;
	private JCalendarCombo myCalendar;
	private Calendar calendar;
String CalendarDate;



/**
 * G³ówny panel centralny odpowiada za wyœwietlenie centralnego menu wraz z poszczególnymi akcjami
 * @author Piotr Sawala
 *
 */
	/**
	 * 
	 * Panel Centralny, komponenty panelu centralnego, oraz Action Listener'y
	 * 
	 */
	CenterAppPanel() {
	
		
	
		
		initComponents();
		createCenterAppPanel();
		setActionListeners();
		}
	
	/**
	 * 	Private method creating GUI objects
	 */
	private void initComponents() {
	
		
		// main center panel
		centerAppPanel = new JPanel();
		
		// labels
		enterNumberLabel = new JLabel("Wprowadz liczbe:");
		rowNumberLabel = new JLabel("Numer wiersza:");
		columnNumberLabel = new JLabel("Numer kolumny:");
		calculations = new JLabel("Oblicz:");
		
		// buttons
		additionCenterButton = new JButton("Dodaj");
		resetCenterButton = new JButton("Wyzeruj");
		fillCenterButton = new JButton("Wype³nij");
		saveCenterButton = new JButton("Zapisz");
		calculateCenterButton = new JButton("Oblicz");
		Wykres = new JButton("Wykres");
		
		
		Kalendarz = new JButton("Kalendarz");

		 myCalendar = new JCalendarCombo(
				Calendar.getInstance(),
				Locale.getDefault(),
				JCalendarCombo.DISPLAY_DATE,
				false
				);
				
		myCalendar.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		
		// right side alignment for enterNumberField
		enterNumberField = new JTextField();
		enterNumberField.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// save stuff
		saveFile = new File("tabela.txt");
		
		// creating and setting FormLayout
		formLayout = new FormLayout(
				"0dlu, 10dlu, 8dlu, 5dlu, 10dlu, 5dlu, 5dlu, 17dlu, 40dlu, 30dlu, pref:grow,"
				+ " 25dlu, 5dlu, 15dlu, 10dlu, 15dlu, 25dlu, pref:grow, 14dlu, 38dlu,"
				+ " 25dlu, 15dlu, 15dlu",
				"15dlu, 15dlu, 15dlu, 15dlu, 5dlu, 15dlu, 5dlu, 15dlu, 5dlu, 15dlu, 25dlu,"
				+ " 18dlu, 30dlu, 10dlu, pref:grow, 5dlu, 20dlu, pref:grow, 15dlu"); //dolny pasek
		centerAppPanel.setLayout(formLayout); 
		cc = new CellConstraints(); 
		
		// column and row spinners 1-5
		oneFiveArray = Arrays.asList(1,2,3,4,5);
		columnsModel = new SpinnerListModel(oneFiveArray);
		rowModel = new SpinnerListModel(oneFiveArray);
		columnSpinner = new JSpinner(rowModel);
		rowSpinner = new JSpinner(columnsModel);
		
		// disable writing in spinners
	    JFormattedTextField cS = ((JSpinner.DefaultEditor) columnSpinner.getEditor()).getTextField();
	    JFormattedTextField rS = ((JSpinner.DefaultEditor) rowSpinner.getEditor()).getTextField();
	    cS.setEditable(false);
	    cS.setBackground(Color.WHITE);
	    rS.setEditable(false);
	    rS.setBackground(Color.WHITE);
	    
		
		// table with "0" (5x5), right alignment of every cell
		columnNames = new String[]{"1","2","3","4","5"};
		tableData = new Long[][]{
			    {0l, 0l, 0l, 0l, 0l},
			    {0l, 0l, 0l, 0l, 0l},
			    {0l, 0l, 0l, 0l, 0l},
			    {0l, 0l, 0l, 0l, 0l},
			    {0l, 0l, 0l, 0l, 0l}
			};
		table = new JTable(tableData, columnNames);
		renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.RIGHT);
		table.setDefaultRenderer(Object.class, renderer);
		table.setEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
        table.setFillsViewportHeight(true);
		table.setRowHeight(20);
		
		// containter for table and tableHeader -> (1,2,3,4,5) on the top
		container = new JPanel(new BorderLayout());
		container.add(table.getTableHeader(), BorderLayout.NORTH);
		container.add(table, BorderLayout.CENTER);
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		container.setBorder(raisedetched);
		
		// result area -> (bottom of the centerAppPanel)
		borderPanel = new JPanel(new BorderLayout());
		resultPanel = new JPanel(new BorderLayout());
		resultArea = new JTextArea();
		resultArea.setFont(new Font(Font.DIALOG, Font.ITALIC, 28));
		resultArea.setEditable(false);
		resultBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
		title = BorderFactory.createTitledBorder(resultBorder, "Wynik");
		title.setTitleJustification(TitledBorder.CENTER);
		resultPanel.setBackground(Color.WHITE);
		resultPanel.add(resultArea, BorderLayout.EAST);
		borderPanel.add(resultPanel);
		borderPanel.setBorder(title);
		
		
		


		
		
		
		// comboBox
		options = new String[] {"Suma", "Œrednia", "Maksymalna wartoœæ", "Minimalna wartoœæ"};
		comboBox = new JComboBox<String>(options);
		comboBoxUsage = true;
	
	
	}
	/**
	 * 	Private method setting up every GUI object in centerAppPanel
	 */
	
	
	
	
	
	
	private void createCenterAppPanel() {
		
		// top of the centerAppPanel
		centerAppPanel.add(enterNumberLabel, cc.xyw(6,2,6,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(enterNumberField, cc.xyw(10,2,1,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(columnNumberLabel, cc.xyw(15,2,3,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(columnSpinner, cc.xyw(18,2,1,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(rowNumberLabel, cc.xyw(19,2,3,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(rowSpinner, cc.xyw(21,2,1,CellConstraints.FILL,CellConstraints.FILL));
		
		// table
		centerAppPanel.add(container, cc.xywh(6,4,14,7,CellConstraints.FILL,CellConstraints.FILL));
		
		// buttons on the right side
		centerAppPanel.add(additionCenterButton, cc.xyw(20,4,4,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(resetCenterButton, cc.xyw(20,6,4,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(fillCenterButton, cc.xyw(20,8,4,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(saveCenterButton, cc.xyw(20,10,4,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(Wykres, cc.xyw(20, 13, 3,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(Kalendarz, cc.xyw(20, 12, 4,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(myCalendar,cc.xyw(20, 11, 4,CellConstraints.FILL,CellConstraints.FILL));
		// down of the centerAppPanel
		centerAppPanel.add(calculations, cc.xyw(6,12,3,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(comboBox, cc.xyw(9,12,7,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(calculateCenterButton, cc.xyw(16,12,3,CellConstraints.FILL,CellConstraints.FILL));
		centerAppPanel.add(borderPanel, cc.xywh(2,14,21,5,CellConstraints.FILL,CellConstraints.FILL));
	
		
		
		
	
	
	}
	private void setActionListeners() {
		calculateCenterButton.addActionListener(this);
		comboBox.addActionListener(this);
		Wykres.addActionListener(this);
		Kalendarz.addActionListener(this);
	}
	
	/**
	 * 	Private method creating interactions 
	 * 	The business logic for the app is included here
	 */
	
	
	
	
	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource().equals(Kalendarz)) {
		
	calendar=myCalendar.getCalendar();
		CalendarDate=calendar.get(Calendar.YEAR) + "-";	
		int  calendarMonth = calendar.get(Calendar.MONTH) + 1;	
		if(calendarMonth<=9) {
		CalendarDate=CalendarDate+ "0"+String.valueOf(calendarMonth)+ "-";
			
				}
		else {
			
			CalendarDate= CalendarDate + String.valueOf(calendarMonth) + "-";
			
		}
		int	CalendarDay =calendar.get(Calendar.DAY_OF_MONTH);
		 if(CalendarDay<=9) {
			 CalendarDate=CalendarDate+"0"+String.valueOf(CalendarDay);
		 }
		 else {
			CalendarDate=CalendarDate+String.valueOf(CalendarDay);
			 
		 }
		resultArea.append("Data: "+ CalendarDate + "\n");	
		 
		}
		
		
		
		if (e.getSource().equals(Wykres)) {
			
			
			int pierwsza = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				long amount = (long) table.getValueAt(i, 0);
				pierwsza += amount;
			}

			int druga = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				long amount = (long) table.getValueAt(i, 1);
				druga += amount;
			}

			int trzecia = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				long amount = (long) table.getValueAt(i, 2);
				trzecia += amount;
			}

			int czwarta = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				long amount = (long) table.getValueAt(i, 3);
				czwarta += amount;
			}

			int piata = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				long amount = (long) table.getValueAt(i, 4);
				piata += amount;
			}

			
			
			
	
				DefaultPieDataset pieDataset = new DefaultPieDataset();
			pieDataset.setValue("Pierwsza kolumna",pierwsza);
			pieDataset.setValue("Druga kolumna", druga);
			pieDataset.setValue("Trzecia Kolumna", trzecia);
			pieDataset.setValue("Czwarta kolumna", czwarta);
			pieDataset.setValue("Pi¹ta kolumna", piata);
			JFreeChart chart = ChartFactory.createPieChart("Pie chart",pieDataset,true,true,true);
		chart.getPlot();
		
			ChartFrame frame = new ChartFrame("Pie Chart",chart);
			frame.setVisible(true);
			frame.setSize(450,500);
		}
	
			
			
			
		
		
		
		// actions of buttons on the right side
		if (e.getSource().equals(additionCenterButton)) {
			try {
				table.getModel().setValueAt(Long.valueOf(enterNumberField.getText()), 
					   ((int)rowSpinner.getValue()-1),
						(int)columnSpinner.getValue()-1);
				enterNumberField.setText("");}
			catch(NumberFormatException e1) {
				if(enterNumberField.getText().equals("")) {
					JOptionPane.showMessageDialog(centerAppPanel,
						    "Nie wprowadzono liczby do wypelnienia",
						    "No number error",
						    JOptionPane.ERROR_MESSAGE);
					}
				else {
					JOptionPane.showMessageDialog(centerAppPanel,
						    "Wprowadzono znak b³êdne dane !",
						    "Wrong input error",
						    JOptionPane.ERROR_MESSAGE);
					enterNumberField.setText("");
				}
			}
		}
	
		
		
		if (e.getSource().equals(resetCenterButton)) {
			for(int i = 0; i <= 4; i++) {
				for(int j = 0; j <= 4; j++) {
					table.getModel().setValueAt(0l, i, j);;
				}
			}
		}
		
		
		
		
		if (e.getSource().equals(fillCenterButton)) {
			try {
				for(int i = 0; i <= 4; i++) {
					for(int j = 0; j <= 4; j++) {
						table.getModel().setValueAt(Long.valueOf(enterNumberField.getText()),i,j);
					}
				}
				enterNumberField.setText("");
			}
			
			catch(NumberFormatException e1) {
				if(enterNumberField.getText().equals("")) {
					JOptionPane.showMessageDialog(centerAppPanel,
						    "Nie wprowadzono liczby do wypelnienia",
						    "No number error",
						    JOptionPane.ERROR_MESSAGE);
					}
				else {
					JOptionPane.showMessageDialog(centerAppPanel,
						    "Wprowadzono znak b³êdne dane !",
						    "Wrong input error",
						    JOptionPane.ERROR_MESSAGE);
					enterNumberField.setText("");
				}
			}
		}
		if (e.getSource().equals(saveCenterButton)) {
			try {
				saveFile.createNewFile();
				saveWriter = new PrintWriter(saveFile);
				for(int i = 0; i <= 4; i++) {
					for(int j = 0; j <= 4; j++) {
						saveWriter.print(table.getModel().getValueAt(i,j));
						saveWriter.append("");
					}
					saveWriter.println("\n");
				}
				saveWriter.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(centerAppPanel,
					    "B³¹d zapisu pliku",
					    "Save error",
					    JOptionPane.ERROR_MESSAGE);
			}			
		}
		
		// actions of checkBox + "calculate" button/ icon menu buttons / basic menu buttons
		if (e.getSource().equals(calculateCenterButton)) {
			result = 0l;
			if (comboBoxUsage)
				chosenOption = (String) comboBox.getSelectedItem();
			if (chosenOption.equals("Suma")) {
				for(int i = 0; i <= 4; i++) {
					for(int j = 0; j <= 4; j++) {
						result += (Long) table.getModel().getValueAt(i, j);
					}
				}
				resultArea.setText(Long.toString(result));
			}
			if (chosenOption.equals("Œrednia")) {
				for(int i = 0; i <= 4; i++) {
					for(int j = 0; j <= 4; j++) {
						result += (Long) table.getModel().getValueAt(i, j);
					}
				};
				resultArea.setText(Float.toString(result.floatValue()/25f));
			}
			if (chosenOption.equals("Maksymalna wartoœæ")) {
				max = (Long) table.getModel().getValueAt(1, 1);
				for(int i = 0; i <= 4; i++) {
					for(int j = 0; j <= 4; j++) {
						if (max < (Long) table.getModel().getValueAt(i, j)) {
							max = (Long) table.getModel().getValueAt(i, j);
						}
					}
				}
				resultArea.setText(Long.toString(max));
			}
			if (chosenOption.equals("Minimalna wartoœæ")) {
				min = (Long) table.getModel().getValueAt(1, 1);
				for(int i = 0; i <= 4; i++) {
					for(int j = 0; j <= 4; j++) {
						if (min > (Long) table.getModel().getValueAt(i, j)) {
							min = (Long) table.getModel().getValueAt(i, j);
						}
					}
				}
				resultArea.setText(Long.toString(min));
			}
			
		}
	}

	
	
	public JPanel getCenterAppPanel() {return centerAppPanel;}
	public JButton getAdditionCenterButton() {return additionCenterButton;}
	public JButton getResetCenterButton() {return resetCenterButton;}
	public JButton getFillCenterButton() {return fillCenterButton;}
	public JButton getSaveCenterButton() {return saveCenterButton;}
	public JButton getCalculateCenterButton() {return calculateCenterButton;}
	public void setComboBoxUsage(boolean argument) {comboBoxUsage = argument;}
	public void setChosenOption(String argument) {chosenOption = argument;}
	
	
	
}