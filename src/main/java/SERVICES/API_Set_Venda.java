package SERVICES;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

public class API_Set_Venda {

    Properties props = new Properties();
    Scanner scan = new Scanner(System.in);


    public void setVenda(String json) throws IOException {

        props.load(new FileInputStream("config.properties"));
        String token = props.getProperty("ACCESS_TOKEN");
        String secret = props.getProperty("SECRET_TOKEN");

        try{



            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.beteltecnologia.com/vendas"))
                    .header("Content-Type","application/json")
                    .header("access-token",token)
                    .header("secret-access-token",secret)
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("STATUS: " + response.statusCode());
            System.out.println("BODY: " + response.body());





        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
