package com.amorapetshop.view;

import com.amorapetshop.controller.AnimalController;
import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Animal;
import com.amorapetshop.model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;


public class NovoAnimal {
    private JTextField nome;
    private JTextField epsecie;
    private JTextField raca;
    private JButton cancelarButton;
    private JButton cadastarButton;
    private JPanel main_frame_aminal_cadastro;
    private JPanel frame_animal;
    private JPanel frame_animal_titulo;
    private JButton buscarDonoButton;
    private JTextField dono;
    private AnimalController animalController;
    private long animalId;
    private JTextField idTextField;
    private Cliente clienteSelecionado;

    public NovoAnimal() {
        animalController = new AnimalController();

        cadastarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nomeAnimal = nome.getText();
                String especieAnimal = epsecie.getText();
                String racaAnimal = raca.getText();

                if (animalId != 0L) {
                    if (clienteSelecionado != null) {
                        Animal animalEditado = new Animal();
                        animalEditado.setId(animalId);
                        animalEditado.setNome(nomeAnimal);
                        animalEditado.setEspecie(especieAnimal);
                        animalEditado.setRaca(racaAnimal);
                        animalEditado.setDono(clienteSelecionado.getId());

                        // Chame o método de salvar no controlador
                        animalController.salvar(animalEditado);
                    }
                } else {
                    if (clienteSelecionado != null) {
                        Animal novoAnimal = new Animal();
                        novoAnimal.setNome(nomeAnimal);
                        novoAnimal.setEspecie(especieAnimal);
                        novoAnimal.setRaca(racaAnimal);
                        novoAnimal.setDono(clienteSelecionado.getId());
                        // Limpa o atributo clienteSelecionado após utilizá-lo

                        animalController.salvar(novoAnimal);
                    }
                    // Limpe os campos de texto ou faça outras ações após o cadastro
                    nome.setText("");
                    epsecie.setText("");
                    raca.setText("");
                    clienteSelecionado = null;
                    dono.setText("");
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
        buscarDonoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância de JDialog
                JDialog currentDialog = new JDialog();
                // Abrir a caixa de diálogo de pesquisa de cliente
                ConsultarClienteDialog pesquisaClienteDialog = new ConsultarClienteDialog(currentDialog);
                pesquisaClienteDialog.pack();
                pesquisaClienteDialog.setSize(450, 250);
                pesquisaClienteDialog.setVisible(true);

                // Obtém o cliente selecionado de todas as caixas de dialogo que forem apertas dentro dessa

                clienteSelecionado = pesquisaClienteDialog.getClienteSelecionado();
                //clienteSelecionado = pesquisaClienteDialog.getClienteSelecionado();
                //clienteSelecionado = consultarClienteDialog.getClienteSelecionado();
                //clienteSelecionado = novoClienteDialog.getClienteSelecionado();

                // Atualiza campos conforme necessário (exemplo: exibir o nome do cliente em um JTextField)
                dono.setText(clienteSelecionado != null ? clienteSelecionado.getNome() : "");
            }
        });
        main_frame_aminal_cadastro.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        dono.setEditable(false);
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
    public void setDono(long donoid) {
        // Verifica se o ID do dono é válido (maior que 0)
        if (donoid > 0) {
            // Crie uma instância do controlador de cliente
            ClienteController clienteController = new ClienteController();

            // Crie uma instância de Cliente com o ID fornecido
            Cliente clienteConsulta = new Cliente();
            clienteConsulta.setId(donoid);

            // Realize a consulta para obter as informações do cliente pelo ID
            List<Cliente> clientes = clienteController.buscarFiltro(clienteConsulta);

            // Verifica se o cliente foi encontrado
            if (!clientes.isEmpty()) {
                // Assume que a busca retornará apenas um cliente (ou você pode lidar com múltiplos resultados conforme necessário)
                Cliente cliente = clientes.get(0);

                // Atualiza o campo de texto "dono" com o nome do cliente
                dono.setText(cliente.getNome());
            } else {
                // Limpa o campo de texto "dono" se o cliente não for encontrado
                dono.setText("");
            }
        } else {
            // Limpa o campo de texto "dono" se o ID do dono não for válido
            dono.setText("");
        }
    }
    public void setAnimalId(long id) {
        this.animalId = id;
    }
}
