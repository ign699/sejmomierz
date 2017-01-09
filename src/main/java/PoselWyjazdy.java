import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan on 27.12.2016.
 */
public class PoselWyjazdy {

    @SerializedName("layers")
    private Departures departures;

    private Data data;

    public Data getData(){
        return data;
    }

    public void setData(Data data){
        this.data = data;
    }

    public Departures getDepartures ()
    {
        return departures;
    }

    public void setDepartures(Departures layers)
    {
        this.departures = layers;
    }

}
