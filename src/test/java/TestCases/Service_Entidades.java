package TestCases;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import util.Constants;
import util.CallRest;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Service_Entidades {

    private DataStore globalData = DataStoreFactory.getSpecDataStore();
    CallRest callRest = new CallRest();

    @Step("Given el usuario quiere ver la informacion <> con headers <> de la entidad <>")
    public void preparar_servicio(String servicio, String headers, String entidad) {
        globalData.put(Constants.REST_SERVICE, servicio);
        globalData.put(Constants.HEADERS_SERVICE, headers);
        globalData.put(Constants.ENTIDAD, entidad);
    }

    @Step("When el usuario <> realice la invocacion del servicio")
    public void invocar_servicio(String usuario) throws Exception {
        String entidad = globalData.get(Constants.ENTIDAD).toString();
        String headers = globalData.get(Constants.HEADERS_SERVICE).toString();
        String servicio = globalData.get(Constants.REST_SERVICE).toString();
        String URL = servicio+usuario;
        Map<String, String> requestHeader = new HashMap<>();
        String [] header = headers.split(";");
        if(header.length > 1){
            for (int i =0; i< header.length; i++){
                String [] head = header[i].split(":");
                requestHeader.put(head[0],head[1]);
            }
        }else if(header.length ==1 ){
            String [] head = header[0].split(":");
            requestHeader.put(head[0],head[1]);
        }
        String [] respuesta = callRest.getMethod(URL, entidad, requestHeader);
        if(respuesta[0].equals("200") || respuesta.equals("201")){
            System.out.println("Respuesta correcta del servicio rest");
            globalData.put(Constants.RESPONSE_SERVICE,respuesta[1]);
        }else{
            System.out.printf("Respuesta desconocida");
            throw new Exception("");
        }

    }

    @Step("Then el usuario visualizara la informacion del servicio")
    public void resultados_servicio() throws Exception {
        String response = globalData.get(Constants.RESPONSE_SERVICE).toString();
        JsonParser parser = new JsonParser();
        JsonObject objecyContentMessage = parser.parse(response).getAsJsonObject();
        JsonElement jsonElementCampos = objecyContentMessage.get("datoConsultado").getAsJsonArray();
        String jsonElementError = objecyContentMessage.get("error").getAsString();
        String jsonElementURLDescarga = objecyContentMessage.get("urlDescarga").getAsString();

        val_error(jsonElementError);
        val_URL(jsonElementURLDescarga);
        val_campos(jsonElementCampos);

    }

    public void val_error(String error) throws Exception {
        if(error.isEmpty()){
            System.out.println("Campo ERROR correcto, no hay valor");
        }else{
            System.out.println("Se retorna un error: " + error);
            throw new Exception("");
        }
    }

    public void val_URL(String URL) throws Exception {
        if(URL.isEmpty()){
            System.out.println("Campo URL correcto, no hay valor");
        }else{
            System.out.println("Se retorna una dirección en URL: " + URL);
            throw new Exception("");
        }
    }

    public void val_campos(JsonElement campos) throws Exception {
        Table tableFields = (Table) globalData.get(Constants.TABLE + "respuestas");
        List<TableRow> rows = tableFields.getTableRows();
        List<String> columnNames = tableFields.getColumnNames();

        JsonParser parser = new JsonParser();
        JsonArray arraycampos = parser.parse(campos.toString()).getAsJsonArray();
        System.out.println(arraycampos.size());

        for(TableRow tcampo: rows){
            System.out.println(tcampo.getCell(columnNames.get(0)) +" - "+ tcampo.getCell(columnNames.get(1)));
            boolean existe = false;
            for(JsonElement campo: arraycampos) {
                String dato= null;
                String valor= null;
                JsonObject objecyContentMessage = parser.parse(campo.toString()).getAsJsonObject();
                dato = objecyContentMessage.get("campoDato").getAsString();
                if(dato.equals(tcampo.getCell(columnNames.get(0)))){
                    existe = true;
                    valor = objecyContentMessage.get("valorDato").getAsString();
                    if(valor.equals(tcampo.getCell(columnNames.get(1)))){
                        System.out.println("Valor correcto en el campo: " +tcampo.getCell(columnNames.get(0)));
                        break;
                    }else{
                        System.out.println("ERROR, se esperaba: " + tcampo.getCell(columnNames.get(1)) +" pero el servicio retorno: "+ valor);
                        throw new Exception("");
                    }
                }
            }
            if(existe == false){
                System.out.println("El servicio no retorna el campo: " + tcampo.getCell(columnNames.get(0)));
                throw new Exception("");
            }
        }
    }
}
