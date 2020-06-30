package edu.pja.mas.eventmasfinal.gui.controller;

import edu.pja.mas.eventmasfinal.gui.view.SearchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class SearchController {

    private MainController mainController;

    @Autowired
    private EventListController eventListController;
    private SearchView searchView;

    public SearchController() {
        searchView = new SearchView();
        initSearchByNameEventButtonListener();
        initSearchAllEventButtonListener();

    }

    public void showGUI(MainController mainController){
        this.mainController = mainController;
        mainController.showView(searchView.getSearchPanel());
    }

    // Add behaviour to the search event by name button
    public void initSearchByNameEventButtonListener(){
        searchView.getSearchByNameEventButton().addActionListener( e -> {
            SwingUtilities.invokeLater(() -> {
                String keyWord = searchView.getSearchEventText().getText();
                SwingUtilities.invokeLater(()-> {
                    eventListController.showGUI(mainController);
                    eventListController.setDataInList(keyWord, "nameEvent");
                } ) ;
            });
        });
    }

    // Add behaviour to the search event by address button
    public void initSearchAllEventButtonListener(){
        searchView.getSearchAllEventButton().addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                String keyWord = searchView.getSearchEventText().getText();
                SwingUtilities.invokeLater(()-> {
                    eventListController.showGUI(mainController);
                    eventListController.setDataInList(keyWord, "all");
                } ) ;
            });
        });
    }

}
