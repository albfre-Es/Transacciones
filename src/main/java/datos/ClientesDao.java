/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.Clientes;
import dominio.EWallet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientesDao {
    private static final String SQL_SELECT = "SELECT DNI, Nombre, apellidos  FROM Clientes";  
    private static final String SQL_registrarse = "INSERT INTO Clientes (DNI, Nombre, Apellidos, EMail,Contrasenya) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE Clientes SET Nombre = ?,Apellidos = ?, Email = ?, Contrasenya = ?";
    private static final String SQL_DELETE = "DELETE FROM Clientes  WHERE DNI = ?";
    
    public List<Clientes> seleccionar() throws SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        Clientes client = null;
        List<Clientes> listClientes = new ArrayList<>();

        try {
            con = getConnection();
            stm = con.prepareStatement(SQL_SELECT);
            rs = stm.executeQuery();

            while (rs.next()) {
                String  DNI = rs.getString("DNI");
                String  nombre = rs.getString("Nombre");
                String apellidos = rs.getString("apellidos");
               
                client = new Clientes(DNI,nombre,apellidos);
                listClientes.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(con);
            Conexion.close(rs);
            Conexion.close(stm);
        }
        return listClientes;
    }
    
    public int Registrarse(Clientes Cliente){
        Connection con = null;
        PreparedStatement stm = null;
        int registros = 0;

        try {
            con = getConnection();
            stm = con.prepareStatement(SQL_registrarse);
            stm.setString(1, Cliente.getDNI());
            stm.setString(2, Cliente.getNombre());
            stm.setString(3, Cliente.getApellidos());
            stm.setString(4, Cliente.getEmail());
            stm.setString(5, Cliente.getContrasenya());
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

    public int actualizar(Clientes cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellidos());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getContrasenya());
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
