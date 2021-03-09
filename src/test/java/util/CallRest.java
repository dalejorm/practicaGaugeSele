package util;

//import com.google.gson.JsonObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.IOUtils;

import java.util.Map;

public class CallRest {


    /**
     * Created by aramirez
     *
     * @param url           servicio que se invoca
     * @param element       tipo de servicio y elemento específico
     * @param requestHeader cabecera para la invocación
     * @return lista de elementos
     * @throws Exception Al obtener un código de error
     */
    public String[] getMethod(String url, String element, Map<String, String> requestHeader) throws Exception {
        int statusCode;
        HttpClient httpClient = new HttpClient();
        GetMethod method = new GetMethod(url);

        if (requestHeader != null) {
            for (String headerName : requestHeader.keySet()) {
                method.addRequestHeader(headerName, requestHeader.get(headerName));
            }
        }

//        logger.info("Se invoca el servicio " + url + " [GET]");

        statusCode = httpClient.executeMethod(method);
        if (statusCode == 500 || statusCode == 403 || statusCode == 401) {
//            logger.error("Problemas con el servicio de:" + element + " " + statusCode);
            throw new Exception("");
        } else {
            String response = new String(IOUtils.toByteArray(method.getResponseBodyAsStream()));
            String[] res = new String[2];
            res[0] = Integer.toString(statusCode);
            res[1] = response;
            return res;
        }
    }

}
