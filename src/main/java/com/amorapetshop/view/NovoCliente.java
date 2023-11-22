package com.amorapetshop.view;

import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NovoCliente {
    private JTextField NomeCliente;
    private JTextField CPFCliente;
    private JTextField Telefone;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JComboBox Sexobox;
    private JPanel NovoCLiente;
    private long clienteId;

    public NovoCliente() {
        ClienteController clienteController = new ClienteController();
        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenha os dados do cliente dos campos de texto
                String nome = NomeCliente.getText();
                String cpf = CPFCliente.getText();
                String numero = Telefone.getText();
                String sexo= (String) Sexobox.getSelectedItem();

                if (clienteId != 0L) {
                    Cliente clienteEditado = new Cliente();
                    clienteEditado.setId(clienteId);
                    clienteEditado.setNome(nome);
                    clienteEditado.setCpf(cpf);
                    clienteEditado.setTelefone(numero);
                    clienteEditado.setSexo(sexo);

                    // Chame o método de salvar no controlador
                    clienteController.salvar(clienteEditado);
                } else {

                    // Crie uma instância da entidade Animal com os dados
                    Cliente novoCliente = new Cliente();
                    novoCliente.setNome(nome);
                    novoCliente.setCpf(cpf);
                    novoCliente.setTelefone(numero);
                    novoCliente.setSexo(sexo);

                    clienteController.salvar(novoCliente);
                }


                // Limpe os campos de texto ou faça outras ações após o cadastro
                NomeCliente.setText("");
                CPFCliente.setText("");
                Telefone.setText("");
                Sexobox.setSelectedIndex(0);

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultarCliente consultarCliente = new ConsultarCliente();
                JPanel newconsultapainel = consultarCliente.getConsultaClientepainel();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        NovoCLiente.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getNovoCLiente() {
        return NovoCLiente;
    };

    public void setNome(String nome) {
        this.NomeCliente.setText(nome);
    }

    public void setCPFCliente(String cpf) {
        this.CPFCliente.setText(cpf);
    }

    public void setTelefone(String numero) {
        this.Telefone.setText(numero);
    }
    public void setSexo(String sexo) {
        // Obter o modelo do JComboBox
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) Sexobox.getModel();

        // Encontrar o índice do sexo no modelo
        int index = -1;
        for (int i = 0; i < model.getSize(); i++) {
            if (model.getElementAt(i).equals(sexo)) {
                index = i;
                break;
            }
        }

        // Definir o índice selecionado no JComboBox
        Sexobox.setSelectedIndex(index);
    }

    public void setClienteIdId(long id) {
        this.clienteId = id;
    }
}
