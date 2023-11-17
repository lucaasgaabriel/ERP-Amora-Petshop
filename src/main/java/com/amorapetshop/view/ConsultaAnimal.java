package com.amorapetshop.view;

import com.amorapetshop.model.Animal;
import com.amorapetshop.model.dao.AnimalJpaDao;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConsultaAnimal {
    private JTable tabela_animais;
    private JTextField nome_input_animal;
    private JTextField Dono_input_animal;
    private JTextField especie_input_animal;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JButton voltarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JPanel animais_consulta;

    private List<Animal> listaDeAnimais;


    public ConsultaAnimal() {
        listaDeAnimais = new ArrayList<>(); // Você precisará importar java.util.ArrayList


        nome_input_animal.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }

            @Override
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
        Dono_input_animal.addInputMethodListener(new InputMethodListener() {
            @Override
            public void inputMethodTextChanged(InputMethodEvent event) {

            }
