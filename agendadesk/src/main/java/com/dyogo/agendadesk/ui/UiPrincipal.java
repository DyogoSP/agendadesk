package com.dyogo.agendadesk.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.dyogo.contato.Contato;

public class UiPrincipal {
	int posicao = 0;
	boolean novo;

	private JFrame frmAgendo;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtTelefone;

//	Botoes de açao
	private JButton btnNovo = new JButton("Novo");
	private JButton btnAlterar = new JButton("Alterar");
	private JButton btnSalvar = new JButton("Salvar");
	private JButton btnCancelar = new JButton("Cancelar");
	private JButton btnDeletar = new JButton("Deletar");
	private JButton btnFechar = new JButton("Fechar");
//	Botoes de navegaçao
	private JButton btnPrimeiro = new JButton("<<");
	private JButton btnAnterior = new JButton("<");
	private JButton btnProximo = new JButton(">");
	private JButton btnUltimo = new JButton(">>");

	List<Contato> lista = new ArrayList<Contato>();

	/**
	 * Create the application.
	 */
	public UiPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgendo = new JFrame();
		frmAgendo.setBounds(100, 100, 450, 300);
		frmAgendo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel pnTitulo = new JPanel();
		pnTitulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmAgendo.getContentPane().add(pnTitulo, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Agenda");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		pnTitulo.add(lblNewLabel);

		JPanel pnNavegador = new JPanel();
		pnNavegador.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmAgendo.getContentPane().add(pnNavegador, BorderLayout.SOUTH);
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicao = 0;
				Contato con = lista.get(posicao);
				txtId.setText(String.valueOf(con.getId()));
				txtNome.setText(con.getNome());
				txtTelefone.setText(con.getTelefone());
			}
		});
		btnPrimeiro.setEnabled(false);

		pnNavegador.add(btnPrimeiro);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (posicao > 0) {
					posicao -= 1;
					Contato con = lista.get(posicao);
					txtId.setText(String.valueOf(con.getId()));
					txtNome.setText(con.getNome());
					txtTelefone.setText(con.getTelefone());
				}
			}
		});
		btnAnterior.setEnabled(false);
		pnNavegador.add(btnAnterior);
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (posicao < lista.size() - 1) {
					posicao += 1;
					Contato con = lista.get(posicao);
					txtId.setText(String.valueOf(con.getId()));
					txtNome.setText(con.getNome());
					txtTelefone.setText(con.getTelefone());
				}
			}
		});
		btnProximo.setEnabled(false);
		pnNavegador.add(btnProximo);
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posicao = lista.size() - 1;
				Contato con = lista.get(posicao);
				txtId.setText(String.valueOf(con.getId()));
				txtNome.setText(con.getNome());
				txtTelefone.setText(con.getTelefone());
			}
		});
		btnUltimo.setEnabled(false);
		pnNavegador.add(btnUltimo);

		JPanel pnBotoes = new JPanel();
		pnBotoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmAgendo.getContentPane().add(pnBotoes, BorderLayout.EAST);
		pnBotoes.setLayout(new GridLayout(6, 1, 0, 5));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarBotoes();
				txtId.setText("");
				txtNome.setText("");
				txtTelefone.setText("");
				txtNome.requestFocus();
				posicao = lista.size();
				novo = true;
			}
		});

		pnBotoes.add(btnNovo);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ativarBotoes();
				txtNome.requestFocus();
				novo = false;
			}
		});
		btnAlterar.setEnabled(false);
		pnBotoes.add(btnAlterar);
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				desativarBotoes();
				Contato con;

				if (!txtNome.getText().trim().isEmpty()) {
					if (novo) {
						con = new Contato();
						con.setId(lista.size() + 1);
						con.setNome(txtNome.getText());
						con.setTelefone(txtTelefone.getText());
						lista.add(con);

						JOptionPane.showMessageDialog(frmAgendo, "Contato salvo");

						txtId.setText(String.valueOf(con.getId()));
						txtNome.setText(con.getNome());
						txtTelefone.setText(con.getTelefone());

					} else {
						con = lista.get(posicao);
						con.setNome(txtNome.getText());
						con.setTelefone(txtTelefone.getText());
						JOptionPane.showMessageDialog(frmAgendo, "Contato alterado");
					}
				} else {
					JOptionPane.showMessageDialog(frmAgendo, "Error, o nome esta em branco!");
					con = new Contato();
					con = lista.get(posicao = 0);
					txtId.setText(String.valueOf(con.getId()));
					txtNome.setText(con.getNome());
					txtTelefone.setText(con.getTelefone());
					ativarBotoes();
				}

			}
		});
		pnBotoes.add(btnSalvar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desativarBotoes();

				Contato con = lista.get(lista.size() - 1);
				txtId.setText(String.valueOf(con.getId()));
				txtNome.setText(con.getNome());
				txtTelefone.setText(con.getTelefone());
			}
		});
		btnCancelar.setEnabled(false);
		pnBotoes.add(btnCancelar);
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Contato con;

				int conf = JOptionPane.showConfirmDialog(frmAgendo, "Tem certeza que dejesa deletar o contato",
						"DELETAR", JOptionPane.YES_NO_OPTION);

				if (conf == JOptionPane.YES_OPTION) {
					con = lista.get(posicao);
					lista.remove(posicao);
					if (posicao == lista.size()) {
						con = lista.get(lista.size() - 1);
						txtId.setText(String.valueOf(con.getId()));
						txtNome.setText(con.getNome());
						txtTelefone.setText(con.getTelefone());
					}
					txtId.setText(String.valueOf(con.getId()));
					txtNome.setText(con.getNome());
					txtTelefone.setText(con.getTelefone());
				}

			}
		});
		btnDeletar.setEnabled(false);
		pnBotoes.add(btnDeletar);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pnBotoes.add(btnFechar);

		JPanel pnConteudo = new JPanel();
		frmAgendo.getContentPane().add(pnConteudo, BorderLayout.CENTER);
		pnConteudo.setLayout(null);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(20, 11, 46, 14);
		pnConteudo.add(lblId);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setBounds(20, 60, 46, 14);
		pnConteudo.add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTelefone.setBounds(10, 111, 69, 14);
		pnConteudo.add(lblTelefone);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(89, 8, 61, 20);
		pnConteudo.add(txtId);
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBounds(89, 59, 121, 20);
		pnConteudo.add(txtNome);
		txtNome.setColumns(10);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(89, 110, 121, 20);
		pnConteudo.add(txtTelefone);
		txtTelefone.setColumns(10);
	}

	public void mostrar() {

		frmAgendo.setVisible(true);
	}

	public void ativarBotoes() {
		btnNovo.setEnabled(false);
		btnSalvar.setEnabled(true);
		btnDeletar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnCancelar.setEnabled(true);
		btnPrimeiro.setEnabled(false);
		btnProximo.setEnabled(false);
		btnAnterior.setEnabled(false);
		btnUltimo.setEnabled(false);
		txtNome.setEditable(true);
		txtTelefone.setEditable(true);
	}

	public void desativarBotoes() {
		btnNovo.setEnabled(true);
		btnSalvar.setEnabled(false);
		btnDeletar.setEnabled(true);
		btnAlterar.setEnabled(true);
		btnCancelar.setEnabled(false);
		btnPrimeiro.setEnabled(true);
		btnProximo.setEnabled(true);
		btnAnterior.setEnabled(true);
		btnUltimo.setEnabled(true);
		txtNome.setEditable(false);
		txtTelefone.setEditable(false);
	}

}
