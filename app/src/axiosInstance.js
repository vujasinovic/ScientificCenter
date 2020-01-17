import AuthService from "./authService";
import axios from "axios"

const isLoggedIn = AuthService.getRoles() != null;

const axiosInstance = axios.create({
});

const isHandlerEnabled = (config = {}) => {
    return config.hasOwnProperty('handlerEnabled') && !config.handlerEnabled ? false : true
};

const requestHandler = (request) => {
    if (isHandlerEnabled(request)) {
        console.log(AuthService.getUserInfo());

        request.headers['Authorization'] = AuthService.getAuthorization();

    }
    return request
};

axiosInstance.interceptors.request.use(
    request => requestHandler(request)
);
axiosInstance.interceptors.response.use(response => response);

export default axiosInstance;