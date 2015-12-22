package VO;

import java.util.ArrayList;


public class UbicacionVO {
	
	private String ubi_id;
	private String ubi_fisica;
	private String anc_id;
	private String descripcion;
	private ArrayList<ActivoVO> activos;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getAnc_id() {
		return anc_id;
	}
	public void setAnc_id(String anc_id) {
		this.anc_id = anc_id;
	}
	public String getUbi_id() {
		return ubi_id;
	}
	public void setUbi_id(String ubi_id) {
		this.ubi_id = ubi_id;
	}
	public String getUbi_fisica() {
		return ubi_fisica;
	}
	public void setUbi_fisica(String ubi_fisica) {
		this.ubi_fisica = ubi_fisica;
	}
	public UbicacionVO() {
		super();
		this.activos= new ArrayList<ActivoVO>();
		
	}
	
	public UbicacionVO(String id) {
		super();
		this.activos= new ArrayList<ActivoVO>();
		this.ubi_id=id;
	}
	
	public ArrayList<ActivoVO> getActivos() {
		return activos;
	}
	public void setActivos(ArrayList<ActivoVO> activos) {
		this.activos = activos;
	}
	@Override
    public boolean equals(Object o){
		UbicacionVO ubicacion = (UbicacionVO) o;
        if(o == null){
            return false;
        }
        if((this.ubi_id.equals(ubicacion.getUbi_id()))){
            return true;
        }
        return false;
    }
	

}
