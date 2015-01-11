package ui;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

import object.Barang;
import system.*;
import ui.listener.CustActionListener;
import ui.listener.CustKeyListener;

public class WindowFormBarang extends JFrame {
	private Core core;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField tfNama,tfid, tfidSupp, tfSatuan, tfHarga;
	private JTable tbl;
	private JLabel lbBarang, lbNama, lbJumlah, lbSatuan, lbHarga;

	private Vector<Barang> barang = new Vector<Barang>();
	private Vector<String> nmBarang = new Vector<String>();

	public WindowFormBarang(final Core core) {
		super("Formulir Barang");
		this.core = core;
		setResizable(false);

		setSize(810, 272);
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		setLayout(null);
		Container container = this.getContentPane();
		container.setBackground(Color.WHITE);
		JMenuBar menu = new JMenuBar();
		this.setJMenuBar(menu);

		JMenu menuUser = new JMenu(
				core.getLoggedInUser().admin() ? "Supervisor " : "Kasir "
						+ core.getLoggedInUser().getUsername());
		JMenuItem miLogOut = new JMenuItem("Log Out");
		miLogOut.addActionListener(new CustActionListener(core, this, miLogOut,
				CustActionListener.LOGOUT));

		JMenu menuTrans = new JMenu("Transaksi");
		JMenuItem miTransData = new JMenuItem("Data Transaksi");
		miTransData.addActionListener(new CustActionListener(core, this,
				miTransData, CustActionListener.SHOW_DATA_TRANSAKSI));
		/*
		 * JMenuItem miTransCetak = new JMenuItem("Cetak Transaksi");
		 */
		JMenu menuBarang = new JMenu("Barang");
		// JMenuItem miBarangData = new JMenuItem("Data Barang");
		/*
		 * miBarangData.addActionListener(new CustActionListener(core, this,
		 * miBarangData, CustActionListener.SHOW_DATA_BARANG));
		 */
		JMenuItem miBarangCetak = new JMenuItem("Cetak Barang");
		miBarangCetak.addActionListener(new CustActionListener(core, this,
				miBarangCetak, CustActionListener.CETAK_BARANG));
		menu.add(menuUser);
		menuUser.add(miLogOut);

		menu.add(menuTrans);
		// menuTrans.add(miTransCetak);
		menuTrans.add(miTransData);
		menu.add(menuBarang);
		// menuBarang.add(miBarangData);
		menuBarang.add(miBarangCetak);

		ResultSet rs = Operator.getListBarang(core.getConnection());
		try {
			while (rs.next()) {
				barang.add(new Barang(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getInt(4), rs.getInt(5)));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

		tbl = new JTable(Operator.resultSetToTableModel(Operator
				.getListBarang(core.getConnection())));
		Operator.disableTableEdit(tbl);
		JPanel panTbl = new JPanel();

		panTbl.setLayout(new BorderLayout());
		panTbl.setBackground(Color.WHITE);
		panTbl.add(new JScrollPane(tbl), BorderLayout.CENTER);

		tfNama = new JTextField();
		tfid = new JTextField();
		tfidSupp = new JTextField();
		tfSatuan = new JTextField();
		tfHarga = new JTextField();

		tfNama.setBounds(115, 10, 170, 20);
		tfidSupp.setBounds(115, 35, 170, 20);
		tfidSupp.addKeyListener(new CustKeyListener(core, this, tfidSupp,
				CustKeyListener.NUMBER_ONLY));
		tfSatuan.setBounds(115, 60, 170, 20);
		tfHarga.setBounds(115, 85, 170, 20);
		tfHarga.addKeyListener(new CustKeyListener(core, this, tfHarga,
				CustKeyListener.NUMBER_ONLY));

		panTbl.setBounds(295, 10, 500, 200);

		lbNama = new JLabel("Nama Barang");
		lbJumlah = new JLabel("Jumlah Barang");
		lbSatuan = new JLabel("Satuan Barang");
		lbHarga = new JLabel("Harga satuan");

		lbNama.setBounds(10, 10, 100, 20);
		lbNama.setHorizontalAlignment(JLabel.RIGHT);
		lbJumlah.setBounds(10, 35, 100, 20);
		lbJumlah.setHorizontalAlignment(JLabel.RIGHT);
		lbSatuan.setBounds(10, 60, 100, 20);
		lbSatuan.setHorizontalAlignment(JLabel.RIGHT);
		lbHarga.setBounds(10, 85, 100, 20);
		lbHarga.setHorizontalAlignment(JLabel.RIGHT);

		JButton buttonTambah = new JButton("Tambah");
		JButton buttonDelete = new JButton("Delete");

		buttonTambah.setBounds(205, 115, 80, 20);
		buttonTambah.addActionListener(new CustActionListener(core, this,tbl,
				buttonTambah, CustActionListener.TAMBAH_BARANG));
		buttonDelete.setBounds(115, 115, 80, 20);
		buttonDelete.addActionListener(new CustActionListener(core, this,tbl,
				buttonDelete, CustActionListener.HAPUS_BARANG));
		// Add Content
		container.add(tfNama);
		container.add(tfid);
		container.add(tfidSupp);
		container.add(tfSatuan);
		container.add(tfHarga);
		container.add(panTbl);
		container.add(lbNama);
		container.add(lbJumlah);
		container.add(lbSatuan);
		container.add(lbHarga);

		container.add(buttonDelete);
		container.add(buttonTambah);
	}

	public Vector<Barang> getListBarang() {
		return barang;
	}

	public Barang getSelectedBarang() {
		return barang.get(tbl.getSelectedRow());
	}

	public void submitToDB() {
		if (Operator.tambahBarang(getBarang(), core.getConnection())) {
			JOptionPane.showMessageDialog(this, "Data telah ditambahkan!");
		} else {
			JOptionPane.showMessageDialog(this, "Terjadi kesalahan!");
		}
		
		((DefaultTableModel)tbl.getModel()).addRow(new Object[]{Operator.getLastIDBarang(core.getConnection()),tfNama.getText(),tfidSupp.getText(),tfSatuan.getText(),tfHarga.getText()});

		tfNama.setText("");
		tfid.setText("");
		tfidSupp.setText("");
		tfSatuan.setText("");
		tfHarga.setText("");
	}

	public void resetForm() {
		tfNama.setText("");
		tfid.setText("");
		tfidSupp.setText("");
		tfSatuan.setText("");
		tfHarga.setText("");

		if (tbl.getSelectedRow() >= 0) {
			((DefaultTableModel) tbl.getModel())
					.removeRow(tbl.getSelectedRow());
		}
	}


	public Barang getBarang() {
		return new Barang(tfid.getText(),tfNama.getText(),tfidSupp.getText(),
				Integer.parseInt(tfHarga.getText()),
				Integer.parseInt(tfSatuan.getText()));
	}
}