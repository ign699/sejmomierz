/**
 * Created by Jan on 22.12.2016.
 */
public class MyPojo {

        private Dataobject[] Dataobject;

        private String Count;

        private Links Links;

        private String Took;

        public Dataobject[] getDataobject ()
        {
            return Dataobject;
        }

        public void setDataobject (Dataobject[] Dataobject)
        {
            this.Dataobject = Dataobject;
        }

        public String getCount ()
        {
            return Count;
        }

        public void setCount (String Count)
        {
            this.Count = Count;
        }

        public Links getLinks ()
        {
            return Links;
        }

        public void setLinks (Links Links)
        {
            this.Links = Links;
        }

        public String getTook ()
        {
            return Took;
        }

        public void setTook (String Took)
        {
            this.Took = Took;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [Dataobject = "+Dataobject+", Count = "+Count+", Links = "+Links+", Took = "+Took+"]";
        }

}
