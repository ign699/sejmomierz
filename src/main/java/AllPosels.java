import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Created by Jan on 25.12.2016.
 */
public class AllPosels {
    private NameToId ids = new NameToId();
    private int cadency;

    public AllPosels(int cadency){
        this.cadency=cadency;
    }

    public void reloadCadencyEarnings() throws IOException {
        Properties properties = new Properties();
        LinkedList<Integer> list = ids.getListOfIds(7);
        for(int i : list){
            PoselSpendingsSummary posel = new PoselSpendingsSummary(i, 7);
            properties.put(Integer.toString(i), Double.toString(posel.getTotalSpendings()));
        }
        FileOutputStream data = new FileOutputStream("src/main/java/cadency7Spendings.properties");
        properties.store(data, null);
        data.close();

        list = ids.getListOfIds(8);
        properties = new Properties();
        for(int i : list){
            PoselSpendingsSummary posel = new PoselSpendingsSummary(i, 8);
            properties.put(Integer.toString(i), Double.toString(posel.getTotalSpendings()));
        }
        data = new FileOutputStream("src/main/java/cadency8Spendings.properties");
        properties.store(data, null);
        data.close();
    }

    private double calculateAvg() throws IOException {
        double sum = 0;
        LinkedList<Integer> list = ids.getListOfIds(cadency);
        for(int i : list){
            PoselSpendingsSummary posel = new PoselSpendingsSummary(i, cadency);
            sum+=posel.getTotalSpendings();
        }
        return sum/list.size();
    }

    public double getAvgSpendings() throws IOException {
        return calculateAvg();
    }
}
