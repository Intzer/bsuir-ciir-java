import { useState } from "react";
import httpClient from "../httpClient";
import { useNavigate } from "react-router-dom";
import Header from "../components/Header.js";
import {useAuth} from "../AuthContext";

const Code = () => {
    const [code, setCode] = useState("");
    const navigate = useNavigate(); // Используем хук для навигации
    const { setIsLogged } = useAuth();


    const handleEnter = async (e) => {
        const formData = new URLSearchParams();
        formData.append("code", code); // Кодируем данные
        formData.append("action", "enter"); // Кодируем данные

        const response = await httpClient.post("/code", formData, {
            headers: { "Content-Type": "application/x-www-form-urlencoded" }
        });

        if (response.data.status === 1) {
            localStorage.setItem("isLogged", "true");
            setIsLogged(true);
            navigate('/cabinet');
        } else {
            alert(response.data.message);
        }
    };

    const handleResend = async (e) => {
        const formData = new URLSearchParams();
        formData.append("code", ""); // Кодируем данные
        formData.append("action", "resend"); // Кодируем данные

        const response = await httpClient.post("/code", formData, {
            headers: { "Content-Type": "application/x-www-form-urlencoded" }
        });

        alert(response.data.message);
    };

    return (
        <div class="row">
            <div class="col-lg-6 col-xl-4 offset-lg-3 offset-xl-4">
                <div class="card">
                    <div class="card-body">
                        <div>
                            <h1 class="h3">Enter code from sms message (valid for 5 min)</h1>
                            <form>
                                <div class="mb-3">
                                    <label for="code">Code:</label>
                                    <input type="text" class="form-control" onChange={(e) => setCode(e.target.value)} placeholder="000000" />
                                </div>
                                <button type="button" class="btn btn-outline-success me-2" onClick={handleEnter}>Enter</button>
                                <button type="button" class="btn btn-outline-primary" onClick={handleResend}>Resend Code</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Code;
