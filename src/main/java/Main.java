import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by Jan on 22.12.2016.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final long startTime = System.nanoTime();
        NameToId nameToId = new NameToId();
        System.out.println(nameToId.getId("Jarosław Kaczyński", 7));
        PoselSpendingsSummary posel = new PoselSpendingsSummary(nameToId.getId("Jarosław Kaczyński", 7), 7);
        System.out.println(posel.getTotalSpendings());

        final long duration = System.nanoTime() - startTime;

        System.out.println(duration/1000000000);
    }
}
