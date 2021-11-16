/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import datos.ClientesDao;
import datos.Conexion;
import datos.EWalletDao;
import datos.ProductosDao;
import dominio.Clientes;
import dominio.EWallet;
import dominio.Productos;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author albfre
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        menu();
    }

    public static void menu() throws SQLException {
        EWallet ewall = new EWallet();
        Clientes cliente = new Clientes();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("1 Crear cliente");
            System.out.println("2 Iniciar sesion");
            System.out.println("3 Eliminar cliente");
            System.out.println("4 Salir");
            opcion = sc.nextInt();
            if (opcion == 1) {
                ewall = CrearCliente();
                menu();
            } else if (opcion == 2) {
                cliente = iniciarSesion();
                subMenu(ewall, cliente);
            } else if (opcion == 3) {
                eliminarCliente();
                menu();
            }
        } while (opcion != 4);
    }

    public static void subMenu(EWallet ewall, Clientes cliente) throws SQLException {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1 Comprar");
            System.out.println("2 Devolver");
            System.out.println("3 Volver al menu");
            opcion = sc.nextInt();
            if (opcion == 1) {
                Comprar(cliente, ewall);
            } else if (opcion == 2) {
                Devolver(cliente, ewall);
                subMenu(ewall, cliente);
            }
        } while (opcion != 3);

    }

    public static EWallet CrearCliente() {
        Scanner sc = new Scanner(System.in);
        String DNI, nombre, apellidos, email, contrasenya;
        System.out.println("Introduce el DNI");
        DNI = sc.nextLine();
        System.out.println("Introduce el nombre");
        nombre = sc.nextLine();
        System.out.println("Introduce el apellidos");
        apellidos = sc.nextLine();
        System.out.println("Introduce el EMail");
        email = sc.nextLine();
        System.out.println("Introduce el contrasenya");
        contrasenya = sc.nextLine();
        Clientes clientes = new Clientes(DNI, nombre, apellidos, email, contrasenya);
        int cdao = new ClientesDao().Registrarse(clientes);
        EWallet ewallet = new EWallet(DNI, 0, 500, 0);
        EWalletDao ewall = new EWalletDao();
        ewall.insertar(ewallet);
        return ewallet;
    }

    public static void eliminarCliente() {
        Scanner sc = new Scanner(System.in);
        String DNI;
        System.out.println("Introduce el DNI");
        DNI = sc.nextLine();
        int cdao = new ClientesDao().eliminar(DNI);
        int ewdao = new EWalletDao().eliminar(DNI);
        System.out.println("Cliente eliminado");
        System.out.println("EWallet del cliente eliminado");
    }

    public static Clientes iniciarSesion() throws SQLException {
        Scanner sc = new Scanner(System.in);
        String DNI = "";
        Clientes cliente = null;
        ClientesDao client = new ClientesDao();
        List<Clientes> listClientes = client.seleccionar();
        listClientes.forEach(clientes -> {
            System.out.println(" " + clientes);
        });
        System.out.println("Introduce el DNI");
        DNI = sc.nextLine();
        for (int i = 0; i < listClientes.size(); i++) {
            if (listClientes.get(i).getDNI().equals(DNI)) {
                cliente = listClientes.get(i);
            }
        }
        return cliente;
    }

    public static void Comprar(Clientes cliente, EWallet ewall) throws SQLException {
        Connection conexion = null;
        
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            
        
        
        Scanner sc = new Scanner(System.in);
        int idProducto, cantidad, puntos, stock;
        double precio, totalDinero;
        int totalPuntos;
        EWalletDao wao = new EWalletDao();
        ProductosDao pro = new ProductosDao();
        List<Productos> listProduct = pro.seleccionar();
        listProduct.forEach(producto -> {
            System.out.println("" + producto);
        });
        Productos product;
        product = new Productos();
        System.out.println("Introduce el producto que quieres comprar");
        idProducto = sc.nextInt();
        System.out.println("Introduce la cantidad del producto");
        cantidad = sc.nextInt();

        for (int i = 0; i < listProduct.size(); i++) {
            if (listProduct.get(i).getIdProducto() == idProducto) {
                product = listProduct.get(i);
            }
        }
        if (ewall.getDNI() == null) {

            List<EWallet> listEwallet = wao.seleccionar();
            
            String dni = cliente.getDNI();
            System.out.println(cliente.getDNI());
            for (int i = 0; i < listEwallet.size(); i++) {
                if (listEwallet.get(i).getDNI().equals(dni)) {
                    ewall = listEwallet.get(i);
                }
            }
        }
        try{
        stock = product.getStock() - cantidad;
        precio = product.getPrecio();
        totalDinero = precio * cantidad;
        puntos = product.getPuntos();
        totalPuntos = puntos * cantidad;
        ewall.setDinero(ewall.getDinero()-totalDinero);
        ewall.setPuntos(totalPuntos);
        product.setStock(stock);
        pro.actualizar(product);
        wao.actualizar(ewall);

        conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");

        }catch (SQLException ex) {
            conexion.rollback();
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");   
        }
    }

    private static void Devolver(Clientes cliente, EWallet ewall)throws SQLException {

    }
}
