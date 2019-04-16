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

$(document).ready(function(){
    $("#become-mentor").click(function(){
        $("#become-mentor-modal").modal();
    });
});

function getOdysseysByMentor(userId) {
    const mentorOdysseysUrl = 'http://localhost:8080/api/odysseys/getOdysseysByMentor/' + userId;
    $.getJSON(mentorOdysseysUrl, function (data) {
        $.each(data, function (key, entry) {

            var odysseyCard = document.createElement("div"); // outer div
            odysseyCard.className = "odyssey-card";

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
            lastMeeting.innerHTML = entry.odysseyMeetings[(entry.odysseyMeetings.length-1)].date;
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
            meetingsDetails.innerHTML = entry.odysseyMeetings[0].time + " on " +entry.odysseyMeetings[0].day + "'s";
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
    });
}
