package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class index {
    private JPanel panel1;
    private JButton funcionarioButton;
    private JButton produtoButton;
    private JButton clienteButton;
    private JButton animalButton;
    private JButton servicosButton;
    private JPanel panel2;

    public index() {
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Amora Petshop");
        index index = new index();
        frame.setContentPane(index.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}
