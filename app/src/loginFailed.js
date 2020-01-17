import {Component, default as React} from "react";

class LoginFailed extends Component {

    render() {
        return <h1 className="text-danger">Login failed. <a href="/login">Try again!</a></h1>
    }

}

export default LoginFailed