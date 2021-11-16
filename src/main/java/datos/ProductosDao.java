/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Productos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Azrael
 */
public class ProductosDao {
    private static final String SQL_SELECT = "SELECT idProducto, puntos, precio, stock FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos (idProducto, puntos, precio, stock) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET puntos=?, precio=? , stock= ? WHERE idProducto=?";
    private static final String SQL_DELETE = "DELETE FROM productos  WHERE idProducto = ?";
 
    
    /*private int idProducto;
    private int puntos;
    private double precio;
    private int stock;*/
    public List<Productos> seleccionar() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Productos producto = null;
        List<Productos> listProductos = new ArrayList<>();

        try {
            con = getConnection();
            stm = con.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idProducto");
                int puntos = rs.getInt("Puntos");
                Double precio = rs.getDouble("Precio");
                int stock = rs.getInt("Stock");
                
                producto = new Productos(idProducto, puntos, precio,stock);
                listProductos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(rs);
            Conexion.close(stm);
        }
        return listProductos;
    }

    public int insertar(Productos producto) {
        Connection con = null;
        PreparedStatement stm = null;
        int registros = 0;

        try {
            con = Conexion.getConnection();
            stm = con.prepareStatement(SQL_INSERT);
            stm.setInt(1, producto.getIdProducto());
            stm.setInt(2, producto.getPuntos());
            stm.setDouble(3, producto.getPrecio());
            stm.setInt(4, producto.getStock());
            registros = stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                close(stm);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
            try {
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int actualizar(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, producto.getPuntos());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getStock());
            stmt.setInt(4, producto.getIdProducto());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Productos producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getIdProducto());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
