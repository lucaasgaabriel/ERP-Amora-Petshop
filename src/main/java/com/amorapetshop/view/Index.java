package com.amorapetshop.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;


import javax.swing.*;
import java.awt.*;


public class Index extends JFrame {

    private JPanel botoes_index;
    private JButton produtoButton;
    private JButton animalButton;
    private JButton funcionarioButton;
    private JButton clienteButton;
    private JButton servicosButton;
    private JPanel mainIndex;
    private JLabel Logo;
    private JLabel teste;
    private JButton button1;
    private JPanel banana;

    public Index() {
        servicosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultaAgendamento Novo_agendamento = new ConsultaAgendamento();
                JPanel Novo_agendamentoPainel = Novo_agendamento.getAgendamentoconsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(Novo_agendamentoPainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        animalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultaAnimal consultaAnimal = new ConsultaAnimal();
                JPanel animaisConsultaPanel = consultaAnimal.getAnimaisConsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(animaisConsultaPanel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();

                //consultaAnimal.carregarTodosDados();
            }
        });
        clienteButton.addActionListener(new ActionListener() {
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
        produtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultarProduto consultarProduto = new ConsultarProduto();
                JPanel newconsultapainel = consultarProduto.getProdutoconsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        //Logo = new JLabel();

        mainIndex.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        produtoButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        animalButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        clienteButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        servicosButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        botoes_index.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    };
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Amora Petshop");
            Index index = new Index();
            frame.setContentPane(index.mainIndex);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 450);
            frame.setVisible(true);
        });
    }

    public JPanel getMainIndex() {
        return mainIndex;
    }
}
