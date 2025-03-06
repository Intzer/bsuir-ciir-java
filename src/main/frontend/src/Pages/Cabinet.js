import {useEffect, useState} from "react";
import httpClient from "../httpClient";
import { useNavigate } from "react-router-dom";
import Header from "../components/Header.js";
import {useAuth} from "../AuthContext";

const Cabinet = () => {

    // Состояние для хранения данных о мотоциклах
    const [phone, setPhone] = useState("");
    const [balance, setBalance] = useState("");

    // Эффект для загрузки данных о мотоциклах с API
    useEffect(() => {
        // Функция для получения мотоциклов
        const fetchInfo = async () => {
            const response = await httpClient.get('/info'); // Замените URL на ваш API
            console.log(response.data);

            setPhone(response.data.phone);
            setBalance(response.data.balance);
        };

        fetchInfo(); // Вызов функции при монтировании компонента
    }, []); // Пустой массив зависимостей означает, что запрос будет выполнен один раз при монтировании

    const deposit = async(e) => {
        let amount = Number(prompt('Введите сумму пополнения: ', ''));
        if (!amount) {
            return;
        }

        const formData = new URLSearchParams();
        formData.append("amount", amount); // Кодируем данные

        const response = await httpClient.post("/deposit", formData, {
            headers: { "Content-Type": "application/x-www-form-urlencoded" }
        });

        alert(response.data.message);
        setBalance(balance + amount);
    };

    return (
        <div class="card">
            <div class="card-body">
                <div>
                    <h3>Cabinet</h3>
                    <div>
                        Balance: {balance}
                        <div class="badge bg-success" onClick={deposit} style={{ cursor: 'pointer' }}>deposit</div><br />
                        Your phone: {phone}<br />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Cabinet;
