<%@ include file="../shared/tags.jsp" %>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/navigation.jsp"/>
<div class="container">
    <form method="POST" action="${contextPath}/bus-fleet/login" class="form-signin">
        <h2 class="form-heading"><fmt:message key="label.login"/></h2>
        <span class="text-danger">${infoMessage}</span>
        <div class="form-group">
            <input name="email" type="text" class="form-control" placeholder="<fmt:message key="email"/>"
                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" required/>
            <span class="text-danger">${emailError}</span>
            <input name="password" type="password" class="form-control" placeholder="<fmt:message key="password"/>"
                   pattern="[-0-9A-Za-z]{5,16}" title="Should have size 5-16 letters or numbers" required/>
            <span class="text-danger">${passwordError}</span>
            <span class="text-danger">${errorMessage}</span>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="label.login"/></button>
        </div>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>

