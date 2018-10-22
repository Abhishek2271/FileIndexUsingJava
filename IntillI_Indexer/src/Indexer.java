//File specific
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
//Lucene specific classes
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;


//Create a indexer class to index the given file.

public class Indexer
{

    class LuceneConstants {
        public static final String CONTENTS = "contents";
        public static final String FILE_NAME = "filename";
        public static final String FILE_PATH = "filepath";
        public static final int MAX_SEARCH = 10;
    }

    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException
    {
        //this directory will contain the indexes
        File f = new File(indexDirectoryPath);

        Directory indexDirectory =
                FSDirectory.open(f.toPath());
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        /* create the indexer */
        writer = new IndexWriter(indexDirectory, config);
    }

    public void close() throws CorruptIndexException, IOException
    {
        writer.close();
    }

    private Document getDocument(File file) throws IOException
    {
        Document document = new Document();

        //index file contents
        Field contentField = new TextField(LuceneConstants.CONTENTS, new FileReader(file));
        //index file name
        Field fileNameField = new StringField(LuceneConstants.FILE_NAME, file.getName(),Field.Store.YES);
        //index file path
        Field filePathField = new StringField(LuceneConstants.FILE_PATH, file.getCanonicalPath(),Field.Store.YES);
        document.add(contentField);
        document.add(fileNameField);
        document.add(filePathField);

        return document;
    }

    private void indexFile(File file) throws IOException
    {
        System.out.println("Indexing "+file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);
    }

    public int createIndex(String dataDirPath, FileFilter filter) throws IOException
    {
        //get all files in the data directory
        File[] files = new File(dataDirPath).listFiles();


        for (File file : files) {
            if(!file.isDirectory()
                    && !file.isHidden()
                    && file.exists()
                    && file.canRead()
                    && filter.accept(file)
                    ){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }
}