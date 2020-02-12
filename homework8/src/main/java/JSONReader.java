import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JSONReader {
    private List<Credit> creditList = new ArrayList<>();
    private String filename;


    public JSONReader(String filename) {
        this.filename = filename;

    }

    public List<Credit> read() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(JsonParser.Feature.ALLOW_MISSING_VALUES, true);
            creditList = objectMapper.readValue(new File(filename), objectMapper.getTypeFactory().constructCollectionType(List.class, Credit.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return creditList;

    }
}
