package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractUsuario {

    //Estado
    protected StringProperty id,nombre,apellido,nombreUsuario,contrasena,telefono,correo,dni,tipo;
    protected DoubleProperty salario;
    public static final String VENTA = "venta";
    public static final String JEFE = "jefe";
    public static final String MECANICO = "mecanico";


    //Constructor
    public AbstractUsuario(ResultSet rs) throws SQLException {
        id = new SimpleStringProperty(rs.getString("idUsuario"));
        nombre= new SimpleStringProperty(rs.getString("nombre"));
        apellido = new SimpleStringProperty(rs.getString("apellido"));
        nombreUsuario= new SimpleStringProperty(rs.getString("nombreUsuario"));
        contrasena= new SimpleStringProperty(rs.getString("contrasena"));
        telefono=new SimpleStringProperty(rs.getString("telefono"));
        correo = new SimpleStringProperty(rs.getString("correo"));
        dni = new SimpleStringProperty(rs.getString("dni"));
        salario=new SimpleDoubleProperty(rs.getDouble("salario"));
        tipo = new SimpleStringProperty(rs.getString("tipo"));
        
    }
    public static AbstractUsuario tipo(ResultSet rs) throws SQLException {
        if (rs.getString("tipo").equals(VENTA)){
            return new Venta(rs);
        }else if(rs.getString("tipo").equals(JEFE)){
            return new Jefe(rs);
        }else if (rs.getString("tipo").equals(MECANICO)){
            if(rs.getBoolean("jefe") == true){
                return new MecanicoJefe(rs);
            }
            return new Mecanico(rs);
        }
        return null;

    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getNombreUsuario() {
        return nombreUsuario.get();
    }

    public StringProperty nombreUsuarioProperty() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario.set(nombreUsuario);
    }

    public String getContrasena() {
        return contrasena.get();
    }

    public StringProperty contrasenaProperty() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena.set(contrasena);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getCorreo() {
        return correo.get();
    }

    public StringProperty correoProperty() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
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

    public String getTipo() {
        return tipo.get();
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public double getSalario() {
        return salario.get();
    }

    public DoubleProperty salarioProperty() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario.set(salario);
    }
}
