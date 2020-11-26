package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PropuestaVenta {
    private StringProperty nombreCliente,primerApellido,dni,fechaNac,direccion,propuesta,estado,fechaLimiteAcep;
    private Integer idCliente,idVehiculo,idVendedor;

    public PropuestaVenta(ResultSet rs) throws SQLException {
        nombreCliente = new SimpleStringProperty(rs.getString("nombreCliente"));
        primerApellido = new SimpleStringProperty(rs.getString("primerApellido"));
        dni = new SimpleStringProperty(rs.getString("dni"));
        //.toLocalDate sale en formato español
        fechaNac = new SimpleStringProperty(rs.getDate("fechaNacimiento").toLocalDate().toString());
        direccion = new SimpleStringProperty(rs.getString("direccion"));
        propuesta = new SimpleStringProperty(rs.getString("propuesta"));
        estado = new SimpleStringProperty(rs.getString("estado"));
        fechaLimiteAcep = new SimpleStringProperty(rs.getString("fechaLimiteAceptación"));
        idCliente = rs.getInt("idCliente");
        idVehiculo= rs.getInt("idVehiculo");
        idVendedor = rs.getInt("idVendedor");
    }

    public PropuestaVenta(HashMap<String,String> propuestaVenta) throws SQLException {
        nombreCliente = new SimpleStringProperty(propuestaVenta.get("nombreCliente"));
        primerApellido = new SimpleStringProperty(propuestaVenta.get("primerApellido"));
        dni = new SimpleStringProperty( propuestaVenta.get("dni"));
        fechaNac = new SimpleStringProperty( propuestaVenta.get("fechaNac"));
        direccion = new SimpleStringProperty( propuestaVenta.get("direccion"));
        propuesta = new SimpleStringProperty( propuestaVenta.get("propuesta"));
        estado = new SimpleStringProperty( propuestaVenta.get("estado"));
        fechaLimiteAcep = new SimpleStringProperty( propuestaVenta.get("FechaLimite"));
        idCliente = Integer.parseInt(propuestaVenta.get("idCliente"));
        idVehiculo= Integer.parseInt(propuestaVenta.get("idVehiculo"));
        idVendedor = Integer.parseInt(propuestaVenta.get("idVendedor"));
    }


    public PropuestaVenta(StringProperty nombreCliente, StringProperty primerApellido, StringProperty dni, StringProperty fechaNac, StringProperty direccion, StringProperty propuesta, StringProperty estado, StringProperty fechaLimiteAcep) {
        this.nombreCliente = nombreCliente;
        this.primerApellido = primerApellido;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.propuesta = propuesta;
        this.estado = estado;
        this.fechaLimiteAcep = fechaLimiteAcep;
    }


    //Getter and Setter


    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombreCliente() {
        return nombreCliente.get();
    }

    public StringProperty nombreClienteProperty() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente.set(nombreCliente);
    }

    public String getPrimerApellido() {
        return primerApellido.get();
    }

    public StringProperty primerApellidoProperty() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido.set(primerApellido);
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public String getFechaNac() {
        return fechaNac.get();
    }

    public StringProperty fechaNacProperty() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac.set(fechaNac);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getPropuesta() {
        return propuesta.get();
    }

    public StringProperty propuestaProperty() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta.set(propuesta);
    }

    public String getEstado() {
        return estado.get();
    }

    public StringProperty estadoProperty() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public String getFechaLimiteAcep() {
        return fechaLimiteAcep.get();
    }

    public StringProperty fechaLimiteAcepProperty() {
        return fechaLimiteAcep;
    }

    public void setFechaLimiteAcep(String fechaLimiteAcep) {
        this.fechaLimiteAcep.set(fechaLimiteAcep);
    }
}