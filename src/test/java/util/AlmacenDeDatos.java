package util;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

public class AlmacenDeDatos {
    DataStore globalData = DataStoreFactory.getSpecDataStore();

    @Step("Valores servicios <nombreTabla> <table>")
    public void newService(String nombreTabla, Table table) {
        globalData.put(Constants.TABLE + nombreTabla, table);
    }

    @Step("Valores usuario <nombreTabla> <table>")
    public void newUser(String nombreTabla, Table table) {
        globalData.put(Constants.TABLE + nombreTabla, table);
    }

}
