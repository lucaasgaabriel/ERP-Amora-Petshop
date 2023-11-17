package com.amorapetshop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NovoAnimal {
    private JTextField nome;
    private JTextField epsecie;
    private JTextField raca;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JPanel main_frame_aminal_cadastro;
    private JPanel frame_animal;
    private JPanel frame_animal_titulo;

    public NovoAnimal() {
        nome.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        epsecie.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        raca.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
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
        main_frame_aminal_cadastro.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getMain_frame_aminal_cadastro() {
        return main_frame_aminal_cadastro;
    }
}
