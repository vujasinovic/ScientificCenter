import JwtDecode from 'jwt-decode';
import axiosInstance from "./axiosInstance";

export class AuthService {

    login(credentials) {
        return axiosInstance.post("/user/api/auth", credentials);
    }

    getUserInfo() {
        return JSON.parse(localStorage.getItem("userInfo"));
    }

    getAuthHeader() {
        return {headers: {Authorization: 'Bearer ' + this.getUserInfo().token}};
    }

    getAuthorization() {
        if (this.getUserInfo()) {
            return 'Bearer ' + this.getUserInfo().token;
        } else {
            return "";
        }
    }

    logOut() {
        localStorage.removeItem("userInfo");
    }

    getRoles() {
        const userInfo = this.getUserInfo();
        if (!userInfo || !userInfo.token) {
            return null;
        }

        const jwt = JwtDecode(userInfo.token);
        return jwt.auth;
    }

    hasRole(role) {
        const roles = this.getRoles();

        if (!this.getRoles())
            return false;

        return roles.indexOf(role) !== -1;
    }

}

export default new AuthService();