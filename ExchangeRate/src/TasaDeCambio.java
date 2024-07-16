import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TasaDeCambio {
    private String monedaBase;
    private String monedaObjetivo;
    private String direccion;

    private double tasaDeConversion;
    private double montoAConvertir;
    private double total;
    //private String direccion = "https://v6.exchangerate-api.com/v6/b54ac3c29e135a10098d84e1/pair/USD/MXN";
    //String direccion = "https://v6.exchangerate-api.com/v6/b54ac3c29e135a10098d84e1/latest/USD";

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;


    //CONSTRUCTOR
    public TasaDeCambio(String[] arrCodMonedas){
        setDireccion(arrCodMonedas);
        setCodigosMonedas(arrCodMonedas);
        setHttps();
    }


    public void convertir(double montoMonedaBase){
        //setDireccion(arrCodMonedas);
        setMontoAConvertir(montoMonedaBase);
        String json = response.body(); //devolvera el json
        //System.out.println(json);

        TasaDeCambioAPI tasaCambioApi = gson.fromJson(json, TasaDeCambioAPI.class);
        //System.out.println(tasaCambioApi);
        setTasaDeConversion(tasaCambioApi.conversion_rate());
        //System.out.println(tasaDeConversion);

        total = montoAConvertir * tasaDeConversion;
        //System.out.println(total);
    }

    //----------------------------------------------------------------setters
    public void setDireccion(String[] codMonedas){
        this.direccion = "https://v6.exchangerate-api.com/v6/b54ac3c29e135a10098d84e1/pair/" + codMonedas[1] + "/" + codMonedas[2];
    }

    public void setHttps(){
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        //HttpResponse<String> response;

        {
            try {
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void setCodigosMonedas(String[] codMonedas){
        this.monedaBase = codMonedas[1];
        this.monedaObjetivo = codMonedas[2];
    }

    public void setTasaDeConversion(String convRate){
        this.tasaDeConversion = Double.parseDouble(convRate);
    }

    public void setMontoAConvertir(double datoMonto){
        this.montoAConvertir = datoMonto;
    }

    //----------------------------------------------------------------getters


    public String getMonedaBase(){
        return monedaBase;
    }

    public String getMonedaObjetivo(){
        return monedaObjetivo;
    }

    public double getTotal(){
        return total;
    }

    public double getMontoAConvertir(){
        return montoAConvertir;
    }

}
