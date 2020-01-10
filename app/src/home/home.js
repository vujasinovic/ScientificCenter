import * as React from "react";
import Button from "reactstrap/lib/Button";

class Home extends React.Component {
    render() {
        return (
            <div className="mt-3 container-fluid">
                <h1>Welcome to scientific center</h1>
                <h3>Functions available: </h3>
                <Button color="primary" className="mr-3">Create magazine</Button>
                <Button color="primary">User registration</Button>
            </div>
        )
    }
}

export default Home