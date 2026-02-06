package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DTOApiCli {

    private List<DTOCLientes> data;

    public List<DTOCLientes> getData() {
        return data;
    }

    public void setData(List<DTOCLientes> data) {
        this.data = data;
    }
}
