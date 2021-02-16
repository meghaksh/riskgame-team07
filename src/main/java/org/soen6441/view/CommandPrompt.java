package org.soen6441.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.soen6441.controller.MapController;
import org.soen6441.model.Map;

public class CommandPrompt {
	private MapController d_mapCtrl;
	private Map d_map;
	public CommandPrompt(){
		d_map = new Map();
		d_mapCtrl = new MapController(d_map);
		drawWindow();
	}
	public void drawWindow() {
		boolean l_MapDone = false;
		JFrame l_CommandPromptWindow = new JFrame("Command Prompt");
		JPanel commandPromptPanel = new JPanel(new GridLayout(1,2));
		JTextField commandInput = new JTextField(100);
		JTextArea l_CommandAcknowledgeArea = new JTextArea(20,1);
		l_CommandAcknowledgeArea.setEditable(false);
		l_CommandAcknowledgeArea.setBackground(Color.black);
		l_CommandAcknowledgeArea.setForeground(Color.cyan);
		l_CommandAcknowledgeArea.append("Welcome to RISK DOMINATION !!\n\n");
		l_CommandAcknowledgeArea.append("Below are the list of commands you can use : \n");
		l_CommandAcknowledgeArea.append("editcontinent -add continentID continentvalue -remove continentID \n"
				+ "editcountry -add countryID continentID -remove countryID \n"
				+ "editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID \n");
		JButton commandSendButton = new JButton("Execute");
		JScrollPane l_AckAreaScrollPane = new JScrollPane(l_CommandAcknowledgeArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		commandPromptPanel.add(commandInput);
		commandPromptPanel.add(commandSendButton);
		l_CommandPromptWindow.add(l_AckAreaScrollPane, BorderLayout.PAGE_START);
		l_CommandPromptWindow.add(commandPromptPanel, BorderLayout.SOUTH);
		l_CommandPromptWindow.setSize(400,400);
		l_CommandPromptWindow.setVisible(true);
		l_CommandPromptWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		commandSendButton.addActionListener(e -> {
			String str = commandInput.getText().trim();
			
			switch(str.split(" ")[0]) {
				case "editcontinent" : 
					try {
						d_mapCtrl.EditContinent(str);
					}catch(Exception p_Exception) {
						l_CommandAcknowledgeArea.setForeground(Color.red);
						l_CommandAcknowledgeArea.append(p_Exception.getMessage());
						l_CommandAcknowledgeArea.append("\n");
						l_CommandAcknowledgeArea.setForeground(Color.cyan);
					}
					break;
				case "editcountry" :
					d_map.EditCountry(str);
					break;
				case "editneighbour" :
					d_map.EditNeighbor(str);
					break;
				case "showmap":
					if(l_MapDone) {
						System.out.println("Call gameplay wala showmap");
					}else {
						System.out.println("Normal showmap");
					}
					break;
				case "savemap":
					break;
				case "editmap":
					break;
				case "validatemap":
					break;
				case "loadmap":
					break;
				case "gameplayer":
					break;
				case "assigncountries":
					break;
				case "deploy":
					break;
				default:
					l_CommandAcknowledgeArea.append("Invalid Command. Please try again.\n");
					break;
			}
			commandInput.setText("");
		});
	}
}
