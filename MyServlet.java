package MyPackage;


//Imports necessary classes for handling HTTP requests, responses, exceptions, input-output operations, networking, JSON parsing, and date formatting.
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet 
// Declares the MyServlet class, which extends HttpServlet. This class will handle HTTP requests and responses.
{
	private static final long serialVersionUID = 1L;
	//Ensures that different versions of the class are compatible during serialization.
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();	//Default constructor for the servlet, calls the superclass constructor.
         }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Handles HTTP GET requests
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//Writes "Served at: " followed by the context path of the application to the response.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Handles HTTP POST requests.
		
		String inputData=request.getParameter("userInput");	//Retrieves form data from the request parameters. userInput is unused, while city is the name of the city entered by the user.
		
		//API setup
		String apiKey = "5b64b1fd75ba02d65e57eafc2e3fdb4c";	//Sets up the OpenWeather API key.
		
		// Get the city from the form input
        String city = request.getParameter("city"); 

        // Create the URL for the OpenWeatherMap API request
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;	//Constructs the URL for the API request using the city name provided by the user.
        
        //API Integration
        URL url=new URL(apiUrl);	//Creates a URL object from the apiUrl.
        HttpURLConnection connection =(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET"); // Opens an HTTP connection to the URL and sets the request method to "GET".
        
        //Reading the data from network
        InputStream inputstream=connection.getInputStream();	// Opens an input stream to read data from the connection.
        InputStreamReader reader=new InputStreamReader(inputstream);	//Creates an InputStreamReader to read the input stream.

        //want to store the string
        StringBuilder responseContent=new StringBuilder();	//Uses a StringBuilder to store the response content.
        
        //take input from the reader,will create scanner object
        Scanner scanner =new Scanner(reader);
        
        while (scanner.hasNext())
        {
        	responseContent.append(scanner.nextLine());
        }
        
        scanner.close();	//Closes the Scanner and prints the response content to the console.
         System.out.println(responseContent);
        
        //TypeCasting =parsing the data into JSON
        Gson gson=new Gson(); // Creates a Gson object for parsing JSON.
        JsonObject jsonObject=gson.fromJson(responseContent.toString(), JsonObject.class);	//Converts the response content from a string to a JsonObject.
       // System.out.println(jsonObject);
        
        // Date & Time
        long dateTimestamp =jsonObject.get("dt").getAsLong()*1000; //Converts the Unix timestamp to milliseconds and formats it as a date string.
        String date =new Date(dateTimestamp).toString();
        
        // Temperature
        double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();// Converts the temperature from Kelvin to Celsius.
        int temperatureCelsius = (int) (temperatureKelvin - 273.15);
       
        //Humidity
        int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();// Retrieves the humidity value
        
        
        //Wind Speed
        double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble(); // Retrieves the wind speed.
        
        //Weather Condition
        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString(); //Retrieves the main weather condition.
        
        // Set the data as request attributes (for sending to the jsp page)
        request.setAttribute("date", date);
        request.setAttribute("city", city);
        request.setAttribute("temperature", temperatureCelsius);
        request.setAttribute("weatherCondition", weatherCondition); 
        request.setAttribute("humidity", humidity);    
        request.setAttribute("windSpeed", windSpeed);
        request.setAttribute("weatherData", responseContent.toString());

        connection.disconnect(); //Disconnects the HTTP connection.
        

        // Forward the request to the weather.jsp page for rendering
        request.getRequestDispatcher("index.jsp").forward(request, response);
   
	}

}
