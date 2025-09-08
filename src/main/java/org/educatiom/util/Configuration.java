package org.educatiom.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Clase de utilidad para cargar y gestionar archivos de configuración.
 */
public class Configuration {

    /**
     * Carga un archivo de propiedades desde el classpath del proyecto.
     * <p>
     * Este método utiliza {@code ClassLoader} para encontrar el archivo de forma
     * independiente de la ubicación del proyecto, asegurando la portabilidad del código.
     * Se utiliza un bloque try-with-resources para garantizar que el
     * {@code InputStream} se cierre automáticamente.
     * </p>
     * @return Un objeto {@link Properties} que contiene las propiedades cargadas del archivo.
     * @throws RuntimeException si el archivo no se encuentra o si ocurre un error de E/S.
     */
    public Properties loadPropertiesFile() {
        //Esta clase me enecuentra la ruta absoluta pero de cualquier equipo
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("conexionBD.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: No se encuentra el archivo. ",  e);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de propiedades.", e);
        }
    }

}
