<%@ include file="tags.jsp" %>
<div class="container">
    <div class="row my-4">
        <div class="col-lg-8">
            <img class="img-fluid rounded" src="${contextPath}/resources/img/bus-depot.jpg" alt="">
        </div>
        <div class="col-lg-4">
            <h1>Comfort Busfleet</h1>
            <p><fmt:message key="service.description"/>.</p>
            <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/bus-fleet/reg_page"><fmt:message key="driver.registration"/></a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title"><fmt:message key="trip.service"/></h2>
                    <img class="img-fluid rounded" src="${contextPath}/resources/img/medium-bus.jpg" alt="">
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary"><fmt:message key ="more.info"/></a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title"><fmt:message key="luxury.service"/></h2>
                    <img class="img-fluid rounded" src="${contextPath}/resources/img/luxury-bus.png" alt="">
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary"><fmt:message key ="more.info"/></a>
                </div>
            </div>
        </div>
        <div class="col-md-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h2 class="card-title"><fmt:message key="route.service"/></h2>
                    <img class="img-fluid rounded" src="${contextPath}/resources/img/route-bus.jpg" alt="">
                </div>
                <div class="card-footer">
                    <a href="#" class="btn btn-primary"><fmt:message key ="more.info"/></a>
                </div>
            </div>
        </div>
    </div>
</div>
