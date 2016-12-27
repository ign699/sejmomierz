import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Jan on 25.12.2016.
 */
public class AllPosels {
    private NameToId ids = new NameToId();
    private int cadency;

    public AllPosels(int cadency){
        this.cadency=cadency;
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
