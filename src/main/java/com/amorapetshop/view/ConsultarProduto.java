package com.amorapetshop.view;

import javax.swing.*;

public class ConsultarProduto {
    private JPanel panel1;
    private JTextField textFieldNome;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JButton voltarButton;
    private JTable tableProdutos;
    private JButton buttonLimpar;

    public JTextField getNomeTextField() {
        return textFieldNome;
    }

    public JButton getCadastrarButton() {
        return cadastrarButton;
    }

    public JButton getPesquisarButton() {
        return pesquisarButton;
    }

    public JButton getVoltarButton() {
        return voltarButton;
    }

    public JTable getTableProdutos() {
        return tableProdutos;
    }

    public JButton getButtonLimpar() {
        return buttonLimpar;
    }
}

