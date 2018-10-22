# Indexing text files
The application is simple.
It reads text files from the specified directory and creates a searchable Lucene index
 
Input:
  
    - Destination folder location where the index is to be created
    - Source folder location (should contain text files to index)
  
Output:
  
    - Lucene index created in the destination folder location


NOTE: 

  - Used Lucene ver 7.4.0 for development. Older versions seem to have few of the functions depricated so the application is only compatible with Lucene 7.4.0
  - Compatible with Java Version 10 but should also work with older versions.
