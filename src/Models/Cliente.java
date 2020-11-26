package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Cliente {

    private StringProperty nombre,apellido,dni,fechaNac,direccion,sexo,correo,telefono,tipoComunicacion;
    private Integer idCliente;

    public Cliente(ResultSet rs) throws SQLException {
        nombre = new SimpleStringProperty(rs.getString("nombre"));
        apellido = new SimpleStringProperty(rs.getString("apellido"));
        dni = new SimpleStringProperty(rs.getString("dni"));
        //.toLocalDate sale en formato español
        fechaNac = new SimpleStringProperty(rs.getDate("fechaNac").toLocalDate().toString());
        direccion = new SimpleStringProperty(rs.getString("direccion"));
        sexo = new SimpleStringProperty(rs.getString("sexo"));
        correo = new SimpleStringProperty(rs.getString("correo"));
        telefono = new SimpleStringProperty(rs.getString("telefono"));
        tipoComunicacion = new SimpleStringProperty(rs.getString("tipoComunicacion"));
        idCliente = rs.getInt("idCliente");
    }

    public Cliente(HashMap<String,String> cliente) throws SQLException {
        nombre = new SimpleStringProperty(cliente.get("Nombre"));
        apellido = new SimpleStringProperty(cliente.get("Apellidos"));
        dni = new SimpleStringProperty(cliente.get("DNI"));
        //.toLocalDate sale en formato español
        fechaNac = new SimpleStringProperty(cliente.get("Fecha"));
        direccion = new SimpleStringProperty(cliente.get("Direccion"));
        sexo = new SimpleStringProperty(cliente.get("Sexo"));
        correo = new SimpleStringProperty(cliente.get("Email"));
        telefono = new SimpleStringProperty(cliente.get("Telefono"));
        tipoComunicacion = new SimpleStringProperty(cliente.get("Opciones"));
        idCliente = Integer.valueOf(cliente.get("idCliente"));
    }

    public Cliente(StringProperty nombre, StringProperty apellido, StringProperty dni, StringProperty fechaNac, StringProperty direccion, StringProperty sexo, StringProperty correo, StringProperty telefono, StringProperty tipoComunicacion,Integer idCliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.sexo = sexo;
        this.correo = correo;
        this.telefono = telefono;
        this.tipoComunicacion = tipoComunicacion;
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public String getDni() {
        return dni.get();
    }

    public StringProperty dniProperty() {
        return dni;
    }

    public String getFechaNac() {
        return fechaNac.get();
    }

    public StringProperty fechaNacProperty() {
        return fechaNac;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getSexo() {
        return sexo.get();
    }

    public StringProperty sexoProperty() {
        return sexo;
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public String getTipoComunicacion() {
        return tipoComunicacion.get();
    }

    public StringProperty tipoComunicacionProperty() {
        return tipoComunicacion;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public void setDni(String dni) {
        this.dni.set(dni);
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac.set(fechaNac);
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public void setSexo(String sexo) {
        this.sexo.set(sexo);
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public void setTipoComunicacion(String tipoComunicacion) {
        this.tipoComunicacion.set(tipoComunicacion);
    }
}

