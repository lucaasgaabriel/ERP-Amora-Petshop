package com.amorapetshop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableModel;

public class ConsultaFuncionario {
    private JTable TabelaFuncionarios;
    private JPanel consulta_funcionario_main;
    private JTextField inputNomeFuncionario;
    private JTextField inputoFuncaoFuncionario;
    private JTextField inputMatriculaFuncionario;
    private JButton editarButton;
    private JButton excluirButton;
    private JButton novoButton;
    private JButton voltarButton;
    private JButton pesquisarButton;
    private JLabel nomefuncionario;
    private JLabel funcaoFuncionario;
    private JLabel MatriculaFuncionario;
    private JPanel cabealhoFuncionario;
    private JPanel conteudofuncionario;
    private JPanel rodapéfuncioanario;
    private DefaultTableModel tableModel;


    public ConsultaFuncionario() {
        pesquisarButton.addActionListener(new ActionListener() {
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
        TabelaFuncionarios.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        consulta_funcionario_main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });

        String[] colunas = {"Matricula", "Nome" , "Função"};
        String[][] objetos = {{"", "", ""}};
        DefaultTableModel tableModel = (new DefaultTableModel(objetos, colunas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });

        conteudofuncionario.setLayout(new BorderLayout());
        conteudofuncionario.add(new JScrollPane(TabelaFuncionarios), BorderLayout.CENTER);


        // Defina o modelo da tabela
        TabelaFuncionarios.setModel(tableModel);
        TabelaFuncionarios.setAutoCreateRowSorter(true);
        TabelaFuncionarios.getTableHeader().setReorderingAllowed(false);
        TabelaFuncionarios.getTableHeader().setVisible(true);
        //TabelaFuncionarios.setVisible(true);

    }
    public JPanel getFunciojario_consulta() {
        return consulta_funcionario_main;
    }
}



