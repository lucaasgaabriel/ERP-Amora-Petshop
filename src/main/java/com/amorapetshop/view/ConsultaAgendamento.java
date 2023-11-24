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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.util.Objects;

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
                // Obtém os critérios de pesquisa do formulário
                String responsavel= responsavelimput.getText();
                String tipo = (String) boxtipo.getSelectedItem();

                // Cria uma instância de Animal com os critérios de pesquisa
                Agendamento agendamentoConsulta = new Agendamento();
                agendamentoConsulta.setResponsavel(responsavel);
                agendamentoConsulta.setTipoAgendamento(tipo);


                // Chama o método buscarComFiltro no back-end
                List<Agendamento> resultados = agendamentoController.buscarFiltro(agendamentoConsulta);

                // Atualiza a tabela com os resultados da pesquisa
                atualizarTabela(resultados);

                responsavelimput.setText("");
                boxtipo.setSelectedIndex(0);
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
                // Obtém a linha selecionada na tabela
                int selectedRow = tebela_agendamento.getSelectedRow();


                // Verifica se uma linha foi realmente selecionada
                if (selectedRow >= 0) {
                    // Obtém os dados da linha selecionada
                    long id = (long) tebela_agendamento.getValueAt(selectedRow, 0);
                    String tipo = (String) tebela_agendamento.getValueAt(selectedRow, 1);
                    Timestamp timestamp = (Timestamp) tebela_agendamento.getValueAt(selectedRow, 2);
                    String hora = (String) tebela_agendamento.getValueAt(selectedRow, 3);
                    String responsavel = (String) tebela_agendamento.getValueAt(selectedRow, 4);
                    Object valorObj = tebela_agendamento.getValueAt(selectedRow, 5);
                    String valor = Objects.toString(valorObj, "");

                    // Cria uma instância de Agendamento com os dados da linha selecionada
                    Date data = new Date(timestamp.getTime());
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    String dataString = format.format(data);



                    // Crie uma instância da entidade Animal com os dados
                    Agendamento agendamentoParaExcluir = new Agendamento();
                    agendamentoParaExcluir.setId(id);
                    agendamentoParaExcluir.setTipoAgendamento(tipo);
                    agendamentoParaExcluir.setDataAgendamento(data);
                    agendamentoParaExcluir.setHoraAgendamento(hora);
                    agendamentoParaExcluir.setResponsavel(responsavel);
                    agendamentoParaExcluir.setPrecoOrcamento(Double.parseDouble(valor));



                    // Confirmação de exclusão
                    int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este agendamento?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // Realiza a exclusão do animal no banco de dados
                        agendamentoController.excluir(agendamentoParaExcluir);

                        // Chama o método buscarComFiltro no back-end
                        List<Agendamento> resultados = agendamentoController.buscarFiltro(agendamentoParaExcluir);

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
                int selectedRow = tebela_agendamento.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtenha os dados da linha selecionada
                    long id = (long) tebela_agendamento.getValueAt(selectedRow, 0);
                    String tipo = (String) tebela_agendamento.getValueAt(selectedRow, 1);
                    Timestamp timestamp = (Timestamp) tebela_agendamento.getValueAt(selectedRow, 2);
                    String hora = (String) tebela_agendamento.getValueAt(selectedRow, 3);
                    String responsavel = (String) tebela_agendamento.getValueAt(selectedRow, 4);
                    Object valorObj = tebela_agendamento.getValueAt(selectedRow, 5);
                    String valor = Objects.toString(valorObj, "");

                    // Crie a tela de edição
                    JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
                    NovoAgendamento telaEdicao = new NovoAgendamento();

                    // Configure os campos com os dados recuperados
                    telaEdicao.setid_Agendamento(id);
                    telaEdicao.setComboBoxtipo(tipo);
                    telaEdicao.setDT_Entrada(timestamp);
                    telaEdicao.setHR_Agendamento(hora);
                    telaEdicao.setResposavel(responsavel);
                    telaEdicao.setValor_Agendamento(valor);

                    // Atualize o conteúdo da janela atual
                    currentFrame.setContentPane(telaEdicao.getAgendemano_painel_new());

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
        PainelAgendamento.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        carregarDadosNaTabela();
        boxtipo.setSelectedIndex(0);
        boxtipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boxtipo.getSelectedIndex() == 0) {
                    boxtipo.setSelectedItem(null);
                }
            }
        });
    }
    private void carregarDadosNaTabela() {
        boxtipo.setSelectedIndex(0);
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
