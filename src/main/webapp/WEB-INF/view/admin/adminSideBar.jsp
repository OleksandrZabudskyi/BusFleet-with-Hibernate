<%@ include file="../shared/tags.jsp" %>
<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
    <p class="visible-xs">
        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i
                class="glyphicon glyphicon-chevron-left"></i></button>
    </p>
    <div class="well sidebar-nav">
        <ul class="nav">
            <li><fmt:message key ="admin.page"/></li>
            <li><a></a></li>
            <li><fmt:message key ="trips"/></li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/trips_and_routes"><fmt:message key ="all.trips"/></a></li>
            <li><fmt:message key ="buses.and.drivers"/></li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/all_buses"><fmt:message key ="all.buses"/></a>
            </li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/all_drivers"><fmt:message key ="all.drivers"/></a></li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/buses_with_drivers"><fmt:message key ="all.buses.drivers"/></a>
            </li>
            <li><fmt:message key ="profile"/></li>
            <li><a href="${pageContext.request.contextPath}/bus-fleet/admin/user_info"><fmt:message key ="info"/></a></li>
        </ul>
    </div>
</div>