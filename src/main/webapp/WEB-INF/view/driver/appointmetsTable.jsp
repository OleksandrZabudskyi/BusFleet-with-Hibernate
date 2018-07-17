<%@ include file="../shared/tags.jsp" %>
<div class="row">
    <div class="table-responsive">

        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="route"/></th>
                <th><fmt:message key="from"/></th>
                <th><fmt:message key="to"/></th>
                <th><fmt:message key="trip"/></th>
                <th><fmt:message key="start.time"/></th>
                <th><fmt:message key="end.time"/></th>
                <th><fmt:message key="bus.model"/></th>
                <th><fmt:message key="parking.spot"/></th>
                <th><fmt:message key="confirmation"/></th>
            </tr>
            </thead>
            <tbody id="allBuses">
            <c:forEach var="trip" items="${trips}">
                <tr>
                    <td>${trip.route.name}</td>
                    <td>${trip.route.destinationFrom}</td>
                    <td>${trip.route.destinationTo}</td>
                    <td>${trip.number}</td>
                    <td>${trip.startTime}</td>
                    <td>${trip.endTime}</td>
                    <td>${trip.bus.model}</td>
                    <td>${trip.bus.parkingSpot}</td>
                    <c:choose>
                        <c:when test="${trip.confirmation == false}">
                            <form method="POST" name="confirm"
                                  action="${pageContext.request.contextPath}/bus-fleet/driver/confirm_appointment">
                                <input type="hidden" name="tripId" value="${trip.id}"/>
                                <td>
                                    <button type="submit" class="btn btn-primary btn-sm"><fmt:message
                                            key="confirm"/></button>
                                </td>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <td><fmt:message key="confirmed"/></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
