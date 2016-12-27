import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by Jan on 25.12.2016.
 */
public class PoselSpendingsSummary {
    private int id;
    private int cadency;
    private PoselWydatki poselWydatki;
    private double totalSpendings = 0;
    private double  minorOffice = 0;



    public PoselSpendingsSummary(int id, int cadency) throws IOException {
        this.id = id;
        this.cadency = cadency;
        URL url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie/" + id + ".json?layers[]=wydatki");
        Gson gson = new Gson();
        poselWydatki = gson.fromJson(new JsonReader(new InputStreamReader(url.openStream())), PoselWydatki.class);
        sumTotalSpendings();
    }

    private void sumTotalSpendings(){
        for(Roczniki r : poselWydatki.getSpendings().getWydatki().getRoczniki()){
            if(checkIfYearinCadency(Integer.parseInt(r.getRok()))){
                totalSpendings += sumArrayOfStrings(r.getPola());
                addToMinor(r.getPola());
            }
        }
    }

    private int sumArrayOfStrings(String[] array){
        int sum = 0;
        for(String i : array){
            sum += Double.parseDouble(i);
        }
        return sum;
    }
    private void addToMinor(String[] array){
        minorOffice+=Double.parseDouble(array[12]);
    }

    private boolean checkIfYearinCadency(int year){
        if(cadency==7 && year<2016){
            return true;
        }
        if(cadency==8 && year>2015){
            return true;
        }
        return false;
    }

    public double getTotalSpendings(){
        return totalSpendings;
    }
    public double getMinorOffice(){
        return minorOffice;
    }
}
