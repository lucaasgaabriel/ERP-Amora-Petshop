package com.amorapetshop.view;

import javax.swing.*;

public class NovoAnimal {
    private JTextField textFieldNome;
    private JTextField textFieldEspecie;
    private JTextField textFieldRaca;
    private JButton cancelarButton;
    private JButton cadastrarButton;

    public JTextField getNomeTextField() {
        return textFieldNome;
    }

    public JTextField getEspecieTextField() {
        return textFieldEspecie;
    }

    public JTextField getRacaTextField() {
        return textFieldRaca;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public JButton getCadastrarButton() {
        return cadastrarButton;
    }

    // Método para limpar os campos após o cadastro
    public void limparCampos() {
        textFieldNome.setText("");
        textFieldEspecie.setText("");
        textFieldRaca.setText("");
    }
}