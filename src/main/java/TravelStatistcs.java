import java.io.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jan on 27.12.2016.
 */
public class TravelStatistcs {
    private Map<String, String> mostSpent = new HashMap<String, String>();
    private Map<String, String> beenToItaly = new HashMap<String, String>();
    private Map<String, String> numberOfTravels = new HashMap<String, String>();
    private Map<String, String> mostTimeAbroad = new HashMap<String, String>();
    private Map<String, String> mosts = new HashMap<String, String>();
    private int cadency;
    private NameToId ids = new NameToId();


    public TravelStatistcs(int cadency){
        this.cadency = cadency;
    }

    private void addPoselsDataToMaps(LinkedList<Integer> list) throws IOException, ParseException {

        for(int id : list){
            String idS = Integer.toString(id);
            PoselWyjazdySummary posel = new PoselWyjazdySummary(cadency, id);
            mostSpent.put(idS, Double.toString(posel.getMostExpesive()));
            beenToItaly.put(idS, Boolean.toString(posel.isBeenToItaly()));
            numberOfTravels.put(idS, Integer.toString(posel.getNumberOfDepartures()));
            mostTimeAbroad.put(idS, Integer.toString(posel.getDaysAbroad()));
         }
    }

    private void createMostSpendFile() throws IOException {
        Properties properties = new Properties();
        properties.putAll(mostSpent);

        String path = "src/main/java/mostSpent" + cadency + ".properties";
        FileOutputStream data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();
    }

    private void createBeenToItalyFile() throws IOException {
        Properties properties = new Properties();
        properties.putAll(beenToItaly);

        String path = "src/main/java/beenToItaly" + cadency + ".properties";
        FileOutputStream data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();

    }

    private void createNumberOfTravelsFile() throws IOException {
        Properties properties = new Properties();
        properties.putAll(numberOfTravels);

        String path = "src/main/java/numberOfTravels" + cadency + ".properties";
        FileOutputStream data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();

    }

    private void createMostTimeAbroadFile() throws IOException {
        Properties properties = new Properties();
        properties.putAll(mostTimeAbroad);

        String path = "src/main/java/mostTimeAbroad" + cadency + ".properties";
        FileOutputStream data = new FileOutputStream(path);
        properties.store(new FileOutputStream(path), null);
        data.close();
    }

    private void createFiles() throws IOException {
        createBeenToItalyFile();
        createMostSpendFile();
        createMostTimeAbroadFile();
        createNumberOfTravelsFile();
    }

    public void loadTravelData() throws IOException, ParseException {
        LinkedList<Integer> list = ids.getListOfIds(cadency);
        addPoselsDataToMaps(list);
        createFiles();
    }


}
