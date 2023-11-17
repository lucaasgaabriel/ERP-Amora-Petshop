package com.amorapetshop.view;

import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultarClienteView {
    private JTable tableClientes;
    private JPanel panel1;
    private JButton voltarButton;
    private JTextField textFieldNome;
    private JTextField textFieldCpf;
    private JButton cadastrarButton;
    private JButton pesquisarButton;

    private ClienteController clienteController;

    public ConsultarClienteView() {
        clienteController = new ClienteController();

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarClientes();
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

    private void pesquisarClientes() {
        Cliente clienteFiltro = new Cliente();
        clienteFiltro.setNome(textFieldNome.getText());
        clienteFiltro.setCpf(textFieldCpf.getText());

        List<Cliente> clientes = clienteController.buscarFiltro(clienteFiltro);

        // Atualiza a tabela com os resultados da pesquisa
        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        model.setRowCount(0);

        for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone()});
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ConsultarClienteView");
        frame.setContentPane(new ConsultarClienteView().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
