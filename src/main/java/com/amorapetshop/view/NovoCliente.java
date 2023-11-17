package com.amorapetshop.view;

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


    public NovoCliente() {
        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
}
