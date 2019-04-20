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
        getMeetingsGraphInfo();
        getRadarGraphInfo();
        getGeoGraphInfo();

    } else {
        document.getElementById("manager-page-title").innerHTML = "NOT AUTHORIZED";
        document.getElementById("manager-page-title").style.color = 'red';
        document.getElementById("manager-page-content").style.display = "none";
    }


}

// --------------------------------- TOPIC PIE CHART -------------------------------------------------------------------
function getTopicUsageInfo(usedTopics) {
    const topicURL = 'http://localhost:8080/api/topics';
    var allTopics = [];
    // Populate list of topics
    $.getJSON(topicURL, function (data) {
        $.each(data, function (key, entry) {
            allTopics.push(entry.name);
        });
        loadTopicUsagePieChart(usedTopics.length, allTopics.length);
    });
}

function loadTopicUsagePieChart(noOfUsedTopics, noOfAllTopics) {
    var noOfNotUsedTopics = noOfAllTopics - noOfUsedTopics;
    new Chart(document.getElementById("pie-chart"), {
        type: 'pie',
        data: {
            labels: ["Number of Used Topics", "Number of Unused Topics"],
            datasets: [{
                label: "Population (millions)",
                backgroundColor: ["#3e95cd", "#c45850"],
                data: [noOfUsedTopics, noOfNotUsedTopics]
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Used Vs Unused Topics'
            }
        }
    });
}

// --------------------------------- RADAR GRAPH -----------------------------------------------------------------------
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

    loadRadarGraph(labels, topicCount);
    getTopicUsageInfo(labels);
}

function loadRadarGraph(listOfLabels, labelCount) {
    new Chart(document.getElementById("radar-chart"), {
        type: 'radar',
        data: {
            // get topics
            labels: listOfLabels,
            datasets: [
                {
                    label: "Topics By Usage",
                    fill: true,
                    backgroundColor: "#3cba9f",
                    borderColor: "#3cba9f",
                    pointBorderColor: "#3e95cd",
                    pointBackgroundColor: "#3e95cd",
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

// --------------------------------------- MEETINGS GRAPHS -------------------------------------------------------------
function getMeetingsGraphInfo() {
    const meetingCountURL = 'http://localhost:8080/api/odysseyMeetings';
    var allOdysseyMeetings = [];
    var allMeetingDays = [];
    // Populate list of topics
    $.getJSON(meetingCountURL, function (data) {
        $.each(data, function (key, entry) {
            allOdysseyMeetings.push(entry.date);
            allMeetingDays.push(entry.day);
        });
        getMeetingsAndCount(allOdysseyMeetings);
        getMeetingDaysAndCount(allMeetingDays);
    });

}

function getMeetingsAndCount(list) {
    var prev;
    var dates = [], dateCount = [];

    list.sort();
    for (var i = 0; i < list.length; i++) {
        if (list[i] !== prev) {
            dates.push(list[i]);
            dateCount.push(1);
        } else {
            dateCount[dateCount.length - 1]++;
        }
        prev = list[i];
    }

    loadMeetingsGraph(dates, dateCount);
}

function getMeetingDaysAndCount(list) {
    var prev;
    var day = [], dayCount = [];

    // change sort to monday -> friday
    list.sort();
    for (var i = 0; i < list.length; i++) {
        if (list[i] !== prev) {
            day.push(list[i]);
            dayCount.push(1);
        } else {
            dayCount[dayCount.length - 1]++;
        }
        prev = list[i];
    }

    loadMeetingDayGraph(day, dayCount);
}

function loadMeetingDayGraph(days, dayCount) {
    new Chart(document.getElementById("bar-chart"), {
        type: 'bar',
        data: {
            labels: days,
            datasets: [
                {
                    label: "Number of meetings on this day",
                    backgroundColor: ["#3e95cd", "#3cba9f", "#3e95cd", "#3cba9f", "#3e95cd"],
                    data: dayCount
                }
            ]
        },
        options: {
            legend: {display: false},
            title: {
                display: true,
                text: 'Number of Meetings By Day'
            }
        }
    });
}

function loadMeetingsGraph(dates, meetingCount) {
    new Chart(document.getElementById("line-chart"), {
        type: 'line',
        data: {
            labels: dates,
            datasets: [{
                data: meetingCount,
                label: "Number of Meetings",
                borderColor: "#3e95cd",
                fill: false
            }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Meeting count by date'
            }
        }
    });
}

//-----------------------------------------------GEO AND POLAR GRAPH ---------------------------------------------------
function getGeoGraphInfo() {
    const employeeURL = 'http://localhost:8080/api/employees';
    var allEmployeeLocations = [];
    var allEmployeeTitles = [];
    var mentors=0,mentees=0,managers=0,admins=0;
    // Populate list of topics
    $.getJSON(employeeURL, function (data) {
        $.each(data, function (key, entry) {
            allEmployeeLocations.push(entry.location);
            allEmployeeLocations.push(entry.title);
            if(entry.mentor === true) { mentors ++;}
            if(entry.mentee === true) { mentees ++;}
            if(entry.manager === true) { managers ++;}
            if(entry.admin === true) { admins ++;}
        });
        getCountriesAndCount(allEmployeeLocations);
        getEmployeeTitlesAndCount(allEmployeeTitles);
        graphEmployeeRoles(mentors,mentees,managers,admins);
    });

}

function graphEmployeeRoles(mentors,mentees,managers,admins) {
    new Chart(document.getElementById("employee-bar-chart"), {
        type: 'bar',
        data: {
            labels: ["Mentors", "Mentees", "Managers", "Admins"],
            datasets: [
                {
                    label: "Number of employees in this role",
                    backgroundColor: ["#3e95cd","#3cba9f","#8e5ea2","#c45850"],
                    data: [mentors,mentees,managers,admins]
                }
            ]
        },
        options: {
            legend: { display: false },
            title: {
                display: true,
                text: 'Employee Role Count'
            }
        }
    });
}
// POLAR GRAPH---------------------------
function getEmployeeTitlesAndCount(list) {
    var prev;
    var labels = [], titleCount = [];

    list.sort();
    for (var i = 0; i < list.length; i++) {
        if (list[i] !== prev) {
            labels.push(list[i]);
            titleCount.push(1);
        } else {
            titleCount[titleCount.length - 1]++;
        }
        prev = list[i];
    }

    loadPolarGraph(labels, titleCount);
}

function loadPolarGraph(titles,titlesCount) {
    new Chart(document.getElementById("polar-chart"), {
        type: 'polarArea',
        data: {
            labels: titles,
            datasets: [
                {
                    label: "number of employees for this title",
                    backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
                    data: titlesCount
                }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Distribution of Job Titles'
            }
        }
    });
}

// GEO GRAPH ------------------------
function getCountriesAndCount(list) {
    var prev;
    var labels = [], countryCount = [];

    list.sort();
    for (var i = 0; i < list.length; i++) {
        if (list[i] !== prev) {
            labels.push(list[i]);
            countryCount.push(1);
        } else {
            countryCount[countryCount.length - 1]++;
        }
        prev = list[i];
    }

    loadGeoGraph(labels, countryCount);
}


function loadGeoGraph(countries, countryCount) {
    google.charts.load('current', {
        'packages': ['geochart'],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
    });
    google.charts.setOnLoadCallback(drawRegionsMap);

    function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable([
            ['Country', 'Employees'],
            [countries[0], countryCount[0]],
            [countries[1], countryCount[1]],
            [countries[2], countryCount[2]],
            [countries[3], countryCount[3]],
            [countries[4], countryCount[4]],
            [countries[5], countryCount[5]],
            [countries[6], countryCount[6]],
            [countries[7], countryCount[7]],
            [countries[8], countryCount[8]],
            [countries[9], countryCount[9]],
            [countries[10], countryCount[10]]
        ]);

        var options = {};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, {});
    }
}


