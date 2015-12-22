/**
 * TecnoglobalServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webserver;

public class TecnoglobalServiceLocator extends org.apache.axis.client.Service implements webserver.TecnoglobalService {

    public TecnoglobalServiceLocator() {
    }


    public TecnoglobalServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TecnoglobalServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for tecnoglobal
    private java.lang.String tecnoglobal_address = "http://localhost:8080/webserver/services/tecnoglobal";

    public java.lang.String gettecnoglobalAddress() {
        return tecnoglobal_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String tecnoglobalWSDDServiceName = "tecnoglobal";

    public java.lang.String gettecnoglobalWSDDServiceName() {
        return tecnoglobalWSDDServiceName;
    }

    public void settecnoglobalWSDDServiceName(java.lang.String name) {
        tecnoglobalWSDDServiceName = name;
    }

    public webserver.Tecnoglobal gettecnoglobal() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(tecnoglobal_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return gettecnoglobal(endpoint);
    }

    public webserver.Tecnoglobal gettecnoglobal(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webserver.TecnoglobalSoapBindingStub _stub = new webserver.TecnoglobalSoapBindingStub(portAddress, this);
            _stub.setPortName(gettecnoglobalWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void settecnoglobalEndpointAddress(java.lang.String address) {
        tecnoglobal_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webserver.Tecnoglobal.class.isAssignableFrom(serviceEndpointInterface)) {
                webserver.TecnoglobalSoapBindingStub _stub = new webserver.TecnoglobalSoapBindingStub(new java.net.URL(tecnoglobal_address), this);
                _stub.setPortName(gettecnoglobalWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("tecnoglobal".equals(inputPortName)) {
            return gettecnoglobal();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webserver", "tecnoglobalService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webserver", "tecnoglobal"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("tecnoglobal".equals(portName)) {
            settecnoglobalEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
