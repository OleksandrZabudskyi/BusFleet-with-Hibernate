<%@ include file="../shared/tags.jsp" %>
<jsp:include page="../shared/header.jsp"/>
<jsp:include page="../shared/navigation.jsp"/>
<div class="container">
    <form method="POST" name="registrationForm" class="form-signin" action="${contextPath}/bus-fleet/registration">
        <h2 class="form-signin-heading"><fmt:message key="driver.registration"/></h2>
        <span class="text-danger">${infoMessage}</span>
        <div class="form-group">
            <input type="text" class="form-control" name="firstName" placeholder="<fmt:message key="first.name"/>"
                    title="Name should start from capital latter" required/>
            <span class="text-danger">${firstNameError}</span>
            <input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="last.name"/>"
                    title="Name should start from capital latter" required/>
            <span class="text-danger">${lastNameError}</span>
            <input type="text" class="form-control" name="email" placeholder="<fmt:message key="email"/>"
                   pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" required/>
            <span class="text-danger">${emailError}</span>
            <input type="text" class="form-control" name="phoneNumber"
                   placeholder="<fmt:message key="phone.number"/>+38(044)234-44-33"
                   pattern="^\+\d{2}\(\d{3}\)\d{3}-\d{2}-\d{2}$" title="ex.+38(044)234-44-33" required />
            <span class="text-danger">${phoneNumberError}</span>
            <input type="text" class="form-control" name="drivingLicenceNumber" placeholder="<fmt:message key="driving.licence"/>"
                    title="Symbols and numbers" required/>
            <span class="text-danger">${drivingLicenceNumberError}</span>
            <input type="text" class="form-control" name="drivingExperience" placeholder="<fmt:message key="driving.experience"/>"
                   pattern="^[1-9]\d*$" title="number" required/>
            <span class="text-danger">${drivingExperienceError}</span>
            <input type="password" class="form-control" name="password" placeholder="<fmt:message key="password"/>"
                   pattern="[-0-9A-Za-z]{5,16}" title="Should have size 5-16 letters or numbers" required/>
            <span class="text-danger">${passwordError}</span>
            <span class="text-danger">${errorMessage}</span>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="registration.submit"/></button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
