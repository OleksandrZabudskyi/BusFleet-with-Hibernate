<%@ include file="tags.jsp" %>
<nav class="navbar navbar-default mega-nav navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <span class="navbar-brand" style="color:#c1e2b3"><fmt:message key="header"/></span>
            </span>
        </div>
        <div class="collapse navbar-collapse" id="MainMenu">
            <ul class="nav navbar-nav menu-list">
                <li><a href="${contextPath}/bus-fleet/index_page"><fmt:message key="home"/></a></li>
                <li><a href="${contextPath}/bus-fleet/contacts_page"><fmt:message key="contacts"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.role eq 'ADMIN' or sessionScope.role eq 'DRIVER'}">
                        <li><a href="${pageContext.request.contextPath}/bus-fleet/logout"><span class="glyphicon glyphicon-log-out">
                        </span> <fmt:message key="logout"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/bus-fleet/login_page"><span class="glyphicon glyphicon-log-in">
                        </span> <fmt:message key="label.login"/></a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav-item">
                    <form action="bus-fleet/language">
                        <select class="nav-select" id="language" name="language" onchange="submit()" title="Language">
                            <option value="en" ${language == 'en' ? 'selected' : ''}><fmt:message key="language.en"/></option>
                            <option value="ua" ${language == 'ua' ? 'selected' : ''}><fmt:message key="language.ua"/></option>
                        </select>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>
