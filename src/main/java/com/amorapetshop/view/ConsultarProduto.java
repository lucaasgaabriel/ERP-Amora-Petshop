package com.amorapetshop.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultarProduto {
    private JPanel Produtoconsulta;
    private JTextField Nome_produlto;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JTable TabelaProduto;
    private JButton VoltarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JPanel cabeçalhoProduto;
    private JPanel conteudoProduto;
    private JPanel rodapeProduto;
    private JTextField Data_Entrada;
    private JTextField valorProduto;
    private DefaultTableModel tableModel;


    public ConsultarProduto() {
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                NovoProduto novoProduto = new NovoProduto();
                JPanel newconsultapainel = novoProduto.getNovoprodutopainel();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        VoltarButton.addActionListener(new ActionListener() {
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
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        TabelaProduto.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        String[] colunas = {"id","Nome", "Data Entrada", "Valor", "Quantidade"};
        String[][] objetos = {{"", "", "", "", ""}};

        tableModel = new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        conteudoProduto.setLayout(new BorderLayout());
        conteudoProduto.add(new JScrollPane(TabelaProduto), BorderLayout.CENTER);

        // Defina o modelo da tabela
        TabelaProduto.setModel(tableModel);

        TableColumnModel columnModel = TabelaProduto.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        TabelaProduto.setAutoCreateRowSorter(true);
        TabelaProduto.getTableHeader().setReorderingAllowed(false);
        TabelaProduto.getTableHeader().setVisible(true);
        Produtoconsulta.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getProdutoconsulta(){
        return Produtoconsulta;
    };
}
