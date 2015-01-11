package object;

public class Barang {
	private String id, nama, idSupp;
	private int harga, stok;

	public Barang(String id, String nama, String idSupp, int harga, int stok){
		this.id=id;
		this.nama=nama;
		this.idSupp=idSupp;
		this.harga=harga;
		this.stok=stok;
	}
	
	public Barang(String nama, String idSupp, int harga, int stok){
		this.nama=nama;
		this.idSupp=idSupp;
		this.harga=harga;
		this.stok=stok;
	}

	public String getId() {
		return id;
	}

	public int getJumlah() {
		return stok;
	}

	public int getHarga() {
		return harga;
	}

	public String getNama() {
		return nama;
	}

	public String getSatuan() {
		return idSupp;
	}
}
