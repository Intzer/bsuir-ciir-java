<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="card">
    <div class="card-body">
        <div>
            <h1 class="h3">Motorcycle Rent</h1>
            <p>We offer a wide variety of motorcycles for rent, catering to different riding styles and preferences. Whether you're looking for a powerful cruiser for a long scenic ride, a nimble sportbike for an adrenaline rush, or a comfortable touring bike for extended trips, we have the perfect option for you. Our fleet includes models from top manufacturers, ensuring reliability, performance, and safety.</p>
            <p>Each motorcycle is well-maintained and regularly serviced to provide a smooth and enjoyable riding experience. We also offer flexible rental options, whether you need a bike for a few hours, a full day, or an extended adventure. Our team is here to assist you in choosing the ideal motorcycle and ensuring you have all the necessary gear for a safe and memorable ride.</p>
        </div>
        <div>
            <h3 class="h3">Our motorcycles</h3>
            <div class="row">
                <c:forEach var="moto" items="${motorcycles}">
                    <div class="col-md-6 col-lg-4 col-xl-3">
                        <div class="card mb-3">
                            <div class="card-header">
                                ${moto.type.name}
                            </div>
                            <div class="card-body">
                                <div class="ratio ratio-16x9">
                                    <img src="https://www.youtube.com/embed/zpOULjyy-n8?rel=0" title="YouTube video" allowfullscreen></img>
                                </div>
                                <p class="mb-0">Engine: ${moto.type.engineVolume} cm</p>
                                <p class="mb-0">Max Speed: ${moto.type.maxSpeed} km/hr</p>
                                <p class="mb-0">Fuel Rate: ${moto.type.fuelRate} l/100km</p>
                                <p class="mb-0">Color: ${moto.color.name}</p>
                                <p class="mb-0">Vin: ${moto.vin}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>