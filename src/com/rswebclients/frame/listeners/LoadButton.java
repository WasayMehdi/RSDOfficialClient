package com.rswebclients.frame.listeners;

import java.awt.event.*;

import com.rswebclients.RSW;
import com.rswebclients.frame.MainFrame;

public class LoadButton implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainFrame.getFrame().setMessage("Attempting to load: %s", MainFrame.getFrame().getName());
		RSW.init(MainFrame.getFrame().getName());
	}

}
