package com.amorapetshop.view;

import com.amorapetshop.controller.ProdutoController;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class ConsultarProduto {
    private JPanel Produtoconsulta;
    private JTextField Nome_produlto;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JTable TabelaProduto;
    private JButton VoltarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JPanel cabeçalhoProduto;
    private JPanel conteudoProduto;
    private JPanel rodapeProduto;
    private JTextField Data_Entrada;
    private JTextField valorProduto;
    private DefaultTableModel tableModel;
    private ProdutoController produtoController;
    private List<Produto> produto;

    public ConsultarProduto() {
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém os critérios de pesquisa do formulário
                String nome = Nome_produlto.getText();
                String data = Data_Entrada.getText();
                String valor = valorProduto.getText();

                // Cria uma instância de Animal com os critérios de pesquisa
                Produto produtoConsulta = new Produto();
                produtoConsulta.setNome(nome);
                produtoConsulta.setDataSaida(data);
                produtoConsulta.setValor(valor);

                // Chama o método buscarComFiltro no back-end
                List<Produto> resultados = produtoController.buscarFiltro(produtoConsulta);

                // Atualiza a tabela com os resultados da pesquisa
                atualizarTabela(resultados);

                Nome_produlto.setText("");
                Data_Entrada.setText("");
                valorProduto.setText("");
            }


        });
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                NovoProduto novoProduto = new NovoProduto();
                JPanel newconsultapainel = novoProduto.getNovoprodutopainel();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(newconsultapainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        VoltarButton.addActionListener(new ActionListener() {
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
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a linha selecionada na tabela
                int selectedRow = TabelaProduto.getSelectedRow();

                // Verifica se uma linha foi realmente selecionada
                if (selectedRow >= 0) {
                    // Obtém os dados da linha selecionada
                    long id = (long) TabelaProduto.getValueAt(selectedRow, 0);
                    String nome = (String) TabelaProduto.getValueAt(selectedRow, 1);
                    String data = (String) TabelaProduto.getValueAt(selectedRow, 2);
                    String valor = (String) TabelaProduto.getValueAt(selectedRow, 3);
                    String quantidade = (String) TabelaProduto.getValueAt(selectedRow, 4);

                    // Cria uma instância de Animal com os dados da linha selecionada
                    Produto produtoParaExcluir = new Produto();
                    produtoParaExcluir.setId(id);
                    produtoParaExcluir.setNome(nome);
                    produtoParaExcluir.setDataEntrada(data);
                    produtoParaExcluir.setValor(valor);
                    produtoParaExcluir.setQuantidade(quantidade);

                    // Confirmação de exclusão
                    int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este produto?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        // Realiza a exclusão do animal no banco de dados
                        produtoController.excluir(produtoParaExcluir);

                        // Chama o método buscarComFiltro no back-end
                        List<Produto> resultados = produtoController.buscarFiltro(produtoParaExcluir);

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
        TabelaProduto.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        String[] colunas = {"id","Nome", "Data Entrada", "Valor", "Quantidade"};
        String[][] objetos = {{"", "", "", "", ""}};

        tableModel = new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        conteudoProduto.setLayout(new BorderLayout());
        conteudoProduto.add(new JScrollPane(TabelaProduto), BorderLayout.CENTER);

        // Defina o modelo da tabela
        TabelaProduto.setModel(tableModel);

        TableColumnModel columnModel = TabelaProduto.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setWidth(0);

        TabelaProduto.setAutoCreateRowSorter(true);
        TabelaProduto.getTableHeader().setReorderingAllowed(false);
        TabelaProduto.getTableHeader().setVisible(true);
        Produtoconsulta.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    private void carregarDadosNaTabela() {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Obtém a lista de produto do banco de dados
        produto = produtoController.buscarTodos();

        // Preenche a tabela com os dados obtidos
        for (Produto produto : produto) {
            tableModel.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getDataEntrada(), produto.getValor(), produto.getQuantidade()});
            // Adicione mais colunas conforme necessário
        }
    }
    private void atualizarTabela(List<Produto> resultados) {
        // Limpa os dados existentes na tabela
        tableModel.setRowCount(0);

        // Preenche a tabela com os dados obtidos
        for (Produto produto : resultados) {
            tableModel.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getDataEntrada(), produto.getValor(), produto.getQuantidade()});
            // Adicione mais colunas conforme necessário
        }
    }
    public JPanel getProdutoconsulta(){
        return Produtoconsulta;
    };
}
