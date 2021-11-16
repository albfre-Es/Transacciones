/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.EWallet;
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
public class EWalletDao {
    /*private String DNI;
    private int idPedido;
    private double dinero;
    private int puntos;*/
    private static final String SQL_SELECT = "SELECT DNI, idPedido, dinero, puntos FROM EWallet";  
    private static final String SQL_INSERT = "INSERT INTO EWallet (DNI, idPedido, dinero, puntos ) VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE EWallet SET idPedido=?,dinero=?,puntos=? WHERE DNI=?";
    private static final String SQL_DELETE = "DELETE FROM EWallet  WHERE DNI = ?";

    public List<EWallet> seleccionar() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        EWallet eWallet = null;
        List<EWallet> listEWallet = new ArrayList<>();

        try {
            con = getConnection();
            stm = con.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();

            while (rs.next()) {
                String  DNI = rs.getString("DNI");
                int  idPedido = rs.getInt("idPedido");
                double dinero = rs.getDouble("dinero");
                int puntos = rs.getInt("puntos");
               
                eWallet = new EWallet(DNI,idPedido,dinero,puntos);
                listEWallet.add(eWallet);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(rs);
            Conexion.close(stm);
        }
        return listEWallet;
    }

    public void insertar(EWallet eWallet) {
        Connection con = null;
        PreparedStatement stm = null;
        int registros = 0;
//INSERT INTO EWallet (DNI, idPedido, dinero, puntos 
        try {
            con = Conexion.getConnection();
            stm = con.prepareStatement(SQL_INSERT);
            stm.setString(1, eWallet.getDNI());
            stm.setInt(2,eWallet.getIdPedido());
            stm.setDouble(3, eWallet.getDinero());
            stm.setInt(4, eWallet.getPuntos());
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
    }

    public void actualizar(EWallet eWallet) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, eWallet.getIdPedido());
            stmt.setDouble(2, eWallet.getDinero());
            stmt.setInt(3, eWallet.getPuntos());
            stmt.setString(4, eWallet.getDNI());
            
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
    }

    public int eliminar(String dni) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, dni);
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
