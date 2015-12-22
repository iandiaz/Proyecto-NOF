package VO;


public class UbicacionVO {
	
	private String ubi_id;
	private String ubi_fisica;
	private String anc_id;
	private String descripcion;
	private String id_activo;
	private String tp_tc_id;
	private String tp_tc_nombre;
	private String activo_tptc_id;
	
	public String getActivo_tptc_id() {
		return activo_tptc_id;
	}
	public void setActivo_tptc_id(String activo_tptc_id) {
		this.activo_tptc_id = activo_tptc_id;
	}
	public String getTp_tc_nombre() {
		return tp_tc_nombre;
	}
	public void setTp_tc_nombre(String tp_tc_nombre) {
		this.tp_tc_nombre = tp_tc_nombre;
	}
	public String getId_activo() {
		return id_activo;
	}
	public String getTp_tc_id() {
		return tp_tc_id;
	}
	public void setId_activo(String id_activo) {
		this.id_activo = id_activo;
	}
	public void setTp_tc_id(String tp_tc_id) {
		this.tp_tc_id = tp_tc_id;
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
	public UbicacionVO() {
		super();
		
		
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
