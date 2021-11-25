import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet(urlPatterns={"/patients"},loadOnStartup = 1)
public class MyServlet  extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getHeader("Welcome");
        resp.getWriter().write("hello, Whats up");

        req.getServletPath();

    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody=req.getReader().lines().collect(Collectors.joining(System.lineSeparator())); resp.setContentType("text/html");
        resp.getWriter().write("Thank you client! "+reqBody);
    }
    protected void makeGetRequest() throws IOException {
        URL myURL = new URL("https://web.whatsapp.com");
        HttpURLConnection conn = (HttpURLConnection) myURL.openConnection(); conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "text/html"); conn.setRequestProperty("charset", "utf-8");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(myURL.openStream()));
        String inputLine;
// Read the body of the response
        while ((inputLine = in.readLine()) != null) { System.out.println(inputLine);
        }
        in.close();
    }
    protected void makePostRequest() throws IOException {
        // Set up the body data
                String message = "Hello servlet";
                byte[] body = message.getBytes(StandardCharsets.UTF_8);
                URL myURL = new URL("http://localhost:8080/MyServlet/patients"); HttpURLConnection conn = null;
                conn = (HttpURLConnection) myURL.openConnection();
        // Set up the header
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept", "text/html"); conn.setRequestProperty("charset", "utf-8"); conn.setRequestProperty("Content-Length", Integer.toString(body.length)); conn.setDoOutput(true);
        // Write the body of the request
                try (OutputStream outputStream = conn.getOutputStream()) { outputStream.write(body, 0, body.length);
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String inputLine;
        // Read the body of the response
                while ((inputLine = bufferedReader.readLine()) != null) { System.out.println(inputLine);
                }
                bufferedReader.close();

    }
}