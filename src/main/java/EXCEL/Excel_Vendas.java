package EXCEL;

import JSON.*;
import SERVICES.API_Set_Venda;
import SERVICES.MontaPedido;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Excel_Vendas {

    static int clienteID;
    static String condicao_pagamento;
    static String produto_id;
    static String quantidade;
    static String valor_venda;
    static String data_vencimento;
    static String valor;
    static String forma_pagamento_id;

    public static void excel(){

        API_Set_Venda apiSetVenda = new API_Set_Venda();
        MontaPedido ped = new MontaPedido();

        try {
            System.out.println("Entrou aqui");


            FileInputStream fis = new FileInputStream("Vendas.xlsx");
            Workbook planilha = WorkbookFactory.create(fis);
            Sheet produtos = planilha.getSheetAt(0);
            Iterator<Row> itLinha = produtos.iterator();
            while(itLinha.hasNext()){
                Row linha = itLinha.next();
                if (linha.getRowNum()==0)
                    continue;
                Iterator<Cell> CelIT = linha.cellIterator();
                while (CelIT.hasNext()){
                    Cell celula = CelIT.next();
                    switch (celula.getColumnIndex()){
                        case 0:
                            clienteID = (int)celula.getNumericCellValue();
                            System.out.println("Cliente" + celula.getNumericCellValue());
                            break;
                        case 1:
                            condicao_pagamento = celula.getStringCellValue();
                            System.out.println("condicao_pagamento" + celula.getStringCellValue());
                            break;
                        case 2:
                            produto_id = String.valueOf((int)celula.getNumericCellValue());
                            System.out.println("produto_id" + celula.getNumericCellValue());
                            break;
                        case 3:
                            quantidade = String.valueOf((int)celula.getNumericCellValue());
                            System.out.println("quantidade" + celula.getNumericCellValue());
                            break;
                        case 4:
                            valor_venda = String.valueOf((int)celula.getNumericCellValue());
                            System.out.println("valor_venda" + celula.getNumericCellValue());
                            break;
                        case 5:
                            data_vencimento = String.valueOf((int)celula.getNumericCellValue());
                            System.out.println("data_vencimento" + celula.getNumericCellValue());
                            break;
                        case 6:
                            valor = String.valueOf((int)celula.getNumericCellValue());
                            System.out.println("valor" + celula.getNumericCellValue());
                            break;
                        case 7:
                            forma_pagamento_id = String.valueOf((int)celula.getNumericCellValue());
                            System.out.println("forma_pagamento_id" + celula.getNumericCellValue());
                            break;


                    }
                }
                System.out.println("A");
                Pedido pedido = new Pedido(clienteID,condicao_pagamento);
                Produto produto = new Produto(produto_id,quantidade,valor_venda);
                Pagamento pagamento = new Pagamento(data_vencimento,valor,forma_pagamento_id);

                ProdutoWrapper pw = new ProdutoWrapper();
                pw.produto = produto;

                PagamentoWrapper pgw = new PagamentoWrapper();
                pgw.pagamento = pagamento;

                pedido.produtos = List.of(pw);
                pedido.pagamentos = List.of(pgw);

                String json = ped.retornaJson(pedido);
                apiSetVenda.setVenda(json);


            }
            for (int i = produtos.getLastRowNum(); i >= 1; i--) {
                Row row = produtos.getRow(i);
                if (row != null) {
                    produtos.removeRow(row);
                }
                fis.close();

                FileOutputStream fos = new FileOutputStream("Vendas.xlsx");
                planilha.write(fos);

                fos.close();
                planilha.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}


