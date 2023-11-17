package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultarProduto {
    private JPanel panel1;
    private JTextField textField1;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JButton voltarButton;
    private JTable table1;
    private JButton button1;

    public ConsultarProduto() {
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
}
