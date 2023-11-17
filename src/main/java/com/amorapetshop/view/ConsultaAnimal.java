package com.amorapetshop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsultaAnimal {
    private JTable tabela_animais;
    private JTextField nome_input_animal;
    private JTextField Dono_input_animal;
    private JTextField especie_input_animal;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JButton voltarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JPanel animais_consulta;


    public ConsultaAnimal() {
        nome_input_animal.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        Dono_input_animal.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        especie_input_animal.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                NovoAnimal Main_frame_aminal_cadastro = new NovoAnimal();
                JPanel main_frame_aminal_cadastro_panel = Main_frame_aminal_cadastro.getMain_frame_aminal_cadastro();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(main_frame_aminal_cadastro_panel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                Index index = new Index();
                JPanel indexpainel = index.getMainIndex();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(indexpainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        tabela_animais.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });

        animais_consulta.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }

    public JPanel getAnimaisConsulta() {
        return animais_consulta;
    }
}