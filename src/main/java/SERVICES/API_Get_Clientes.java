package SERVICES;

import DTOs.DTOApiCli;
import DTOs.DTOCLientes;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import static Repositories.ClientesRepository.AdicionaCliente;

public class API_Get_Clientes {

    DTOApiCli dados = new DTOApiCli();
    Properties props = new Properties();



    public void getAPICLI() throws IOException, InterruptedException {

        props.load(new FileInputStream("config.properties"));
        String token = props.getProperty("ACCESS_TOKEN");
        String secret = props.getProperty("SECRET_TOKEN");

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.beteltecnologia.com/clientes"))
                    .header("Content-Type","application/json")
                    .header("access-token",token)
                    .header("secret-access-token",secret)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            dados = mapper.readValue(response.body(), DTOApiCli.class);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        for (DTOCLientes cliente : dados.getData()) {

            AdicionaCliente(cliente);
        };

    }
}
