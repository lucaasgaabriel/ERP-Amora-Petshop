package com.amorapetshop.view;

import com.amorapetshop.controller.AgendamentoController;
import com.amorapetshop.model.Agendamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class NovoAgendamento {
    private JTextField resposavel;
    private JTextField Data_agendamento;
    private JTextField HR_Agendamento;
    private JTextField valor_Agendamento;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JPanel Agendemano_painel_new;
    private JComboBox comboBoxtipo;
    private  AgendamentoController agendamentoController;

    public NovoAgendamento() {
        agendamentoController = new AgendamentoController();
        comboBoxtipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenha o tipo selecionado
                String tipoSelecionado = (String) comboBoxtipo.getSelectedItem();

                // Defina o valor sugerido com base no tipo
                switch (tipoSelecionado) {
                    case "Banho":
                        valor_Agendamento.setText("50.00");
                        break;
                    case "Tosa":
                        valor_Agendamento.setText("30.00");
                        break;
                    case "Banho+Tosa":
                        valor_Agendamento.setText("65.00");
                        break;
                    case "Vaterinario":
                        valor_Agendamento.setText("130.00");
                        break;
                    default:
                        valor_Agendamento.setText(""); // Limpar o valor sugerido se nenhum tipo corresponder
                        break;
                }
            }
        });
        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String formatoData = "dd-MM-yyyy";
                SimpleDateFormat format = new SimpleDateFormat(formatoData);

                // Obtenha os dados do agendamento dos campos de texto
                String tipo = (String) comboBoxtipo.getSelectedItem();
                String dataString = Data_agendamento.getText();
                String hora = HR_Agendamento.getText();
                String responsavel = resposavel.getText();
                String valor = valor_Agendamento.getText();

                try {
                    Date data = format.parse(dataString);

                    // Crie uma instância da entidade Animal com os dados
                    Agendamento novoAgendamento = new Agendamento();
                    novoAgendamento.setTipoAgendamento(tipo);
                    novoAgendamento.setDataAgendamento(data);
                    novoAgendamento.setHoraAgendamento(hora);
                    novoAgendamento.setResponsavel(responsavel);
                    novoAgendamento.setPrecoOrcamento(Double.parseDouble(valor));

                    agendamentoController.salvar(novoAgendamento);

                } catch (ParseException x) {
                    x.printStackTrace();
                }

                comboBoxtipo.setSelectedIndex(0);
                Data_agendamento.setText("");
                HR_Agendamento.setText("");
                resposavel.setText("");
                valor_Agendamento.setText("");
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultaAgendamento Novo_agendamento = new ConsultaAgendamento();
                JPanel Novo_agendamentoPainel = Novo_agendamento.getAgendamentoconsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(Novo_agendamentoPainel);

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
