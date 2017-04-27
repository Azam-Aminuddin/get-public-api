package persons.azam_ami.get_public_api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

//https://developer.spotify.com/web-api/search-item/
public class iTunes_API extends Base_API
{
    private iTunes_API()
    {
    }

    @Override
    public void doSearch( final String search, int limit ) throws IOException
    {
        String URL = "https://itunes.apple.com/search";
        Connection.Response res = Jsoup.connect( URL )
                .data(
                        "term", search,
                        "entity", "album"
                        )
                .method( Method.GET )
                .ignoreContentType( true )
                .execute();
        String json = res.body();
        // System.out.println( json );
        
        Map json_as_map = (Map) gson.fromJson( json, Object.class );
        List<Map> results = (List<Map>) json_as_map.get( "results" );
        if( results!=null )
        {
            for( Map result : results )
            {
                if( limit==0 ) break;
                limit--;
                
                // final String wrapperType = (String) result.get( "wrapperType" );
                final String collectionName = (String) result.get( "collectionName" );
                final String artistName = (String) result.get( "artistName" );
                final String releaseDate = (String) result.get( "releaseDate" );
                String releaseDate_date = null;
                if( releaseDate!=null )
                {
                    releaseDate_date = releaseDate.substring( 0, 10 ); 
                }
                System.out.println( releaseDate_date + "," + artistName + "," + collectionName );
            }
        }
    }
    
    public static iTunes_API Instance = new iTunes_API();
}
