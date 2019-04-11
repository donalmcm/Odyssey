let mentorList = $('#managers-team');

// const managersTeam = 'http://localhost:8080/api/employees/byManager/' + managerId; // id passed in from current user
const managersEmployeesUrl = 'http://localhost:8080/api/employees';

// Populate dropdown with list of topics
$.getJSON(managersEmployeesUrl, function (data) {
    $.each(data, function (key, entry) {
        mentorList.append($('<tr>'));
        mentorList.append($('<td></td>').attr('value', entry.id).text(entry.id));
        mentorList.append($('<td></td>').attr('value', entry.firstName).text(entry.firstName));
        mentorList.append($('<td></td>').attr('value', entry.lastName).text(entry.lastName));
        mentorList.append($('<td></td>').attr('value', entry.email).text(entry.email));
        mentorList.append($('</tr>'));

    })
});

// values to be taken from database - hardcoded for demo
new Chart(document.getElementById("line-chart"), {
    type: 'line',
    data: {
        labels: [1,2,3,4,5,6],
        datasets: [{
            data: [86,114,106,106,107,111],
            label: "Mentor",
            borderColor: "#3e95cd",
            fill: false
        }, {
            data: [282,350,411,502,635,809],
            label: "Mentee",
            borderColor: "#8e5ea2",
            fill: false
        }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Your Teams Progress - This Year'
        }
    }
});

const topicCountByOdysseysURL = 'http://localhost:8080/api/odysseys/countTopicsByOdyssey';
var labels=[], topicCount=[];
// Populate dropdown with list of topics
$.getJSON(topicCountByOdysseysURL, function (data) {
    $.each(data, function (key, entry) {
        mentorDropdown.append($('<option></option>').attr('value', entry.name).text(entry.name));
    })
});
new Chart(document.getElementById("radar-chart"), {
    type: 'radar',
    data: {
        // get topics
        labels: ["JPA", "Gosu", "Git", "API", "Management"],
        datasets: [
            {
                label: "Managers Employees",
                fill: true,
                backgroundColor: "rgba(179,181,198,0.2)",
                borderColor: "rgba(179,181,198,1)",
                pointBorderColor: "#3e95cd",
                pointBackgroundColor: "rgba(179,181,198,1)",
                data: [20.0,20.0,20.0,20.0,20.0]
            }
        ]
    },
    options: {
        title: {
            display: true,
            text: 'Distribution in % of Topics'
        }
    }
});
