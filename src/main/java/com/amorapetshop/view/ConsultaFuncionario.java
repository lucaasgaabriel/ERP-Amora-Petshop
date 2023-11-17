package com.amorapetshop.controller;

import com.amorapetshop.model.Funcionario;
import com.amorapetshop.model.dao.FuncionarioJpaDao;
import com.amorapetshop.view.ConsultaFuncionario;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultaFuncionarioController {
    private FuncionarioJpaDao funcionarioDao;
    private ConsultaFuncionario view;

    public ConsultaFuncionarioController(FuncionarioJpaDao funcionarioDao, ConsultaFuncionario view) {
        this.funcionarioDao = funcionarioDao;
        this.view = view;

        view.getPesquisarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisarFuncionarios();
            }
        });

        view.getCadastrarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para abrir a tela de cadastro
            }
        });

        view.getVoltarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para voltar para a tela anterior
            }
        });
    }

    private void pesquisarFuncionarios() {
        String nome = view.getTextField1().getText();
        String cargo = view.getTextField2().getText();
        String outroCampo = view.getTextField3().getText();

        Funcionario funcionarioFiltro = new Funcionario();
        funcionarioFiltro.setNome(nome);
        funcionarioFiltro.setCargo(cargo);
        funcionarioFiltro.setOutroCampo(outroCampo);

        List<Funcionario> funcionarios = funcionarioDao.buscaFiltro(funcionarioFiltro);

        // Atualiza a tabela com os resultados da pesquisa
        DefaultTableModel model = (DefaultTableModel) view.getTable1().getModel();
        model.setRowCount(0);

        for (Funcionario funcionario : funcionarios) {
            model.addRow(new Object[]{funcionario.getId(), funcionario.getNome(), funcionario.getCargo(), funcionario.getOutroCampo()});
        }
    }

    public static void main(String[] args) {
        // Supondo que você já tenha uma instância de FuncionarioJpaDao e ConsultaFuncionario
        FuncionarioJpaDao funcionarioDao = new FuncionarioJpaDao();
        ConsultaFuncionario consultaFuncionarioView = new ConsultaFuncionario();
        ConsultaFuncionarioController controller = new ConsultaFuncionarioController(funcionarioDao, consultaFuncionarioView);
    }
}
