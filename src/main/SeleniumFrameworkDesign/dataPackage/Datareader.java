package dataPackage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class Datareader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

//     read the JSON content
       String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/main/SeleniumFrameworkDesign/dataPackage/PurchaseOrder.json"), StandardCharsets.UTF_8);

//     Convert String to HashMap - JackSon DataBind

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }
}
