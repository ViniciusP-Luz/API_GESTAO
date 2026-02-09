package org.main;

import SERVICES.API_Get_Products;
import org.apache.poi.ss.formula.atp.Switch;

import java.io.IOException;
import java.util.Scanner;

import static EXCEL.ExcelClientes.excel_Cliente;
import static EXCEL.Excel_Produtos.excel_Produtos;
import static EXCEL.Excel_Vendas.excel;

public class Main {
   public static void main(String[] args) throws IOException, InterruptedException {

       Scanner scan = new Scanner(System.in);
       int y = 1;

       while(y != 0){
           System.out.println("O que deseja fazer?");
           System.out.println("0 - FECHAR");
           System.out.println("1 - IMPORTAR PRODUTOS");
           System.out.println("2 - IMPORTAR CLIENTES");
           System.out.println("3 - EXPORTAR VENDAS");
           y = scan.nextInt();

           switch (y) {
               case 1:
                   excel_Produtos();
                   break;
               case 2:
                   excel_Cliente();
                   break;
               case 3:
                   excel();
                   break;
               case 0:
                   break;
           }


       }

    }
}