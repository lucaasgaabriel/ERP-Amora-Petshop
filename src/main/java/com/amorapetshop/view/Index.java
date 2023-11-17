package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;


public class Index extends JFrame {

    private JPanel titulo_index;
    private JPanel botoes_index;
    private JButton produtoButton;
    private JButton animalButton;
    private JButton funcionarioButton;
    private JButton clienteButton;
    private JButton servicosButton;
    private JPanel mainIndex;

    public Index() {
        servicosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        animalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultaAnimal consultaAnimal = new ConsultaAnimal();
                JPanel animaisConsultaPanel = consultaAnimal.getAnimaisConsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(animaisConsultaPanel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        funcionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        clienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        produtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainIndex.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    };
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Amora Petshop");
            Index index = new Index();
            frame.setContentPane(index.mainIndex);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 450);
            frame.setVisible(true);
        });
    }

    public JPanel getMainIndex() {
        return mainIndex;
    }
}
