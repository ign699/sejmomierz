import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan on 22.12.2016.
 */
public class Data {
    @SerializedName("poslowie.nazwa")
    private String nazwa;

    public Data(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa ()
    {
        return nazwa;
    }


    /*public void setNazwa (String nazwa)
    {
        this.nazwa = nazwa;
    }*/

}
