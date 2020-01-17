import React, {useState} from 'react';
import AuthService from "./authService";

const Login = () => {
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [error, setError] = useState();


    const performLogin = () => {
        setError(undefined);

        AuthService.login({username, password})
            .then(response => {
                localStorage.setItem("userInfo", JSON.stringify(response.data));
                window.location.href = '/';
            }).catch(e => {
            setError("Bad credentials");
        })

    };

    return (
        <div className="container-fluid">
            <div className="container">
                <div className="nine wide column">
                    {error &&
                    <div className="ui icon warning message">
                        <i className="lock icon"></i>
                        <div className="content">
                            <div className="header">
                                Login failed!
                            </div>
                            <p>{error}</p>
                        </div>
                    </div>
                    }
                    <div className="ui fluid card">
                        <div className="content">
                            <form className="form" method="POST" onSubmit={e => {
                                e.preventDefault();
                                performLogin()
                            }}>
                                <div className="form-group">
                                    <label>User</label>
                                    <input className="form-control" onChange={e => setUsername(e.target.value)} type="text" name="user"
                                           placeholder="User"/>
                                </div>
                                <div className="form-group">
                                    <label>Password</label>
                                    <input className="form-control" onChange={e => setPassword(e.target.value)} type="password" name="password"
                                           placeholder="Password"/>
                                </div>
                                <button onClick={() => performLogin()} className="btn btn-primary"
                                        type="submit">
                                    <i className="unlock alternate icon"></i>
                                    Login
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;