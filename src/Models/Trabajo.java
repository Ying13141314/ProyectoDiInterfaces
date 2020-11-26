package Models;

import javafx.beans.property.*;
import javafx.css.SimpleStyleableStringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Trabajo {

    private IntegerProperty idtrabajo, cliente, numeroPuertas,numeroRuedas, idConcesionario, idMecanico;
    private BooleanProperty vendido;
    private StringProperty mecanico,marca,telefono,modelo,fechaEntrada,fechaSalida,numeroBastidor,tipotrabajo,email,observaciones, piezas;
    private FloatProperty presupuesto;

    public Trabajo(ResultSet rs) throws SQLException {
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
        idConcesionario = new SimpleIntegerProperty(rs.getInt("idConcesionarios"));
        numeroBastidor = new SimpleStringProperty(rs.getString("numeroBastidor"));
        tipotrabajo = new SimpleStringProperty(rs.getString("tipotrabajo"));
    }

    public Trabajo(HashMap<String,Object> trabajo) throws SQLException {
        idMecanico = new SimpleIntegerProperty(Integer.parseInt((String)trabajo.get("IdMecanico")));
        mecanico = new SimpleStringProperty((String) trabajo.get("Mecanico"));
        cliente = new SimpleIntegerProperty(Integer.parseInt((String)trabajo.get("Cliente")));
        marca = new SimpleStringProperty((String) trabajo.get("Marca"));
        modelo = new SimpleStringProperty((String) trabajo.get("Modelo"));
        telefono = new SimpleStringProperty((String) trabajo.get("Telefono"));
        presupuesto = new SimpleFloatProperty(Float.parseFloat((String)trabajo.get("Presupuesto")));
        observaciones = new SimpleStringProperty((String) trabajo.get("Observaciones"));
        email = new SimpleStringProperty((String) trabajo.get("Email"));
        piezas = new SimpleStringProperty((String) trabajo.get("Piezas"));
    }

    public int getIdConcesionario() {
        return idConcesionario.get();
    }

    public IntegerProperty idConcesionarioProperty() {
        return idConcesionario;
    }

    public void setIdConcesionario(int idConcesionario) {
        this.idConcesionario.set(idConcesionario);
    }

    public String getPiezas() {
        return piezas.get();
    }

    public StringProperty piezasProperty() {
        return piezas;
    }

    public void setPiezas(String piezas) {
        this.piezas.set(piezas);
    }

    public int getIdtrabajo() {
        return idtrabajo.get();
    }

    public IntegerProperty idtrabajoProperty() {
        return idtrabajo;
    }

    public void setIdtrabajo(int idtrabajo) {
        this.idtrabajo.set(idtrabajo);
    }

    public int getCliente() {
        return cliente.get();
    }

    public IntegerProperty clienteProperty() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente.set(cliente);
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

    public int getNumeroRuedas() {
        return numeroRuedas.get();
    }

    public IntegerProperty numeroRuedasProperty() {
        return numeroRuedas;
    }

    public void setNumeroRuedas(int numeroRuedas) {
        this.numeroRuedas.set(numeroRuedas);
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

    public String getMecanico() {
        return mecanico.get();
    }

    public StringProperty mecanicoProperty() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico.set(mecanico);
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

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
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

    public String getTipotrabajo() {
        return tipotrabajo.get();
    }

    public StringProperty tipotrabajoProperty() {
        return tipotrabajo;
    }

    public int getIdMecanico() {
        return idMecanico.get();
    }

    public IntegerProperty idMecanicoProperty() {
        return idMecanico;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico.set(idMecanico);
    }

    public void setTipotrabajo(String tipotrabajo) {
        this.tipotrabajo.set(tipotrabajo);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }

    public float getPresupuesto() {
        return presupuesto.get();
    }

    public FloatProperty presupuestoProperty() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto.set(presupuesto);
    }
}