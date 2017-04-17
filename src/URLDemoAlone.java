import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class URLDemoAlone {
    public static void main(String[]args){

        try {
            URL url = new URL("http://www.google.com");

            URLConnection conn = url.openConnection();

            InputStream Is = url.openStream();
            InputStreamReader ISA = new InputStreamReader(Is);
            BufferedReader br = new BufferedReader(ISA);

            String line ="";
            List<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null){


                System.out.println(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
