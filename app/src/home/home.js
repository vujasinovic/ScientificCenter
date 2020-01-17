import * as React from "react";
import {Link} from "react-router-dom";
import axiosInstance from "../axiosInstance";

class Home extends React.Component {
    state = {
        isLoading: true,
        taskFormFields: {
            processInstanceId: String,
            taskId: String,
            formFields: []
        }
    };

    async componentDidMount() {
        let url = '/user/api/auth';

        const response = await axiosInstance.get(url);

        const body = response.data;

        console.log(body);
    }

    render() {
        return (
            <div className="mt-3 container-fluid">
                <h1>Welcome to scientific center</h1>
                <h3>Functions available: </h3>
                <Link color="primary" className="btn btn-primary mr-3" to="/createMagazine">Create magazine</Link>
                <Link color="primary" className="btn btn-primary" to="/userRegistration">User registration</Link>
            </div>
        )
    }
}

export default Home