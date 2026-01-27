package org.example;

import DTOs.DTOApi;
import SERVICES.API_Get_Products;
import DTOs.DTOProdutos;

import java.io.IOException;
import java.util.List;

public class Main {
   public static void main(String[] args) throws IOException, InterruptedException {

        API_Get_Products apiProdutos = new API_Get_Products();

       apiProdutos.getAPI();


    }
}