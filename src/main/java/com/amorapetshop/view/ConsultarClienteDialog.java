package com.amorapetshop.view;

import com.amorapetshop.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.amorapetshop.view.NovoClienteDialog;

import com.amorapetshop.controller.ClienteController;

public class ConsultarClienteDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable tabelaClientes;
    private JTextField pesquisaNome;
    private JTextField pesquisaCPF;
    private JButton pesquisarButton;
    private JPanel cebecalhoCliente;
    private JPanel ConteudoCliente;
    private JButton novoButton;
    private DefaultTableModel tableModel;
    private ClienteController clienteController;
    private Cliente clienteSelecionado;

    public ConsultarClienteDialog(JDialog currentDialog) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        clienteController = new ClienteController();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK(currentDialog);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel(currentDialog);
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel(currentDialog);
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel(currentDialog);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        tabelaClientes.addComponentListener(new ComponentAdapter() {
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

        ConteudoCliente.setLayout(new BorderLayout());
        ConteudoCliente.add(new JScrollPane(tabelaClientes), BorderLayout.CENTER);

        // Defina o modelo da tabela
        tabelaClientes.setModel(tableModel);

        TableColumnModel columnModel = tabelaClientes.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        tabelaClientes.setAutoCreateRowSorter(true);
        tabelaClientes.getTableHeader().setReorderingAllowed(false);
        tabelaClientes.getTableHeader().setVisible(true);
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os critérios de pesquisa do formulário
                String nome = pesquisaNome.getText();
                String cpf = pesquisaCPF.getText();

                // Cria uma instância de cliente com os critérios de pesquisa
                Cliente clienteConsulta = new Cliente();
                clienteConsulta.setNome(nome);
                clienteConsulta.setCpf(cpf);

                // Chama o método buscarComFiltro no back-end
                List<Cliente> resultados = clienteController.buscarFiltro(clienteConsulta);

                // Atualiza a tabela com os resultados da pesquisa
                atualizarTabela(resultados);

                pesquisaNome.setText("");
                pesquisaCPF.setText("");
            }
        });
        novoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /// Obtém a janela atual associada ao botão clicado
                JDialog currentDialog = (JDialog) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                NovoClienteDialog novoClienteDialog = new NovoClienteDialog(currentDialog, tableModel);
                JPanel newconsultapainel = novoClienteDialog.getNovoClienteDialog();

                // Atualiza o conteúdo da janela atual
                currentDialog.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentDialog.revalidate();
                currentDialog.repaint();

                clienteSelecionado = novoClienteDialog.getClienteSelecionado();
            }
        });
    }

    private void atualizarTabela(List<Cliente> resultados) {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Preenche a tabela com os dados obtidos
        for (Cliente cliente : resultados) {
            tableModel.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getCpf()});
            // Adicione mais colunas conforme necessário
        }
    }

    private void onOK(JDialog currentDialog) {
        // Obtém a linha selecionada na tabela
        int selectedRow = tabelaClientes.getSelectedRow();

        // Verifica se uma linha foi realmente selecionada
        if (selectedRow >= 0) {
            // Obtém os dados da linha selecionada
            long id = (long) tableModel.getValueAt(selectedRow, 0);
            String nome = (String) tableModel.getValueAt(selectedRow, 1);

            // Cria um objeto Cliente com os dados da linha selecionada
            clienteSelecionado = new Cliente();
            clienteSelecionado.setId(id);
            clienteSelecionado.setNome(nome);

            System.out.println(clienteSelecionado.getNome());
            System.out.println(clienteSelecionado.getId());

            // Fecha a janela
            dispose();  // Fecha o NovoClienteDialog
            currentDialog.dispose();  // Fecha o ConsultarClienteDialog
        } else {
            // Nenhuma linha selecionada, trate conforme necessário
            JOptionPane.showMessageDialog(this, "Nenhum cliente selecionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void onCancel(JDialog currentDialog) {
        // add your code here if necessary
        dispose();
        currentDialog.dispose();
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public static void main(String[] args) {
        // Cria uma instância de JDialog
        JDialog currentDialog = new JDialog();

        // Cria uma instância de ConsultarClienteDialog passando o JDialog como argumento
        ConsultarClienteDialog dialog = new ConsultarClienteDialog(currentDialog);

        dialog.pack();
        dialog.setSize(450, 350);
        dialog.setVisible(true);
        System.exit(0);
    }

    public JPanel getConsultaClienteDialog() {
        return contentPane;
    }
}
