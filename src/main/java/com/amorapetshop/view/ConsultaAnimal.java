package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultaAnimal {
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JButton voltarButton;

    public ConsultaAnimal() {
        table1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
}
