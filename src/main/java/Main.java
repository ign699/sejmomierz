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
        TravelStatistcs travels = new TravelStatistcs(7);
        System.out.println(travels.getBeenToItaly());
        final long duration = System.nanoTime() - startTime;
        System.out.println(duration/1000000000);
    }
}
