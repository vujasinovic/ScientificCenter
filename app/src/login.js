import React, {useState} from 'react';
import AuthService from "./authService";
import Form from "reactstrap/es/Form";
import FormGroup from "reactstrap/es/FormGroup";
import Container from "reactstrap/es/Container";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import FormText from "reactstrap/es/FormText";

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
        <Row>
            <Col xs="6" sm="4"></Col>
            <Col xs="6" sm="4">
                <Container className="border p-3 mt-4">
                    <h5 className="text-center">Please, log in</h5>
                    <Form method="POST" onSubmit={e => {
                        e.preventDefault();
                        performLogin()
                    }}>
                        <FormGroup>
                            <label>User</label>
                            <input className="form-control" onChange={e => setUsername(e.target.value)} type="text"
                                   name="user"
                                   placeholder="User"/>
                        </FormGroup>
                        <FormGroup>
                            <label>Password</label>
                            <input className="form-control" onChange={e => setPassword(e.target.value)} type="password"
                                   name="password"
                                   placeholder="Password"/>
                        </FormGroup>
                        <FormGroup>
                            <button onClick={() => performLogin()} className="btn btn-primary"
                                    type="submit">
                                Login
                            </button>
                        </FormGroup>
                    </Form>
                </Container>
            </Col>
            <Col xs="6" sm="4"></Col>
        </Row>
    );
};

export default Login;