package org.educatiom;

import org.educatiom.view.TestConexion;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(TestConexion::new);

    }

}