import { format } from 'date-fns';
import {useEffect, useState} from "react";
import httpClient from "../httpClient";

const Active = () => {
    // Состояние для хранения данных о мотоциклах
    const [rentals, setRentals] = useState([]);

    // Эффект для загрузки данных о мотоциклах с API
    useEffect(() => {
        // Функция для получения мотоциклов
        const fetchRentals = async () => {
            try {
                const response = await httpClient.get('/api/rentals'); // Замените URL на ваш API
                setRentals(response.data); // Сохраняем полученные данные в состоянии
            } catch (error) {
                console.error('Error fetching motorcycles:', error);
            }
        };

        fetchRentals(); // Вызов функции при монтировании компонента
    }, []); // Пустой массив зависимостей означает, что запрос будет выполнен один раз при монтировании

    return (
        <div class="card">
            <div class="card-body">
                <div>
                    <h3>Your active rents</h3>

                    <div class="row">
                        {rentals.map((rental) => (
                            <div class="col-md-6 col-lg-4 col-xl-3" key={rental.id}>
                                <div class="card mb-3">
                                    <div class="card-header">
                                        {rental.motorcycle.type.name} ({rental.motorcycle.color.name})
                                    </div>
                                    <div class="card-body">
                                        <div class="ratio ratio-16x9">
                                            <img src={rental.motorcycle.type.image} alt={rental.motorcycle.type.name} />
                                        </div>
                                        <p class="mb-0">Start: {format(new Date(rental.createdAt), 'MMMM dd, yyyy HH:mm:ss')}</p>
                                        <p class="mb-0">Expired: {format(new Date(rental.expiredAt), 'MMMM dd, yyyy HH:mm:ss')}</p>
                                        <p class="mb-0">Vin: {rental.motorcycle.vin} </p>
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

export default Active;
