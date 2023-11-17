package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultaFuncionario {
    private JTable table1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton voltarButton;
    private JButton pesquisarButton;
    private JButton cadastrarButton;

    public ConsultaFuncionario() {
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
}
