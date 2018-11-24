
package br.com.correios.bsb.sigep.master.bean.cliente;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 3.2.5
 * 2018-11-24T16:08:05.244-02:00
 * Generated source version: 3.2.5
 */

@WebFault(name = "SQLException", targetNamespace = "http://cliente.bean.master.sigep.bsb.correios.com.br/")
public class SQLException_Exception extends java.lang.Exception {

    private br.com.correios.bsb.sigep.master.bean.cliente.SQLException sqlException;

    public SQLException_Exception() {
        super();
    }

    public SQLException_Exception(String message) {
        super(message);
    }

    public SQLException_Exception(String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public SQLException_Exception(String message, br.com.correios.bsb.sigep.master.bean.cliente.SQLException sqlException) {
        super(message);
        this.sqlException = sqlException;
    }

    public SQLException_Exception(String message, br.com.correios.bsb.sigep.master.bean.cliente.SQLException sqlException, java.lang.Throwable cause) {
        super(message, cause);
        this.sqlException = sqlException;
    }

    public br.com.correios.bsb.sigep.master.bean.cliente.SQLException getFaultInfo() {
        return this.sqlException;
    }
}