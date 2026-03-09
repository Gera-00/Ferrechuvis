package org.upemor.ferrechuvis;

import org.upemor.ferrechuvis.model.entity.Usuarios;
import org.upemor.ferrechuvis.view.auth.Login;
import org.upemor.ferrechuvis.view.usuarios.PrincipalAdministrador;

/**@author GERARDO AYON*/
public class Ferrechuvis {

    public static void main(String[] args) {
        System.out.println("FERRECHUVIS - Desarrollado por Gerardo Ayon");
        /*    
        Login login = new Login();
        login.mostrar();
        */
        Usuarios user = new Usuarios();
        user.setNombre("Jose Luis");
        try {
            PrincipalAdministrador pantallaPrueba = new PrincipalAdministrador(user);
            pantallaPrueba.mostrar();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
