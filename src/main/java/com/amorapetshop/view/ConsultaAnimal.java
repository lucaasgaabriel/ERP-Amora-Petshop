package com.amorapetshop.view;

import com.amorapetshop.model.Animal;
import com.amorapetshop.model.dao.AnimalJpaDao;
import com.amorapetshop.controller.AnimalController;
import javax.swing.table.TableColumnModel;


import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

public class ConsultaAnimal {
    private JTextField nome_input_animal;
    private JTextField Dono_input_animal;
    private JTextField especie_input_animal;
    private JButton novoButton;
    private JButton pesquisarButton;
    private JButton voltarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JPanel animais_consulta;
    private JTable TableaAnimais;
    private JPanel CabecalhoAnimais;
    private JPanel ConteudoAnimais;
    private JPanel RodapeAnimais;
    private DefaultTableModel tableModel;
    private AnimalController animalController;
    private List<Animal> animais;


    public ConsultaAnimal() {
        animalController = new AnimalController();
        animais = animalController.buscarTodos();
        novoButton.addActionListener(new ActionListener() {
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
                // Obtém os critérios de pesquisa do formulário
                String nome = nome_input_animal.getText();
                String especie = especie_input_animal.getText();
                String raca = Dono_input_animal.getText();

                // Cria uma instância de Animal com os critérios de pesquisa
                Animal animalConsulta = new Animal();
                animalConsulta.setNome(nome);
                animalConsulta.setEspecie(especie);
                animalConsulta.setRaca(raca);

                // Chama o método buscarComFiltro no back-end
                List<Animal> resultados = animalController.buscarFiltro(animalConsulta);

                // Atualiza a tabela com os resultados da pesquisa
                atualizarTabela(resultados);

                nome_input_animal.setText("");
                especie_input_animal.setText("");
                Dono_input_animal.setText("");
            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a linha selecionada na tabela
                int selectedRow = TableaAnimais.getSelectedRow();

                // Verifica se uma linha foi realmente selecionada
                if (selectedRow >= 0) {
                    // Obtém os dados da linha selecionada
                    long id = (long) TableaAnimais.getValueAt(selectedRow, 0);
                    String nome = (String) TableaAnimais.getValueAt(selectedRow, 1);
                    String especie = (String) TableaAnimais.getValueAt(selectedRow, 2);
                    String raca = (String) TableaAnimais.getValueAt(selectedRow, 3);

                    // Cria uma instância de Animal com os dados da linha selecionada
                    Animal animalParaExcluir = new Animal();
                    animalParaExcluir.setId(id);
                    animalParaExcluir.setNome(nome);
                    animalParaExcluir.setEspecie(especie);
                    animalParaExcluir.setRaca(raca);

                    // Confirmação de exclusão
                    int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este animal?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // Realiza a exclusão do animal no banco de dados
                        animalController.excluir(animalParaExcluir);

                        // Chama o método buscarComFiltro no back-end
                        List<Animal> resultados = animalController.buscarFiltro(animalParaExcluir);

                        // Atualiza a tabela com os resultados da pesquisa
                        atualizarTabela(resultados);

                        // Atualiza a tabela após a exclusão
                        carregarDadosNaTabela();
                    }
                } else {
                    // Se nenhuma linha estiver selecionada, exiba uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para excluir.",
                            "Nenhuma Linha Selecionada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifique se uma linha está selecionada
                int selectedRow = TableaAnimais.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtenha os dados da linha selecionada
                    long id = (long) TableaAnimais.getValueAt(selectedRow, 0);
                    String nome = (String) TableaAnimais.getValueAt(selectedRow, 1);
                    String especie = (String) TableaAnimais.getValueAt(selectedRow, 2);
                    String raca = (String) TableaAnimais.getValueAt(selectedRow, 3);

                    // Crie a tela de edição
                    JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
                    NovoAnimal telaEdicao = new NovoAnimal();

                    // Configure os campos com os dados recuperados
                    telaEdicao.setAnimalId(id);
                    telaEdicao.setNome(nome);
                    telaEdicao.setEspecie(especie);
                    telaEdicao.setRaca(raca);

                    // Atualize o conteúdo da janela atual
                    currentFrame.setContentPane(telaEdicao.getMain_frame_aminal_cadastro());

                    // Atualize a exibição
                    currentFrame.revalidate();
                    currentFrame.repaint();
                } else {
                    // Se nenhuma linha estiver selecionada, exiba uma mensagem de aviso
                    JOptionPane.showMessageDialog(null, "Selecione uma linha para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
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

        TableaAnimais.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });


        String[] colunas = {"id","Nome", "Especie", "Raça"};
        String[][] objetos = {{"", "", ""}};

        tableModel = new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ConteudoAnimais.setLayout(new BorderLayout());
        ConteudoAnimais.add(new JScrollPane(TableaAnimais), BorderLayout.CENTER);

        // Defina o modelo da tabela
        TableaAnimais.setModel(tableModel);

        TableColumnModel columnModel = TableaAnimais.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        TableaAnimais.setAutoCreateRowSorter(true);
        TableaAnimais.getTableHeader().setReorderingAllowed(false);
        TableaAnimais.getTableHeader().setVisible(true);
        //TabelaFuncionarios.setVisible(true);
        animais_consulta.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        carregarDadosNaTabela();
    }
    private void carregarDadosNaTabela() {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Obtém a lista de animais do banco de dados
        animais = animalController.buscarTodos();

        // Preenche a tabela com os dados obtidos
        for (Animal animal : animais) {
            tableModel.addRow(new Object[]{animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca()});
            // Adicione mais colunas conforme necessário
        }
    }
    private void atualizarTabela(List<Animal> resultados) {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Preenche a tabela com os dados obtidos
        for (Animal animal : resultados) {
            tableModel.addRow(new Object[]{animal.getId(), animal.getNome(), animal.getEspecie(), animal.getRaca()});
            // Adicione mais colunas conforme necessário
        }
    }
    public JPanel getAnimaisConsulta() {
        return animais_consulta;
    }
}