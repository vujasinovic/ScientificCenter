import * as React from "react";
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
            </div>
        )
    }
}

export default Home