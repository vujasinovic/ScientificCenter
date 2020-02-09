import * as React from "react";
import {Link} from "react-router-dom";
import axiosInstance from "../axiosInstance";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import Container from "reactstrap/es/Container";

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
        const {tasks} = this.state;

        return (
            <Row>
                <Col xs="6" sm="4"></Col>
                <Col xs="6" sm="4">
                    <Container className="border p-3 mt-4">
                        <h5>Tasks</h5>
                        <h5>{tasks.map(task =>
                            <Link color="primary" className="btn btn-primary"
                                  to={"/textReview/" + task.id}>{task.name}</Link>
                        )}</h5>
                    </Container>
                </Col>
                <Col xs="6" sm="4"></Col>
            </Row>
        )
    }
}

export default UserTask