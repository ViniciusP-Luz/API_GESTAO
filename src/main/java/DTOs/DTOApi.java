package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTOApi {

    private List<DTOProdutos> data;

    public List<DTOProdutos> getData() {
        return data;
    }

    public void setData(List<DTOProdutos> data) {
        this.data = data;
    }
}