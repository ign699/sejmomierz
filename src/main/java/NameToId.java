import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jan on 22.12.2016.
 */
public class NameToId {
    private Map<String, String> nameMap = new HashMap<String, String>();
    private MyPojo json;


    public NameToId (){
    }

    public void updateIdList() throws IOException {
        loadData();
        Properties properties = new Properties();

        properties.putAll(nameMap);

        FileOutputStream data = new FileOutputStream("src/main/java/data.properties");
        properties.store(data, null);
        data.close();
    }

    private void loadData() throws IOException {
        String link = "https:\\//api-v3.mojepanstwo.pl\\/dane\\/poslowie.json";
        createObject(link);

        while(json.getLinks().getNext()!=null) {
            addJsonToMap();
            createObject(json.getLinks().getNext());
        }
        addJsonToMap();
    }

    private void createObject(String link) throws IOException {
        URL url = new URL(removeBacklashes(link));
        Gson gson = new Gson();
        json = gson.fromJson(new JsonReader(new InputStreamReader(url.openStream())), MyPojo.class);
    }

    private void addJsonToMap() {
        for (int i = 0; i < json.getDataobject().length; i++) {
            nameMap.put(json.getDataobject()[i].getData().getNazwa(), json.getDataobject()[i].getId());
        }
    }
    private String removeBacklashes(String link){
        String newlink = link.replace("\\/", "/");
        return newlink;
    }
    private void loadFileToMap() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/data.properties"));
        for(String key : properties.stringPropertyNames()){
            nameMap.put(key, properties.getProperty(key));
        }
    }
    public int getId(String name) throws IOException {
        loadFileToMap();
        return Integer.parseInt(nameMap.get(name));
    }
}
