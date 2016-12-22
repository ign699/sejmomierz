import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jan on 22.12.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://api-v3.mojepanstwo.pl/dane/poslowie.json");
        NameToId nameToId = new NameToId();
        System.out.print(nameToId.getId("Jarosław Gonciarz"));
    }
}
