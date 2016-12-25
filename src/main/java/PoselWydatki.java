import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan on 25.12.2016.
 */
public class PoselWydatki {
        @SerializedName("layers")
        private Spendings spendings;

        public Spendings getSpendings ()
        {
            return spendings;
        }

        public void setLayers (Spendings layers)
        {
            this.spendings = layers;
        }

}
