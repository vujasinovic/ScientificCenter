import {Component, default as React} from "react";
import axios from "axios";
import FormGroup from "reactstrap/lib/FormGroup";
import Label from "reactstrap/lib/Label";
import Input from "reactstrap/lib/Input";
import {Form} from "reactstrap";
import Button from "reactstrap/lib/Button";
import axiosInstance from "./axiosInstance";

class Login extends Component {
    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    state = {
        isLoading: true,
    };

    async handleSubmit(event) {
        event.preventDefault();

        const requestData = new FormData(event.target);

        let {data} = await axiosInstance.post('/user/api/auth', requestData);

        if (data.authenticated === true) {
            window.location = "/";
        } else {
            window.location = "/loginFailed"
        }

        console.log(data);

    }

    render() {
        const {isLoading} = this.state;

        return (
            <div className="container-fluid">
                <div className="col-md-4">
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="username">Username</Label>
                            <Input type="text" id="username" name="username"/>
                        </FormGroup>
                        <FormGroup>
                            <Label for="password">Password</Label>
                            <Input type="text" id="password" name="password"/>
                        </FormGroup>
                        <Button color="primary">Submit</Button>
                    </Form>
                </div>
            </div>
        )
    }

}

export default Login