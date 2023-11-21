package com.amorapetshop.view;

import com.amorapetshop.model.Produto;
import com.amorapetshop.controller.ProdutoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date

public class NovoProduto {
    private JTextField nomeProduto;
    private JTextField DT_Entrada;
    private JTextField Valor;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JSpinner Estoque;
    private JPanel Novoprodutopainel;


    public NovoProduto() {
        ProdutoController produtoController = new ProdutoController();

        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoData = "dd-MM-aaaa";
                SimpleDateFormat format = new SimpleDateFormat(formatoData);
                // Obtenha os dados do animal dos campos de texto
                String nome = nomeProduto.getText();
                String dataString = DT_Entrada.getText();
                String valor = Valor.getText();

                try {
                    Date data = format.parse(dataString);
                    // Crie uma instância da entidade Animal com os dados
                    Produto novoProduto = new Produto();
                    novoProduto.setNome(nome);
                    novoProduto.setDataEntrada(data);
                    novoProduto.setValor(valor);

                    produtoController.salvar(novoProduto);

                } catch (ParseException x) {
                    x.printStackTrace();







                // Limpe os campos de texto ou faça outras ações após o cadastro
                nomeProduto.setText("");
                DT_Entrada.setText("");
                Valor.setText("");

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
