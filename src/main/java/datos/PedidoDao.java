/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Pedido;
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
public class PedidoDao {
    private static final String SQL_SELECT = "SELECT idPedido, idProducto, precio, cantidad, totalPrecio,totalPuntos FROM pedidos";
    private static final String SQL_INSERT = "INSERT INTO pedidos (idProducto, precio, cantidad, totalPrecio,totalPuntos) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE pedidos SET precio=?, cantidad=? , totalPrecio= ?,totalPuntos=? WHERE idProducto=?";
    private static final String SQL_DELETE = "DELETE FROM productos  WHERE idPedido = ?";
 
    /*private int idPedido;
    private int idProducto;
    private double precio;
    private int cantidad;
    private double totalPrecio;
    private int totalPuntos;*/
    public List<Pedido> seleccionar() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Pedido pedido = null;
        List<Pedido> listPedidos = new ArrayList<>();

        try {
            con = getConnection();
            stm = con.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                int idProducto = rs.getInt("idProducto");
                double precio = rs.getDouble("precio");
                int cantidad = rs.getInt("cantidad");
                double totalPrecio = rs.getDouble("totalPrecio");
                int totalPuntos = rs.getInt("totalPuntos");
                pedido = new Pedido(idPedido,idProducto,precio,cantidad,totalPrecio, totalPuntos);
                listPedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(rs);
            Conexion.close(stm);
        }
        return listPedidos;
    }

    public int insertar(Pedido pedido) {
        Connection con = null;
        PreparedStatement stm = null;
        int registros = 0;

        try {
            con = Conexion.getConnection();
            stm = con.prepareStatement(SQL_INSERT);
            stm.setInt(1, pedido.getIdProducto());
            stm.setDouble(2,pedido.getPrecio());
            stm.setInt(3, pedido.getCantidad());
            stm.setDouble(4, pedido.getTotalPrecio());
            stm.setInt(4, pedido.getTotalPuntos());
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

    public int actualizar(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setDouble(1, pedido.getPrecio());
            stmt.setInt(2, pedido.getCantidad());
            stmt.setDouble(3, pedido.getTotalPrecio());
            stmt.setInt(4, pedido.getTotalPuntos());
            stmt.setInt(5, pedido.getIdPedido());
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

    public int eliminar(Pedido pedido) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, pedido.getIdPedido());
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

