 

<title>${customer.firstName} Account History</title>
    <%--Header--%>
    <%@include file="../WEB-INF/jspf/Cusheader.jspf" %>
    <%@include file="../WEB-INF/jspf/taglib.jspf" %>
    <%--/Header--%>
    <%--Nav--%>
    <div id="navigation">
    <%@include file="../WEB-INF/jspf/CusNav.jspf" %>
    </div>
    <%--/Nav--%>
    <%--Body--%>
    <div id="main">
   <div id="basicPanel">
        <table>
            <caption>${customer.firstName}&nbsp;-&nbsp;</caption>
            <thead>
                <tr><th>Date</th><th>Description</th><th>Amount</th><th>Balance</th></tr>
            </thead>


            <c:forEach var="item" items="${account.transactions}">
                <tr>
                    <td><f:formatDate type="date" value="${today}"/></td>
                    <td>${item.info}</td>
                    <td><div itemid="L1"><f:formatNumber type="number"  minFractionDigits="2" maxFractionDigits="2" value="${item.amount}"/></div></td>
                    <td><div itemid="L1"><f:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.balance}"/></div></td>
                </tr>
            </c:forEach>
        </table>
    </div>
            </div>
    <%--/Body--%>
    <%@include file="../WEB-INF/jspf/CusFooter.jspf" %>