<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="card">
    <div class="card-body">
        <div>
            <h3>Rent motorcycles</h3>
            <div class="row">
                <c:forEach var="moto" items="${motorcycles}">
                    <div class="col-md-6 col-lg-4 col-xl-3">
                        <div class="card mb-3">
                            <div class="card-header">
                                    ${moto.type.name}
                            </div>
                            <div class="card-body">
                                <div class="ratio ratio-16x9">
                                    <img src="${moto.type.image}">
                                </div>
                                <p class="mb-0">Engine: ${moto.type.engineVolume} cm</p>
                                <p class="mb-0">Max Speed: ${moto.type.maxSpeed} km/hr</p>
                                <p class="mb-0">Fuel Rate: ${moto.type.fuelRate} l/100km</p>
                                <p class="mb-0">Color: ${moto.color.name}</p>
                                <p class="mb-0">Vin: ${moto.vin}</p>

                                <form method="post" action="/rent">
                                    <input type="hidden" name="motorcycleId" value="${moto.id}">
                                    <button type="submit" class="w-100 btn-success">Rent</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>