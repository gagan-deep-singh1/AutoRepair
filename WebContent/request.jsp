<body onload="getLocations()">


<p id="demo"></p>

<script>
var x = document.getElementById("demo");

function getLocations() {
    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";}
    }
    
function showPosition(position) {
    x.innerHTML="Latitude: " + position.coords.latitude + 
    "<br>Longitude: " + position.coords.longitude;
}
</script>
</body>
<% 
    String latitude="<script>document.writeln(position.coords.latitude)</script>";
    String longitude="<script>document.writeln(position.coords.longitude)</script>";
    out.println("<form action='MapServlet' method='post'>");
    out.println("<input type='hidden' name='longitude' value='"+longitude+"'>");
    out.println("<input type='hidden' name='latitude' value='"+latitude+"'>");
	out.println("<input type='submit'value='Raise a Request'>");    
	out.println("</form>");    
%>