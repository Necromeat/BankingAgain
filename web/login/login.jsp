


    <%@include file="../WEB-INF/jspf/MainHeader.jspf"%>
     <title>Login</title>
      
            <%@include file="../WEB-INF/jspf/MainNav.jspf" %>
     
       <div id="main">



    <div id="loginpanel">
        <h1>Login:</h1><br>
        <form id="loginform" action="Controller">
            <input type="hidden" name="command" value="login"/>
            <p>Username: <input type="email" name="custemail" required/></p>
            <p>Password: <input type="password" name="password" required/></p>
            <p><input type="submit" value="login"/></p>
            <p style="color: red"></p>
        </form>

    </div>

    <div id="newspanel">
        <h1>News:</h1><br>
        <h2>The web site is currently under maintenance so things may not work as desired. Thank you for your patients</h2>
        <p>FOR TEST ACCESS!!!!!!!</p>
        <p>customer:</p>
        <p>username = test@gmail.com : password = test</p>
        <p>Bank Teller:</p>
        <p>username = test@dailybank.com : password = test</p>
    </div>

</div>
   

        <%@include file="../WEB-INF/jspf/MainFooter.jspf"%>
