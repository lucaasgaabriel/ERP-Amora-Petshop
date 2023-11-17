package com.amorapetshop.view;

import javax.swing.*;

public class NovoAgendamento {
    private JTextField textFieldResponsavel;
    private JTextField textFieldTipoAgendamento;
    private JTextField textField3;
    private JTextField textField4;
    private JButton cancelarButton;
    private JButton cadastrarButton;

    public JTextField getResponsavelTextField() {
        return textFieldResponsavel;
    }

    public JTextField getTipoAgendamentoTextField() {
        return textFieldTipoAgendamento;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }

    public JButton getCadastrarButton() {
        return cadastrarButton;
    }

    // Método para limpar os campos após o cadastro
    public void limparCampos() {
        textFieldResponsavel.setText("");
        textFieldTipoAgendamento.setText("");
        // Limpe outros campos conforme necessário
    }

}