<%@ include file="view/shared/tags.jsp" %>
<jsp:include page="view/shared/header.jsp"/>
<jsp:include page="view/shared/navigation.jsp"/>
<%@ page isErrorPage="true" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>
    Error Page<br/>
    <div class="alert alert-danger" style="height: 200px; width: 100%">Error: <%=exception.getMessage()%></div>
    </span>
</h2>
<br>
<a class="btn btn-primary"
       href="${pageContext.request.contextPath}/bus-fleet/index_page"
       role="button">Index Page</a>
</body>
</html>