package edu.pja.mas.eventmasfinal.gui.controller;

import edu.pja.mas.eventmasfinal.entity.Event;
import edu.pja.mas.eventmasfinal.entity.FinancialSupport;
import edu.pja.mas.eventmasfinal.entity.GoodsSupport;
import edu.pja.mas.eventmasfinal.gui.view.DetailedEventView;
import edu.pja.mas.eventmasfinal.repository.EventRepo;
import edu.pja.mas.eventmasfinal.repository.ISupportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DetailedEventController {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private ISupportRepo supportRepo;

    private int idEvent;
    @Autowired
    private MainController mainController;
//    @Autowired
//    private EditEventController editEventController;

    private DetailedEventView view;

    public DetailedEventController() {
        view = new DetailedEventView();
        initListModel();
       // editButtonListener();
    }

    public void showGUI(MainController mainController){

        mainController.showView(view.getMainPanel());
        this.mainController = mainController;
        updateDetailedEventView();
    }



    public void setIdEvent(int idEvent){
        this.idEvent = idEvent;
    }

    public void updateDetailedEventView(){
        Event selectedEvent = eventRepo.findByIdWithFinancialSupport(idEvent).get();
        setFieldTextValue(selectedEvent);
        updateFinancialSupportList(selectedEvent);
        updateGoodsSupportList();

    }

    // Event detail form
    public void setFieldTextValue(Event selectedEvent){
        view.getAddressText().setText(selectedEvent.getAddress() );
        view.getAddressText().setEditable(false);
        view.getNameEvent().setText(selectedEvent.getNameEvent());
        view.getIdText().setText(String.valueOf(selectedEvent.getIdEvent()));
        view.getIdText().setEditable(false);
        view.getTicketPriceText().setText(String.valueOf(selectedEvent.getTicketPrice()));
        view.getTicketPriceText().setEditable(false);
        view.getBudgetText().setText(String.valueOf(selectedEvent.getBudget()));
        view.getBudgetText().setEditable(false);

    }

    private void initListModel(){
        view.getFinancialSupportList().setModel(new DefaultListModel<FinancialSupport>());
        view.getGoodsSupportList().setModel(new DefaultListModel<GoodsSupport>());
    }

    public void updateFinancialSupportList(Event event){
        List<FinancialSupport> fSupports = new ArrayList<>(event.getFinancialSupport());
        fSupports = fSupports.stream()
                .collect(Collectors.toList());

        if(!fSupports.isEmpty()){
            DefaultListModel financialSupportModel = (DefaultListModel) view.getFinancialSupportList().getModel();
            for(FinancialSupport fSupport : fSupports){
                financialSupportModel.addElement(fSupport);
            }
        } else {
            view.getFinancialSupportLabel().setText("Sorry no available financial support of this event at the moment");

        }

    }

    public void updateGoodsSupportList(){
        Event selectedBook = eventRepo.findByIdWithGoodsSupport(idEvent).get();
        Set<GoodsSupport> goodsSupports = selectedBook.getGoodsSupport();
        if(!goodsSupports.isEmpty()){
            DefaultListModel goodsSupportListModel = (DefaultListModel) view.getGoodsSupportList().getModel();
            for(GoodsSupport goodsSupport : goodsSupports){
                goodsSupportListModel.addElement(goodsSupport);
            }
        } else {
            view.getGoodsSupportLabel().setText("Sorry we haven't update the goods support of this event!");
            view.getGoodsSupportLabel().setVisible(true);
        }

    }

//    public void editButtonListener(){
//        view.getEditButton().addActionListener(e -> {
//            SwingUtilities.invokeLater( () -> {
//                editEventController.showGUI(mainController);
//            });
//
//        });
//    }
}

