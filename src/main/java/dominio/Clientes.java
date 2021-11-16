package dominio;

import java.util.Date;

/**
 *
 * @author Azrael
 */
public class Clientes {
   private String DNI;
   private String Nombre; 
   private String Apellidos;
   private String Email;
   private String Contrasenya;
   private EWallet ewallet;
    public Clientes(String DNI, String Nombre, String Apellidos, String Email,String Contrasenya) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.Email = Email;
        this.Contrasenya = Contrasenya;
    }

    public Clientes() {
    }

    public Clientes(String DNI, String Nombre, String Apellidos) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
    }
    

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContrasenya() {
        return Contrasenya;
    }

    public void setContrasenya(String Contrasenya) {
        this.Contrasenya = Contrasenya;
    }

    public EWallet getEwallet() {
        return ewallet;
    }

    public void setEwallet(EWallet ewallet) {
        this.ewallet = ewallet;
    }
    
    @Override
    public String toString() {
        return "Clientes:  " + "DNI: " + DNI + ", Nombre: " + Nombre + ", Apellidos: " + Apellidos + ", Email: " + Email + ", Contraseña=" + Contrasenya;
    }
}
