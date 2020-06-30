package edu.pja.mas.eventmasfinal.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame{

	JMenuItem menuItemEvent;

	public MainWindow() {
		setTitle("Event Management");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initMenuBar();
	}
	
	public void initMenuBar() {

		//Creating the MenuBar and adding components
		JMenuBar bar = new JMenuBar();
		JMenu menuEvent = new JMenu("Event-Support");
		bar.add(menuEvent);
		menuItemEvent = new JMenuItem("Search Event");
		menuEvent.add(menuItemEvent);
		this.setJMenuBar(bar);


	}

	public JMenuItem getMenuItemEvent() {
		return menuItemEvent;
	}
}
