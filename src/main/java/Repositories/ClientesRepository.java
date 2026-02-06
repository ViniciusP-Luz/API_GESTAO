package Repositories;

import DTOs.DTOCLientes;

import java.util.HashMap;
import java.util.Map;

public class ClientesRepository {
    static Map<Integer, DTOCLientes> ClientesMap = new HashMap();

    public static void AdicionaCliente(DTOCLientes cliente){
        ClientesMap.put(Integer.parseInt(cliente.getId()),cliente);
    }

    public static Map<Integer, DTOCLientes> getHashCli(){
        return ClientesMap;
    }
}
