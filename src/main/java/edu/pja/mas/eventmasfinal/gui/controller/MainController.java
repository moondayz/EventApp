package edu.pja.mas.eventmasfinal.gui.controller;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.pja.mas.eventmasfinal.gui.view.MainWindow;
import edu.pja.mas.eventmasfinal.repository.EventRepo;
import edu.pja.mas.eventmasfinal.entity.Event;

@Component
public class MainController {

	private MainWindow view;

	//Allow us to connect the controller
	@Autowired
	private SearchController searchController;
	
	public MainController() {
		
        this.view = new MainWindow();
        initMenuListener();
      //  initListController();
	}

	private void initMenuListener() {
		view.getMenuItemEvent().addActionListener( e -> {
			System.out.println(e);
			SwingUtilities.invokeLater(()-> {
				searchController.showGUI(this);
			} ) ;
		});

	}
	
	public void showGUI() {
	//	updateEventListFromDb();
		view.setVisible(true);
	}

	// allows us to change window
	public void showView(JPanel panel) {
		
		view.getContentPane().removeAll();
		view.getContentPane().add(panel);
		view.revalidate();
		
	}

}
