package Models;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Vehiculo {

    private IntegerProperty idVehiculo, numeroPuertas,numeroRuedas, idConsecionario;
    private BooleanProperty vendido;
    private StringProperty marca,modelo,fechaEntrada,fechaSalida,numeroBastidor,tipoVehiculo;
    private FloatProperty precio;

    public Vehiculo(ResultSet rs) throws SQLException {
        marca = new SimpleStringProperty(rs.getString("marca"));
        modelo = new SimpleStringProperty(rs.getString("modelo"));
        numeroPuertas = new SimpleIntegerProperty(rs.getInt("numeroPuertas"));
        vendido = new SimpleBooleanProperty(rs.getBoolean("vendido"));
        fechaEntrada = new SimpleStringProperty(rs.getDate("fechaEntrada").toLocalDate().toString());
        numeroRuedas = new SimpleIntegerProperty(rs.getInt("numeroRuedas"));
        if (rs.getDate("fechaSalida")!=null){
            fechaSalida = new SimpleStringProperty(rs.getDate("fechaSalida").toLocalDate().toString());
        }else{
            fechaSalida = new SimpleStringProperty("");
        }
        idConsecionario = new SimpleIntegerProperty(rs.getInt("idConcesionarios"));
        numeroBastidor = new SimpleStringProperty(rs.getString("numeroBastidor"));
        tipoVehiculo = new SimpleStringProperty(rs.getString("tipoVehiculo"));
        precio = new SimpleFloatProperty(rs.getFloat("precio"));
    }

    public Vehiculo(HashMap<String,Object> vehiculo) throws SQLException {
        marca = new SimpleStringProperty((String) vehiculo.get("Marca"));
        modelo = new SimpleStringProperty((String) vehiculo.get("Modelo"));
        numeroPuertas = new SimpleIntegerProperty(Integer.parseInt((String)vehiculo.get("NumeroPuertas")));
        precio = new SimpleFloatProperty(Float.parseFloat((String)vehiculo.get("Precio")));
        fechaEntrada = new SimpleStringProperty((String) vehiculo.get("FechaEntrada"));
        vendido = new SimpleBooleanProperty(false);
        numeroRuedas = new SimpleIntegerProperty((Integer) vehiculo.get("numeroRuedas"));
        fechaSalida = new SimpleStringProperty((String) vehiculo.get("fechaSalida"));
        numeroBastidor = new SimpleStringProperty((String) vehiculo.get("numeroBastidor"));
        tipoVehiculo = new SimpleStringProperty((String) vehiculo.get("tipoVehiculo"));
        idConsecionario = new SimpleIntegerProperty((Integer) vehiculo.get("idConcesionario"));
    }

    // getter and setter


    public int getIdConsecionario() {
        return idConsecionario.get();
    }

    public IntegerProperty idConsecionarioProperty() {
        return idConsecionario;
    }

    public void setIdConsecionario(int idConsecionario) {
        this.idConsecionario.set(idConsecionario);
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo.set(tipoVehiculo);
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

    public Integer getNumeroPuertas() {
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
    public Float getPrecio() {
        return precio.get();
    }

    public FloatProperty precioProperty() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio.set(precio);
    }

    public Integer getNumeroRuedas() {
        return numeroRuedas.get();
    }

    public IntegerProperty numeroRuedasProperty() {
        return numeroRuedas;
    }

    public void setNumeroRuedas(int numeroRuedas) {
        this.numeroRuedas.set(numeroRuedas);
    }

    public String getFechaSalida() {
        return fechaSalida.get();
    }

    public StringProperty fechaSalidaProperty() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public String getNumeroBastidor() {
        return numeroBastidor.get();
    }

    public StringProperty numeroBastidorProperty() {
        return numeroBastidor;
    }

    public void setNumeroBastidor(String numeroBastidor) {
        this.numeroBastidor.set(numeroBastidor);
    }

    public String getTipoVehiculo() {
        return tipoVehiculo.get();
    }

    public StringProperty tipoVehiculoProperty() {
        return tipoVehiculo;
    }
}