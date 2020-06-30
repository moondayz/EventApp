package edu.pja.mas.eventmasfinal.gui.controller;

import edu.pja.mas.eventmasfinal.entity.Event;
import edu.pja.mas.eventmasfinal.gui.view.EventListView;
import edu.pja.mas.eventmasfinal.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class EventListController {

    private MainController mainController;
    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private DetailedEventController DetailedEventController;

    public EventListView view;

    public EventListController() {
        view = new EventListView();

        initListModel();
        listActionListener();
    }
    private void initListModel(){
        view.getEventList().setModel(new DefaultListModel<Event>());
    }

    public void listActionListener(){
        view.getEventList().addListSelectionListener( e -> {
            SwingUtilities.invokeLater( () -> {
                if(!e.getValueIsAdjusting()){
                    Event selectedEvent = (Event) view.getEventList().getSelectedValue();
                    DetailedEventController.setIdEvent(selectedEvent.getIdEvent());
                    DetailedEventController.showGUI(mainController);
                }
            });
        });

    }

    public void showGUI(MainController mainController){
        mainController.showView(view.getEventListPanel());
        this.mainController = mainController;
    }

    // queries from repo to get data
    public void setDataInList(String keyWord, String filterBy){
        List<Event> eventList;
        switch (filterBy){
            case "nameEvent":
                eventList = (List<Event>) eventRepo.findByNameEvent(keyWord);
                break;
            case "all":
                eventList = (List<Event>) eventRepo.findAllEvents();
                break;
            default:
                eventList = (List<Event>) eventRepo.findByAddress(keyWord);

        }
        // create list model and load data to the list
        DefaultListModel<Event> listModel = (DefaultListModel) view.getEventList().getModel();
        for(Event event : eventList){
            listModel.addElement(event);
        }

    }


}
