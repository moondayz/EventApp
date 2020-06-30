package edu.pja.mas.eventmasfinal.gui.view;
import edu.pja.mas.eventmasfinal.entity.Event;

import javax.swing.*;
import java.awt.*;

public class EventListView {
    private JPanel eventListPanel;
    private JLabel eventListLabel;
    private JList eventList;

    public JPanel getEventListPanel() { return eventListPanel; }

    public JLabel getEventListLabel() { return eventListLabel; }

    public JList getEventList() { return eventList; }

    private void createUIComponents() {
        eventList = new JList();
        eventList.setCellRenderer(new EventListCustomRenderer());
    }

    // custom view for Event List
    private class EventListCustomRenderer extends JLabel implements ListCellRenderer<Event> {

        public EventListCustomRenderer(){
            setOpaque(true);
        }
        @Override
        public Component getListCellRendererComponent(JList<? extends Event> list, Event value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getNameEvent() +  "  -  " + value.getAddress());
            setHorizontalAlignment(SwingConstants.CENTER);


            if(isSelected){
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());

            }

            return this;
        }
    }
}
