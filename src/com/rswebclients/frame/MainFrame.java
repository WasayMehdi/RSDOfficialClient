package com.rswebclients.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.rswebclients.frame.listeners.CommandListener;
import com.rswebclients.frame.listeners.LoadButton;
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JLabel message;
	private JTextField textField;
	private static MainFrame frame;
	public final static MainFrame getFrame() {
		return frame;
	}
	
	public final String getName() {
		return textField.getText();
	}
	
	public final void setMessage(String s, Object... args) {
		message.setText(String.format(s, args));
		message.repaint();
	}
	
	/**
	 * Start up frame
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
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
	public MainFrame() {
		super("RSDClient v 1.1");
		setPreferredSize(new Dimension(766, 590));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.addKeyListener(new CommandListener());
		setContentPane(contentPane);
		final JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new BorderLayout());
		final JLabel label = new JLabel("Server Name:");
		label.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		optionPanel.add(label, BorderLayout.WEST);
		message = new JLabel("Applet Dormant");
		textField = new JTextField("");
		optionPanel.add(textField, BorderLayout.CENTER);
		final JButton button = new JButton("LOAD");
		button.setFont(new Font("Batang", Font.PLAIN, 14));
		button.setBackground(new Color(102, 255, 51));
		button.addActionListener(new LoadButton());
		optionPanel.add(button, BorderLayout.EAST);
		optionPanel.add(message, BorderLayout.NORTH);
		
		addKeyListener(new CommandListener());
		contentPane.add(optionPanel, BorderLayout.SOUTH);
		pack();
	}

}
