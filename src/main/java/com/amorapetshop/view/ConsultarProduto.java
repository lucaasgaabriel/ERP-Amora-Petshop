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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;
import java.util.Objects;


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
        produtoController = new ProdutoController();
        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoData = "dd-MM-yyyy";
                SimpleDateFormat format = new SimpleDateFormat(formatoData);

                // Obtém os critérios de pesquisa do formulário
                String nome = Nome_produlto.getText();
                String dataString = Data_Entrada.getText();
                String valorString = valorProduto.getText();

                try {
                    Produto produtoConsulta = new Produto();

                    // Verifica e configura o critério de pesquisa por nome
                    if (!nome.isEmpty()) {
                        produtoConsulta.setNome(nome);
                    }

                    // Verifica e configura o critério de pesquisa por data
                    if (!dataString.isEmpty()) {
                        Date data = format.parse(dataString);
                        produtoConsulta.setDataEntrada(data);
                    }

                    // Verifica e configura o critério de pesquisa por valor
                    if (!valorString.isEmpty()) {
                        double valor = Double.parseDouble(valorString);
                        produtoConsulta.setValor(valor);
                    }

                    // Chama o método buscarComFiltro no back-end
                    List<Produto> resultados = produtoController.buscarFiltro(produtoConsulta);

                    // Atualiza a tabela com os resultados da pesquisa
                    atualizarTabela(resultados);

                } catch (ParseException | NumberFormatException ex) {
                    ex.printStackTrace(); // ou trate o erro de outra forma
                } finally {
                    // Limpa os campos após a pesquisa
                    Nome_produlto.setText("");
                    Data_Entrada.setText("");
                    valorProduto.setText("");
                }
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
                int selectedRow = TabelaProduto.getSelectedRow();
                if (selectedRow != -1) {
                    String formatoData = "dd/MM/yyyy HH:mm:ss";
                    SimpleDateFormat format = new SimpleDateFormat(formatoData);

                    // Obtém os dados da linha selecionada
                    long id = (long) TabelaProduto.getValueAt(selectedRow, 0);
                    String nome = (String) TabelaProduto.getValueAt(selectedRow, 1);

                    // Obtém o valor da célula da data (tipo java.sql.Timestamp)
                    Object dataCell = TabelaProduto.getValueAt(selectedRow, 2);
                    String dataString = "";

                    if (dataCell instanceof java.sql.Timestamp) {
                        java.sql.Timestamp timestamp = (java.sql.Timestamp) dataCell;
                        // Formata a data como uma string
                        dataString = format.format(new Date(timestamp.getTime()));
                    } else {
                        // Lida com outros tipos de valores que podem estar na célula
                        // (nesse caso, você pode atribuir diretamente)
                        dataString = dataCell.toString();
                    }

                    Object valorCell = TabelaProduto.getValueAt(selectedRow, 3);
                    String valorString = "";

                    if (valorCell instanceof Double) {
                        // Converte o valor Double para String
                        valorString = Double.toString((Double) valorCell);
                    } else {
                        // Se não for um Double, converte para String diretamente
                        valorString = valorCell.toString();
                    }

                    try {
                        // Convertendo a string para um valor double
                        double valor = Double.parseDouble(valorString);

                        // Restante do seu código...
                    } catch (NumberFormatException x) {
                        x.printStackTrace();
                    }

                    Integer quantidade = (Integer) TabelaProduto.getValueAt(selectedRow, 4);

                    try {
                        Date data = format.parse(dataString);
                        try {
                            // Convertendo a string para um valor double
                            double valor = Double.parseDouble(valorString);

                            JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
                            NovoProduto telaedicao = new NovoProduto();

                            telaedicao.setProdutoId(id);
                            telaedicao.setNomeProduto(nome);
                            telaedicao.setDT_Entrada(data);
                            telaedicao.setValor(valor);
                            telaedicao.setEstoque(quantidade);

                            // Atualiza o conteúdo da janela atual
                            currentFrame.setContentPane(telaedicao.getNovoprodutopainel());

                            // Atualiza a exibição
                            currentFrame.revalidate();
                            currentFrame.repaint();
                        } catch (NumberFormatException x) {
                            x.printStackTrace();
                        }
                    } catch (ParseException x) {
                        x.printStackTrace();
                    }
                } else {
                    // Se nenhuma linha estiver selecionada, exiba uma mensagem de erro
                    JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha para editar.",
                            "Nenhuma Linha Selecionada", JOptionPane.ERROR_MESSAGE);
                }
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
                    Timestamp timestamp = (Timestamp) TabelaProduto.getValueAt(selectedRow, 2);
                    Object valorObj = TabelaProduto.getValueAt(selectedRow, 3);
                    String valorString = Objects.toString(valorObj, "");
                    Integer quantidade = (Integer) TabelaProduto.getValueAt(selectedRow, 4);

                    // Converte o Timestamp para uma String formatada
                    Date data = new Date(timestamp.getTime());
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    String dataString = format.format(data);

                    try {
                        // Convertendo a string para um valor double
                        double valor = Double.parseDouble(valorString);

                        // Cria uma instância de Produto com os dados da linha selecionada
                        Produto produtoParaExcluir = new Produto();
                        produtoParaExcluir.setId(id);
                        produtoParaExcluir.setNome(nome);
                        produtoParaExcluir.setDataEntrada(data);
                        produtoParaExcluir.setDataSaida(null);
                        produtoParaExcluir.setValor(valor);
                        produtoParaExcluir.setQuantidade(quantidade);

                        int option = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este produto?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {
                            // Realiza a operação de exclusão
                            produtoController.excluir(produtoParaExcluir);

                            // Atualiza a tabela após a exclusão
                            carregarDadosNaTabela();
                        }

                    } catch (NumberFormatException | HeadlessException x) {
                        x.printStackTrace();
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
        carregarDadosNaTabela();
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
