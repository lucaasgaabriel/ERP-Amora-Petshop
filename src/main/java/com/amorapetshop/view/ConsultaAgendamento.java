package com.amorapetshop.view;

import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Agendamento;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.Cliente;
import com.amorapetshop.controller.AgendamentoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class ConsultaAgendamento {
    private JButton voltarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JButton novoButton;
    private JTable tebela_agendamento;
    private JTextField responsavelimput;
    private JComboBox boxtipo;
    private JPanel cabelhaoAgendamento;
    private JPanel conteudoAgendamento;
    private JPanel rodapeAgendamneto;
    private JPanel PainelAgendamento;
    private JButton buttonPesquisar;
    private DefaultTableModel tableModel;
    private List<Agendamento> agendamento;
    private AgendamentoController agendamentoController;

    public ConsultaAgendamento() {
        agendamentoController = new AgendamentoController();
        buttonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        tebela_agendamento.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        String[] colunas = {"id","Tipo", "Data", "Hora", "Responsavel", "Valor"};
        String[][] objetos = {{"", "", "", "", "", "", ""}};

        tableModel = new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        conteudoAgendamento.setLayout(new BorderLayout());
        conteudoAgendamento.add(new JScrollPane(tebela_agendamento), BorderLayout.CENTER);

        // Defina o modelo da tabela
        tebela_agendamento.setModel(tableModel);

        TableColumnModel columnModel = tebela_agendamento.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        tebela_agendamento.setAutoCreateRowSorter(true);
        tebela_agendamento.getTableHeader().setReorderingAllowed(false);
        tebela_agendamento.getTableHeader().setVisible(true);
        //TabelaFuncionarios.setVisible(true);
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                NovoAgendamento Novo_agendamento = new NovoAgendamento();
                JPanel Novo_agendamentoPainel = Novo_agendamento.getAgendemano_painel_new();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(Novo_agendamentoPainel);

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
        PainelAgendamento.addComponentListener(new ComponentAdapter() {
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

        // Obtém a lista de cliente do banco de dados
        agendamento = agendamentoController.buscarTodos();

        // Preenche a tabela com os dados obtidos
        for (Agendamento agendamento : agendamento) {
            tableModel.addRow(new Object[]{agendamento.getId(), agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getResponsavel(), agendamento.getPrecoOrcamento()});
            // Adicione mais colunas conforme necessário
        }
    }
    private void atualizarTabela(List<Agendamento> resultados) {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Preenche a tabela com os dados obtidos
        for (Agendamento agendamento : resultados) {
            tableModel.addRow(new Object[]{agendamento.getId(), agendamento.getTipoAgendamento(), agendamento.getDataAgendamento(), agendamento.getHoraAgendamento(), agendamento.getResponsavel(), agendamento.getPrecoOrcamento()});
            // Adicione mais colunas conforme necessário
        }
    }
    public JPanel getAgendamentoconsulta() {
        return PainelAgendamento;
    }
}
