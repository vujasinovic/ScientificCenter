import * as React from "react";
import {Link} from "react-router-dom";
import axiosInstance from "../axiosInstance";

class UserTask extends React.Component {
    state = {
        isLoading: true,
        tasks: []
    };

    async componentDidMount() {
        let url = '/user/api/task/all';

        const response = await axiosInstance.get(url);

        const body = response.data;
        this.setState({tasks: body, isLoading: false});

        console.log(body);
    }

    render() {
        const {tasks, isLoading} = this.state;

        return (
            <div className="mt-3 container-fluid">
                <h1>Tasks:</h1>
                {tasks.map(task =>
                    <Link color="primary" className="btn btn-primary" to={"/createMagazine/" + task.id}>{task.name}</Link>
                )}
            </div>
        )
    }
}

export default UserTask