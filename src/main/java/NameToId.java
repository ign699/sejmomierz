import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Jan on 22.12.2016.
 */
public class NameToId {
    private MyPojo json;
    private Map<String, String> nameMap7 = new HashMap<String, String>();
    private Map<String, String> nameMap8 = new HashMap<String, String>();


    public NameToId (){
    }

    public void updateIdList() throws IOException {
        loadData();
        Properties properties = new Properties();

        properties.putAll(nameMap7);

        FileOutputStream data = new FileOutputStream("src/main/java/Ids7.properties");
        properties.store(data, null);
        data.close();

        properties = new Properties();
        properties.putAll(nameMap8);

        data = new FileOutputStream("src/main/java/Ids8.properties");
        properties.store(data, null);
        data.close();
    }

    private void update8Cadency() throws IOException {
        String link = "https:\\//api-v3.mojepanstwo.pl\\/dane\\/poslowie.json?conditions[poslowie.kadencja]=8";
        createObject(link);
        while(json.getLinks().getNext()!=null) {
            addJsonToMap(nameMap8);
            createObject(json.getLinks().getNext());
        }
        addJsonToMap(nameMap8);
    }

    private void update7Cadency() throws IOException {
        String link = "https:\\//api-v3.mojepanstwo.pl\\/dane\\/poslowie.json?conditions[poslowie.kadencja]=7";
        createObject(link);
        while(json.getLinks().getNext()!=null) {
            addJsonToMap(nameMap7);
            createObject(json.getLinks().getNext());
        }
        addJsonToMap(nameMap7);
    }


    private void loadData() throws IOException {
        update8Cadency();
        update7Cadency();
    }

    private void createObject(String link) throws IOException {
        URL url = new URL(removeBacklashes(link));
        Gson gson = new Gson();
        json = gson.fromJson(new JsonReader(new InputStreamReader(url.openStream())), MyPojo.class);
    }

    private void addJsonToMap(Map<String, String> map) {
        for (int i = 0; i < json.getDataobject().length; i++) {
            map.put(json.getDataobject()[i].getData().getNazwa(), json.getDataobject()[i].getId());
        }
    }
    private String removeBacklashes(String link){
        String newlink = link.replace("\\/", "/");
        return newlink;
    }

    private void loadFileCadency7() throws IOException {
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("src/main/java/Ids7.properties"));
            Utils.propertiesToMap(properties, nameMap7);
        }
        catch(IOException e){
            updateIdList();
        }
    }

    private void loadFileCadency8() throws IOException {
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("src/main/java/Ids8.properties"));
            for(String key: properties.stringPropertyNames()){
                nameMap8.put(key, properties.getProperty(key).toString());
            }
        }
        catch(IOException e){
            updateIdList();
        }
    }

    private void loadFileToMap(int Cadency) throws IOException {
        switch (Cadency){
            case 7: loadFileCadency7();
            case 8: loadFileCadency8();
            default: return;
        }
    }
    private int idFromMap(String name, int Cadency) throws Exception {
        if(Cadency==7){
            if(nameMap7.containsKey(name)){
                return Integer.parseInt(nameMap7.get(name));
            }
            else{
                throw new Exception("No such posel in specified cadency");
            }
        }
        if(Cadency==8){
            if(nameMap8.containsKey(name)){
                return Integer.parseInt(nameMap8.get(name));
            }
            else{
                throw new Exception("No such posel in specified cadency");
            }
        }
        return 1;
    }

    public int getId(String name, int Cadency) throws Exception {
        loadFileToMap(Cadency);
        int id = idFromMap(name, Cadency);
        return id;
    }

    public LinkedList getListOfIds(int cadency) throws IOException {
        loadFileToMap(cadency);
        LinkedList<Integer> list = null;
        if (cadency==7){
            list = new LinkedList<Integer>();
            for(String value : nameMap7.values()){
                list.add(Integer.parseInt(value));
            }
        }
        if (cadency==8){
            list = new LinkedList<Integer>();
            for(String value : nameMap8.values()){
                list.add(Integer.parseInt(value));
            }
        }
        return list;

    }

}
