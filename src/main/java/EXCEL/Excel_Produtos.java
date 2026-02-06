package EXCEL;

import DTOs.DTOProdutos;
import SERVICES.API_Get_Products;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static Repositories.ProdutosRepository.getHashProd;

public class Excel_Produtos {

    public static void excel_Produtos() throws IOException, InterruptedException {

        API_Get_Products apiProdutos = new API_Get_Products();
        apiProdutos.getAPIPROD();
        Map<Integer, DTOProdutos> MAPA =  getHashProd();
        try {
            FileInputStream fis = new FileInputStream("Produtos.xlsx");
            Workbook planilha = WorkbookFactory.create(fis);
            Sheet produtos = planilha.getSheetAt(0);
            int linhaIndex = 1;
            for (int i = produtos.getLastRowNum(); i >= 1; i--) {
                Row row = produtos.getRow(i);
                if (row != null) {
                    produtos.removeRow(row);
                }
            }

            for (DTOProdutos produto : MAPA.values()) {
                Row linha = produtos.createRow(linhaIndex++);
                linha.createCell(0).setCellValue(produto.getId());
                linha.createCell(1).setCellValue(produto.getCodigo_interno());
                linha.createCell(2).setCellValue(produto.getNome());
                linha.createCell(3).setCellValue(produto.getEstoque());
                linha.createCell(4).setCellValue(produto.getValor_venda());
            }

            fis.close();

            FileOutputStream fos = new FileOutputStream("Produtos.xlsx");
            planilha.write(fos);

            fos.close();
            planilha.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
