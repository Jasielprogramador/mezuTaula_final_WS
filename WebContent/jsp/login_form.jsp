<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>


   <head>

      <title>LoginForm</title>

      <link href="/mezuTaula/css/styleSheet.css" rel="stylesheet" />

   </head>


<body>

      <header>

         <h1>A webapp to share short messages</h1>

      </header>
      
      <%
	
	Object login_error_aux = request.getAttribute("login_error");
	
	//Pones esto porque al pricipio cargas el documento sin pasar por el servlet, por ello 
	//pones esta primera condicion porque si no la pusieras daria error ya que boolean no puede tener el valor null
	if(login_error_aux != null){
		if((boolean) login_error_aux){
		%>
			<h3>LOGIN ERROR</h3>
		<%
		}
	}
	
	%>


      <section>

         <form method="POST" action="/mezuTaula/servlet/LoginServlet">

            <table>

                 <tr>

                    <td>Email:</td>

                    <td><input name="email"/></td>

                 </tr>            

                 <tr>

                    <td>Password:</td>

                    <td><input type="password" name="password"/></td>

                 </tr>

            
            </table>

            <button>Send</button>

         </form>

      </section>

      <footer>Web Systems - EUITI Bilbao</footer>

</body>


</html>