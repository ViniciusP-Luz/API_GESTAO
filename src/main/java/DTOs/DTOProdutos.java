package DTOs;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)

public class DTOProdutos {

    private String id;
    private String nome;
    private String codigo_interno;
    private int estoque;
    private float valor_venda;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo_interno() {
        return codigo_interno;
    }

    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    @Override
    public String toString() {
        return id +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", codigo_interno='" + codigo_interno + '\'' +
                ", estoque=" + estoque +
                ", valor_venda=" + valor_venda +
                '}' +
                "\n";
    }
}
