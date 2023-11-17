package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultarCliente {
    private JTable table1;
    private JPanel panel1;
    private JButton voltarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton cadastrarButton;
    private JButton pesquisarButton;

    public ConsultarCliente() {
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
}
