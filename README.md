 ## image-gallery-search
 
 ##### What you'll build
 You'll build a simple REST application with Spring Boot which represents image-gallery-search.
 
 ##### What you'll need
 java_version: 1.11
 
 ##### Run the application
 To run the application on Unix:
```
 git clone https://github.com/grafgeest/image-gallery-search.git  
 cd image-galery-search  
 gradle clean build && java -jar build/libs/image-gallery-search-0.0.1-SNAPSHOT.jar
```

 To run the application on Windows:
 ```
  git clone https://github.com/grafgeest/image-gallery-search.git  
  cd image-galery-search  
  gradlew clean build && java -jar build/libs/image-gallery-search-0.0.1-SNAPSHOT.jar
```
 
 
 ##### Check out the service.  
 ```
 $ curl localhost:8080/search/{searchTerm}  
 ```
 You will get photos metadata
 
 ```
 $ curl localhost:8080/admin/invalidateCache
 ```
 Invalidates cache


##### To improve:
- Multithreading for fetching images 
- Consider Ehcache, Memcache, Lucene instead of H2 db
- Unit Testing 
- Secure endpoints  
- Enable CORS
