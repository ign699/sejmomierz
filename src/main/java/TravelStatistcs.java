import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jan on 27.12.2016.
 */
public class TravelStatistcs {
    private int cadency;
    private NameToId ids = new NameToId();


    public TravelStatistcs(int cadency){
        this.cadency = cadency;
    }


    public void loadTravelData(int cadency) throws IOException, ParseException {
        LinkedList<Integer> list = ids.getListOfIds(cadency);

        Map<String, String> mostSpent = new HashMap<String, String>();
        Map<String, String> beenToItaly = new HashMap<String, String>();
        Map<String, String> numberOfTravels = new HashMap<String, String>();
        Map<String, String> mostTimeAbroad = new HashMap<String, String>();

        for(int id : list){
            String idS = Integer.toString(id);
            PoselWyjazdySummary posel = new PoselWyjazdySummary(cadency, id);
            mostSpent.put(idS, Double.toString(posel.getMostExpesive()));
            beenToItaly.put(idS, Boolean.toString(posel.isBeenToItaly()));
            numberOfTravels.put(idS, Integer.toString(posel.getNumberOfDepartures()));
            mostTimeAbroad.put(idS, Integer.toString(posel.getDaysAbroad()));
        }

        Properties properties = new Properties();
        properties.putAll(mostSpent);

        String path = "src/main/java/mostSpent" + cadency + ".properties";
        FileOutputStream data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();

        properties = new Properties();
        properties.putAll(beenToItaly);

        path = "src/main/java/beenToItaly" + cadency + ".properties";
        data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();

        properties = new Properties();
        properties.putAll(numberOfTravels);

        path = "src/main/java/numberOfTravels" + cadency + ".properties";
        data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();

        properties = new Properties();
        properties.putAll(mostTimeAbroad);

        path = "src/main/java/mostTimeAbroad" + cadency + ".properties";
        data = new FileOutputStream(path);
        properties.store(new FileOutputStream(path), null);
        data.close();
    }
}
