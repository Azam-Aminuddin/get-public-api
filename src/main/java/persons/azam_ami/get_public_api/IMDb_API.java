package persons.azam_ami.get_public_api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IMDb_API extends Base_API
{
    private IMDb_API()
    {
        
    }
    
    @Override
    public void doSearch( final String search, int limit ) throws IOException
    {
        final String URL = "http://www.imdb.com/xml/find";
        Connection.Response res = Jsoup.connect( URL )
                .data( 
                        "json", "1",
                        "nr", "1", 
                        "tt", "on", 
                        "q", search 
                        )
                .method( Method.GET )
                .ignoreContentType( true )
                .execute();
        final String json = res.body();
        // System.out.println( json );
        
        Map json_as_map = (Map) gson.fromJson( json, Object.class );
        // System.out.println( gson.toJson( map ) );
        
        List<Map> title_popular = (List<Map>) json_as_map.get( "title_popular" );
        if( title_popular!=null )
        {
            for( Map item : title_popular )
            {
                if( limit==0 ) break;
                limit--;
                
                final String title = (String) item.get( "title" );
                String title_description = (String) item.get( "title_description" );
                
                String year = null;
                if( title_description!=null ) 
                {
                    if( title_description.trim().length()>=4 )
                    {
                        year = title_description.trim().substring( 0, 4 );
                    }
                }
                
                StringBuffer director = new StringBuffer();
                if( title_description!=null )
                {
                    Document doc = Jsoup.parse( title_description );
                    Elements as = doc.select("a");
                    for( Element a : as )
                    {
                        final String text = a.text();
                        if( text!=null )
                        {
                            if( director.length()>0 ) director.append( ";" );
                            director.append( text );
                        }
                    }
                }
                
                System.out.println( year + "," + director + "," + title );
            }
        }
    }    
    
    
    public static IMDb_API Instance = new IMDb_API();
}
