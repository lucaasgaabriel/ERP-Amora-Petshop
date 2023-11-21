package com.amorapetshop.view;

import com.amorapetshop.controller.AgendamentoController;
import com.amorapetshop.model.Agendamento;
import com.amorapetshop.model.Cliente;
import com.amorapetshop.model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class NovoAgendamento {
    private JTextField tipoAgendamento;
    private JTextField Data_agendamento;
    private JTextField HR_Agendamento;
    private JTextField Responsavel_Agendamento;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JPanel Agendemano_painel_new;
    private  AgendamentoController agendamentoController;

    public NovoAgendamento() {
        agendamentoController = new AgendamentoController();
        tipoAgendamento.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {
                String formatoData = "dd-MM-yyyy";
                SimpleDateFormat format = new SimpleDateFormat(formatoData);

                // Obtenha os dados do agendamento dos campos de texto
                String nome = tipoAgendamento.getText();
                String dataString = Data_agendamento.getText();
                String hora = HR_Agendamento.getText();
                String responsavel = Responsavel_Agendamento.getText();


                try {
                    Date data = format.parse(dataString);

                    // Crie uma instância da entidade Animal com os dados
                    Agendamento novoAgendamento = new Agendamento();
                    novoAgendamento.setTipoAgendamento(nome);
                    novoAgendamento.setDataAgendamento(data);
                    novoAgendamento.setHoraAgendamento(hora);
                    novoAgendamento.setResponsavel(responsavel);

                    agendamentoController.salvar(novoAgendamento);

                } catch (ParseException x) {
                    x.printStackTrace();


                }
                // Limpe os campos de texto ou faça outras ações após o cadastro
                tipoAgendamento.setText("");
                Data_agendamento.setText("");
                HR_Agendamento.setText("");
                Responsavel_Agendamento.setText("");
            };

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        Data_agendamento.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        HR_Agendamento.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        Responsavel_Agendamento.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
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

                Index index = new Index();
                JPanel indexpainel = index.getMainIndex();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(indexpainel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        Agendemano_painel_new.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getAgendemano_painel_new() {
        return Agendemano_painel_new;
    }
}
