package org.educatiom.data;

import org.educatiom.util.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Gestiona la conexión a la base de datos utilizando el patrón de diseño Singleton.
 */
public class Conexion {

    private static final Logger logger = LoggerFactory.getLogger(Conexion.class);
    private static Conexion instancia;
    Configuration configuration = new Configuration();

    /** Constructor privado para implementar Singleton. */
    private Conexion() {
        try {
            Class.forName(" ");
            logger.info("Driver PostgreSQL cargado exitosamente.");
        } catch (ClassNotFoundException e) {
            logger.error("No se pudo cargar el driver PostgreSQL. Asegúrate de que la dependencia esté en el classpath.", e);
            throw new RuntimeException("Fallo al iniciar el driver JDBC", e);
        }
    }

    /**
     * Retorna la instancia única de la clase (Patrón Singleton).
     * @return instancia de Conexion
     */
    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }


    /**
     * Establece y retorna una conexión activa a la base de datos.
     * <p>
     * Este método lee las propiedades de conexión (URL, usuario, contraseña)
     * desde un archivo y las utiliza para abrir una nueva conexión.
     * </p>
     * @return Una nueva conexión activa a la base de datos.
     * @throws RuntimeException si ocurre un error al cargar las propiedades o al
     * establecer la conexión con la base de datos.
     */
    public Connection getConnection() {
        logger.debug("Intentando establecer conexión a la base de datos...");
        Connection connection = null;
        Properties properties = configuration.loadPropertiesFile();
        String url = properties.getProperty("conexion.url");
        String user = properties.getProperty("conexion.user");
        String password = properties.getProperty("conexion.password");

        logger.info("Url: "  + url);
        logger.info("User: " + user);
        logger.info("Password: " + password);

        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.debug("Conexión establecida exitosamente.");
            return connection;
        } catch (SQLException e) {
            logger.error("Error al establecer la conexión con la base de datos.", e);
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

}