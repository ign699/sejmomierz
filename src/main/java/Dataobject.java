import com.google.gson.annotations.SerializedName;

/**
 * Created by Jan on 22.12.2016.
 */
public class Dataobject {
    @SerializedName("id")
    private String ide;

    private String schema_url;

    private String dataset;

    private Data data;

    private String global_id;

    private String mp_url;

    private String slug;

    private String url;

    public Dataobject(String ide, String schema_url, String dataset, Data data, String global_id, String mp_url, String slug, String url) {
        this.ide = ide;
        this.schema_url = schema_url;
        this.dataset = dataset;
        this.data = data;
        this.global_id = global_id;
        this.mp_url = mp_url;
        this.slug = slug;
        this.url = url;
    }


    public String getId ()
    {
        return ide;
    }

    public void setIde (String id)
    {
        this.ide = id;
    }

    public String getSchema_url ()
    {
        return schema_url;
    }

    public void setSchema_url (String schema_url)
    {
        this.schema_url = schema_url;
    }

    public String getDataset ()
    {
        return dataset;
    }

    public void setDataset (String dataset)
    {
        this.dataset = dataset;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }


    public String getGlobal_id ()
    {
        return global_id;
    }

    public void setGlobal_id (String global_id)
    {
        this.global_id = global_id;
    }

    public String getMp_url ()
    {
        return mp_url;
    }

    public void setMp_url (String mp_url)
    {
        this.mp_url = mp_url;
    }

    public String getSlug ()
    {
        return slug;
    }

    public void setSlug (String slug)
    {
        this.slug = slug;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+ide+", schema_url = "+schema_url+", dataset = "+dataset+", data = "+data+", score = "+", global_id = "+global_id+", mp_url = "+mp_url+", slug = "+slug+", url = "+url+"]";
    }
}
