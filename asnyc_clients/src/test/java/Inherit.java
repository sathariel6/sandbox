import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Inherit {

    @Test
    public void justATest() throws IOException {
        URL url = new URL("https://www.google.com");

        URLConnection connection = url.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
        httpsURLConnection.setRequestMethod("POST");
        System.out.println(((HttpURLConnection) connection).getRequestMethod());

        Parent parent = new Child();
        System.out.println(((Child) parent).surname);

    }

}


class Child extends Parent {
    String surname = "Here";

    public String getSurname() {
        return surname;
    }
}

abstract class Parent {
    String name = "Name";
}
