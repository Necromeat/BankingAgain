 <title>${customer.firstName} Accounts</title>
    <%--Header--%>
    <%@include file="../WEB-INF/jspf/Cusheader.jspf" %>
    <%@include file="../WEB-INF/jspf/taglib.jspf" %>
    <%--/Header--%>
    <%--Nav--%>
    <%@include file="../WEB-INF/jspf/CusNav.jspf" %>
    <%--/Nav--%>
    <%--Body--%>
    <div id="basicPanel">
        <h3>Your accounts</h3>
            <table>
                <tr>
                    <th>Account ID</th>
                    <th>Account Type</th>
                    <th>Account Balance</th>
                </tr>
          <c:forEach var="custAccount" items="${custAccounts}">

                <tr>
                    <td>${custAccount.accountId}</td>
                    <td>${custAccount.accountType}</td>
                    <td>${custAccount.balance}</td>
                    <td><a href="Controller?accountid=${custAccount.accountId}&command=customershowaccounthistory">Account history</a></td>
                </tr>
           </c:forEach>
            </table>
     
    </div>
    <%--/Body--%>
    <%@include file="../WEB-INF/jspf/CusFooter.jspf" %>