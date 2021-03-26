<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*,helper.info.*" %>

<%String username = (String) session.getAttribute("username"); 
int inactive_interval = session.getMaxInactiveInterval();
ServletContext context = request.getServletContext();
HashMap<String,String> loggedinUsers = (HashMap) context.getAttribute("loggedin_users");

ArrayList<MessageInfo> messageList = (ArrayList) request.getAttribute("messageList");
%>
   
<html>
	<head>
		<title>View Messages</title>
		<link href = "/mezuTaula/css/styleSheet.css" rel = "stylesheet" />
	</head>
	<body>
		<header>
			<h1>A webap to share short messages</h1>
			<h3>View Messages</h3>
		</header>
		
		<section>
			<a href="/mezuTaula/servlet/MainServlet" style = "text-decoration:none">
				<font color = "white" >Refresh</font>
			</a>
			
			<a href="/mezuTaula/servlet/MainServlet?action=logout" style="text-decoration:none">
				<font color = "white" >Logout</font>
			</a>
		
		</section>
		
		<section>
			<font color = "white" >You are logged in as: </font>
			<%= username %>
		</section>
		
		<section>
			<script>
				var timeleft = <%= inactive_interval %>;
				var downloadTimer = setInterval(function()){
					if(timeleft==1){
						clearInterval(downloadTimer);
					}
					document.getElementById("progressBar").value = <%= inactive_interval %> - timeleft;
					timeleft -= 1;
				},1000);
			
			</script>
			<font color="white">Session timeout</font>
			<progress value="0" max="<%= inactive_interval %>"id="progressBar"></progress>
		
		</section>
		
		<section>
			<font color = "white" >Active users: </font>
			<%for(Map.Entry<String,String> entry : loggedinUsers.entrySet()){ %>
				<%=entry.getKey() %>
			<%} %>
		</section>
		
		<section>
			<table>
				<tr>
					<th>Username</th>
					<th>Message</th>
				</tr>
				
				<%for(int i = 0;i<messageList.size();i++){
					MessageInfo messageInfo = messageList.get(i);%>
					<tr>
						<td><%= messageInfo.getUsername() %></td>
						<td><%= messageInfo.getMessage() %></td>
					</tr>
				<% } %>
			</table>
		
		</section>
		

	</body>
</html>