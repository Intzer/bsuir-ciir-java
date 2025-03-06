import axios from 'axios';

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
            localStorage.setItem('isLogged', "false")
            window.location.href = '/auth'; // Если пользователь не авторизован, редиректим на страницу логина
        }

        // Возвращаем ошибку, чтобы она была доступна в компоненте
        return Promise.reject(error);
    }
);

export default httpClient;
