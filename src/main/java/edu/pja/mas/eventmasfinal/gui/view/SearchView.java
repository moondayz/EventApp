package edu.pja.mas.eventmasfinal.gui.view;

import javax.swing.*;

public class SearchView {
    private JPanel searchPanel;
    private JLabel searchEvent;
    private JLabel addressSearch;
    private JTextField searchEventText;
//    private JTextField searchAllText;
    private JButton searchByNameEventButton;
    private JButton searchAllEventButton;

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JLabel getSearchEvent() {
        return searchEvent;
    }

    public JLabel getAddressSearch() {
        return addressSearch;
    }

    public JTextField getSearchEventText() {
        return searchEventText;
    }


    public JButton getSearchByNameEventButton() {
        return searchByNameEventButton;
    }

    public JButton getSearchAllEventButton() {
        return searchAllEventButton;
    }
}
