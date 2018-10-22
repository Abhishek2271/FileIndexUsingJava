import java.lang.Exception;
import java.lang.RuntimeException;

public class CustomExceptionClass extends Exception
{
    public CustomExceptionClass(String message) {
        //super is used to call the constructor
        super(message);
    }
}

