package util;

import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileManager {

    private DataStore globalData = DataStoreFactory.getSpecDataStore();


    public void readProperties() throws IOException {
        Properties propiedades = new Properties();
        FileInputStream in = null;

        in = readProperties("env/ccd-test.properties");
        propiedades.load(in);
        String estado = propiedades.getProperty("isRest");
        String entornoWeb = propiedades.getProperty("entornoWeb");
        String entornoService = propiedades.getProperty("entornoService");
        String navegador = propiedades.getProperty("navegador");
        globalData.put(Constants.ISREST,estado);
        globalData.put(Constants.entornoWeb,entornoWeb);
        globalData.put(Constants.entornoService,entornoService);
        globalData.put(Constants.NAVEGADOR,navegador);

    }

    /**
     * Carga en un String el contenido del fichero desde la ubicación del properties
     *
     * @param pathFile Nombre del fichero a cargar (toda la rurta donde se encuentra el fichero)
     * @return String con el contenido del fichero.
     */
    public FileInputStream readProperties(String pathFile) {
        FileInputStream in;
        try {
            in = new FileInputStream(pathFile);
            return in;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
