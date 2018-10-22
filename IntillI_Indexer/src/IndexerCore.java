import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;

//THIS CLASS JUST CALLS THE INDEXING CLASS TO INDEX THE FILES FROM THE GIVEN FILE LOCATION
//ONLY TEXT FILES ARE INDEXED AND THESE ARE FILTERED USING TEXTFILEFILTER CLASS

public class IndexerCore {

    //INDEX FILES FROM THIS LOCATION
    String indexFrom = "D:\\TU\\RD\\Index_Files";
    //CREATE INDEX IN THIS LOCATION
    String indexLocation = "D:\\TU\\RD\\Indexed_Files";
    Indexer indexer;


    public static void main(String[] args) {
        IndexerCore core;
        try
        {
            core = new IndexerCore();
            System.out.println("Working as intended...");
            core.createIndex();
        }
        catch (IOException e) {
            String message = e.getMessage();
            System.out.println(message);
        }

    }


    //NOT ACTUALLY NECESSARY TO CREATE THIS FUNCTION BUT HERE WE CANNOT REFERENCE THIS CLASS AS "this." SO LEFT THIS JUST TO DEMONSTRATE THAT
    private void createIndex() throws IOException {
        try
        {
            indexer = new Indexer(indexLocation);
            int numIndexed = 0;
            long startTime = System.currentTimeMillis();
            numIndexed = indexer.createIndex(indexFrom, new TextFileFilter());
            long endTime = System.currentTimeMillis();
            indexer.close();
            System.out.println(numIndexed + " File indexed, time taken: " + (startTime - endTime) + " ms");
        }
        catch (IOException e)
        {
            String message = e.getMessage();
            System.out.println(message);
        }
    }

}
