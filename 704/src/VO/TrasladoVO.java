package VO;

public class TrasladoVO {
	String ubi_id_destino;
	String ubi_id_origen;
	String dire_origen;
	String dire_destino;
	String ubi_id_both;
	String fecha;
	String pnumber;
	String serie; 
	String prod_id;
	
	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getPnumber() {
		return pnumber;
	}

	public String getSerie() {
		return serie;
	}

	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUbi_id_both() {
		return ubi_id_both;
	}

	public void setUbi_id_both(String ubi_id_both) {
		this.ubi_id_both = ubi_id_both;
	}

	public String getDire_origen() {
		return dire_origen;
	}

	public String getDire_destino() {
		return dire_destino;
	}

	public void setDire_origen(String dire_origen) {
		this.dire_origen = dire_origen;
	}

	public void setDire_destino(String dire_destino) {
		this.dire_destino = dire_destino;
	}

	public String getUbi_id_destino() {
		return ubi_id_destino;
	}

	public String getUbi_id_origen() {
		return ubi_id_origen;
	}

	public void setUbi_id_destino(String ubi_id_destino) {
		this.ubi_id_destino = ubi_id_destino;
	}

	public void setUbi_id_origen(String ubi_id_origen) {
		this.ubi_id_origen = ubi_id_origen;
	}

	public TrasladoVO() {
		super();
		
	}

}
