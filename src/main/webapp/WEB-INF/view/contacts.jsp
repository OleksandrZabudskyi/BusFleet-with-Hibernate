<%@ include file="shared/tags.jsp" %>
<jsp:include page="shared/header.jsp"/>
<jsp:include page="shared/navigation.jsp"/>
<div class="container" align="center">
    <div class="panel panel-default" style="max-width: 80%">
        <div class="panel-heading" align="left"><h2><fmt:message key="contacts"/></h2></div>
        <div class="panel-body" align="left">
            <div class="col-sm-6" style="color:#00b1b1;">
                <p><span><fmt:message key="developer"/></span></p>
                <p><span><fmt:message key="developer.phone"/></span></p>
                <p><span><fmt:message key="developer.email"/></span></p>
            </div>
            <div class="clearfix"></div>
            <hr style="margin:5px 0 5px 0;">

            <h4><fmt:message key="project"/></h4>
            <span><fmt:message key="project.info"/></span>
        </div>
    </div>
</div>
<jsp:include page="shared/footer.jsp"/>