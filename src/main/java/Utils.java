import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Jan on 03.01.2017.
 */
public final class Utils {

    private Utils(){

    }
    public static void propertiesToMap(Properties properties, Map<String, String> map){
        for(String key: properties.stringPropertyNames()){
            map.put(key, properties.getProperty(key).toString());
        }
    }
}
