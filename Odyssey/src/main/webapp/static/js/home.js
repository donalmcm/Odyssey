function getOdysseys(userId, userName) {
    const adminEmployeeUrl = 'http://localhost:8080/api/odysseys/getEmployeeOdysseys/' + userId;
    $.getJSON(adminEmployeeUrl, function (data) {
        if (data.length === 0) {
            document.getElementById("home-page-title").innerHTML = "Currently you have no Odysseys";
        } else {
            $.each(data, function (key, entry) {

                var odysseyCard = document.createElement("div");
                odysseyCard.className = "odyssey-card";

                var odysseyCardMain = document.createElement("div");
                odysseyCardMain.className = "odyssey-card-main";
                if (!entry.active) {
                    // set text to active or not active
                }

                // Type - either Mentor or Mentee
                var odysseyType = document.createElement("div"); // left inner div
                odysseyType.className = "col-md-2 odyssey-type";
                var odysseyTypeTitle = document.createElement("h1");
                odysseyType.appendChild(odysseyTypeTitle);
                odysseyCardMain.appendChild(odysseyType);

                // Details - show partner name and topic name
                var odysseyDetails = document.createElement("div"); // middle inner div
                odysseyDetails.className = " col-md-3 odyssey-details";

                // Top Details - Name and topic
                var odysseyPartnerAndTopic = document.createElement("div");
                odysseyPartnerAndTopic.className = "odyssey-partner-and-topic";

                // Partners name
                var odysseyPartner = document.createElement("div");
                odysseyPartner.className = "odyssey-partner";
                var odysseyPartnerLabel = document.createElement("h4");
                odysseyPartner.appendChild(odysseyPartnerLabel);
                var odysseyPartnerTitle = document.createElement("h2");
                odysseyPartner.appendChild(odysseyPartnerTitle);
                odysseyPartnerAndTopic.appendChild(odysseyPartner);

                // Topics name
                var odysseyTopic = document.createElement("div");
                odysseyTopic.className = "odyssey-topic";
                var odysseyTopicLabel = document.createElement("h4");
                odysseyTopicLabel.innerHTML = "Topic:";
                odysseyTopic.appendChild(odysseyTopicLabel);
                var odysseyTopicTitle = document.createElement("h2");
                odysseyTopicTitle.innerHTML = entry.topic.name;
                odysseyTopic.appendChild(odysseyTopicTitle);
                odysseyPartnerAndTopic.appendChild(odysseyTopic);

                // adding partner and topic to top details
                odysseyDetails.appendChild(odysseyPartnerAndTopic);

                // More details with button
                var moreDetails = document.createElement("div");
                moreDetails.className = "more-details";
                var moreDetailsLabel = document.createElement("h4");
                moreDetailsLabel.innerHTML = "For more details :";
                moreDetails.appendChild(moreDetailsLabel);
                var moreDetailsButton = document.createElement("button");
                moreDetailsButton.className = "btn btn-success btn-lg more-details-button";
                moreDetailsButton.innerHTML = "Click Here";
                moreDetailsButton.onclick = function () {
                    displayMoreDetails(entry.id);
                };
                moreDetails.appendChild(moreDetailsButton);
                odysseyDetails.appendChild(moreDetails);
                odysseyCardMain.appendChild(odysseyDetails);

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

                odysseyCardMain.appendChild(odysseyProgress);

                if (userName === entry.mentor.firstName) {
                    odysseyTypeTitle.innerHTML = "Mentor";
                    odysseyPartnerLabel.innerHTML = "Mentee:";
                    odysseyPartnerTitle.innerHTML = entry.mentee.firstName;
                } else {
                    odysseyTypeTitle.innerHTML = "Mentee";
                    odysseyPartnerLabel.innerHTML = "Mentor:";
                    odysseyPartnerTitle.innerHTML = entry.mentor.firstName;
                }
                odysseyCard.appendChild(odysseyCardMain);

                var odysseyMoreDetails = document.createElement("div");
                odysseyMoreDetails.className = "odyssey-more-details";

                for(let i=0 ;i <entry.odysseyMeetings.length;i++) {
                    var odysseyMeeting = document.createElement("div");
                    odysseyMeeting.className = "odyssey-meeting";

                    var meetingHeader = document.createElement("h2");
                    meetingHeader.innerHTML = "Meeting " + (i+1);
                    odysseyMeeting.appendChild(meetingHeader);

                    var meetingDate = document.createElement("h4");
                    meetingDate.innerHTML = "Date: " + entry.odysseyMeetings[i].date;
                    odysseyMeeting.appendChild(meetingDate);

                    var meetingDayAndTime = document.createElement("h4");
                    meetingDayAndTime.innerHTML = "Time and Day: " + entry.odysseyMeetings[i].time + " "+ entry.odysseyMeetings[i].day;
                    odysseyMeeting.appendChild(meetingDayAndTime);

                    var meetingCompleted = document.createElement("h4");
                    odysseyMeeting.appendChild(meetingCompleted);

                    var meetingNote = document.createElement("div");
                    var meetingNoteLabel = document.createElement("h4");
                    meetingNoteLabel.innerHTML = "Note: ";
                    meetingNote.appendChild(meetingNoteLabel);
                    var meetingNoteText = document.createElement("p");
                    meetingNoteText.innerHTML = entry.odysseyMeetings[i].meetingNote;
                    meetingNote.appendChild(meetingNoteText);

                    var meetingNoteButton = document.createElement("button");
                    meetingNoteButton.className = "btn btn-success btn-lg more-details-button";
                    meetingNoteButton.innerHTML = "Edit Note";
                    meetingNoteButton.onclick = function () {
                        editMeetingNoteModal(entry.odysseyMeetings[i].id,entry.odysseyMeetings[i].meetingNote);
                    };

                    if(entry.odysseyMeetings[i].isCompleted) {
                        meetingCompleted.innerHTML = "Completed: Yes";
                    } else {
                        meetingCompleted.innerHTML = "Completed: No";
                        meetingNoteButton.disabled = true;
                    }
                    meetingNote.appendChild(meetingNoteButton);

                    odysseyMeeting.appendChild(meetingNote);
                    // last line
                    odysseyMoreDetails.appendChild(odysseyMeeting);
                }

                odysseyCard.appendChild(odysseyMoreDetails);
                // Add card to list
                document.getElementById("odyssey-list").appendChild(odysseyCard);
            })
        }
    });
}

function displayMoreDetails(odysseyId) {
    // unhide more details for a particular odyssey
}

function editMeetingNoteModal(meetingId,currentNote) {
    // create modal with post for a meetings notes
    $('#edit-meeting-note-modal').modal('show');
    var action = document.getElementById("edit-meeting-note-form");
    action.action = "../api/odysseyMeetings/"+meetingId+"/note";

    if(currentNote != null || currentNote !== "") {
        var existingNote = document.getElementById("meetingNote");
        existingNote.innerHTML = currentNote;
    }
    // disable button if meeting is not complete
}


