package persons.azam_ami.get_public_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

// https://www.omdbapi.com/
public class OMDb_API extends Base_API
{
    private OMDb_API()
    {
        
    }
    
    @Override
    public void doSearch( final String search, int limit ) throws IOException
    {
        final String URL = "http://www.omdbapi.com/";
        Connection.Response res = Jsoup.connect( URL )
                .data( "s", search )
                .method( Method.GET )
                .ignoreContentType( true )
                .execute();
        String json = res.body();
        // System.out.println( json );
        
        Map json_as_map = (Map) gson.fromJson( json, Object.class );
        List<Map> Search = (List<Map>) json_as_map.get( "Search" );
        List<String> imdbIDs = new ArrayList<String>();
        if( Search!=null )
        {
            for( Map Search_item : Search )
            {
                final String imdbID = (String) Search_item.get( "imdbID" );
                // System.out.println( imdbID );
                imdbIDs.add( imdbID );
            }
        }
        
        for( String imdbID : imdbIDs )
        {
            if( limit==0 ) break;
            limit--;
            
            res = Jsoup.connect( URL )
                    .data( "i", imdbID )
                    .method( Method.GET )
                    .ignoreContentType( true )
                    .execute();
            json = res.body();
            // System.out.println( json );
            json_as_map = (Map) gson.fromJson( json, Object.class );
            
            final String Title = (String) json_as_map.get( "Title" ); 
            final String Year = (String) json_as_map.get( "Year" ); 
            final String Director = (String) json_as_map.get( "Director" ); 
            
            System.out.println( Year + "," + Director + "," + Title );
        }
    }    
    
    
    public static OMDb_API Instance = new OMDb_API();
}
