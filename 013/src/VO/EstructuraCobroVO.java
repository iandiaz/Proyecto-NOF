package VO;

import java.util.ArrayList;

public class EstructuraCobroVO {
	private String anc_id;
	private String estrcobro_id;
	private ArrayList<RangoEstructuraCobroVO> rangosEstCobro;
	private String estrcobro_cxa;
	private String tipo_cambio_id; 
	private String estrcobro_observaciones;
	private String estrcobro_nombre;
	private TipoEstcobroVO tipo_estructuraC;
	private TipoCambioVO tipocambio;
	private EstadosVigNoVigVO estadoVignoVig;
	private ArrayList<MaquinaContadorVO> maquinasContador;
	
	

	public ArrayList<MaquinaContadorVO> getMaquinasContador() {
		return maquinasContador;
	}

	public void setMaquinasContador(ArrayList<MaquinaContadorVO> maquinasContador) {
		this.maquinasContador = maquinasContador;
	}

	public String getEstrcobro_nombre() {
		return estrcobro_nombre;
	}

	public TipoEstcobroVO getTipo_estructuraC() {
		return tipo_estructuraC;
	}

	public void setTipo_estructuraC(TipoEstcobroVO tipo_estructuraC) {
		this.tipo_estructuraC = tipo_estructuraC;
	}

	public void setEstrcobro_nombre(String estrcobro_nombre) {
		this.estrcobro_nombre = estrcobro_nombre;
	}

	public EstadosVigNoVigVO getEstadoVignoVig() {
		return estadoVignoVig;
	}

	public void setEstadoVignoVig(EstadosVigNoVigVO estadoVignoVig) {
		this.estadoVignoVig = estadoVignoVig;
	}

	public TipoCambioVO getTipocambio() {
		return tipocambio;
	}

	public void setTipocambio(TipoCambioVO tipocambio) {
		this.tipocambio = tipocambio;
	}

	public String getEstrcobro_cxa() {
		return estrcobro_cxa;
	}

	public void setEstrcobro_cxa(String estrcobro_cxa) {
		this.estrcobro_cxa = estrcobro_cxa;
	}

	public String getTipo_cambio_id() {
		return tipo_cambio_id;
	}

	public void setTipo_cambio_id(String tipo_cambio_id) {
		this.tipo_cambio_id = tipo_cambio_id;
	}

	public String getEstrcobro_observaciones() {
		return estrcobro_observaciones;
	}

	public void setEstrcobro_observaciones(String estrcobro_observaciones) {
		this.estrcobro_observaciones = estrcobro_observaciones;
	}

	public EstructuraCobroVO() {
		super();
		this.maquinasContador= new ArrayList<MaquinaContadorVO>();
		this.rangosEstCobro= new ArrayList<RangoEstructuraCobroVO>();
	}
	public EstructuraCobroVO(String id) {
		super();
		this.maquinasContador= new ArrayList<MaquinaContadorVO>();
		this.rangosEstCobro= new ArrayList<RangoEstructuraCobroVO>();
		this.estrcobro_id=id;
	}

	public String getAnc_id() {
		return anc_id;
	}

	public void setAnc_id(String anc_id) {
		this.anc_id = anc_id;
	}

	public String getEstrcobro_id() {
		return estrcobro_id;
	}

	public void setEstrcobro_id(String estrcobro_id) {
		this.estrcobro_id = estrcobro_id;
	}

	public ArrayList<RangoEstructuraCobroVO> getRangosEstCobro() {
		return rangosEstCobro;
	}

	public void setRangosEstCobro(ArrayList<RangoEstructuraCobroVO> rangosEstCobro) {
		this.rangosEstCobro = rangosEstCobro;
	}
	
	@Override
    public boolean equals(Object o){
		EstructuraCobroVO estr = (EstructuraCobroVO) o;
        if(o == null){
            return false;
        }
        if((this.estrcobro_id.equals(estr.getEstrcobro_id())) ){
            return true;
        }
        return false;
    }
	
}
