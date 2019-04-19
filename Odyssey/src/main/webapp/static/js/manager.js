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
        getRadarGraphInfo();

    } else {
        document.getElementById("manager-page-title").innerHTML = "NOT AUTHORIZED";
        document.getElementById("manager-page-title").style.color = 'red';
        document.getElementById("manager-page-content").style.display = "none";
    }


}

function getRadarGraphInfo() {
    const topicCountByOdysseysURL = 'http://localhost:8080/api/odysseys';
    var allOdysseyTopics = [];
    // Populate list of topics
    $.getJSON(topicCountByOdysseysURL, function (data) {
        $.each(data, function (key, entry) {
            allOdysseyTopics.push(entry.topic.name);
        });
        getLabelsAndCount(allOdysseyTopics);
    });

}

function getLabelsAndCount(list) {
    var prev;
    var labels = [], topicCount = [];

    list.sort();
    for (var i = 0; i < list.length; i++) {
        if (list[i] !== prev) {
            labels.push(list[i]);
            topicCount.push(1);
        } else {
            topicCount[topicCount.length - 1]++;
        }
        prev = list[i];
    }

    loadRadarGraph(labels,topicCount);
}

function loadRadarGraph(listOfLabels,labelCount) {
    new Chart(document.getElementById("radar-chart"), {
        type: 'radar',
        data: {
            // get topics
            labels: listOfLabels,
            datasets: [
                {
                    label: "Managers Employees",
                    fill: true,
                    backgroundColor: "rgba(179,181,198,0.2)",
                    borderColor: "rgba(179,181,198,1)",
                    pointBorderColor: "#3e95cd",
                    pointBackgroundColor: "rgba(179,181,198,1)",
                    data: labelCount
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
//----------------------------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------------------------

