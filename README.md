# Movie/Music Public API

This is a java console tool which uses public websites API to retrieve
informations about a given movie or music album. The public websites are :
- [IMDb](http://www.imdb.com/)
- [OMDb](https://www.omdbapi.com/)
- [iTunes](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/)
- [Spotify](https://developer.spotify.com/web-api/)

## How to build
> mvn clean package

It will build 2 jars: query.jar (without dependencies) and query-jar-with-dependencies.jar.
  
## How to run
> java -jar target/query-jar-with-dependencies.jar api=imdb movie="Indiana Jones"
