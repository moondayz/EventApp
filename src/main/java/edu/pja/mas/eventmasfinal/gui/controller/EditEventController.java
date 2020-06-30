//package edu.pja.mas.eventmasfinal.gui.controller;
//
//import edu.pja.mas.eventmasfinal.entity.Event;
//import edu.pja.mas.eventmasfinal.gui.view.EditEventView;
//import edu.pja.mas.eventmasfinal.repository.EventRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.swing.*;
//
//@Component
//public class EditEventController {
//
//
//    private MainController mainController;
//
//    @Autowired
//    private EventRepo eventRepo;
//
//    private Event event;
//    private int idEvent;
//    public EditEventView view;
//
////    @Autowired
////    private SuccessPageController successPageController;
//
//    private Boolean isEditable;
//
//    public EditEventController() {
//        view = new EditEventView();
//        initSaveButtonListener();
//        initEditEventButtonListener();
//        updateEditEventView();
//
//    }
//
//    public void setIdEvent(int idEvent){
//        this.idEvent = idEvent;
//    }
//
//    public void updateEditEventView(){
//        Event selectedEvent = eventRepo.findById(idEvent).get();
//        setTextField();
//
//    }
//
//
//    public void initEditEventButtonListener(){
//        view.getEditEventButton().addActionListener( e -> {
//            SwingUtilities.invokeLater( () -> {
//                this.showGUI(mainController, true);
//            });
//
//        });
//    }
//
//    public void initSaveButtonListener(){
//        view.getSaveButton().addActionListener( e -> {
//            updateEventData();
//            SwingUtilities.invokeLater( () -> {
//                this.showGUI(mainController, false);
//            });
//        });
//    }
//    public void showGUI(MainController mainController, Boolean isEditable){
//        this.isEditable = isEditable;
//        mainController.showView(view.getMainPanel());
//        this.mainController = mainController;
//        setTextField();
//        setButtonVisiblity();
//
//    }
//    public void showGUI(MainController mainController){
//        mainController.showView(view.getMainPanel());
//        this.mainController = mainController;
//    }
//
//    public void setEvent(Event event){
//        this.event = event;
//    }
//
//    public void setButtonVisiblity(){
//        if(this.isEditable){
//            view.getEditEventButton().setVisible(false);
//            view.getSaveButton().setVisible(true);
//        } else {
//            view.getEditEventButton().setVisible(true);
//            view.getSaveButton().setVisible(false);
//        }
//
//    }
//
//    public void setTextField(){
//        view.getAddressText().setText(event.getAddress());
//        view.getNameEventText().setText(event.getNameEvent());
//        view.getBudgetText().setText(String.valueOf(event.getBudget()));
//        view.getTicketPriceText().setText(String.valueOf(event.getTicketPrice()));
//
//        setEditableComponent();
//    }
//
//    public void setEditableComponent(){
//        view.getTicketPriceText().setEditable(isEditable);
//        view.getBudgetText().setEditable(isEditable);
//        view.getNameEventText().setEditable(isEditable);
//        view.getAddressText().setEditable(isEditable);
//
//    }
//
//    public void updateEventData(){
//        event.setNameEvent(view.getNameEventText().getText().trim());
//        event.setAddress(view.getAddressText().getText().trim());
//        event.setBudget(Integer.valueOf(view.getBudgetText().getText().trim()));
//        event.setTicketPrice(Integer.valueOf(view.getTicketPriceText().getText().trim()));
//
//        eventRepo.save(event);
//
//
//    }
//
//}
