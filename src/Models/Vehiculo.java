package Models;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Vehiculo {

    private IntegerProperty idVehiculo, numeroPuertas;
    private BooleanProperty vendido;
    private StringProperty marca,modelo,fechaEntrada;
    private FloatProperty precio;

    public Vehiculo(ResultSet rs) throws SQLException {
        marca = new SimpleStringProperty(rs.getString("marca"));
        modelo = new SimpleStringProperty(rs.getString("modelo"));
        numeroPuertas = new SimpleIntegerProperty(rs.getInt("numeroPuertas"));
        vendido = new SimpleBooleanProperty(rs.getBoolean("vendido"));
        fechaEntrada = new SimpleStringProperty(rs.getString("fechaEntrada"));
    }

    public Vehiculo(HashMap<String,Object> vehiculo) throws SQLException {
        marca = new SimpleStringProperty((String) vehiculo.get("Marca"));
        modelo = new SimpleStringProperty((String) vehiculo.get("Modelo"));
        numeroPuertas = new SimpleIntegerProperty(Integer.parseInt((String)vehiculo.get("NumeroPuertas")));
        precio = new SimpleFloatProperty(Float.parseFloat((String)vehiculo.get("Precio")));
        fechaEntrada = new SimpleStringProperty((String) vehiculo.get("FechaEntrada"));
        vendido = new SimpleBooleanProperty(false);
    }

    public int getIdVehiculo() {
        return idVehiculo.get();
    }

    public IntegerProperty idVehiculoProperty() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo.set(idVehiculo);
    }

    public int getNumeroPuertas() {
        return numeroPuertas.get();
    }

    public IntegerProperty numeroPuertasProperty() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas.set(numeroPuertas);
    }

    public boolean isVendido() {
        return vendido.get();
    }

    public BooleanProperty vendidoProperty() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido.set(vendido);
    }

    public String getMarca() {
        return marca.get();
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca.set(marca);
    }

    public String getModelo() {
        return modelo.get();
    }

    public StringProperty modeloProperty() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo.set(modelo);
    }

    public String getFechaEntrada() {
        return fechaEntrada.get();
    }

    public StringProperty fechaEntradaProperty() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada.set(fechaEntrada);
    }
    public float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }
}