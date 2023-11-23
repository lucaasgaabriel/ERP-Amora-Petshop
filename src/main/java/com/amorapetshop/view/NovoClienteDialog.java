package com.amorapetshop.view;

import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class NovoClienteDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField NomeCliente;
    private JTextField CPFCliente;
    private JTextField Telefone;
    private JComboBox Sexobox;
    private Cliente clienteSelecionado;

    public NovoClienteDialog(JDialog currentDialog, DefaultTableModel tableModel) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        ClienteController clienteController = new ClienteController();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = NomeCliente.getText();
                String cpf = CPFCliente.getText();
                String numero = Telefone.getText();
                String sexo= (String) Sexobox.getSelectedItem();

                // Crie uma instância da entidade Animal com os dados
                Cliente novoCliente = new Cliente();
                novoCliente.setNome(nome);
                novoCliente.setCpf(cpf);
                novoCliente.setTelefone(numero);
                novoCliente.setSexo(sexo);

                clienteController.salvar(novoCliente);

                // Obtenha o ID e o nome do cliente recém-criado
                long id = novoCliente.getId();
                String nomeCliente = novoCliente.getNome();

                // Cria um objeto Cliente com os dados da linha selecionada
                clienteSelecionado = new Cliente();
                clienteSelecionado.setId(id);
                clienteSelecionado.setNome(nome);

                System.out.println(clienteSelecionado.getNome());
                System.out.println(clienteSelecionado.getId());

                onOK(currentDialog, tableModel);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JDialog currentDialog = (JDialog) SwingUtilities.getRoot((Component) e.getSource());


                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultarClienteDialog consultarClienteDialog = new ConsultarClienteDialog(currentDialog);
                JPanel newconsultapainel = consultarClienteDialog.getConsultaClienteDialog();

                // Atualiza o conteúdo da janela atual
                currentDialog.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentDialog.revalidate();
                currentDialog.repaint();

                clienteSelecionado = consultarClienteDialog.getClienteSelecionado();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK(JDialog currentDialog, DefaultTableModel tableModel) {



        tableModel.addRow(new Object[]{NomeCliente.getText(), CPFCliente.getText(), Telefone.getText(), Sexobox.getSelectedItem()});
        // add your code here
        dispose();
        currentDialog.dispose();
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public JPanel getNovoClienteDialog() {
        return contentPane;
    }
}
