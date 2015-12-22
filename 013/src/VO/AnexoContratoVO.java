package VO;

import java.util.ArrayList;

public class AnexoContratoVO {

	private ArrayList<MaquinaContadorVO> maquinasContadores;
	private ArrayList<EstructuraCobroVO> estructurasCobro;
	private String cargoFijoUnico;
	private String observacion;
	private String tipoCambio_id;
	private String anc_id;
	private EmpresaVO empresa;
	private ContratoVO contrato;
	private EstadosVigNoVigVO estadoVignoVig;
	private TipoCambioVO tipocambio;
	private String fecha; 
	private String fecha_desde;
	private String fecha_hasta;
	private String nombre;

	public ArrayList<MaquinaContadorVO> getMaquinasContadores() {
		return maquinasContadores;
	}

	public void setMaquinasContadores(
			ArrayList<MaquinaContadorVO> maquinasContadores) {
		this.maquinasContadores = maquinasContadores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha_desde() {
		return fecha_desde;
	}

	public String getFecha_hasta() {
		return fecha_hasta;
	}

	public void setFecha_desde(String fecha_desde) {
		this.fecha_desde = fecha_desde;
	}

	public void setFecha_hasta(String fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}

	public AnexoContratoVO() {
		super();
		this.maquinasContadores= new ArrayList<MaquinaContadorVO>();
		this.estructurasCobro = new ArrayList<EstructuraCobroVO>();
	}
	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public TipoCambioVO getTipocambio() {
		return tipocambio;
	}

	public void setTipocambio(TipoCambioVO tipocambio) {
		this.tipocambio = tipocambio;
	}

	public EstadosVigNoVigVO getEstadoVignoVig() {
		return estadoVignoVig;
	}

	public void setEstadoVignoVig(EstadosVigNoVigVO estadoVignoVig) {
		this.estadoVignoVig = estadoVignoVig;
	}

	
	public ArrayList<EstructuraCobroVO> getEstructurasCobro() {
		return estructurasCobro;
	}

	public void setEstructurasCobro(ArrayList<EstructuraCobroVO> estructurasCobro) {
		this.estructurasCobro = estructurasCobro;
	}

	public ContratoVO getContrato() {
		return contrato;
	}

	public void setContrato(ContratoVO contrato) {
		this.contrato = contrato;
	}

	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}

	public String getAnc_id() {
		return anc_id;
	}

	public void setAnc_id(String anc_id) {
		this.anc_id = anc_id;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCargoFijoUnico() {
		return cargoFijoUnico;
	}

	public String getTipoCambio_id() {
		return tipoCambio_id;
	}

	public void setTipoCambio_id(String tipoCambio_id) {
		this.tipoCambio_id = tipoCambio_id;
	}

	public void setCargoFijoUnico(String cargoFijoUnico) {
		this.cargoFijoUnico = cargoFijoUnico;
	}


}
