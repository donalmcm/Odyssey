let mentorList = $('#managers-team');

function getManagersTeam(managerId, isManager) {
    if (isManager === true) {

        document.getElementById("manager-page-title").innerHTML = "Management";

        const managersEmployeesUrl = 'http://localhost:8080/api/employees/getTeamMembers/manager/' + managerId;

        let isCurrentMentee = "No";
        let isCurrentMentor = "No";
        // Populate dropdown with list of topics
        $.getJSON(managersEmployeesUrl, function (data) {
            $.each(data, function (key, entry) {
                var tr = document.createElement('tr');

                // Full name
                var fullName = document.createElement('td');
                fullName.innerHTML = entry.fullName;
                tr.append(fullName);

                // Email
                var email = document.createElement('td');
                email.innerHTML = entry.email;
                tr.append(email);

                // Is employee a mentor
                var isCurrentMentor = document.createElement('td');
                isCurrentMentor.innerHTML = entry.mentor;
                tr.append(isCurrentMentor);

                // Is employee a mentee
                var isCurrentMentee = document.createElement('td');
                isCurrentMentee.innerHTML = entry.mentee;
                tr.append(isCurrentMentee);

                // count of odysseys
                var odysseyCount = document.createElement('td');
                odysseyCount.innerHTML = "SQL COUNT";
                tr.append(odysseyCount);

                mentorList.append(tr);
            })
        });
    } else {
        document.getElementById("manager-page-title").innerHTML = "NOT AUTHORIZED";
        document.getElementById("manager-page-title").style.color = 'red';
        document.getElementById("manager-page-content").style.display = "none";
    }


}


// values to be taken from database - hardcoded for demo
new Chart(document.getElementById("line-chart"), {
    type: 'line',
    data: {
        labels: [1, 2, 3, 4, 5, 6],
        datasets: [{
            data: [86, 114, 106, 106, 107, 111],
            label: "Mentor",
            borderColor: "#3e95cd",
            fill: false
        }, {
            data: [282, 350, 411, 502, 635, 809],
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

// const topicCountByOdysseysURL = 'http://localhost:8080/api/odysseys/countTopicsByOdyssey';
// var labels=[], topicCount=[];
// // Populate dropdown with list of topics
// $.getJSON(topicCountByOdysseysURL, function (data) {
//     $.each(data, function (key, entry) {
//         mentorDropdown.append($('<option></option>').attr('value', entry.name).text(entry.name));
//     })
// });

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
                data: [20.0, 20.0, 20.0, 20.0, 20.0]
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
