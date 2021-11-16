/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Azrael
 */
public class EWallet {
    private String DNI;
    private int idPedido;
    private double dinero;
    private int puntos;

    public EWallet(String DNI, int idPedido, double dinero, int puntos) {
        this.DNI = DNI;
        this.idPedido = idPedido;
        this.dinero = dinero;
        this.puntos = puntos;
    }

    public EWallet() {
        
    }
    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "EWallet  " + "DNI: " + DNI + ", idPedido: " + idPedido + ", dinero: " + dinero + ", puntos: " + puntos;
    }

}
