package edu.pja.mas.eventmasfinal.gui.view;

import edu.pja.mas.eventmasfinal.entity.FinancialSupport;
import edu.pja.mas.eventmasfinal.entity.GoodsSupport;

import javax.swing.*;
import java.awt.*;

public class DetailedEventView {
    private JPanel eventdetailAll;
    private JPanel eventdetail1;
    private JLabel addressLabel;
    private JTextField addressText;
    private JLabel idEventLabel;
    private JTextField idText;
    private JLabel ticketPriceLabel;
    private JTextField ticketPriceText;
    private JPanel eventdetail2;
    private JLabel capacityLabel;
    private JTextField capacityText;
    private JLabel budgetLabel;
    private JTextField budgetText;
    private JLabel typeEventLabel;
    private JTextField typeEventText;
    private JLabel nameEvent;
    private JPanel financialAndGoods;
    private JPanel financialSupportPanel;
    private JLabel financialSupportLabel;
    private JList financialSupportList;
    private JLabel goodsSupportLabel;
    private JList goodsSupportList;
    private JPanel mainPanel;
    private JButton editButton;

    // custom view for goods and financial support
    private void createUIComponents() {
        financialSupportList = new JList();
        financialSupportList.setCellRenderer(new financialSupportListCellRenderer());
        goodsSupportList = new JList();
        goodsSupportList.setCellRenderer(new goodsSupportListCellRenderer());
    }

    private class financialSupportListCellRenderer extends JLabel implements ListCellRenderer<FinancialSupport>{

        public financialSupportListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends FinancialSupport> list, FinancialSupport value, int index, boolean isSelected, boolean cellHasFocus) {
            setText("ID : " + value.getIdFinancialSupport() + " Support amount : " + value.getAmount() + " PLN ");
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

    private class goodsSupportListCellRenderer extends JLabel implements ListCellRenderer<GoodsSupport> {
        public goodsSupportListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends GoodsSupport> list, GoodsSupport value, int index, boolean isSelected, boolean cellHasFocus) {
            setText("ID : " + value.getIdGoodsSupport() +" Goods name : " + value.getGoodsName());
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            return this;
        }
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JPanel getEventdetailAll() {
        return eventdetailAll;
    }

    public JPanel getEventdetail1() {
        return eventdetail1;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public JTextField getAddressText() {
        return addressText;
    }

    public JLabel getIdEventLabel() {
        return idEventLabel;
    }

    public JTextField getIdText() {
        return idText;
    }

    public JLabel getTicketPriceLabel() {
        return ticketPriceLabel;
    }

    public JTextField getTicketPriceText() {
        return ticketPriceText;
    }

    public JPanel getEventdetail2() {
        return eventdetail2;
    }

    public JLabel getCapacityLabel() {
        return capacityLabel;
    }

    public JTextField getCapacityText() {
        return capacityText;
    }

    public JLabel getBudgetLabel() {
        return budgetLabel;
    }

    public JTextField getBudgetText() {
        return budgetText;
    }

    public JLabel getTypeEventLabel() {
        return typeEventLabel;
    }

    public JTextField getTypeEventText() {
        return typeEventText;
    }

    public JLabel getNameEvent() {
        return nameEvent;
    }

    public JPanel getFinancialAndGoods() {
        return financialAndGoods;
    }

    public JPanel getFinancialSupportPanel() {
        return financialSupportPanel;
    }

    public JLabel getFinancialSupportLabel() {
        return financialSupportLabel;
    }

    public JList getFinancialSupportList() {
        return financialSupportList;
    }

    public JLabel getGoodsSupportLabel() {
        return goodsSupportLabel;
    }

    public JList getGoodsSupportList() {
        return goodsSupportList;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
