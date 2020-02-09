import * as React from "react";
import axiosInstance from "../axiosInstance";
import Col from "reactstrap/es/Col";
import Container from "reactstrap/es/Container";
import {Link} from "react-router-dom";
import Row from "reactstrap/es/Row";
import {FaPlayCircle, FaRegIdCard} from "react-icons/all";

class Home extends React.Component {
    state = {
        isLoading: true,
        username: String,
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
        this.setState({username : body.username});
    }

    render() {
        return (
            <div className="mt-3 container-fluid text-center">
                <h3>Hello {this.state.username}! Welcome to scientific center</h3>
                <Row>
                    <Col xs="6" sm="4"></Col>
                    <Col xs="6" sm="4">
                        <Container className="border p-3 mt-4 text-center">
                            <h5><a href="/textReview">Start text review process <FaPlayCircle/></a></h5>
                            <hr/>
                            <h5><a href="/myTasks">Tasks assigned to me <FaRegIdCard/></a></h5>
                        </Container>
                    </Col>
                    <Col xs="6" sm="4"></Col>
                </Row>
            </div>
        )
    }
}

export default Home