package SERVICES;

import JSON.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MontaPedido {

    public String retornaJson(Pedido pedido) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pedido);

        System.out.println(json);

        return json;
    }



}
