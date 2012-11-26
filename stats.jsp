<%@page import="Listener.ConcurrentUserTracker" %>
<html>
<link href="style/style1.css" rel="stylesheet" type="text/css" />
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

      // Load the Visualization API and the piechart package.
      google.load('visualization', '1.0', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Topping');
        data.addColumn('number', 'Slices');
        data.addRows([
          ['Sessions Created', <%=ConcurrentUserTracker.globalhit(0) %>],
	  ['Photos Uploaded', <%=ConcurrentUserTracker.globalhit(1) %>],
	  ['xml Edited', <%=ConcurrentUserTracker.globalhit(2) %>],
                    
        ]);

        // Set chart options
        var options = {'title':'Website Traffic Stats',
			'backgroundColor':'#ffe4b5',
                        'height':400,
			'width':'600'};

        // Instantiate and draw our chart, passing in some options.
        var chart = new  google.visualization.PieChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div" ></div>

	
<table border="1" class="Traffic">
<tr>
  <th>Online Users(Active Sessions):</th>
  <td><%=ConcurrentUserTracker.getConcurrentUsers() %></td>
</tr>
<tr>
  <th>Most Recent User Loged in:</th>
	
  <td><%=ConcurrentUserTracker.getLoginName()%></td>
</tr>
<tr>
  <th>Alltime Sessions Created:</th>
  <td><%=ConcurrentUserTracker.globalhit(0) %></td>
</tr>
<tr>
  <th>Photos Uploaded:</th>
  <td><%=ConcurrentUserTracker.globalhit(1) %></td>
</tr>
<tr>
  <th>Xml Edited</th>
  <td><%=ConcurrentUserTracker.globalhit(2) %></td>
</tr>
<tr>
  <th>Online Users:</th>
  <td><%=ConcurrentUserTracker.getArray()%></td>
</tr>
</table>
  </body>
</html>
