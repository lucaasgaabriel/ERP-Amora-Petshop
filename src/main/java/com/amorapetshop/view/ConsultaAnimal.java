package com.amorapetshop.view;

import com.amorapetshop.controller.AnimalController;
import com.amorapetshop.model.Animal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaAnimalView {
    private JPanel panel1;
    private JTable tableAnimais;
    private JTextField textFieldNome;
    private JTextField textFieldEspecie;
    private JTextField textFieldOutroCampo;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JButton voltarButton;

    private AnimalController animalController;

    public ConsultaAnimalView() {
        animalController = new AnimalController();

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarAnimais();
            }
        });

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir a tela de cadastro
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para voltar para a tela anterior
            }
        });
    }

    private void pesquisarAnimais() {
        Animal animalFiltro = new Animal();
        animalFiltro.setNome(textFieldNome.getText());
        animalFiltro.setEspecie(textFieldEspecie.getText());
        animalFiltro.setOutroCampo(textFieldOutroCampo.getText());

        List<Animal> animais = animalController.buscarFiltro(animalFiltro);

        // Atualiza a tabela com os resultados da pesquisa
        DefaultTableModel model = (DefaultTableModel) tableAnimais.getModel();
        model.setRowCount(0);

        for (Animal animal : animais) {
            model.addRow(new Object[]{animal.getId(), animal.getNome(), animal.getEspecie(), animal.getOutroCampo()});
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConsultaAnimalView");
        frame.setContentPane(new ConsultaAnimalView().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
