<%@ include file="../shared/tags.jsp" %>
<div class="row">
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="licence.number"/></th>
                <th><fmt:message key="first.name"/></th>
                <th><fmt:message key="last.name"/></th>
                <th><fmt:message key="skill.year"/></th>
                <th><fmt:message key="to.trip"/></th>
            </tr>
            </thead>
            <tbody id="allBuses">
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>${driver.drivingLicenceNumber}</td>
                    <td>${driver.firstName}</td>
                    <td>${driver.lastName}</td>
                    <td>${driver.drivingExperience}</td>
                    <c:choose>
                        <c:when test="${driver.assigned == false}">
                            <form method="POST" name="add"
                                  action="${pageContext.request.contextPath}/bus-fleet/admin/set_driver?page=${page}">
                                <input type="hidden" name="tripId" value="${tripId}"/>
                                <input type="hidden" name="driverId" value="${driver.id}"/>
                                <td>
                                    <button type="submit" class="btn btn-primary btn-sm"><fmt:message
                                            key="add"/></button>
                                </td>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>