package com.amorapetshop.view;


import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

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
    private List<Cliente> cliente;
    private ClienteController clienteController;

    public ConsultarCliente() {
        clienteController = new ClienteController();
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os critérios de pesquisa do formulário
                String nome = NomeClinete.getText();
                String cpf = CPFCliente.getText();

                // Cria uma instância de cliente com os critérios de pesquisa
                Cliente clienteConsulta = new Cliente();
                clienteConsulta.setNome(nome);
                clienteConsulta.setCpf(cpf);

                // Chama o método buscarComFiltro no back-end
                List<Cliente> resultados = clienteController.buscarFiltro(clienteConsulta);

                // Atualiza a tabela com os resultados da pesquisa
                atualizarTabela(resultados);

                NomeClinete.setText("");
                CPFCliente.setText("");

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
                // Obtém a linha selecionada na tabela
                int selectedRow = tabelaCliente.getSelectedRow();

                // Verifica se uma linha foi realmente selecionada
                if (selectedRow >= 0) {
                    // Obtém os dados da linha selecionada
                    long id = (long) tabelaCliente.getValueAt(selectedRow, 0);
                    String nome = (String) tabelaCliente.getValueAt(selectedRow, 1);
                    String cpf = (String) tabelaCliente.getValueAt(selectedRow, 2);
                    String telefone = (String) tabelaCliente.getValueAt(selectedRow, 3);
                    String sexo = (String) tabelaCliente.getValueAt(selectedRow, 4);

                    // Cria uma instância de Animal com os dados da linha selecionada
                    Cliente clienteParaExcluir = new Cliente();
                    clienteParaExcluir.setId(id);
                    clienteParaExcluir.setNome(nome);
                    clienteParaExcluir.setCpf(cpf);
                    clienteParaExcluir.setTelefone(telefone);
                    clienteParaExcluir.setSexo(sexo);

                    // Confirmação de exclusão
                    int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este cliente?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // Realiza a exclusão do animal no banco de dados
                        clienteController.excluir(clienteParaExcluir);

                        // Chama o método buscarComFiltro no back-end
                        List<Cliente> resultados = clienteController.buscarFiltro(clienteParaExcluir);

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
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaCliente.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtém os dados da linha selecionada
                    long id = (long) tabelaCliente.getValueAt(selectedRow, 0);
                    String nome = (String) tabelaCliente.getValueAt(selectedRow, 1);
                    String cpf = (String) tabelaCliente.getValueAt(selectedRow, 2);
                    String telefone = (String) tabelaCliente.getValueAt(selectedRow, 3);
                    String sexo = (String) tabelaCliente.getValueAt(selectedRow, 4);


                    // Obtém a janela atual associada ao botão clicado
                    JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
                    NovoCliente telaEdicao = new NovoCliente();

                    telaEdicao.setClienteIdId(id);
                    telaEdicao.setNome(nome);
                    telaEdicao.setCPFCliente(cpf);
                    telaEdicao.setTelefone(telefone);
                    telaEdicao.setSexo(sexo);

                    // Atualiza o conteúdo da janela atual
                    currentFrame.setContentPane(telaEdicao.getNovoCLiente());

                    // Atualiza a exibição
                    currentFrame.revalidate();
                    currentFrame.repaint();
                } else {
                    // Se nenhuma linha estiver selecionada, exiba uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para editar.",
                            "Nenhuma Linha Selecionada", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        tabelaCliente.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        String[] colunas = {"id","Nome", "CPF","Telefone","Sexo"};
        String[][] objetos = {{"", "", "", "", ""}};

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
        carregarDadosNaTabela();
    }
    private void carregarDadosNaTabela() {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Obtém a lista de cliente do banco de dados
        cliente = clienteController.buscarTodos();

        // Preenche a tabela com os dados obtidos
        for (Cliente cliente : cliente) {
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(),cliente.getSexo()});
            // Adicione mais colunas conforme necessário
        }
    }
    private void atualizarTabela(List<Cliente> resultados) {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Preenche a tabela com os dados obtidos
        for (Cliente cliente : resultados) {
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(),cliente.getSexo()});
            // Adicione mais colunas conforme necessário
        }
    }
    public JPanel getConsultaClientepainel() {
        return ClienteConsultapainel;
    };
}
