# Movie/Music Public API

This is a java console tool which uses public websites API to retrieve
informations about a given movie or music album. The public websites are :
- IMDb
- OMDb
- iTunes
- Spotify

## How to build
> mvn clean package

It will build 2 jars: query.jar (without dependencies) and query-jar-with-dependencies.jar.
  
## How to run
> java -jar target\query-jar-with-dependencies.jar api=imdb movie="Indiana Jones"
