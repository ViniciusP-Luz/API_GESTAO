package EXCEL;

import JSON.*;
import SERVICES.API_Set_Venda;
import SERVICES.MontaPedido;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
            FileInputStream fis = new FileInputStream("Vendas.xlsx");
            Workbook planilha = WorkbookFactory.create(fis);
            Sheet produtos = planilha.getSheetAt(0);
            Pedido pedido = null;
            Produto produto = null;
            ArrayList<ProdutoWrapper> ListaProdutoWrapper  = new ArrayList<>();

            for(int i = 1; i<= produtos.getLastRowNum();i++){
                Row linha = produtos.getRow(i);
                if(linha.getCell(0) != null){

                    if (pedido != null) {

                        Pagamento pagamento = new Pagamento(data_vencimento,valor,forma_pagamento_id);
                        PagamentoWrapper pgw = new PagamentoWrapper();
                        pgw.pagamento = pagamento;
                        pedido.pagamentos = List.of(pgw);

                        pedido.produtos = ListaProdutoWrapper;

                        String json = ped.retornaJson(pedido);
                        apiSetVenda.setVenda(json);



                    }
                    ListaProdutoWrapper.clear();
                    clienteID = (int) linha.getCell(1).getNumericCellValue();
                    condicao_pagamento = linha.getCell(2).getStringCellValue();
                    valor = String.valueOf((int)linha.getCell(3).getNumericCellValue());
                    pedido = new Pedido(clienteID,condicao_pagamento);
                    continue;
                }
                produto_id = String.valueOf((int) linha.getCell(4).getNumericCellValue());
                quantidade = String.valueOf((int) linha.getCell(5).getNumericCellValue());
                valor_venda = String.valueOf((int) linha.getCell(6).getNumericCellValue());
                produto = new Produto(produto_id, quantidade, valor_venda);
                ListaProdutoWrapper.add(new ProdutoWrapper(produto));
            }
            if (pedido != null && !ListaProdutoWrapper.isEmpty()) {

                Pagamento pagamento = new Pagamento(data_vencimento, valor, forma_pagamento_id);
                PagamentoWrapper pgw = new PagamentoWrapper();
                pgw.pagamento = pagamento;

                pedido.pagamentos = List.of(pgw);
                pedido.produtos = ListaProdutoWrapper;

                String json = ped.retornaJson(pedido);
                apiSetVenda.setVenda(json);
            }

            for (int i = produtos.getLastRowNum(); i >= 1; i--) {
                Row row = produtos.getRow(i);
                if (row != null) {
                    produtos.removeRow(row);
                }
            }
            fis.close();

            FileOutputStream fos = new FileOutputStream("Vendas.xlsx");
            planilha.write(fos);

            fos.close();
            planilha.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}


