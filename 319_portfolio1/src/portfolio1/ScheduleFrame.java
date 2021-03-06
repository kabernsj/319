package portfolio1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ScheduleFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtFieldActivity1;
	private JTextField txtFieldActivity2;
	private JTextField txtFieldActivity3;
	private JTextField txtFieldActivity4;
	private JTextField txtFieldActivity5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScheduleFrame frame = new ScheduleFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public ScheduleFrame() {
		setTitle("Schedule Display");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel schedulePanel = new JPanel();
		schedulePanel.setBounds(10, 53, 1164, 275);
		contentPane.add(schedulePanel);
		schedulePanel.setLayout(new GridLayout(8, 16, 0, 0));
		
		JLabel invisLabel = new JLabel("");
		
		JLabel[] dayLabels = getDayLabels();
		JLabel[] timeLabels = getTimeLabels();
		JPanel[][] schedulePanels = getSchedulePanels();
		
		schedulePanel.add(invisLabel);
		
		JLabel lblScheduleDisplay = new JLabel("Schedule Display");
		lblScheduleDisplay.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblScheduleDisplay.setBounds(10, 11, 239, 39);
		contentPane.add(lblScheduleDisplay);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 339, 1164, 1);
		contentPane.add(separator);
		
		JPanel entryPanel = new JPanel();
		entryPanel.setBounds(10, 351, 1164, 162);
		contentPane.add(entryPanel);
		entryPanel.setLayout(null);
		
		fillEntryPanel(entryPanel, schedulePanels);
		
		for (JLabel label : timeLabels) {
			schedulePanel.add(label);
		}
		
		for (int i = 0; i < 7; i++) {
			schedulePanel.add(dayLabels[i]);
			for (int j = 0; j < 15; j++) {
				schedulePanel.add(schedulePanels[i][j]);
			}
		}
		
	}

	private void displayActivity(JPanel panel, Color color, String activityName) {	
		//store label
		JLabel eventLabel = (JLabel) panel.getComponent(0);
		
		//remove check
		if(activityName.equals("REMOVE"))
		{
			panel.setBackground(new Color(238, 238, 238));
			eventLabel.setText("");
		}
		
		//conflict check
		else if(!eventLabel.getText().equals(""))
		{
			panel.setBackground(Color.red);
			eventLabel.setText("CONFLICTED");
		}
		//if no conflict
		else
		{
			panel.setBackground(color);
			eventLabel.setText(activityName);
		}
	}
	
	private void processEntry(JPanel[][] schedulePanels, String activityName, boolean[] days, String startTime, String endTime, String Color) {
		int start, end;
		
		//initialize timeParser for startTime string
		Scanner timeParser = new Scanner(startTime);
		
		//convert start time
		if(startTime.contains("am"))
		{
			start = timeParser.nextInt() - 8;
		}
		else if(startTime.contains("12 pm"))
		{
			start = 4;
		}
		else
		{
			start = timeParser.nextInt() + 4;
		}
		
		//initialize time parser for endTime string
		timeParser = new Scanner(endTime);
		
		//convert end time
		if(endTime.contains("am"))
		{
			end = timeParser.nextInt() - 8;
		}
		else if(endTime.contains("12 pm"))
		{
			end = 4;
		}
		else
		{
			end = timeParser.nextInt() + 4;
		}
		
		//loop through days
		for(int i = 0; i < 7; i++)
		{
			//if event is planned for the i-th day of the week then draw it
			if(days[i])
			{
				//loop through start and end panels and draw event
				for(int j = start; j < end; j++)
				{
					if(Color.contains("Blue"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.blue, activityName);
					}
					else if(Color.contains("Cyan"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.cyan, activityName);
					}
					else if(Color.contains("Gray"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.gray, activityName);
					}
					else if(Color.contains("Green"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.green, activityName);
					}
					else if(Color.contains("Magenta"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.magenta, activityName);
					}
					else if(Color.contains("Orange"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.orange, activityName);
					}
					else if(Color.contains("Pink"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.pink, activityName);
					}
					else if(Color.contains("White"))
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.white, activityName);
					}
					else
					{
						displayActivity(schedulePanels[i][j], java.awt.Color.yellow, activityName);
					}
				}	
			}
		}
	}
	private void fillEntryPanel(JPanel entryPanel, final JPanel[][] schedulePanels) {
		JLabel lblActivityName = new JLabel("Activity Name:");
		lblActivityName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblActivityName.setBounds(10, 14, 70, 14);
		entryPanel.add(lblActivityName);
		
		final JCheckBox chckbxEnable1 = new JCheckBox("Enable");
		chckbxEnable1.setBounds(1067, 10, 97, 23);
		entryPanel.add(chckbxEnable1);
		
		txtFieldActivity1 = new JTextField();
		txtFieldActivity1.setBounds(90, 11, 86, 20);
		entryPanel.add(txtFieldActivity1);
		txtFieldActivity1.setColumns(10);
		addListenerToTextField(txtFieldActivity1, chckbxEnable1);
		
		final JCheckBox chckbxMonday1 = new JCheckBox("Monday");
		chckbxMonday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxMonday1.setBounds(182, 10, 63, 23);
		entryPanel.add(chckbxMonday1);
		
		final JCheckBox chckbxTuesday1 = new JCheckBox("Tuesday");
		chckbxTuesday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTuesday1.setBounds(247, 10, 67, 23);
		entryPanel.add(chckbxTuesday1);
		
		final JCheckBox chckbxWednesday1 = new JCheckBox("Wednesday");
		chckbxWednesday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxWednesday1.setBounds(316, 10, 83, 23);
		entryPanel.add(chckbxWednesday1);
		
		final JCheckBox chckbxThursday1 = new JCheckBox("Thursday");
		chckbxThursday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxThursday1.setBounds(401, 10, 71, 23);
		entryPanel.add(chckbxThursday1);
		
		final JCheckBox chckbxFriday1 = new JCheckBox("Friday");
		chckbxFriday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxFriday1.setBounds(474, 10, 55, 23);
		entryPanel.add(chckbxFriday1);
		
		final JCheckBox chckbxSaturday1 = new JCheckBox("Saturday");
		chckbxSaturday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSaturday1.setBounds(531, 10, 69, 23);
		entryPanel.add(chckbxSaturday1);
		
		final JCheckBox chckbxSunday1 = new JCheckBox("Sunday");
		chckbxSunday1.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSunday1.setBounds(602, 10, 63, 23);
		entryPanel.add(chckbxSunday1);
		
		JLabel lblStartTime = new JLabel("Start Time:");
		lblStartTime.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStartTime.setBounds(686, 14, 53, 14);
		entryPanel.add(lblStartTime);
		
		final JComboBox<String> startTime1 = new JComboBox<String>();
		startTime1.setFont(new Font("Arial", Font.PLAIN, 11));
		startTime1.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		startTime1.setBounds(745, 11, 54, 20);
		entryPanel.add(startTime1);
		
		JLabel lblEndTime = new JLabel("End Time:");
		lblEndTime.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEndTime.setBounds(809, 14, 47, 14);
		entryPanel.add(lblEndTime);
		
		final JComboBox<String> endTime1 = new JComboBox<String>();
		endTime1.setFont(new Font("Arial", Font.PLAIN, 11));
		endTime1.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		endTime1.setSelectedIndex(1);
		endTime1.setBounds(866, 11, 54, 20);
		entryPanel.add(endTime1);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setFont(new Font("Arial", Font.PLAIN, 11));
		lblColor.setBounds(949, 14, 28, 14);
		entryPanel.add(lblColor);
		
		final JComboBox<String> color1 = new JComboBox<String>();
		color1.setFont(new Font("Arial", Font.PLAIN, 11));
		color1.setModel(new DefaultComboBoxModel<String>(new String[] {"Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "White", "Yellow"}));
		color1.setSelectedIndex(0);
		color1.setBounds(987, 11, 67, 20);
		entryPanel.add(color1);
		
		addActionListenerToCheckbox(chckbxEnable1,
									schedulePanels,
									txtFieldActivity1,
									startTime1,
									endTime1,
									color1,
									chckbxMonday1,
									chckbxTuesday1,
									chckbxWednesday1,
									chckbxThursday1,
									chckbxFriday1,
									chckbxSaturday1,
									chckbxSunday1);
		
		JLabel label_1 = new JLabel("Activity Name:");
		label_1.setFont(new Font("Arial", Font.PLAIN, 11));
		label_1.setBounds(10, 39, 70, 14);
		entryPanel.add(label_1);
		
		JCheckBox chckbxEnable2 = new JCheckBox("Enable");
		chckbxEnable2.setBounds(1067, 35, 97, 23);
		entryPanel.add(chckbxEnable2);
		
		
		txtFieldActivity2 = new JTextField();
		txtFieldActivity2.setColumns(10);
		txtFieldActivity2.setBounds(90, 36, 86, 20);
		entryPanel.add(txtFieldActivity2);
		
		final JCheckBox chckbxMonday2 = new JCheckBox("Monday");
		chckbxMonday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxMonday2.setBounds(182, 35, 63, 23);
		entryPanel.add(chckbxMonday2);
		
		final JCheckBox chckbxTuesday2 = new JCheckBox("Tuesday");
		chckbxTuesday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTuesday2.setBounds(247, 35, 67, 23);
		entryPanel.add(chckbxTuesday2);
		
		final JCheckBox chckbxWednesday2 = new JCheckBox("Wednesday");
		chckbxWednesday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxWednesday2.setBounds(316, 35, 83, 23);
		entryPanel.add(chckbxWednesday2);
		
		final JCheckBox chckbxThursday2 = new JCheckBox("Thursday");
		chckbxThursday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxThursday2.setBounds(401, 35, 71, 23);
		entryPanel.add(chckbxThursday2);
		
		final JCheckBox chckbxFriday2 = new JCheckBox("Friday");
		chckbxFriday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxFriday2.setBounds(474, 35, 55, 23);
		entryPanel.add(chckbxFriday2);
		
		final JCheckBox chckbxSaturday2 = new JCheckBox("Saturday");
		chckbxSaturday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSaturday2.setBounds(531, 35, 69, 23);
		entryPanel.add(chckbxSaturday2);
		
		final JCheckBox chckbxSunday2 = new JCheckBox("Sunday");
		chckbxSunday2.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSunday2.setBounds(602, 35, 63, 23);
		entryPanel.add(chckbxSunday2);
		
		JLabel label_2 = new JLabel("Start Time:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 11));
		label_2.setBounds(686, 39, 53, 14);
		entryPanel.add(label_2);
		
		final JComboBox<String> startTime2 = new JComboBox<String>();
		startTime2.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		startTime2.setFont(new Font("Arial", Font.PLAIN, 11));
		startTime2.setBounds(745, 36, 54, 20);
		entryPanel.add(startTime2);
		
		JLabel label_3 = new JLabel("End Time:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 11));
		label_3.setBounds(809, 39, 47, 14);
		entryPanel.add(label_3);
		
		final JComboBox<String> endTime2 = new JComboBox<String>();
		endTime2.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		endTime2.setSelectedIndex(1);
		endTime2.setFont(new Font("Arial", Font.PLAIN, 11));
		endTime2.setBounds(866, 36, 54, 20);
		entryPanel.add(endTime2);
		
		JLabel label_4 = new JLabel("Color:");
		label_4.setFont(new Font("Arial", Font.PLAIN, 11));
		label_4.setBounds(949, 39, 28, 14);
		entryPanel.add(label_4);
		
		final JComboBox<String> color2 = new JComboBox<String>();
		color2.setModel(new DefaultComboBoxModel<String>(new String[] {"Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "White", "Yellow"}));
		color2.setSelectedIndex(0);
		color2.setFont(new Font("Arial", Font.PLAIN, 11));
		color2.setBounds(987, 36, 67, 20);
		entryPanel.add(color2);
		
		addActionListenerToCheckbox(chckbxEnable2,
				schedulePanels,
				txtFieldActivity2,
				startTime2,
				endTime2,
				color2,
				chckbxMonday2,
				chckbxTuesday2,
				chckbxWednesday2,
				chckbxThursday2,
				chckbxFriday2,
				chckbxSaturday2,
				chckbxSunday2);
		
		
		JCheckBox chckbxEnable3 = new JCheckBox("Enable");
		chckbxEnable3.setBounds(1067, 60, 97, 23);
		entryPanel.add(chckbxEnable3);
		
		JLabel label_5 = new JLabel("Activity Name:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 11));
		label_5.setBounds(10, 64, 70, 14);
		entryPanel.add(label_5);
		
		txtFieldActivity3 = new JTextField();
		txtFieldActivity3.setColumns(10);
		txtFieldActivity3.setBounds(90, 61, 86, 20);
		entryPanel.add(txtFieldActivity3);
		addListenerToTextField(txtFieldActivity3, chckbxEnable3);
		
		final JCheckBox chckbxMonday3 = new JCheckBox("Monday");
		chckbxMonday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxMonday3.setBounds(182, 60, 63, 23);
		entryPanel.add(chckbxMonday3);
		
		final JCheckBox chckbxTuesday3 = new JCheckBox("Tuesday");
		chckbxTuesday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTuesday3.setBounds(247, 60, 67, 23);
		entryPanel.add(chckbxTuesday3);
		
		final JCheckBox chckbxWednesday3 = new JCheckBox("Wednesday");
		chckbxWednesday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxWednesday3.setBounds(316, 60, 83, 23);
		entryPanel.add(chckbxWednesday3);
		
		final JCheckBox chckbxThursday3 = new JCheckBox("Thursday");
		chckbxThursday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxThursday3.setBounds(401, 60, 71, 23);
		entryPanel.add(chckbxThursday3);
		
		final JCheckBox chckbxFriday3 = new JCheckBox("Friday");
		chckbxFriday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxFriday3.setBounds(474, 60, 55, 23);
		entryPanel.add(chckbxFriday3);
		
		final JCheckBox chckbxSaturday3 = new JCheckBox("Saturday");
		chckbxSaturday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSaturday3.setBounds(531, 60, 69, 23);
		entryPanel.add(chckbxSaturday3);
		
		final JCheckBox chckbxSunday3 = new JCheckBox("Sunday");
		chckbxSunday3.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSunday3.setBounds(602, 60, 63, 23);
		entryPanel.add(chckbxSunday3);
		
		final JComboBox<String> startTime3 = new JComboBox<String>();
		startTime3.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		startTime3.setFont(new Font("Arial", Font.PLAIN, 11));
		startTime3.setBounds(745, 61, 54, 20);
		entryPanel.add(startTime3);
		
		JLabel label_6 = new JLabel("Start Time:");
		label_6.setFont(new Font("Arial", Font.PLAIN, 11));
		label_6.setBounds(686, 64, 53, 14);
		entryPanel.add(label_6);
		
		JLabel label_7 = new JLabel("End Time:");
		label_7.setFont(new Font("Arial", Font.PLAIN, 11));
		label_7.setBounds(809, 64, 47, 14);
		entryPanel.add(label_7);
		
		final JComboBox<String> endTime3 = new JComboBox<String>();
		endTime3.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		endTime3.setSelectedIndex(1);
		endTime3.setFont(new Font("Arial", Font.PLAIN, 11));
		endTime3.setBounds(866, 61, 54, 20);
		entryPanel.add(endTime3);
		
		JLabel label_8 = new JLabel("Color:");
		label_8.setFont(new Font("Arial", Font.PLAIN, 11));
		label_8.setBounds(949, 64, 28, 14);
		entryPanel.add(label_8);
		
		final JComboBox<String> color3 = new JComboBox<String>();
		color3.setModel(new DefaultComboBoxModel<String>(new String[] {"Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "White", "Yellow"}));
		color3.setSelectedIndex(0);
		color3.setFont(new Font("Arial", Font.PLAIN, 11));
		color3.setBounds(987, 61, 67, 20);
		entryPanel.add(color3);
		
		addActionListenerToCheckbox(chckbxEnable3,
				schedulePanels,
				txtFieldActivity3,
				startTime3,
				endTime3,
				color3,
				chckbxMonday3,
				chckbxTuesday3,
				chckbxWednesday3,
				chckbxThursday3,
				chckbxFriday3,
				chckbxSaturday3,
				chckbxSunday3);
		
		
		JCheckBox chckbxEnable4 = new JCheckBox("Enable");
		chckbxEnable4.setBounds(1067, 85, 97, 23);
		entryPanel.add(chckbxEnable4);
		
		JLabel label_9 = new JLabel("Activity Name:");
		label_9.setFont(new Font("Arial", Font.PLAIN, 11));
		label_9.setBounds(10, 89, 70, 14);
		entryPanel.add(label_9);
		
		txtFieldActivity4 = new JTextField();
		txtFieldActivity4.setColumns(10);
		txtFieldActivity4.setBounds(90, 86, 86, 20);
		entryPanel.add(txtFieldActivity4);
		addListenerToTextField(txtFieldActivity4, chckbxEnable4);
		
		final JCheckBox chckbxMonday4 = new JCheckBox("Monday");
		chckbxMonday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxMonday4.setBounds(182, 85, 63, 23);
		entryPanel.add(chckbxMonday4);
		
		final JCheckBox chckbxTuesday4 = new JCheckBox("Tuesday");
		chckbxTuesday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTuesday4.setBounds(247, 85, 67, 23);
		entryPanel.add(chckbxTuesday4);
		
		final JCheckBox chckbxWednesday4 = new JCheckBox("Wednesday");
		chckbxWednesday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxWednesday4.setBounds(316, 85, 83, 23);
		entryPanel.add(chckbxWednesday4);
		
		final JCheckBox chckbxThursday4 = new JCheckBox("Thursday");
		chckbxThursday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxThursday4.setBounds(401, 85, 71, 23);
		entryPanel.add(chckbxThursday4);
		
		final JCheckBox chckbxFriday4 = new JCheckBox("Friday");
		chckbxFriday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxFriday4.setBounds(474, 85, 55, 23);
		entryPanel.add(chckbxFriday4);
		
		final JCheckBox chckbxSaturday4 = new JCheckBox("Saturday");
		chckbxSaturday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSaturday4.setBounds(531, 85, 69, 23);
		entryPanel.add(chckbxSaturday4);
		
		final JCheckBox chckbxSunday4 = new JCheckBox("Sunday");
		chckbxSunday4.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSunday4.setBounds(602, 85, 63, 23);
		entryPanel.add(chckbxSunday4);
		
		JLabel label_10 = new JLabel("Start Time:");
		label_10.setFont(new Font("Arial", Font.PLAIN, 11));
		label_10.setBounds(686, 89, 53, 14);
		entryPanel.add(label_10);
		
		final JComboBox<String> startTime4 = new JComboBox<String>();
		startTime4.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		startTime4.setFont(new Font("Arial", Font.PLAIN, 11));
		startTime4.setBounds(745, 86, 54, 20);
		entryPanel.add(startTime4);
		
		JLabel label_11 = new JLabel("End Time:");
		label_11.setFont(new Font("Arial", Font.PLAIN, 11));
		label_11.setBounds(809, 89, 47, 14);
		entryPanel.add(label_11);

		final JComboBox<String> endTime4 = new JComboBox<String>();
		endTime4.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		endTime4.setSelectedIndex(1);
		endTime4.setFont(new Font("Arial", Font.PLAIN, 11));
		endTime4.setBounds(866, 86, 54, 20);
		entryPanel.add(endTime4);
		
		JLabel label_12 = new JLabel("Color:");
		label_12.setFont(new Font("Arial", Font.PLAIN, 11));
		label_12.setBounds(949, 89, 28, 14);
		entryPanel.add(label_12);
		
		final JComboBox<String> color4 = new JComboBox<String>();
		color4.setModel(new DefaultComboBoxModel<String>(new String[] {"Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "White", "Yellow"}));
		color4.setSelectedIndex(0);
		color4.setFont(new Font("Arial", Font.PLAIN, 11));
		color4.setBounds(987, 86, 67, 20);
		entryPanel.add(color4);
		
		addActionListenerToCheckbox(chckbxEnable4,
				schedulePanels,
				txtFieldActivity4,
				startTime4,
				endTime4,
				color4,
				chckbxMonday4,
				chckbxTuesday4,
				chckbxWednesday4,
				chckbxThursday4,
				chckbxFriday4,
				chckbxSaturday4,
				chckbxSunday4);

		JCheckBox chckbxEnable5 = new JCheckBox("Enable");
		chckbxEnable5.setBounds(1067, 110, 97, 23);
		entryPanel.add(chckbxEnable5);
		
		JLabel label_13 = new JLabel("Activity Name:");
		label_13.setFont(new Font("Arial", Font.PLAIN, 11));
		label_13.setBounds(10, 114, 70, 14);
		entryPanel.add(label_13);
		
		txtFieldActivity5 = new JTextField();
		txtFieldActivity5.setColumns(10);
		txtFieldActivity5.setBounds(90, 111, 86, 20);
		entryPanel.add(txtFieldActivity5);
		addListenerToTextField(txtFieldActivity5, chckbxEnable5);
		
		final JCheckBox chckbxMonday5 = new JCheckBox("Monday");
		chckbxMonday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxMonday5.setBounds(182, 110, 63, 23);
		entryPanel.add(chckbxMonday5);
		
		final JCheckBox chckbxTuesday5 = new JCheckBox("Tuesday");
		chckbxTuesday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxTuesday5.setBounds(247, 110, 67, 23);
		entryPanel.add(chckbxTuesday5);
		
		final JCheckBox chckbxWednesday5 = new JCheckBox("Wednesday");
		chckbxWednesday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxWednesday5.setBounds(316, 110, 83, 23);
		entryPanel.add(chckbxWednesday5);
		
		final JCheckBox chckbxThursday5 = new JCheckBox("Thursday");
		chckbxThursday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxThursday5.setBounds(401, 110, 71, 23);
		entryPanel.add(chckbxThursday5);
		
		final JCheckBox chckbxFriday5 = new JCheckBox("Friday");
		chckbxFriday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxFriday5.setBounds(474, 110, 55, 23);
		entryPanel.add(chckbxFriday5);
		
		final JCheckBox chckbxSaturday5 = new JCheckBox("Saturday");
		chckbxSaturday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSaturday5.setBounds(531, 110, 69, 23);
		entryPanel.add(chckbxSaturday5);
		
		final JCheckBox chckbxSunday5 = new JCheckBox("Sunday");
		chckbxSunday5.setFont(new Font("Arial", Font.PLAIN, 11));
		chckbxSunday5.setBounds(602, 110, 63, 23);
		entryPanel.add(chckbxSunday5);
		
		JLabel label_14 = new JLabel("Start Time:");
		label_14.setFont(new Font("Arial", Font.PLAIN, 11));
		label_14.setBounds(686, 114, 53, 14);
		entryPanel.add(label_14);
		
		final JComboBox<String> startTime5 = new JComboBox<String>();
		startTime5.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		startTime5.setFont(new Font("Arial", Font.PLAIN, 11));
		startTime5.setBounds(745, 111, 54, 20);
		entryPanel.add(startTime5);
		
		JLabel label_15 = new JLabel("End Time:");
		label_15.setFont(new Font("Arial", Font.PLAIN, 11));
		label_15.setBounds(809, 114, 47, 14);
		entryPanel.add(label_15);
		
		final JComboBox<String> endTime5 = new JComboBox<String>();
		endTime5.setModel(new DefaultComboBoxModel<String>(new String[] {"8 am", "9 am", "10 am", "11 am", "12 pm", "1 pm", "2 pm", "3 pm", "4 pm", "5 pm", "6 pm", "7 pm", "8 pm", "9 pm", "10 pm", "11 pm"}));
		endTime5.setSelectedIndex(1);
		endTime5.setFont(new Font("Arial", Font.PLAIN, 11));
		endTime5.setBounds(866, 111, 54, 20);
		entryPanel.add(endTime5);
		
		JLabel label_16 = new JLabel("Color:");
		label_16.setFont(new Font("Arial", Font.PLAIN, 11));
		label_16.setBounds(949, 114, 28, 14);
		entryPanel.add(label_16);
		
		final JComboBox<String> color5 = new JComboBox<String>();
		color5.setModel(new DefaultComboBoxModel<String>(new String[] {"Blue", "Cyan", "Gray", "Green", "Magenta", "Orange", "Pink", "White", "Yellow"}));
		color5.setSelectedIndex(0);
		color5.setFont(new Font("Arial", Font.PLAIN, 11));
		color5.setBounds(987, 111, 67, 20);
		entryPanel.add(color5);
		
		addActionListenerToCheckbox(chckbxEnable5,
				schedulePanels,
				txtFieldActivity5,
				startTime5,
				endTime5,
				color5,
				chckbxMonday5,
				chckbxTuesday5,
				chckbxWednesday5,
				chckbxThursday5,
				chckbxFriday5,
				chckbxSaturday5,
				chckbxSunday5);
		
	}

	private void addListenerToTextField(
			JTextField component, final JCheckBox chckbxEnable) {
		component.getDocument().addDocumentListener( new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) {
				chckbxEnable.setSelected(false);
			}

			public void insertUpdate(DocumentEvent arg0) {
				chckbxEnable.setSelected(false);
			}

			public void removeUpdate(DocumentEvent arg0) {
				chckbxEnable.setSelected(false);
			}
	});
	}
	
	private void addActionListenerToCheckbox(final JCheckBox component,
											 final JPanel[][] schedulePanels,
											 final JTextField textfield,
											 final JComboBox<String> startTime,
											 final JComboBox<String> endTime,
											 final JComboBox<String> color,
											 final JCheckBox monday,
											 final JCheckBox tuesday,
											 final JCheckBox wednesday,
											 final JCheckBox thursday,
											 final JCheckBox friday,
											 final JCheckBox saturday,
											 final JCheckBox sunday) {
			component.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean[] week = {monday.isSelected(),tuesday.isSelected(), wednesday.isSelected(), thursday.isSelected(), friday.isSelected(), saturday.isSelected(), sunday.isSelected()};
					if(component.isSelected())
					{
						processEntry(schedulePanels,textfield.getText(),week ,(String) startTime.getSelectedItem(),endTime.getSelectedItem().toString(), color.getSelectedItem().toString());
						textfield.setEditable(false);
						startTime.setEnabled(false);
						endTime.setEnabled(false);
						color.setEnabled(false);
						monday.setEnabled(false);
						tuesday.setEnabled(false);
						wednesday.setEnabled(false);
						thursday.setEnabled(false);
						friday.setEnabled(false);
						saturday.setEnabled(false);
						sunday.setEnabled(false);
					}
					else
					{
						processEntry(schedulePanels,"REMOVE",week ,(String) startTime.getSelectedItem(),endTime.getSelectedItem().toString(), color.getSelectedItem().toString());
						textfield.setEditable(true);
						startTime.setEnabled(true);
						endTime.setEnabled(true);
						color.setEnabled(true);
						monday.setEnabled(true);
						tuesday.setEnabled(true);
						wednesday.setEnabled(true);
						thursday.setEnabled(true);
						friday.setEnabled(true);
						saturday.setEnabled(true);
						sunday.setEnabled(true);
					}
				}
			});
	}

	private JLabel[] getTimeLabels() {
		JLabel lbl8Am = new JLabel("8 am");
		JLabel lbl9Am = new JLabel("9 am");
		JLabel lbl10Am = new JLabel("10 am");
		JLabel lbl11Am = new JLabel("11 am");
		JLabel lbl12Pm = new JLabel("12 pm");
		JLabel lbl1Pm = new JLabel("1 pm");
		JLabel lbl2Pm = new JLabel("2 pm");
		JLabel lbl3Pm = new JLabel("3 pm");
		JLabel lbl4Pm = new JLabel("4 pm");
		JLabel lbl5Pm = new JLabel("5 pm");
		JLabel lbl6Pm = new JLabel("6 pm");
		JLabel lbl7Pm = new JLabel("7 pm");
		JLabel lbl8Pm = new JLabel("8 pm");
		JLabel lbl9Pm = new JLabel("9 pm");
		JLabel lbl10Pm = new JLabel("10 pm");
		
		JLabel[] ret = {lbl8Am, lbl9Am, lbl10Am, lbl11Am, lbl12Pm, lbl1Pm, lbl2Pm, lbl3Pm, lbl4Pm, lbl5Pm, lbl6Pm, lbl7Pm,
				lbl8Pm, lbl9Pm, lbl10Pm};
		
		return ret;
	}
	
	private JLabel[] getDayLabels() {
		JLabel lblMonday = new JLabel("Monday");
		JLabel lblTuesday = new JLabel("Tuesday");
		JLabel lblWednesday = new JLabel("Wednesday");
		JLabel lblThursday = new JLabel("Thursday");
		JLabel lblFriday = new JLabel("Friday");
		JLabel lblSaturday = new JLabel("Saturday");
		JLabel lblSunday = new JLabel("Sunday");
		
		JLabel[] ret = {lblMonday, lblTuesday, lblWednesday, lblThursday, lblFriday, lblSaturday, lblSunday};
		
		return ret;
	}
	
	private JPanel[][] getSchedulePanels() {
		JPanel[][] ret = new JPanel[7][15];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 15; j++) {
				ret[i][j] = new JPanel();
				ret[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
				
				JLabel eventLabel = new JLabel("");
				eventLabel.setFont(new Font("Arial", Font.PLAIN, 11));
				ret[i][j].add(eventLabel);
			}
		}
		return ret;
	}
	
}