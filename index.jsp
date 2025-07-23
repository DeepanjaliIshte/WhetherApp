<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!--  language="java": The scripting language used in the page is Java.
	contentType="text/html; charset=UTF-8": The MIME type and character encoding of the response.
	pageEncoding="UTF-8": The character encoding for the JSP page itself. -->
<!DOCTYPE html>
<html lang="en">

<head>
<!-- The head section contains metadata and links to external resources -->
    <meta charset="UTF-8">
   <!--  Sets the character encoding for the HTML document. -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Ensures compatibility with IE browsers. -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Sets the viewport to ensure the page is responsive on different devices. -->
    <title>Weather App - Deepanjali </title>
    <!-- Sets the title of the web page. -->
    <link rel="stylesheet" href="style.css" />
    <!-- Links to an external CSS file for styling. -->
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
	<!-- Links to an external CSS file for Font Awesome icons. -->
</head>

<body>
	<!-- The body section contains the content of the HTML document. -->
    <div class="mainContainer">
    <!--Defines a div element with the class mainContainer to wrap the main content.  -->
     <form action="MyServlet" method="post" class="searchInput">
     <!-- 
     	action="MyServlet": Specifies the servlet to handle the form submission.
		method="post": Specifies the HTTP method to use when submitting the form.
		class="searchInput": Sets the class for styling the form.
      -->
            <input type="text" placeholder="Enter City Name" id="searchInput"  name="city"/>
            <!--An input field for entering the city name.  -->
            <button id="searchButton"><i class="fa-solid fa-magnifying-glass"></i>
            <!-- A submit button with a Font Awesome search icon. -->
            </button>
      </form>
        <div class="weatherDetails">
            <div class="weatherIcon">
               <img src="" alt="Clouds" id="weather-icon">
                <!--  An image element for the weather icon. -->
                <h2>${temperature} °C</h2>
                <!--A JSP expression to display the temperature in Celsius.  -->
                 <input type="hidden" id="wc" value="${weatherCondition}"> </input>
                 <!-- A hidden input field to store the weather condition. -->
            </div>
            
            <div class="cityDetails">        
            	<!--<div class="date">${date}</div>  -->
                <div class="desc"><strong>${city}</strong></div>
                <!--A JSP expression to display the city name.  -->
            
            <script>
				/*A JavaScript function to display the current time and update it every second  */
				function startTime() /* Function to get the current time and update the HTML element with id txt. */
				{
				  var today = new Date();
				  var h = today.getHours();
				  var m = today.getMinutes();
				  var s = today.getSeconds();
				  m = checkTime(m);	/* Function to add a leading zero to numbers less than 10 */
				  s = checkTime(s);
				  document.getElementById('txt').innerHTML =
				  h + ":" + m + ":" + s;
				  var t = setTimeout(startTime, 500);
				}
				function checkTime(i) {
				  if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
				  return i;
				}
			</script>
             <body onload="startTime()">	<!-- Calls startTime() when the page loads. -->
	
			<div id="txt"></div>	<!-- A div element to display the current time. -->
              </div>
              
            <div class="windDetails">
            	<div class="humidityBox">
            	<img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhgr7XehXJkOPXbZr8xL42sZEFYlS-1fQcvUMsS2HrrV8pcj3GDFaYmYmeb3vXfMrjGXpViEDVfvLcqI7pJ03pKb_9ldQm-Cj9SlGW2Op8rxArgIhlD6oSLGQQKH9IqH1urPpQ4EAMCs3KOwbzLu57FDKv01PioBJBdR6pqlaxZTJr3HwxOUlFhC9EFyw/s320/thermometer.png" alt="Humidity">
                <div class="humidity">
                   <span>Humidity </span>
                   <h2>${humidity}% </h2>	<!-- A JSP expression to display the humidity. -->
                </div>
               </div> 
               
                <div class="windSpeed">
                    <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiyaIguDPkbBMnUDQkGp3wLRj_kvd_GIQ4RHQar7a32mUGtwg3wHLIe0ejKqryX8dnJu-gqU6CBnDo47O7BlzCMCwRbB7u0Pj0CbtGwtyhd8Y8cgEMaSuZKrw5-62etXwo7UoY509umLmndsRmEqqO0FKocqTqjzHvJFC2AEEYjUax9tc1JMWxIWAQR4g/s320/wind.png">
                    <div class="wind">
                    <span>Wind Speed</span>
                    <h2> ${windSpeed} km/h</h2> <!--  A JSP expression to display the wind speed. -->
                    </div>
                </div>
            </div>
        </div>
    </div>
	  
	  <script src="myscript.js"> </script>	<!--  Includes an external JavaScript file-->
</body>

</html>
