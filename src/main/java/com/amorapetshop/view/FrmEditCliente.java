package com.amorapetshop.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.amorapetshop.controller.ClienteController;
import com.amorapetshop.model.Cliente;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmEditCliente extends JFrame {
	//Campos declareados como atributos (Globais na classe)
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTable tablePessoa;
	private JTextField txtDtNacimento;
	private JTextField txtSexo;
	private DefaultTableModel modelo;
	
	//Lista que irá carregar a tabela
	private List<Cliente> listaCliente = new ArrayList<>();
	//Cliente que irá espelhar os campos
	private Cliente clienteAtual = new Cliente();
	//Controller responsavel por persistir Cliente
	private ClienteController clienteController;

	/**
	 * Método construtor do formulário
	 */
	public FrmEditCliente(){
		clienteController = new ClienteController();
		
		//cofiguração do formulário
		setTitle("Manter Cliente");
		setSize(700,495);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//criação dos paineis principais
		JPanel panelSuperior = new JPanel();
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);
		
		JPanel panelRodape = new JPanel();
		getContentPane().add(panelRodape, BorderLayout.SOUTH);
		
		
		/*Inseindo os campos no formulário*/
		JLabel lblTitulo = new JLabel("Cadastro de Cliente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelSuperior.add(lblTitulo);
		
		//legenda
		JLabel lbId = new JLabel("Código:");
		lbId.setBounds(10, 11, 72, 23);
		lbId.setFont(new Font("Tahoma", Font.BOLD, 12));
		//campo de entrada
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(119, 11, 104, 23);
		txtId.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtId.setColumns(10);
		
		//legenda
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(10, 41, 99, 23);
		//campo de entrada
		txtNome = new JTextField();
		txtNome.setFont(new Font("Dialog", Font.PLAIN, 12));
		txtNome.setBounds(119, 41, 543, 23);
		txtNome.setColumns(40);
		
		//legenda
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCpf.setBounds(10, 79, 99, 14);
		
		//campo de entrada
		txtCpf = new JTextField();
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtCpf.setBounds(119, 75, 182, 23);
		panelCentral.add(txtCpf);
		txtCpf.setColumns(10);

		//legenda
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTelefone.setBounds(371, 79, 99, 14);
		//campo texto
		txtTelefone = new JTextField();
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTelefone.setBounds(480, 75, 182, 23);
		txtTelefone.setColumns(10);
		
		//legenda
		JLabel lblEmail = new JLabel("e-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEmail.setBounds(10, 113, 99, 23);
		//campo texto
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setBounds(119, 113, 279, 23);
		txtEmail.setColumns(10);
		
		//legenda
		JLabel lbDtNacimento = new JLabel("Dt. Nascimento:");
		lbDtNacimento.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbDtNacimento.setBounds(10, 152, 99, 14);
		//campo texto
		txtDtNacimento = new JTextField();
		txtDtNacimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtDtNacimento.setBounds(119, 147, 104, 24);
		txtDtNacimento.setColumns(10);
		
		//legenda
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSexo.setBounds(281, 152, 46, 14);
		//campo texto
		txtSexo = new JTextField();
		txtSexo.setBounds(337, 147, 86, 23);
		txtSexo.setColumns(10);
		
		//inserindo os campos no painel Central
		panelCentral.add(lbId);
		panelCentral.add(txtId);
		panelCentral.add(lblNome);
		panelCentral.add(txtNome);
		panelCentral.add(lblCpf);
		panelCentral.add(lblTelefone);
		panelCentral.add(txtTelefone);
		panelCentral.add(lblEmail);
		panelCentral.add(txtEmail);
		panelCentral.add(lbDtNacimento);
		panelCentral.add(txtDtNacimento);
		panelCentral.add(lblSexo);
		panelCentral.add(txtSexo);
				
		//Configurações da Tabela que irá listar as pessoas
		tablePessoa = new JTable();
		tablePessoa.addMouseListener(new MouseAdapter() {
			//configuração do evento click na tablea
			@Override
			public void mouseClicked(MouseEvent e) {
				//verifica a linha que foi clicada
				int linha = tablePessoa.getSelectedRow();
				clienteAtual = listaCliente.get(linha);
				preencheCampos(clienteAtual);
			}
		});
		tablePessoa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePessoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablePessoa.setBounds(10, 178, 664, 188);
		
		//Definindo as colunas da tabela
        String[] colunas = {"Nome", "CPF" , "e-mail"};
        String[][] objetos = {{"", "", ""}};

        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });
        tablePessoa.setModel(modelo);
		
        JScrollPane scroll = new JScrollPane(tablePessoa);
        scroll.setSize(664, 212);
        scroll.setLocation(10, 177);
		panelCentral.add(scroll);
		
		//Criação e configuração dos botões
		//Botão pesquisar
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				carregaPessoaAtual();
				listaCliente = clienteController.buscarFiltro(clienteAtual);
				carregarTabela();
			}
		});
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//Botão novo
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteAtual = new Cliente();
				preencheCampos(clienteAtual);
			}
		});
		btnNovo.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//Botão salvar
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregaPessoaAtual();
				clienteController.salvar(clienteAtual);
				txtId.setText(clienteAtual.getId().toString());
				listaCliente = clienteController.buscarTodos();
				carregarTabela();
			}
		});
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//Botão excluir
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clienteController.excluir(clienteAtual);
				clienteAtual =new Cliente();
				preencheCampos(clienteAtual);
				listaCliente = clienteController.buscarTodos();
				carregarTabela();
			}
		});
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		//insterindo os botões no Painel rodapé
		panelRodape.setLayout(new GridLayout(0, 4, 0, 0));
		panelRodape.add(btnPesquisar);
		panelRodape.add(btnNovo);
		panelRodape.add(btnSalvar);
		panelRodape.add(btnExcluir);


	}

	/**
	 * Preenche a tabela com todos os elementos da lista de pessoa
	 */
	public void carregarTabela() {
			String[] colunas = {"Nome", "CPF","e-mail"};
	        			
			String[][] objetos = new String [listaCliente.size()][3];
			int i=0;
			for(Cliente cliente : listaCliente) {
				objetos[i]= new String[]{cliente.getNome(), cliente.getCpf()};
				i++;
			}
			
	        modelo = (new DefaultTableModel(objetos,colunas){
	            @Override
	            public boolean isCellEditable(int row, int column){
	                return false;
	            }
	        });
	        
	        tablePessoa.setModel(modelo);
	}
	
	/**
	 * Insere nos campos, os dados de uma cliente
	 * @param cliente
	 */
	void preencheCampos(Cliente cliente) {
		txtId.setText(cliente.getId().toString());
		txtNome.setText(cliente.getNome());
		txtCpf.setText(cliente.getCpf());
		txtTelefone.setText(cliente.getTelefone());
		
		txtSexo.setText(cliente.getSexo());
		txtTelefone.setText(cliente.getTelefone());
	}
	
	/**
	 * Carrega para a Cliente Atual os dados que estão no formulário
	 */
	void carregaPessoaAtual() {
		if(!txtId.getText().isEmpty())
			clienteAtual.setId(Long.parseLong(txtId.getText()));
		clienteAtual.setCpf(txtCpf.getText());

		clienteAtual.setNome(txtNome.getText());
		clienteAtual.setSexo(txtSexo.getText());
		clienteAtual.setTelefone(txtTelefone.getText());
	}
}
