import { format } from 'date-fns';
import {useEffect, useState} from "react";
import httpClient from "../httpClient";
import {useNavigate} from "react-router-dom";

const Rent = () => {
    // Состояние для хранения данных о мотоциклах
    const [motorcycles, setMotorcycles] = useState([]);
    const [rentalDurations, setRentalDurations] = useState([]);
    const [rentalDurationId, setRentalDurationId] = useState("");
    const [selectedMotoId, setSelectedMotoId] = useState("");
    const navigate = useNavigate(); // Используем хук для навигации

    // Эффект для загрузки данных о мотоциклах с API
    useEffect(() => {
        // Функция для получения мотоциклов
        const fetchMotorcycles = async () => {
            const response = await httpClient.get('/api/freemotorcycles'); // Замените URL на ваш API
            setMotorcycles(response.data); // Сохраняем полученные данные в состоянии
        };

        const fetchRentalDurations = async () => {
            const response = await httpClient.get('/api/rentaldurations'); // Замените URL на ваш API
            setRentalDurations(response.data); // Сохраняем полученные данные в состоянии
            // Устанавливаем значение по умолчанию, если rentalDurations не пустой
            if (response.data.length > 0) {
                setRentalDurationId(response.data[0].id);
            }
        };

        fetchMotorcycles(); // Вызов функции при монтировании компонента
        fetchRentalDurations(); // Вызов функции при монтировании компонента
    }, []); // Пустой массив зависимостей означает, что запрос будет выполнен один раз при монтировании

    const openModal = (motoId) => {
        setSelectedMotoId(motoId);
    };

    const onSubmitPost = async(e) => {
        const formData = new URLSearchParams();
        formData.append("rentalDurationId", rentalDurationId); // Кодируем данные
        formData.append("motorcycleId", selectedMotoId); // Кодируем данные

        console.log(formData);

        const response = await httpClient.post("/rent", formData, {
            headers: { "Content-Type": "application/x-www-form-urlencoded" }
        });

        alert(response.data.message);
        if (response.data.status == 1) {
            navigate('/active');
        }
    }

    return (
        <div class="card">
            <div class="card-body">
                <div>
                    <h3>Rent motorcycles</h3>

                    <div class="row">
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
                                    <p class="mb-0">Vin: {moto.vin} {moto.id} </p>

                                    <button class="w-100 btn btn-success" onClick={() => openModal(moto.id)}>Rent</button>

                                    {moto.id === selectedMotoId && (
                                        <div>
                                            <form>
                                                <input type="hidden" formControlName="motorcycleId" />

                                                <div class="form-group">
                                                    <label>Rental Duration</label>
                                                    <select class="form-control" onChange={(e) => setRentalDurationId(e.target.value)}>
                                                        {rentalDurations.map((rental) => (
                                                        <option value={rental.id}>
                                                            {rental.days} дней - ${rental.cost}
                                                        </option>
                                                        ))}
                                                    </select>
                                                </div>

                                                <button type="button" class="btn btn-success w-100 mt-2" onClick={onSubmitPost}>Submit</button>
                                            </form>
                                        </div>
                                    )}
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

export default Rent;
