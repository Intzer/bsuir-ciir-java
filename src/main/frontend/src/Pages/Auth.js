import { useState } from "react";
import httpClient from "../httpClient";
import { useNavigate } from "react-router-dom";

const Auth = () => {
    const [phoneNumber, setPhoneNumber] = useState("");
    const navigate = useNavigate(); // Используем хук для навигации


    const handleSubmit = async (e) => {
        e.preventDefault(); // Предотвращаем перезагрузку страницы

        const formData = new URLSearchParams();
        formData.append("phoneNumber", phoneNumber); // Кодируем данные

        const response = await httpClient.post("/auth", formData, {
            headers: { "Content-Type": "application/x-www-form-urlencoded" }
        });

        alert(response.data.message);
        if (response.data.status == 1) {
            navigate("/code"); // Переход на страницу ввода кода
        }
    };

    return (
        <div className="row">
            <div className="col-lg-6 col-xl-4 offset-lg-3 offset-xl-4">
                <div className="card">
                    <div className="card-body">
                        <h1 className="h3">Sign in</h1>
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="phoneNumber">Phone number:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="phoneNumber"
                                    value={phoneNumber}
                                    onChange={(e) => setPhoneNumber(e.target.value)}
                                    placeholder="+37529......."
                                />
                            </div>
                            <button type="submit" className="btn btn-outline-success">Sign In</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Auth;
