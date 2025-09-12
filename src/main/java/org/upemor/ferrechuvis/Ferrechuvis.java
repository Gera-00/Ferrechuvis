package org.upemor.ferrechuvis;

import org.upemor.ferrechuvis.view.auth.Login;

/**@author GERARDO AYON*/
public class Ferrechuvis {

    public static void main(String[] args) {
        System.out.println("FERRECHUVIS - Desarrollado por Gerardo Ayon");

        Login login = new Login();
        login.mostrar();
    }
}
