import axios from 'axios';
import {useNavigate} from 'react-router-dom';

// Функция для обработки навигации
const useAuthRedirect = () => {
    const navigate = useNavigate();

    // Обрабатываем редирект на страницу авторизации
    return () => {
        navigate('/auth');
    };
};

const httpClient = axios.create();

// Интерсептор для перехвата всех запросов
httpClient.interceptors.response.use(
    (response) => {
        // Возвращаем успешный ответ
        return response;
    },
    (error) => {
        // Перехватываем ошибку, если ответ с кодом 401
        if (error.response && error.response.status === 401) {
            const redirectToAuth = useAuthRedirect();
            // Перенаправляем на страницу авторизации
            redirectToAuth(); // Вызываем редирект
        }

        // Возвращаем ошибку, чтобы она была доступна в компоненте
        return Promise.reject(error);
    }
);

export default httpClient;
