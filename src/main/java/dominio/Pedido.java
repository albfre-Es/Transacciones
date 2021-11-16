package dominio;

/**
 *
 * @author Azrael
 */
public class Pedido {
    private int idPedido;
    private int idProducto;
    private double precio;
    private int cantidad;
    private double totalPrecio;
    private int totalPuntos;

    public Pedido(int idPedido, int idProducto, double precio, int cantidad, double totalPrecio, int totalPuntos) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.totalPrecio = totalPrecio;
        this.totalPuntos = totalPuntos;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public int getTotalPuntos() {
        return totalPuntos;
    }

    public void setTotalPuntos(int totalPuntos) {
        this.totalPuntos = totalPuntos;
    }

    @Override
    public String toString() {
        return "Pedido" + "idPedido: " + idPedido + ", idProducto: " + idProducto + ", precio: " + precio + ", cantidad: " + cantidad + ", totalPrecio: " + totalPrecio + ", totalPuntos: " + totalPuntos;
    }
}
