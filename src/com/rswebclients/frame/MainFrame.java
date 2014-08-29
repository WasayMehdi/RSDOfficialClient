package com.rswebclients.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rswebclients.ChatboxPanel;
import com.rswebclients.frame.listeners.CommandListener;
import com.rswebclients.frame.listeners.LoadButton;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JLabel message;
	private JTextField textField;
	private static MainFrame frame;
	private JEditorPane toplistPane;
	private JScrollPane topListScrollPane;
	public final static MainFrame getFrame() {
		return frame;
	}
	
	public final String getName() {
		return textField.getText();
	}
	
	public final void removeToplist() {
		contentPane.remove(topListScrollPane);
	}
	
	public final void setMessage(String s, Object... args) {
		message.setText(String.format(s, args));
		message.repaint();
	}

    public static JPanel chatbox;
	
	/**
	 * Start up frame
	 */
	public static void main(String[] args) {
        System.out.println("init");
       // NativeInterface.initialize();
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
		setPreferredSize(new Dimension(756, 598));
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
        final JButton cboxToggle = new JButton("Toggle Chat");
        optionPanel.add(cboxToggle, BorderLayout.SOUTH);
        cboxToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(containsComp(contentPane, chatbox)) {
                	contentPane.remove(chatbox);
                	frame.setPreferredSize(new Dimension(756, 598));
                	contentPane.setPreferredSize(frame.getPreferredSize());
                	contentPane.resize(frame.getPreferredSize());
                	frame.pack();
                } else {
                    chatbox = new ChatboxPanel();
                    chatbox.setPreferredSize(new Dimension(500, 556));
                    contentPane.add(chatbox, BorderLayout.EAST);
                    frame.setPreferredSize(new Dimension(1256, 598));
                    contentPane.setPreferredSize(frame.getPreferredSize());
                    contentPane.resize(frame.getPreferredSize());
                    frame.pack();
                }
        }
        });
		
		addKeyListener(new CommandListener());
		contentPane.add(optionPanel, BorderLayout.SOUTH);
		toplistPane = RSWToplist.createPane();
		topListScrollPane = new JScrollPane(toplistPane);
		contentPane.add(topListScrollPane, BorderLayout.CENTER);
		SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
					topListScrollPane.getVerticalScrollBar().setValue(0);
			   }
			});
		pack();
	}

    private static boolean containsComp(final JPanel jPanel, Component c)
    {
        Component[] components = jPanel.getComponents();
        for (Component component : components) {
            if (c.equals(component)) {
                return true;
            }
        }
        return false;
    }

}
