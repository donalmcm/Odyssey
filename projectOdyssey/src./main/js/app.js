'use strict';

import {FormGroup, ControlLabel, FormControl, Button, Checkbox, Table} from "react-bootstrap";
import {Card, CardTitle, CardSubtitle, CardBody, CardDeck, ListGroup, ListGroupItem, CardText } from 'reactstrap';

const React = require('react');
const ReactDOM = require('react-dom');
const when = require('when');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const stompClient = require('./websocket-listener');

const root = '/api';

class App extends React.Component {


    render(){
        return(
            <div>
                <h1>Search for a Mentor</h1>
                <MenteeList />
            </div>
        )
    }
}

class MenteeList extends React.Component {

    constructor(props) {
        super(props);
    }

    render(){

        return (
            <div>
                <form>
                    <h3>Filter By</h3>
                    <FormGroup controlId="formControlsSelect">
                        <ControlLabel>Topic</ControlLabel>
                        <FormControl componentClass="select" placeholder="select">
                            <option value="gosu">Gosu</option>
                            <option value="java">Java</option>
                            <option value="javaScript">JavaScript</option>
                            <option value="git">Git</option>
                        </FormControl>
                    </FormGroup>
                    <FormGroup controlId="formControlsSelect">
                        <ControlLabel>Duration of Mentoring in weeks</ControlLabel>
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
                                <tr>
                                    <th>Time of Day</th>
                                    <th>Monday</th>
                                    <th>Tuesday</th>
                                    <th>Wednesday</th>
                                    <th>Thursday</th>
                                    <th>Friday</th>
                                </tr>
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
                    <Checkbox>
                        Remote
                    </Checkbox>
                </form>
                <CardDeck>
                    <h3>List of Mentors</h3>
                    <Card>
                        <CardBody>
                            <CardTitle>Topic : Java</CardTitle>
                            <CardSubtitle>Subtopic : JPA</CardSubtitle>
                            <CardSubtitle>Duration : 4 weeks</CardSubtitle>
                            <CardSubtitle>Remote : Unavailable</CardSubtitle>
                            <CardSubtitle>Availability</CardSubtitle>
                            <CardText>Monday AM</CardText>
                            <CardText>Monday PM</CardText>
                            <CardText>Wednesday AM</CardText>
                            <Button type="submit">Begin Odyssey</Button>
                        </CardBody>
                    </Card>
                    <Card>
                        <CardBody>
                            <CardTitle>Topic : Management</CardTitle>
                            <CardSubtitle>Subtopic : Leadership</CardSubtitle>
                            <CardSubtitle>Duration : 3 weeks</CardSubtitle>
                            <CardSubtitle>Remote : Unavailable</CardSubtitle>
                            <CardSubtitle>Availability</CardSubtitle>
                            <CardText>Monday AM</CardText>
                            <CardText>Monday PM</CardText>
                            <CardText>Wednesday AM</CardText>
                            <Button type="submit">Begin Odyssey</Button>
                        </CardBody>
                    </Card>
                    <Card>
                        <CardBody>
                            <CardTitle>Topic: Git</CardTitle>
                            <CardSubtitle>Subtopic : Branching</CardSubtitle>
                            <CardSubtitle>Duration : 1 weeks</CardSubtitle>
                            <CardSubtitle>Remote : Available</CardSubtitle>
                            <CardSubtitle>Availability</CardSubtitle>
                            <CardText>Monday AM</CardText>
                            <CardText>Monday PM</CardText>
                            <CardText>Wednesday AM</CardText>
                            <Button type="submit">Begin Odyssey</Button>
                        </CardBody>
                    </Card>
                    <Card>
                        <CardBody>
                            <CardTitle>Topic : Gosu</CardTitle>
                            <CardSubtitle>Subtopic : Policy Center</CardSubtitle>
                            <CardSubtitle>Duration : 8 weeks</CardSubtitle>
                            <CardSubtitle>Remote : Unavailable</CardSubtitle>
                            <CardSubtitle>Availability</CardSubtitle>
                            <CardText>Monday AM</CardText>
                            <CardText>Monday PM</CardText>
                            <CardText>Wednesday AM</CardText>
                            <Button type="submit">Begin Odyssey</Button>
                        </CardBody>
                    </Card>
                </CardDeck>
            </div>
        )

    }
}


ReactDOM.render(
    <App />,
    document.getElementById('react')
)