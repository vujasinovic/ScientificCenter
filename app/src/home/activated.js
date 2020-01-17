import * as React from "react";
import {Link} from "react-router-dom";
import axios from "axios";
import axiosInstance from "../axiosInstance";

class Activated extends React.Component {
    state = {
        isLoading: true,
        taskFormFields: {
            processInstanceId: String,
            taskId: String,
            formFields: []
        }
    };

    async componentDidMount() {
        let url = '/api/user/activation/' + this.props.match.params.id;

        const response = await axiosInstance.get(url);

        window.location = "/";
    }

    render() {
        return (
            <div className="mt-3 container-fluid">
                <h1>Activated</h1>
            </div>
        )
    }
}

export default Activated