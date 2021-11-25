import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void GetMakeRequest() throws IOException {
        URL myURL = new URL("http://imperial.ac.uk");
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
}