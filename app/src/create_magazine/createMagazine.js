import {Component, default as React} from "react";
import axios from "axios";
import {generate} from "../formGenerator";

class CreateMagazine extends Component {
    state = {
        isLoading: true,
        taskFormFields: {
            processInstanceId: String,
            taskId: String,
            formFields: []
        }
    };

    async componentDidMount() {
        const response = await axios.get('/api/magazine');
        const body = response.data;

        this.setState({isLoading: false, taskFormFields: body});
    }

    render() {
        const {taskFormFields, isLoading} = this.state;

        if (isLoading) {
            return <h3>Loading...</h3>
        } else {
            return (
                <div className="container-fluid">
                    <h5>Process instance id: {taskFormFields.processInstanceId}</h5>
                    <h5>Task id: {taskFormFields.taskId}</h5>
                    {generate(taskFormFields.formFields)}
                </div>
            )
        }
    }

}

export default CreateMagazine