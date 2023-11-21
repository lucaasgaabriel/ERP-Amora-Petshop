package com.amorapetshop.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsultarCliente {
    private JTable tabelaCliente;
    private JPanel ClienteConsultapainel;
    private JButton voltarButton;
    private JTextField NomeClinete;
    private JTextField CPFCliente;
    private JButton NovoButton;
    private JButton pesquisarButton;
    private JButton excluirButton;
    private JButton Editar;
    private JPanel cabeçalhoCleinte;
    private JPanel conteudoCliente;
    private DefaultTableModel tableModel;
    private JPanel RodapeCliente;

    public ConsultarCliente() {
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        NovoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                NovoCliente novoCliente = new NovoCliente();
                JPanel newconsultapainel = novoCliente.getNovoCLiente();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
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
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tabelaCliente.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        String[] colunas = {"id","Nome", "CPF"};
        String[][] objetos = {{"", "", ""}};

        tableModel = new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        conteudoCliente.setLayout(new BorderLayout());
        conteudoCliente.add(new JScrollPane(tabelaCliente), BorderLayout.CENTER);

        // Defina o modelo da tabela
        tabelaCliente.setModel(tableModel);

        TableColumnModel columnModel = tabelaCliente.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        tabelaCliente.setAutoCreateRowSorter(true);
        tabelaCliente.getTableHeader().setReorderingAllowed(false);
        tabelaCliente.getTableHeader().setVisible(true);
        //TabelaFuncionarios.setVisible(true);
        ClienteConsultapainel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getConsultaClientepainel() {
        return ClienteConsultapainel;
    };
}
