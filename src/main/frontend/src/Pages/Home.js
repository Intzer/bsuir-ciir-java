import {useEffect, useState} from "react";
import httpClient from "../httpClient"

const Home = () => {
    // Состояние для хранения данных о мотоциклах
    const [motorcycles, setMotorcycles] = useState([]);

    // Эффект для загрузки данных о мотоциклах с API
    useEffect(() => {
        // Функция для получения мотоциклов
        const fetchMotorcycles = async () => {
            try {
                const response = await httpClient.get('http://127.0.0.1:8080/api/motorcycles'); // Замените URL на ваш API
                setMotorcycles(response.data); // Сохраняем полученные данные в состоянии
            } catch (error) {
                console.error('Error fetching motorcycles:', error);
            }
        };

        fetchMotorcycles(); // Вызов функции при монтировании компонента
    }, []); // Пустой массив зависимостей означает, что запрос будет выполнен один раз при монтировании

    return (
        <div className="card">
            <div className="card-body">
                <div>
                    <h1 className="h3">Motorcycle Rent</h1>
                    <p>We offer a wide variety of motorcycles for rent, catering to different riding styles and preferences. Whether you're looking for a powerful cruiser for a long scenic ride, a nimble sportbike for an adrenaline rush, or a comfortable touring bike for extended trips, we have the perfect option for you.</p>
                    <p>Each motorcycle is well-maintained and regularly serviced to provide a smooth and enjoyable riding experience. We also offer flexible rental options, whether you need a bike for a few hours, a full day, or an extended adventure.</p>
                </div>
                <div>
                    <h3 className="h3">Our motorcycles</h3>
                    <div className="row">
                        {motorcycles.map((moto) => (
                            <div class="col-md-6 col-lg-4 col-xl-3">
                                <div class="card mb-3">
                                    <div class="card-header">
                                        {moto.type.name}
                                    </div>
                                    <div class="card-body">
                                        <div class="ratio ratio-16x9">
                                            <img src={moto.type.image} />
                                        </div>
                                        <p class="mb-0">Engine: {moto.type.engineVolume} cm</p>
                                        <p class="mb-0">Max Speed: {moto.type.maxSpeed} km/hr</p>
                                        <p class="mb-0">Fuel Rate: {moto.type.fuelRate} l/100km</p>
                                        <p class="mb-0">Color: {moto.color.name}</p>
                                        <p class="mb-0">Vin: {moto.vin}</p>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Home;
