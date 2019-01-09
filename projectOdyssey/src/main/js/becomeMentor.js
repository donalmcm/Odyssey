'use strict';

import {FormGroup, ControlLabel, FormControl, Button, Checkbox, Table} from "react-bootstrap";
import '../resources/templates/becomeMentor.html';

const React = require('react');
const ReactDOM = require('react-dom');
const when = require('when');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const stompClient = require('./websocket-listener');

const root = '/api';

class BecomeMentor extends React.Component {


    render(){
        return(
            <div>
                <h1>Becoming a Mentee</h1>
                <MenteeForm />
            </div>
        )
    }
}

class MenteeForm extends React.Component {

    constructor(props) {
        super(props);
    }

    render(){

        return (
            <div>
                <form>
                    <FormGroup controlId="formControlsSelect">
                        <ControlLabel>Select Topic</ControlLabel>
                        <FormControl componentClass="select" placeholder="select">
                            <option value="gosu">Gosu</option>
                            <option value="java">Java</option>
                            <option value="javaScript">JavaScript</option>
                            <option value="git">Git</option>
                        </FormControl>
                    </FormGroup>
                    <FormGroup controlId="formControlsSelect">
                        <ControlLabel>Select Duration of Mentoring in weeks</ControlLabel>
                        <FormControl componentClass="select" placeholder="select">
                            <option value="one">1</option>
                            <option value="two">2</option>
                            <option value="three">3</option>
                            <option value="four">4</option>
                        </FormControl>
                    </FormGroup>
                    <FormGroup>
                        <Table striped bordered condensed hover>
                            <thead>
                            <th>Time of Day</th>
                            <th>Monday</th>
                            <th>Tuesday</th>
                            <th>Wednesday</th>
                            <th>Thursday</th>
                            <th>Friday</th>
                            </thead>
                            <tbody>
                            <tr>
                                <td><strong>AM</strong></td>
                                <td><Checkbox value="mondayAM"></Checkbox></td>
                                <td><Checkbox value="tuesdayAM"></Checkbox></td>
                                <td><Checkbox value="wednesdayAM"></Checkbox></td>
                                <td><Checkbox value="thursdayAM"></Checkbox></td>
                                <td><Checkbox value="fridayAM"></Checkbox></td>
                            </tr>
                            <tr>
                                <td><strong>PM</strong></td>
                                <td><Checkbox value="mondayPM"></Checkbox></td>
                                <td><Checkbox value="tuesdayPM"></Checkbox></td>
                                <td><Checkbox value="wednesdayPM"></Checkbox></td>
                                <td><Checkbox value="thursdayPM"></Checkbox></td>
                                <td><Checkbox value="fridayPM"></Checkbox></td>
                            </tr>
                            </tbody>
                        </Table>
                    </FormGroup>
                    <Checkbox checked readOnly>
                        Remote
                    </Checkbox>

                    <Button type="submit">Become Mentor</Button>
                </form>
            </div>
        )

    }
}


ReactDOM.render(
    <BecomeMentor />,
    document.getElementById('becomeMentor')
)