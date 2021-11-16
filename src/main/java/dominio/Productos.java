package dominio;

public class Productos {
    private int idProducto;
    private int puntos;
    private double precio;
    private int stock;

    public Productos(int idProducto, int puntos, double precio, int stock) {
        this.idProducto = idProducto;
        this.puntos = puntos;
        this.precio = precio;
        this.stock = stock;
    }

    public Productos() {
    
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Productos  " + "idProducto: " + idProducto + ", puntos: " + puntos + ", precio: " + precio + ", stock: " + stock;
    } 

}
