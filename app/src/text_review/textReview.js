import {Component, default as React} from "react";
import axios from "axios";
import Form from "reactstrap/lib/Form";
import FormGroup from "reactstrap/lib/FormGroup";
import Label from "reactstrap/lib/Label";
import Button from "reactstrap/lib/Button";
import {inputType} from "../const/inputType";
import Input from "reactstrap/lib/Input";
import axiosInstance from "../axiosInstance";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import Container from "reactstrap/es/Container";

class TextReview extends Component {
    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    generate(formFields) {
        let retVal;

        retVal =
            <Row>
                <Col xs="6" sm="4"></Col>
                <Col xs="6" sm="4">
                    <Container className="border p-3 mt-4">
                        <Form onSubmit={this.handleSubmit}>
                            {formFields.map(formField =>
                                <FormGroup key={formField.id}>
                                    <Label for={formField.id}>{formField.label}</Label>
                                    {this.generateInputField(formField)}
                                </FormGroup>
                            )}
                            <Button color="primary">Submit</Button>
                        </Form>
                    </Container>
                </Col>
                <Col xs="6" sm="4"></Col>
            </Row>;

        return retVal;
    }

    generateInputField(formField) {
        let retVal;

        const typeName = formField.type.name;

        console.log(typeName);

        const id = formField.id;

        if (formField.availableValues) {
            let options = Object.values(formField.availableValues);

            retVal = <Input type="select" name={id} id={id}>
                {options.map(option => <option key={option}>{option}</option>)}
            </Input>;
        } else if (typeName === inputType.STRING) {
            retVal = <Input type="text" name={id} id={id}/>;
        } else if (typeName === inputType.DATE) {
            retVal = <Input type="date" name={id} id={id}/>;
        } else if (typeName === inputType.LONG) {
            retVal = <Input type="number" name={id} id={id}/>;
        } else if (typeName === inputType.BOOL) {
            retVal = <Input className="ml-2" type="checkbox" name={id} id={id}/>
        } else if (typeName === inputType.ENUM) {
            let options = Object.values(formField.type.values);

            retVal = <Input type="select" name={id} id={id}>
                {options.map(option => <option key={option}>{option}</option>)}
            </Input>;
        } else {
            retVal = <Input type="text" name={id} id={id}/>;
        }

        return retVal;
    }

    state = {
        isLoading: true,
        me: String,
        taskFormFields: {
            processInstanceId: String,
            taskId: String,
            formFields: []
        }
    };

    async handleSubmit(event) {
        event.preventDefault();

        const requestData = new FormData(event.target);

        let {data} = await axios.post('/api/textReview/' + this.state.taskFormFields.taskId, requestData);

        if (data === "") {
            window.location = "/";
        } else if (data.assignee !== this.state.me) {
            window.location = "/";
        } else {
            window.location = '/textReview/' + data.id;
        }
    }

    async componentDidMount() {
        const authResponse = await axiosInstance.get('/user/api/auth');
        const user = authResponse.data;

        console.log(user.username);
        this.setState({me: user.username});

        let url = '/api/textReview';
        let taskId = this.props.match.params.id;

        if (taskId !== undefined) {
            url = url + "/" + taskId;
            console.log(url);
        }

        const response = await axiosInstance.get(url);

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
                    {this.generate(taskFormFields.formFields)}
                </div>
            )
        }
    }

}

export default TextReview