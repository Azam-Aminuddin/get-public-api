package persons.azam_ami.get_public_api;

import java.io.IOException;

public class Main
{
    private static String getArg( final String arg, final String startsWith )
    {
        if( arg.startsWith( startsWith ))
        {
            return arg.substring( startsWith.length() );
        }
        return null;
    }
    
    private static void errorMissingArg( final String argName )
    {
        System.out.println( "*** Missing required argument \"" + argName + "\"." );
    }
    
    
    public static void main(final String[] args ) throws IOException
    {
        String api = null;
        String movie = null;
        String music = null;
        for( String arg : args )
        {
            //  System.out.println( arg );
            String arg_value = getArg( arg, "api=" );
            if( arg_value!=null ) 
            {
                api = arg_value;
                continue;
            }

            arg_value = getArg( arg, "movie=" );
            if( arg_value!=null ) 
            {
                movie = arg_value;
                continue;
            }

            arg_value = getArg( arg, "music=" );
            if( arg_value!=null ) 
            {
                music = arg_value;
                continue;
            }
        }
        
        if( api==null )
        {
            errorMissingArg( "api" );
            return;
        }
        
        if( "imdb".equals( api ))
        {
            if( movie==null )
            {
                errorMissingArg( "movie" );
                return;
            }
            IMDb_API.Instance.doSearch( movie, 4 );
        }
        else if( "omdb".equals( api ))
        {
            if( movie==null )
            {
                errorMissingArg( "movie" );
                return;
            }
            OMDb_API.Instance.doSearch( movie, 4 );
        }
        else if( "itunes".equals( api ))
        {
            if( music==null )
            {
                errorMissingArg( "music" );
                return;
            }
            iTunes_API.Instance.doSearch( music, 4 );
        }
        else if( "spotify".equals( api ))
        {
            if( music==null )
            {
                errorMissingArg( "music" );
                return;
            }
            Spotify_API.Instance.doSearch( music, 4 );
        }
        else
        {
            System.out.println( "*** Unknown api=" + api + ". Please select imdb, omdb, itunes or spotify." );
        }
    }
}
