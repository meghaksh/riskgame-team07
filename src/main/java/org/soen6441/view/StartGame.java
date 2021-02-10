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
		System.out.println(Thread.currentThread());
		
		
		
	}

}

class CommandPrompt{
	Thread t;
	public CommandPrompt(){
		//t = new Thread(this);
		//t.setName("Command Prompt");
		//t.start();
		drawWindow();
	}
	public void run() {
		//this.drawWindow();	
	}
	public void drawWindow() {
		boolean mapDone = false;
		System.out.println(Thread.currentThread());
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
			System.out.println(commandInput.getText());
			String str = commandInput.getText();
			switch(str) {
				case "editcontinent" : 
					System.out.println("editcontinent");
					break;
				case "editcountry" :
					System.out.println("editcountry");
					break;
				case "editneighbour" :
					System.out.println("editneighbour");
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
