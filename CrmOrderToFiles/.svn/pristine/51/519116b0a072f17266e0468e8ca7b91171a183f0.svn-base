/**
 * GeneralLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package crmOrder;

public class GeneralLocator extends org.apache.axis.client.Service implements crmOrder.General {

    public GeneralLocator() {
    }


    public GeneralLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GeneralLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for generalHttpSoap11Endpoint
    private java.lang.String generalHttpSoap11Endpoint_address = "http://132.228.224.153/crm_dilatation_5/services/general.generalHttpSoap11Endpoint/";

    public java.lang.String getgeneralHttpSoap11EndpointAddress() {
        return generalHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String generalHttpSoap11EndpointWSDDServiceName = "generalHttpSoap11Endpoint";

    public java.lang.String getgeneralHttpSoap11EndpointWSDDServiceName() {
        return generalHttpSoap11EndpointWSDDServiceName;
    }

    public void setgeneralHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        generalHttpSoap11EndpointWSDDServiceName = name;
    }

    public crmOrder.GeneralPortType getgeneralHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(generalHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getgeneralHttpSoap11Endpoint(endpoint);
    }

    public crmOrder.GeneralPortType getgeneralHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            crmOrder.GeneralSoap11BindingStub _stub = new crmOrder.GeneralSoap11BindingStub(portAddress, this);
            _stub.setPortName(getgeneralHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setgeneralHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        generalHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (crmOrder.GeneralPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                crmOrder.GeneralSoap11BindingStub _stub = new crmOrder.GeneralSoap11BindingStub(new java.net.URL(generalHttpSoap11Endpoint_address), this);
                _stub.setPortName(getgeneralHttpSoap11EndpointWSDDServiceName());
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
        if ("generalHttpSoap11Endpoint".equals(inputPortName)) {
            return getgeneralHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.asiainfo-linkage.com", "general");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.asiainfo-linkage.com", "generalHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("generalHttpSoap11Endpoint".equals(portName)) {
            setgeneralHttpSoap11EndpointEndpointAddress(address);
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
