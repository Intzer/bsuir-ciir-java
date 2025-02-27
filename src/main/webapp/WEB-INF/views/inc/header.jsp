<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/" class="nav-link px-2 text-white">Home</a></li>
            </ul>

            <div class="text-end">
                <c:if test="${not empty sessionScope.isLogged and sessionScope.isLogged == true}">
                    <a href="/cabinet" class="btn btn-light">Cabinet</a>
                    <a href="/logout" class="btn btn-danger">Log out</a>
                </c:if>

                <c:if test="${empty sessionScope.isLogged or sessionScope.isLogged == false}">
                    <a href="/auth" class="btn btn-light">Sign in</a>
                </c:if>
            </div>
        </div>
    </div>
</header>