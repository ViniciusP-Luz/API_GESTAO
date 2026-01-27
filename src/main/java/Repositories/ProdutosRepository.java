package Repositories;

import DTOs.DTOProdutos;
import java.util.HashMap;
import java.util.Map;

public class ProdutosRepository{

    static Map<Integer, DTOProdutos> ProdutosMap = new HashMap();

    public static void AdicionaProdutos(DTOProdutos produto){
        ProdutosMap.put(Integer.parseInt(produto.getId()),produto);
    }

    public DTOProdutos getProduto(int id){
        return(ProdutosMap.get(id));
    }

    public static Map<Integer, DTOProdutos> getHashProd(){
        return ProdutosMap;
    }

}
