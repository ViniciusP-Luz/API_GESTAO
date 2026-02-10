package EXCEL;

import DTOs.DTOCLientes;
import DTOs.DTOProdutos;
import SERVICES.API_Get_Clientes;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import static Repositories.ClientesRepository.getHashCli;


public class ExcelClientes {

    public static void excel_Cliente() throws IOException, InterruptedException {

        API_Get_Clientes apiGetClientes = new API_Get_Clientes();
        apiGetClientes.getAPICLI();
        Map<Integer, DTOCLientes> MAPA =  getHashCli();
        try {
            FileInputStream fis = new FileInputStream("Vendas.xlsm");
            Workbook planilha = WorkbookFactory.create(fis);
            Sheet produtos = planilha.getSheetAt(3);
            int linhaIndex = 1;
            for (int i = produtos.getLastRowNum(); i >= 1; i--) {
                Row row = produtos.getRow(i);
                if (row != null) {
                    produtos.removeRow(row);
                }
            }

            for (DTOCLientes cliente : MAPA.values()) {
                Row linha = produtos.createRow(linhaIndex++);
                linha.createCell(0).setCellValue(cliente.getNome());
                linha.createCell(1).setCellValue(cliente.getId());

            }

            fis.close();

            FileOutputStream fos = new FileOutputStream("Vendas.xlsm");
            planilha.write(fos);

            fos.close();
            planilha.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
