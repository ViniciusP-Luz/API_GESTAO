package SERVICES;

import DTOs.DTOApi;
import DTOs.DTOProdutos;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import static Repositories.ProdutosRepository.AdicionaProdutos;

public class API_Get_Products {

    DTOApi dados = new DTOApi();
    Properties props = new Properties();



    public void getAPI() throws IOException, InterruptedException {

        props.load(new FileInputStream("config.properties"));
        String url = props.getProperty("API_URL");
        String token = props.getProperty("ACCESS_TOKEN");
        String secret = props.getProperty("SECRET_TOKEN");

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type","application/json")
                    .header("access-token",token)
                    .header("secret-access-token",secret)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            dados = mapper.readValue(response.body(), DTOApi.class);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        for (DTOProdutos produto : dados.getData()) {
            AdicionaProdutos(produto);
        };

    }
}
