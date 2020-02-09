import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavItem,
    NavLink,
    UncontrolledDropdown
} from "reactstrap";
import Notfound from "./notFound";
import Home from "./home/home";
import Login from "./login";
import LoginFailed from "./loginFailed";
import Activated from "./home/activated";
import UserTask from "./user_task/userTask";
import Button from "reactstrap/lib/Button";
import AuthService from "./authService";
import TextReview from "./text_review/textReview";
import ProcessFinished from "./processFinished";
import {
    FaHome,
    FaRegIdCard,
    FaRegPaperPlane,
    GiMaterialsScience,
    GiPlayButton,
    IoMdLogIn,
    IoMdLogOut
} from "react-icons/all";

const routing = (
    <Router>
        <div>
            <Navbar color="light" light expand="md">
                <NavbarBrand href="/"><GiMaterialsScience/> Scientific Center</NavbarBrand>
                <Nav className="mr-auto" navbar>
                    <NavItem>
                        {AuthService.getUserInfo() && <NavLink href="/"><FaHome/> Home</NavLink>}
                    </NavItem>
                    {AuthService.getUserInfo() &&
                    <UncontrolledDropdown nav inNavbar>
                        <DropdownToggle nav caret>
                             Process
                        </DropdownToggle>
                        <DropdownMenu right>
                            <DropdownItem>
                                <NavLink href="/textReview"><FaRegPaperPlane/> Text review </NavLink>
                            </DropdownItem>
                        </DropdownMenu>
                    </UncontrolledDropdown>
                    }
                    <NavItem>
                        {AuthService.getUserInfo() && <NavLink href="/myTasks"><FaRegIdCard/> My tasks </NavLink>}
                    </NavItem>
                </Nav>
                <Nav className="ml-auto">
                    <NavItem>
                        {!AuthService.getUserInfo() &&
                        <NavLink href="/login">
                            <Button className="btn btn-sm btn-primary" onClick={AuthService.login}><IoMdLogIn/> Login</Button>
                        </NavLink>}
                    </NavItem>
                    <NavItem>
                        <NavLink href="/login">
                            {AuthService.getUserInfo() &&
                            <Button className="btn btn-sm btn-danger" onClick={AuthService.logOut}><IoMdLogOut/> Logout</Button>}
                        </NavLink>
                    </NavItem>
                </Nav>
            </Navbar>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/textReview/:id" component={TextReview}/>
                <Route path="/textReview" component={TextReview}/>
                <Route path="/login" component={Login}/>
                <Route path="/loginFailed" component={LoginFailed}/>
                <Route path="/activate/:id" component={Activated}/>
                <Route path="/myTasks" component={UserTask}/>
                <Route path="/finished" component={ProcessFinished}/>
                <Route component={Notfound}/>
            </Switch>
        </div>
    </Router>
);

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
