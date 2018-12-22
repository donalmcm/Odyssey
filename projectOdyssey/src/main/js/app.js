'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const when = require('when');
const client = require('./client');

const follow = require('./follow'); // function to hop multiple links by "rel"

const stompClient = require('./websocket-listener');

const root = '/api';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {employees: [], attributes: []};
        this.onCreateOdyssey = this.onCreateOdyssey.bind(this);
    }

    render(){
        return(
            <div>
                <MentorList employees={this.state.employees}
                              attributes={this.state.attributes}/>
            </div>
        )
    }

    onCreateOdyssey(newOdyssey) {
        follow(client, root, ['odysseys']).done(response => {
            client({
                method: 'POST',
                path: response.entity._links.self.href,
                entity: newOdyssey,
                headers: {'Content-Type': 'application/json'}
            })
        })
    }
}

class MentorList extends React.Component {

    constructor(props) {
        super(props);
    }

    render(){

        const employees = this.props.employees.map(employee =>
            <Employee key={employee.entity._links.self.href}
                      employee={employee}
                      attributes={this.props.attributes}/>
        );

        return (
            <div>
                <table>
                    <tbody>
                    <tr>
                        <th>Topic</th>
                        <th>Availability</th>
                        <th></th>
                    </tr>
                    {employees}
                    </tbody>
                </table>
            </div>
        )

    }
}

class Mentor extends React.Component {

    constructor(props) {
        super(props);
    }

    render(){
        return (
            <tr>
                <td>{this.props.employee.entity.topic}</td>
                <td>{this.props.employee.entity.availability}</td>
                <td>
                    <button>Create Odyssey</button>
                </td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)