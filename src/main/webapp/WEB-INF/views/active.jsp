<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card">
    <div class="card-body">
        <div>
            <h3>Your rent motorcycles</h3>
            <div class="row">
                <c:choose>
                    <c:when test="${not empty rentals}">
                        <c:forEach var="rental" items="${rentals}">
                            <div class="col-md-6 col-lg-4 col-xl-3">
                                <div class="card mb-3">
                                    <div class="card-header">
                                            ${rental.motorcycle.type.name} (${rental.motorcycle.color.name})
                                    </div>
                                    <div class="card-body">
                                        <div class="ratio ratio-16x9">
                                            <img src="${rental.motorcycle.type.image}">
                                        </div>
                                        <p class="mb-0">Start: ${rental.createdAt}</p>
                                        <p class="mb-0">Expired: ${rental.expiredAt}</p>
                                        <p class="mb-0">Vin: ${rental.motorcycle.vin}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-12">
                            <div class="alert alert-info">
                                No motorcycles are rent.
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>