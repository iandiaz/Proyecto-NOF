package VO;



public class UbicacionVO {
	
	private String ubi_id;
	private String ubi_fisica;
	private String anc_id;
	private String descripcion;
	
	private DireccionVO direccion;
	private EstadosVigNoVigVO estadoVigNoVig;
	
	
	public EstadosVigNoVigVO getEstadoVigNoVig() {
		return estadoVigNoVig;
	}
	public void setEstadoVigNoVig(EstadosVigNoVigVO estadoVigNoVig) {
		this.estadoVigNoVig = estadoVigNoVig;
	}
	public DireccionVO getDireccion() {
		return direccion;
	}
	public void setDireccion(DireccionVO direccion) {
		this.direccion = direccion;
	}
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
