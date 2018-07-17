<%@ include file="../shared/tags.jsp" %>
<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    <p class="visible-xs">
        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i
                class="glyphicon glyphicon-chevron-left"></i></button>
    </p>
    <div class="well sidebar-nav">
        <ul class="nav">
            <li><fmt:message key="driver.page"/></li>
            <li><a></a></li>
            <li><fmt:message key="trip.appointment"/></li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/driver/get_appointment"><fmt:message key="driver.appointment"/></a></li>
            <li>Profile</li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/driver/user_info"><fmt:message key="info"/></a></li>
        </ul>
    </div>
</div>
