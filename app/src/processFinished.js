import React from "react";
import Row from "reactstrap/es/Row";
import Col from "reactstrap/es/Col";
import Container from "reactstrap/es/Container";
import {FaCheck} from "react-icons/all";

const ProcessFinished = () => <Row>
    <Col xs="6" sm="4"></Col>
    <Col xs="6" sm="4">
        <Container className="border p-3 mt-4 text-center">
            <FaCheck className="text-success"/><h3>Process finished</h3>
        </Container>
    </Col>
    <Col xs="6" sm="4"></Col>
</Row>;

export default ProcessFinished