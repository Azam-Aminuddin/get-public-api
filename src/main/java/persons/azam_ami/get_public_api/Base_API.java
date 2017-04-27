package persons.azam_ami.get_public_api;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Base_API
{
    protected static Gson gson;
    static 
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.setPrettyPrinting().create();
    }
    
    public void doSearch( final String search, int limit ) throws IOException
    {
        throw new AssertionError();
    }
}
