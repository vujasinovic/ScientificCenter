import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import * as serviceWorker from './serviceWorker';
import 'bootstrap/dist/css/bootstrap.min.css';

import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import {Nav, Navbar, NavbarBrand, NavItem, NavLink} from "reactstrap";
import Notfound from "./notFound";
import Home from "./home/home";
import CreateMagazine from "./create_magazine/createMagazine";
import UserRegistration from "./user_registration/userRegistration";
import Login from "./login";
import LoginFailed from "./loginFailed";
import Activated from "./home/activated";
import UserTask from "./user_task/userTask";
import Button from "reactstrap/lib/Button";
import AuthService from "./authService";

const routing = (
    <Router>
        <div>
            <Navbar color="light" light expand="md">
                <NavbarBrand href="/">Scientific Center</NavbarBrand>
                <Nav className="mr-auto" navbar>
                    <NavItem>
                        <NavLink href="/">Home</NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink href = "/userRegistration">User registration</NavLink>
                    </NavItem>
                    <NavItem>
                        {AuthService.getUserInfo() && <NavLink href = "/createMagazine">Create magazine</NavLink>}
                    </NavItem>
                    <NavItem>
                        {AuthService.getUserInfo() && <NavLink href = "/myTasks">My tasks </NavLink>}
                    </NavItem>
                    <NavItem>
                        {!AuthService.getUserInfo() && <NavLink href = "/login">Login</NavLink>}
                    </NavItem>
                    <NavItem>
                        {AuthService.getUserInfo() && <Button className="btn btn-primary" onClick={AuthService.logOut}>Logout</Button>}
                    </NavItem>
                </Nav>
            </Navbar>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route path="/createMagazine/:id" component={CreateMagazine}/>
                <Route path="/createMagazine" component={CreateMagazine}/>
                <Route path="/userRegistration/:id" component={UserRegistration}/>
                <Route path="/userRegistration" component={UserRegistration}/>
                <Route path="/login" component={Login}/>
                <Route path="/loginFailed" component={LoginFailed}/>
                <Route path="/activate/:id" component={Activated}/>
                <Route payh="/myTasks" component={UserTask}/>
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
