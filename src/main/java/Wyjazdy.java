import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan on 27.12.2016.
 */
public class Wyjazdy {

    private String kraj;

    private String od;

    private int liczba_dni;

    private String koszt_suma;

    @SerializedName("do")
    private String Do;

    public String getKraj ()
    {
        return kraj;
    }


    public void setKraj (String kraj)
    {
        this.kraj = kraj;
    }

    public String getOd ()
    {
        return od;
    }

    public void setOd (String od)
    {
        this.od = od;
    }

    public String getDo ()
    {
        return Do;
    }

    public void setDo (String Do)
    {
        this.Do = Do;
    }
    public String getKoszt_suma ()
    {
        return koszt_suma;
    }

    public void setKoszt_suma (String koszt_suma)
    {
        this.koszt_suma = koszt_suma;
    }

    public int getLiczba_dni() {
        return liczba_dni;
    }

    public void setLiczba_dni(int liczba_dni) {
        this.liczba_dni = liczba_dni;
    }
}
