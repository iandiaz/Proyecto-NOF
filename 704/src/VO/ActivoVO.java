package VO;

import java.util.ArrayList;

public class ActivoVO {
	String id;
	String serie;
	String estado_prod;
	String prod_id;
	ArrayList<TipoTcVO> TiposTC;
	
	public ArrayList<TipoTcVO> getTiposTC() {
		return TiposTC;
	}
	public void setTiposTC(ArrayList<TipoTcVO> tiposTC) {
		TiposTC = tiposTC;
	}
	public ActivoVO() {
		super();
		this.TiposTC=new ArrayList<TipoTcVO>();
	}
	public String getId() {
		return id;
	}
	public String getSerie() {
		return serie;
	}
	public String getEstado_prod() {
		return estado_prod;
	}
	public String getProd_id() {
		return prod_id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public void setEstado_prod(String estado_prod) {
		this.estado_prod = estado_prod;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	

}
