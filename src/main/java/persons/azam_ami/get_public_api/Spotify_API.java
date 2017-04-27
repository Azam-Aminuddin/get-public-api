package persons.azam_ami.get_public_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

//https://developer.spotify.com/web-api/search-item/
public class Spotify_API extends Base_API
{
    private Spotify_API()
    {
    }

    @Override
    public void doSearch( final String search, int limit ) throws IOException
    {
        final String URL = "https://api.spotify.com/v1/search";
        Connection.Response res = Jsoup.connect( URL )
                .data( 
                        "q", search,
                        "type", "album" 
                        )
                .method( Method.GET )
                .ignoreContentType( true )
                .execute();
        String json = res.body();
        // System.out.println( json );
        
        Map json_as_map = (Map) gson.fromJson( json, Object.class );
        Map albums = (Map) json_as_map.get( "albums" );
        List<Map> items = (List<Map>) albums.get( "items" );
        List<String> ids = new ArrayList<String>();
        if( items!=null )
        {
            for( Map item : items )
            {
                final String id = (String) item.get( "id" );
                ids.add( id );
            }
        }
        
        for( String id : ids )
        {
            if( limit==0 ) break;
            limit--;
            
            String URL_2 = "https://api.spotify.com/v1/albums/" + id;
            res = Jsoup.connect( URL_2 )
                    .method( Method.GET )
                    .ignoreContentType( true )
                    .execute();
            json = res.body();
            // System.out.println( json );
            json_as_map = (Map) gson.fromJson( json, Object.class );
            final String album_type = (String) json_as_map.get( "album_type" );
            final List<Map> artists = (List<Map>) json_as_map.get( "artists" );
            StringBuffer artist_names = new StringBuffer();
            if( artists!=null )
            {
                for( Map artist : artists )
                {
                    String artist_name = (String) artist.get( "name" );
                    if( artist_names.length()!=0 )
                    {
                        artist_names.append( ";" );
                    }
                    artist_names.append( artist_name ); 
                }
            }
            final String name = (String) json_as_map.get( "name" );
            final String release_date = (String) json_as_map.get( "release_date" );
            //System.out.println( "-------------------------------");
            //System.out.println( "--- id: " + id );
            //System.out.println( "--- name: " + name );
            //System.out.println( "--- album_type: " + album_type );
            //System.out.println( "--- release_date: " + release_date );
            
            System.out.println( release_date + "," + artist_names + "," + name );
        }
    }
    
    public static Spotify_API Instance = new Spotify_API();
}
