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

    public NovoAnimal() {
        animalController = new AnimalController();
        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtenha os dados do animal dos campos de texto
                String nomeAnimal = nome.getText();
                String especieAnimal = epsecie.getText();
                String racaAnimal = raca.getText();

                // Crie uma instância da entidade Animal com os dados
                Animal novoAnimal = new Animal();
                novoAnimal.setNome(nomeAnimal);
                novoAnimal.setEspecie(especieAnimal);
                novoAnimal.setRaca(racaAnimal);

                animalController.salvar(novoAnimal);

                // Limpe os campos de texto ou faça outras ações após o cadastro
                nome.setText("");
                epsecie.setText("");
                raca.setText("");
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
}
