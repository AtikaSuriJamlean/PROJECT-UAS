package object;

public class DetilTransaksi {
	private Barang barang;
	private int jumlah;
	private Transaksi transaksi;

	public DetilTransaksi(Transaksi transaksi, Barang barang, int jumlah) {
		this.transaksi = transaksi;
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public DetilTransaksi(Barang barang, int jumlah) {
		this.barang = barang;
		this.jumlah = jumlah;
	}

	public Barang getBarang() {
		return barang;
	}

	public int getJumlah() {
		return jumlah;
	}

	public Transaksi getTransaksi() {
		return transaksi;
	}

}
