package org.soen6441.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.soen6441.model.Country;
import org.soen6441.model.Map;

public class StartGame {
	public static void main(String[] args) {
		CommandPrompt cp = new CommandPrompt();
	}
}

class CommandPrompt{
	Map d_map;
	public CommandPrompt(){
		d_map = new Map();
		drawWindow();
	}
	public void drawWindow() {
		boolean mapDone = false;
		JFrame commandPromptWindow = new JFrame("Command Prompt");
		JPanel commandPromptPanel = new JPanel(new GridLayout(1,2));
		JTextField commandInput = new JTextField(100);
		JButton commandSendButton = new JButton("Execute");
		
		commandPromptPanel.add(commandInput);
		commandPromptPanel.add(commandSendButton);
		commandPromptWindow.add(commandPromptPanel, BorderLayout.SOUTH);
		commandPromptWindow.setSize(400,100);
		commandPromptWindow.setVisible(true);
		commandPromptWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		commandSendButton.addActionListener(e -> {
			String str = commandInput.getText();
			
			switch(str.split(" ")[0]) {
				case "editcontinent" : 
					d_map.EditContinent(str);
					System.out.println(d_map.getContinentList().get(0).GetContinentName());
					break;
				case "editcountry" :
					d_map.EditCountry(str);
					break;
				case "editneighbour" :
					d_map.EditNeighbor(str);
					break;
				case "showmap":
					if(mapDone) {
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
			}
	
			commandInput.setText("");
		});
	}
}
