package JSON;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Pedido {

    public String tipo;
    public int cliente_id;
    public String data;
    public String condicao_pagamento;
    public List<PagamentoWrapper> pagamentos;
    public List<ProdutoWrapper> produtos;

    String hoje = LocalDate.now().toString();

    public Pedido(int cliente_id, String condicao_pagamento) {
        this.tipo = "produto";
        this.cliente_id = cliente_id;
        this.data = "2026-02-04";
        this.condicao_pagamento = condicao_pagamento;
    }
}



