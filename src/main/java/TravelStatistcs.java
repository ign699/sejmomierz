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
    private Map<String, String> mosts = new HashMap<String, String>();
    private int cadency;
    private double mostSpent = 0;
    private String mostSpentName;
    private String beenToItaly ="";
    private int mostTravels = 0;
    private String mostTravelsName;
    private int mostDaysAbroad = 0;
    private String mostDaysAbroadName;
    private NameToId ids = new NameToId();
    private LinkedList<Integer> list ;


    public TravelStatistcs(int cadency) throws IOException, ParseException {
        this.cadency = cadency;
        list = ids.getListOfIds(cadency);
        try{
            FileInputStream in = new FileInputStream("src/main/java/stats"+cadency+".properties");
            Properties properties = new Properties();
            properties.load(in);
            Utils.propertiesToMap(properties, mosts);
        }
        catch (Exception e){
            reloadData();
        }
    }

    public void reloadData() throws IOException, ParseException {

        for(int id : list){
            PoselWyjazdySummary posel = new PoselWyjazdySummary(cadency, id);
            updateBeenToItaly(posel);
            updateMostDaysAbroad(posel);
            updateMostSpent(posel);
            updateMostTravels(posel);
         }

         saveDataToFile();
    }

    private void saveDataToFile() throws IOException {
        Properties properties = new Properties();
        properties.put("mostSpent", mostSpentName + " spent " + mostSpent);
        properties.put("beenToItaly", beenToItaly);
        properties.put("mostDaysAbroad", mostDaysAbroadName + " spend " + mostDaysAbroad + " abroad");
        properties.put("mostTravels", mostTravelsName + " was on " + mostTravels +" travels");

        String path = "src/main/java/stats" + cadency + ".properties";
        FileOutputStream data = new FileOutputStream(path);
        properties.store(data, null);
        data.close();
    }
    private void updateMostSpent(PoselWyjazdySummary posel){
        if(posel.getMostExpesive() > mostSpent ){
            mostSpent = posel.getMostExpesive();
            mostSpentName = posel.getName();
        }
    }

    private void updateMostDaysAbroad(PoselWyjazdySummary posel){
        if(posel.getDaysAbroad() > mostDaysAbroad){
            mostDaysAbroad = posel.getDaysAbroad();
            mostDaysAbroadName = posel.getName();
        }
    }

    private void updateBeenToItaly(PoselWyjazdySummary posel){
        if(posel.isBeenToItaly()){
            beenToItaly += posel.getName() + "\n";
        }
    }

    private void updateMostTravels(PoselWyjazdySummary posel){
        if(posel.getNumberOfDepartures() > mostTravels){
            mostTravels = posel.getNumberOfDepartures();
            mostTravelsName = posel.getName();
        }
    }

    public String getBeenToItaly(){
        return mosts.get("beenToItaly");
    }

    public String getMostSpent(){
        return mosts.get("mostSpent");
    }
    public String getMostTravels(){
        return mosts.get("mostTravels");
    }
    public String getMostDaysAbroad(){
        return mosts.get("mostDaysAbroad");
    }

}
