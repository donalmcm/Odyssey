let mentorDropdown = $('#topic-dropdown');

mentorDropdown.empty();

mentorDropdown.append('<option selected="true" disabled>Choose Topic</option>');
mentorDropdown.prop('selectedIndex', 0);

const topicMentorUrl = 'http://localhost:8080/api/topics';

// Populate dropdown with list of topics
$.getJSON(topicMentorUrl, function (data) {
    $.each(data, function (key, entry) {
        mentorDropdown.append($('<option></option>').attr('value', entry.name).text(entry.name));
    })
});

$(document).ready(function () {
    $("#become-mentor").click(function () {
        $("#become-mentor-modal").modal();
    });
});

function validateMentorModalInputs() {

    let formValues = document.getElementsByClassName("checkInput");
    let selectedValue = "";
    for (let i = 0; i < formValues.length; i++) {
        if (formValues[i].checked) {
            selectedValue = formValues[i].name;
        }
    }
    if (selectedValue === "") {
        document.getElementById("select-a-time-error-space").innerHTML = "Please select at least one time slot";
    }
    if (document.getElementById("topic-dropdown").value === "Choose Topic") {
        document.getElementById("select-a-topic-error-space").innerHTML = "Please select a topic";
    }
}

function getMentorAwaitingMentee(userId, userAwaitingMentee) {
    if (!userAwaitingMentee) {
        document.getElementById("mentor-awaiting-mentee-title").innerHTML = ""
    } else {
        const employeeURL = 'http://localhost:8080/api/employees/' + userId;
        var mentorTopic, mentorDuration;
        var mentorAvailability = [];
        $.getJSON(employeeURL, function (data) {
            document.getElementById("topic-value").innerHTML = data.topic.name;
            document.getElementById("duration-value").innerHTML = data.mentorDuration;
            mentorAvailability = checkAvailability(data);
            for(var i =0; i<mentorAvailability.length; i++) {
                var availableTime = document.createElement("h4");
                availableTime.innerHTML = mentorAvailability[i];
                availabilityDiv.appendChild(availableTime);
            }
        });

        var awaitingMenteeCard = document.createElement("div"); // outer div
        awaitingMenteeCard.className = "awaiting-mentee-card";

        var topicDiv = document.createElement("div");
        var topic = document.createElement("h3");
        var topicValue = document.createElement("h4");
        topicValue.id = "topic-value";
        topicDiv.className = "awaiting-mentee-topic";
        topic.innerHTML = "Topic: ";
        topicDiv.appendChild(topic);
        topicDiv.appendChild(topicValue);
        awaitingMenteeCard.appendChild(topicDiv);

        var durationDiv = document.createElement("div");
        var duration = document.createElement("h3");
        var durationValue = document.createElement("h4");
        durationValue.id = "duration-value";
        durationDiv.className = "col-md-12 awaiting-mentee-duration";
        duration.innerHTML = "Duration in Weeks: ";
        durationDiv.appendChild(duration);
        durationDiv.appendChild(durationValue);
        awaitingMenteeCard.appendChild(durationDiv);

        var availabilityDiv = document.createElement("div");
        var availability = document.createElement("h3");
        availabilityDiv.className = "col-md-12 awaiting-mentee-duration";
        availability.innerHTML = "Availability: ";
        availabilityDiv.appendChild(availability);
        awaitingMenteeCard.appendChild(availabilityDiv);

        document.getElementById("mentor-awaiting-mentee-list").appendChild(awaitingMenteeCard);
    }
}

function getOdysseysByMentor(userId, isAwaitingMentee) {


    getMentorAwaitingMentee(userId, isAwaitingMentee);
    const mentorOdysseysUrl = 'http://localhost:8080/api/odysseys/getOdysseysByMentor/' + userId;
    $.getJSON(mentorOdysseysUrl, function (data) {
        if (!data.length) {
            document.getElementById("mentor-page-odyssey-title").innerHTML = "";
        } else {
            $.each(data, function (key, entry) {

                var odysseyCard = document.createElement("div"); // outer div
                odysseyCard.className = "odyssey-card";
                if (!entry.active) {
                    odysseyCard.style.backgroundColor = "darkgrey";
                }

                // Type - either Mentor or Mentee
                var odysseyType = document.createElement("div"); // left inner div
                odysseyType.className = "col-md-2 odyssey-type";
                var odysseyTypeTitle = document.createElement("h1");
                odysseyTypeTitle.innerHTML = "Mentor";
                odysseyType.appendChild(odysseyTypeTitle);
                odysseyCard.appendChild(odysseyType);

                // Details - show partner name and topic name
                // Partners name
                var odysseyDetails = document.createElement("div"); // middle inner div
                odysseyDetails.className = " col-md-3 odyssey-details";
                // Partners name
                var odysseyPartner = document.createElement("div");
                odysseyPartner.className = "odyssey-partner";
                var odysseyPartnerLabel = document.createElement("h4");
                odysseyPartnerLabel.innerHTML = "Mentee:";
                odysseyPartner.appendChild(odysseyPartnerLabel);
                var odysseyPartnerTitle = document.createElement("h2");
                odysseyPartnerTitle.innerHTML = entry.mentee.firstName;
                odysseyPartner.appendChild(odysseyPartnerTitle);
                odysseyDetails.appendChild(odysseyPartner);

                // Topics name
                var odysseyTopic = document.createElement("div");
                odysseyTopic.className = "odyssey-topic";
                var odysseyTopicLabel = document.createElement("h4");
                odysseyTopicLabel.innerHTML = "Topic:";
                odysseyTopic.appendChild(odysseyTopicLabel);
                var odysseyTopicTitle = document.createElement("h2");
                odysseyTopicTitle.innerHTML = entry.topic.name;
                odysseyTopic.appendChild(odysseyTopicTitle);
                odysseyDetails.appendChild(odysseyTopic);
                // add partner name and topic to card
                odysseyCard.appendChild(odysseyDetails);

                // Progress
                var odysseyProgress = document.createElement("div");
                odysseyProgress.className = "col-md-7 odyssey-progress";
                var progress = document.createElement("div");
                progress.className = "progress";

                // progress-bar
                var bootstrapProgressBar = document.createElement("div");
                bootstrapProgressBar.className = "progress-bar progress-bar-success";
                // setting percentage in progress bar
                bootstrapProgressBar.style.width = entry.percentageCompleteOfOdyssey + '%';
                bootstrapProgressBar.innerHTML = entry.percentageCompleteOfOdyssey + "% Complete";
                progress.appendChild(bootstrapProgressBar);
                odysseyProgress.appendChild(progress);

                // Meeting date's labels
                var firstMeetingLabel = document.createElement("h4");
                firstMeetingLabel.className = "first-meeting-label";
                firstMeetingLabel.innerHTML = "Start Date:";
                odysseyProgress.appendChild(firstMeetingLabel);
                var lastMeetingLabel = document.createElement("h4");
                lastMeetingLabel.className = "last-meeting-label";
                lastMeetingLabel.innerHTML = "End Date:";
                odysseyProgress.appendChild(lastMeetingLabel);

                // First meeting date
                var firstMeeting = document.createElement("h3");
                firstMeeting.className = "first-meeting";
                firstMeeting.innerHTML = entry.odysseyMeetings[0].date;
                odysseyProgress.appendChild(firstMeeting);

                // Last meeting date
                var lastMeeting = document.createElement("h3");
                lastMeeting.className = "last-meeting";
                lastMeeting.innerHTML = entry.odysseyMeetings[(entry.odysseyMeetings.length - 1)].date;
                odysseyProgress.appendChild(lastMeeting);

                // Labels for time & date and completed meetings
                var meetingDetailsLabel = document.createElement("h4");
                meetingDetailsLabel.className = "meetings-details-label";
                meetingDetailsLabel.innerHTML = "Time and Day of Meetings:";
                odysseyProgress.appendChild(meetingDetailsLabel);
                var meetingsCompleteLabel = document.createElement("h4");
                meetingsCompleteLabel.className = "meetings-complete-label";
                meetingsCompleteLabel.innerHTML = "Meetings Complete:";
                odysseyProgress.appendChild(meetingsCompleteLabel);

                // Time and Day of meetings
                var meetingsDetails = document.createElement("h2");
                meetingsDetails.className = "meetings-details";
                meetingsDetails.innerHTML = entry.odysseyMeetings[0].time + " on " + entry.odysseyMeetings[0].day + "'s";
                odysseyProgress.appendChild(meetingsDetails);

                // Number of meetings complete vs Overall
                var meetingsComplete = document.createElement("h2");
                meetingsComplete.className = "meetings-complete";
                meetingsComplete.innerHTML = entry.odysseyMeetingsCompleteVsOverall;
                odysseyProgress.appendChild(meetingsComplete);

                odysseyCard.appendChild(odysseyProgress);

                // Add card to list
                document.getElementById("odyssey-list-by-mentor").appendChild(odysseyCard);
            })
        }
    });
}

function checkAvailability(data) {
    listOfTimes = [];

    if (data.availability.monday10 === true) {
        listOfTimes.push("Monday 10 AM");
    }
    if (data.availability.monday11 === true) {
        listOfTimes.push("Monday 11 AM");
    }
    if (data.availability.monday12 === true) {
        listOfTimes.push("Monday 12 AM");
    }
    if (data.availability.monday14 === true) {
        listOfTimes.push("Monday 2 PM");
    }
    if (data.availability.monday15 === true) {
        listOfTimes.push("Monday 3 PM");
    }
    if (data.availability.monday16 === true) {
        listOfTimes.push("Monday 4 PM");
    }
// check what times on tuesday are available
    if (data.availability.tuesday10 === true) {
        listOfTimes.push("Tuesday 10 AM");
    }
    if (data.availability.tuesday11 === true) {
        listOfTimes.push("Tuesday 11 AM");
    }
    if (data.availability.tuesday12 === true) {
        listOfTimes.push("Tuesday 12 AM");
    }
    if (data.availability.tuesday14 === true) {
        listOfTimes.push("Tuesday 2 PM");
    }
    if (data.availability.tuesday15 === true) {
        listOfTimes.push("Tuesday 3 PM");
    }
    if (data.availability.tuesday16 === true) {
        listOfTimes.push("Tuesday 4 PM");
    }
// check what times on wednesday are available
    if (data.availability.wednesday10 === true) {
        listOfTimes.push("Wednesday 10 AM");
    }
    if (data.availability.wednesday11 === true) {
        listOfTimes.push("Wednesday 11 AM");
    }
    if (data.availability.wednesday12 === true) {
        listOfTimes.push("Wednesday 12 AM");
    }
    if (data.availability.wednesday14 === true) {
        listOfTimes.push("Wednesday 2 PM");
    }
    if (data.availability.wednesday15 === true) {
        listOfTimes.push("Wednesday 3 PM");
    }
    if (data.availability.wednesday16 === true) {
        listOfTimes.push("Wednesday 4 PM");
    }
// check what times on thursday are available
    if (data.availability.thursday10 === true) {
        listOfTimes.push("Thursday 10 AM");
    }
    if (data.availability.thursday11 === true) {
        listOfTimes.push("Thursday 11 AM");
    }
    if (data.availability.thursday12 === true) {
        listOfTimes.push("Thursday 12 AM");
    }
    if (data.availability.thursday14 === true) {
        listOfTimes.push("Thursday 2 PM");
    }
    if (data.availability.thursday15 === true) {
        listOfTimes.push("Thursday 3 PM");
    }
    if (data.availability.thursday16 === true) {
        listOfTimes.push("Thursday 4 PM");
    }
// check what times on friday are available
    if (data.availability.friday10 === true) {
        listOfTimes.push("Friday 10 AM");
    }
    if (data.availability.friday11 === true) {
        listOfTimes.push("Friday 11 AM");
    }
    if (data.availability.friday12 === true) {
        listOfTimes.push("Friday 12 AM");
    }
    if (data.availability.friday14 === true) {
        listOfTimes.push("Friday 2 PM");
    }
    if (data.availability.friday15 === true) {
        listOfTimes.push("Friday 3 PM");
    }
    if (data.availability.friday16 === true) {
        listOfTimes.push("Friday 4 PM");
    }

    return listOfTimes;
}
