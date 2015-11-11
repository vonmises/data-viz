package ml.dev2dev.droiddata;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> getCSVData(InputStream input_stream){
        List<String> values = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(input_stream));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                values.add(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return values;
    }

    /**
     * Haven't implemented this yet
     * @param input_stream
     * @return
     */
    public static JSONArray getJSONData(InputStream input_stream){
        return null;
    }
}
