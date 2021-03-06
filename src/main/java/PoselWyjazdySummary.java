import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jan on 27.12.2016.
 */
public class PoselWyjazdySummary {
    private int cadency;
    private int id;
    private String name;
    private PoselWyjazdy wyjazdy;
    private int numberOfDepartures = 0;
    private int daysAbroad = 0;
    private double mostExpesive = 0;
    private boolean beenToItaly = false;

    public PoselWyjazdySummary(int cadency, int id) throws IOException, ParseException {
        this.cadency = cadency;
        this.id = id;
        URL url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie/" + this.id + ".json?layers[]=wyjazdy");
        Gson gson = new Gson();
        try {
            wyjazdy = gson.fromJson(new JsonReader(new InputStreamReader(url.openStream())), PoselWyjazdy.class);
            extractData();
        }
        catch (Exception e){

        }
    }

    private void extractData() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        for(Wyjazdy wyjazd : wyjazdy.getDepartures().getWyjazdy()){
            Date startDate = df.parse(wyjazd.getOd());
            if(checkIfYearinCadency(getDateYear(startDate))){
                numberOfDepartures++;
                daysAbroad+=wyjazd.getLiczba_dni();
                checkIfBeenToItaly(wyjazd);
                updateMostExpensive(Double.parseDouble(wyjazd.getKoszt_suma()));
            }
        }
        name = wyjazdy.getData().getNazwa();
    }

    public  int getNumberOfDepartures(){
        return numberOfDepartures;
    }

    private void checkIfBeenToItaly(Wyjazdy wyjazd){
        if(wyjazd.getKraj().equals("Włochy")){
            beenToItaly = true;
        }
    }

    private int getDateYear(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private void updateMostExpensive(double price){
        if(price > mostExpesive){
            mostExpesive = price;
        }
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

    public int getDaysAbroad() {
        return daysAbroad;
    }

    public double getMostExpesive() {
        return mostExpesive;
    }

    public boolean isBeenToItaly() {
        return beenToItaly;
    }

    public String getName(){
        return this.name;
    }
}
