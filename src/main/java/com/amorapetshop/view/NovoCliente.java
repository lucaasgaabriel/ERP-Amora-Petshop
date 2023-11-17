package com.amorapetshop.view;

import javax.swing.*;

public class NovoCliente {
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JTextField textFieldTelefone;
    private JButton cancelarButton;
    private JButton cadastrarButton;
    private JComboBox<String> comboBoxCategoria;

    public JTextField getNomeTextField() {
        return textFieldNome;
    }

    public JTextField getEmailTextField() {
        return textFieldEmail;
    }

    public JTextField getTelefoneTextField() {
        return textFieldTelefone;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public JButton getCadastrarButton() {
        return cadastrarButton;
    }

    public JComboBox<String> getCategoriaComboBox() {
        return comboBoxCategoria;
    }

    // Método para limpar os campos após o cadastro
    public void limparCampos() {
        textFieldNome.setText("");
        textFieldEmail.setText("");
        textFieldTelefone.setText("");
    }
}

