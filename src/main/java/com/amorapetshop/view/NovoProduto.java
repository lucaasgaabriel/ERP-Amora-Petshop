package com.amorapetshop.view;

import com.amorapetshop.model.Animal;
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
import java.util.Date;
import java.text.DateFormat;


public class NovoProduto {
    private JTextField nomeProduto;
    private JTextField DT_Entrada;
    private JTextField Valor;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JSpinner Estoque;
    private JPanel Novoprodutopainel;

    private ProdutoController produtoController;
    private long produtoId;


    public NovoProduto() {
        produtoController = new ProdutoController();

        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoData = "dd-MM-yyyy";
                SimpleDateFormat format = new SimpleDateFormat(formatoData);

                // Obtenha os dados do animal dos campos de texto
                String nome = nomeProduto.getText();
                String dataString = DT_Entrada.getText();
                String valorString = Valor.getText();
                Integer estoque = (Integer) Estoque.getValue();

                try {
                    Date data = format.parse(dataString);

                    try {
                        // Convertendo a string para um valor double
                        double valor = Double.parseDouble(valorString);

                        if (produtoId != 0L) {
                            Produto produtoEditado = new Produto();
                            produtoEditado.setId(produtoId);
                            produtoEditado.setNome(nome);
                            produtoEditado.setDataEntrada(data);
                            produtoEditado.setValor(valor);
                            produtoEditado.setQuantidade(estoque);
                            produtoEditado.getDataSaida();

                            // Chame o método de salvar no controlador
                            produtoController.salvar(produtoEditado);
                        } else {
                            // Crie uma instância da entidade Animal com os dados
                            Produto novoProduto = new Produto();
                            novoProduto.setNome(nome);
                            novoProduto.setDataEntrada(data);
                            novoProduto.setValor(valor);
                            novoProduto.setQuantidade(estoque);
                            novoProduto.getDataSaida();

                            produtoController.salvar(novoProduto);
                        }

                    } catch (NumberFormatException x) {
                        x.printStackTrace();
                    }
                } catch (ParseException x) {
                    x.printStackTrace();
            }
                // Limpe os campos de texto ou faça outras ações após o cadastro
                nomeProduto.setText("");
                DT_Entrada.setText("");
                Valor.setText("");
                Estoque.setValue(0);
        }});
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
    public void setNomeProduto(String nome) {
        this.nomeProduto.setText(nome);
    }

    public void setDT_Entrada(Date data) {
        // Crie um formato de data
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Formate a data como uma string
        String dataFormatada = dateFormat.format(data);

        // Defina a string formatada no componente de interface gráfica
        this.DT_Entrada.setText(dataFormatada);
    }
    public void setValor(Double valor) {
        this.Valor.setText(String.valueOf(valor));
    }
    public void setEstoque(Integer estoque) {
        // Verifique se o valor de estoque não é nulo antes de tentar definir
        if (estoque != null) {
            // Converta o valor inteiro para uma string antes de definir no JSpinner
            String estoqueString = String.valueOf(estoque);

            // Defina a string no JSpinner
            this.Estoque.setValue(Integer.valueOf(estoqueString));
        } else {
            // Se o valor de estoque for nulo, você pode querer tomar alguma ação apropriada
            // Aqui, estou simplesmente deixando o JSpinner sem valor
            this.Estoque.setValue(null);
        }
    }
    public void setProdutoId(long id) {
        this.produtoId = id;
    }

}
