package org.soen6441.view;

import java.awt.BorderLayout;			import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;				import java.awt.event.ActionListener;
import javax.swing.JButton;				import javax.swing.JFrame;
import javax.swing.JPanel;				import javax.swing.JScrollPane;
import javax.swing.JTextArea;			import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 * This class is the View part of the MVC architecture. The window interacts with the user and show feedback. 
 *
 */
public class CommandPrompt {
	private JFrame d_CommandPromptWindow;
	private JPanel d_CommandPromptPanel;
	private JTextField d_CommandInput;
	private JTextArea d_CommandAcknowledgeArea;
	private JButton d_CommandSendButton;
	private JScrollPane d_AckAreaScrollPane;
	private Font d_Font;
	private DefaultCaret d_Caret;

	/**
	 * This is a default constructor which initialize the window and its components to display. 
	 */
	public CommandPrompt(){
		d_CommandPromptWindow = new JFrame("Command Prompt");
		d_CommandPromptPanel = new JPanel(new GridLayout(1,2));
		d_CommandInput = new JTextField(100);
		d_CommandAcknowledgeArea = new JTextArea(41,1);
		d_CommandSendButton = new JButton("Execute");
		d_AckAreaScrollPane = new JScrollPane(d_CommandAcknowledgeArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		d_Caret = (DefaultCaret)d_CommandAcknowledgeArea.getCaret();
		d_Font = new Font("Verdana", Font.BOLD, 12);
		drawWindow();
	}

	/**
	 * This method is to access text from the private input text field.
	 * 
	 * @return a string which was entered by user in the text field.
	 */
	public String getCommandInput() {
		return this.d_CommandInput.getText();
	}

	/**
	 * This method is to append acknowledgement messages on the window.
	 * 
	 * @param p_Str string which is passed to the append method of the text area which displays information.
	 */
	public void setCommandAcknowledgement(String p_Str) {
		this.d_CommandAcknowledgeArea.append(p_Str);
	}
	
	public String getCommandAcknowledgement() {
		return this.d_CommandAcknowledgeArea.getText();
	}

	/**
	 * This method sets the input to the text field where user is giving commands
	 * 
	 * @param p_Str string to set to the command input text field. 
	 */
	public void setCommandInput(String p_Str) {
		this.d_CommandInput.setText(p_Str);
	}

	/**
	 * This method draws the window displayed to the user. 
	 * It consist of java swing functionality for different components. 
	 */
	public void drawWindow() {
		d_CommandAcknowledgeArea.setEditable(false);
		d_CommandAcknowledgeArea.setBackground(Color.black);
		d_CommandAcknowledgeArea.setForeground(Color.cyan);
		d_CommandAcknowledgeArea.setFont(d_Font);
		d_Caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		d_CommandAcknowledgeArea.append("Welcome to RISK DOMINATION !!\n\n");
		d_CommandAcknowledgeArea.append("Below are the list of commands you can use in map edit phase : \n");
		d_CommandAcknowledgeArea.append("editcontinent -add continentID continentvalue -remove continentID \n"
				+ "editcountry -add countryID continentID -remove countryID \n"
				+ "editneighbor -add countryID neighborcountryID -remove countryID neighborcountryID \n");
		d_CommandAcknowledgeArea.append("savemap filename \neditmap filename \nvalidatemap \nshowmap \n");
		d_CommandAcknowledgeArea.append("Below are the list of commands you can use in game phase : \n");
		d_CommandAcknowledgeArea.append("loadmap filename \ngameplayer -add playername -remove playername \nassigncountries \nshowmap \n");
		d_CommandPromptPanel.add(d_CommandInput);
		d_CommandPromptPanel.add(d_CommandSendButton);
		d_CommandPromptWindow.add(d_AckAreaScrollPane, BorderLayout.PAGE_START);
		d_CommandPromptWindow.add(d_CommandPromptPanel, BorderLayout.SOUTH);
		d_CommandPromptWindow.setSize(900,725);
		d_CommandPromptWindow.setVisible(true);
		d_CommandPromptWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method registers the action listener to the execute button
	 * 
	 * @param p_ListenForCommandButton ActionListener to be registered with the button
	 */
	public void commandSendButtonListener(ActionListener p_ListenForCommandButton) {
		d_CommandSendButton.addActionListener(p_ListenForCommandButton);
	}
	
	public void clearTextArea() {
		this.d_CommandAcknowledgeArea.setText("");
	}
}
