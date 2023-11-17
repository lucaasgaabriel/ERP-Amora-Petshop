package com.amorapetshop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class NovoProduto {
    private JTextField nomeProduto;
    private JTextField DT_Entrada;
    private JTextField Valor;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JSpinner Estoque;
    private JPanel Novoprodutopainel;


    public NovoProduto() {
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
                ConsultarProduto consultarProduto = new ConsultarProduto();
                JPanel newconsultapainel = consultarProduto.getProdutoconsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        Novoprodutopainel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getNovoprodutopainel(){
        return Novoprodutopainel;
    };
}
