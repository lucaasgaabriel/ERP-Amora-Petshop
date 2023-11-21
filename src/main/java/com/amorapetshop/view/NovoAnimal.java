package com.amorapetshop.view;

import com.amorapetshop.controller.AnimalController;
import com.amorapetshop.model.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NovoAnimal {
    private JTextField nome;
    private JTextField epsecie;
    private JTextField raca;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JPanel main_frame_aminal_cadastro;
    private JPanel frame_animal;
    private JPanel frame_animal_titulo;
    private AnimalController animalController;
    private long animalId;
    private JTextField idTextField;

    public NovoAnimal() {
        animalController = new AnimalController();

        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (animalId != 0L) {
                    // Edição de um animal existente
                    String nomeAnimal = nome.getText();
                    String especieAnimal = epsecie.getText();
                    String racaAnimal = raca.getText();

                    Animal animalEditado = new Animal();
                    animalEditado.setId(animalId);
                    animalEditado.setNome(nomeAnimal);
                    animalEditado.setEspecie(especieAnimal);
                    animalEditado.setRaca(racaAnimal);

                    // Chame o método de salvar no controlador
                    animalController.salvar(animalEditado);
                } else {
                    // Novo cadastro de animal
                    String nomeAnimal = nome.getText();
                    String especieAnimal = epsecie.getText();
                    String racaAnimal = raca.getText();

                    Animal novoAnimal = new Animal();
                    novoAnimal.setNome(nomeAnimal);
                    novoAnimal.setEspecie(especieAnimal);
                    novoAnimal.setRaca(racaAnimal);

                    // Chame o método de salvar no controlador
                    animalController.salvar(novoAnimal);

                    // Limpe os campos de texto ou faça outras ações após o cadastro
                    nome.setText("");
                    epsecie.setText("");
                    raca.setText("");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtém a janela atual associada ao botão clicado
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

                // Cria e configura o novo conteúdo (ConsultaAnimal)
                ConsultaAnimal consultaAnimal = new ConsultaAnimal();
                JPanel animaisConsultaPanel = consultaAnimal.getAnimaisConsulta();

                // Atualiza o conteúdo da janela atual
                currentFrame.setContentPane(animaisConsultaPanel);

                // Atualiza a exibição
                currentFrame.revalidate();
                currentFrame.repaint();
            }
        });
        main_frame_aminal_cadastro.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
    }
    public JPanel getMain_frame_aminal_cadastro() {
        return main_frame_aminal_cadastro;
    }


    public void setNome(String nome) {
        this.nome.setText(nome);
    }

    public void setEspecie(String epsecie) {
        this.epsecie.setText(epsecie);
    }

    public void setRaca(String raca) {
        this.raca.setText(raca);
    }
    public void setAnimalId(long id) {
        this.animalId = id;
    }
}
