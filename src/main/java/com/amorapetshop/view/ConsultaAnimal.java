package com.amorapetshop.view;

import com.amorapetshop.model.Animal;
import com.amorapetshop.model.dao.AnimalJpaDao;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


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

    private List<Animal> listaDeAnimais;


    public ConsultaAnimal() {
        listaDeAnimais = new ArrayList<>(); // Você precisará importar java.util.ArrayList


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
                pesquisarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Obtém os critérios de pesquisa do formulário
                        String nome = nome_input_animal.getText();
                        String especie = especie_input_animal.getText();
                        String raca = Dono_input_animal.getText();

                        // Cria uma instância de Animal com os critérios de pesquisa
                        Animal animalConsulta = new Animal();
                        animalConsulta.setNome(nome);
                        animalConsulta.setEspecie(especie);
                        animalConsulta.setRaca(raca);

                        // Chama o método buscaFiltro no back-end
                        AnimalJpaDao animalDao = new AnimalJpaDao();
                        List<Animal> resultados = animalDao.buscaFiltro(animalConsulta);

                        // Atualiza o modelo da tabela com os resultados
                        DefaultTableModel modeloTabela = (DefaultTableModel) tabela_animais.getModel();

                        // Limpa o modelo antes de adicionar os novos resultados
                        modeloTabela.setRowCount(0);

                        // Adiciona os resultados ao modelo da tabela
                        for (Animal animal : resultados) {
                            Object[] rowData = {animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca()};
                            modeloTabela.addRow(rowData);
                        }
                    }
                });

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

    public void carregarTodosDados() {
        AnimalJpaDao animalDao = new AnimalJpaDao();
        List<Animal> todosAnimais = animalDao.buscaTodos();

        DefaultTableModel modeloTabela = (DefaultTableModel) tabela_animais.getModel();
        modeloTabela.setRowCount(0);

        for (Animal animal : todosAnimais) {
            Object[] rowData = {animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca()};
            modeloTabela.addRow(rowData);
        }
    }
}