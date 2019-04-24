function populateModal() {

    if (document.getElementById("topic-filter").value === "Choose Topic" && document.getElementById("duration-dropdown").value === "Choose Duration") {
        document.getElementById("select-a-topic-error-space").innerHTML = "Please select a topic";
        document.getElementById("select-a-duration-error-space").innerHTML = "Please select a duration";
    } else if (document.getElementById("topic-filter").value === "Choose Topic") {
        document.getElementById("select-a-topic-error-space").innerHTML = "Please select a topic";
    } else if (document.getElementById("duration-dropdown").value === "Choose Duration") {
        document.getElementById("select-a-duration-error-space").innerHTML = "Please select a duration";
    } else {

        let formValues = document.getElementsByClassName("resetToDisabled");
        let selectedValue = "";
        for (let i = 0; i < formValues.length; i++) {
            if (formValues[i].checked) {
                selectedValue = formValues[i].name;
            }
        }

        if (selectedValue === "") {
            document.getElementById("select-a-time-error-space").innerHTML = "Please select one time slot";
        } else {

            let topic = document.getElementById("topic-filter").value;
            let duration = document.getElementById("duration-dropdown").value;
            let day = selectedValue.slice(0, -2); // e.g. monday
            let time = selectedValue.slice(-2); // e.g. 10

            document.getElementById("topicId").value = topic;
            document.getElementById("mentorDuration").value = duration;
            document.getElementById("dayOfMeetings").value = day;
            document.getElementById("timeOfMeetings").value = time;

            $('#odyssey-modal').modal('show');
        }
    }
}

// remove error message on click
$('#select-a-time-error-space').change(function () {
    document.getElementById("select-a-time-error-space").innerHTML = "";
});

let menteeDropdown = $('#topic-filter');
menteeDropdown.empty();
menteeDropdown.append('<option selected="true" disabled>Choose Topic</option>');
menteeDropdown.prop('selectedIndex', 0);

// remove error message on click
$(menteeDropdown).change(function () {
    document.getElementById("select-a-topic-error-space").innerHTML = "";
});

function populateTopicDropdown(userId) {
    const topicURL = 'http://localhost:8080/api/employees/mentors/awaiting/excludingUser/' + userId;
    $.getJSON(topicURL, function (data) {
        if (data.length === 0) {
            document.getElementById("no-mentors-error").innerHTML = "Sorry there are no available mentors at this time";
        } else {
            $.each(data, function (key, entry) {
                //create input, add value, add name, add event listener and then append
                var dropDownElement = document.createElement('option');
                dropDownElement.value = entry.topic.name;
                dropDownElement.text = entry.topic.name;

                menteeDropdown.append(dropDownElement);
            })
        }
    });
}


let durationDropdown = $('#duration-dropdown');
durationDropdown.empty();
durationDropdown.append('<option selected="true" disabled>Choose Duration</option>');
durationDropdown.prop('selectedIndex', 0);

// remove error message on click
$(durationDropdown).change(function () {
    document.getElementById("select-a-duration-error-space").innerHTML = "";
});

function getMentorsByTopic(topic, userId) {

    // get all time slot buttons
    let times = document.getElementsByClassName("resetToDisabled");
    // reset dropdown
    durationDropdown.empty();
    durationDropdown.append('<option selected="true" disabled>Choose Duration</option>');
    durationDropdown.prop('selectedIndex', 0);
    // on change of topic reset all times to unavailable
    for (let i = 0; i < times.length; i++) {
        times[i].disabled = true;
    }
    let durationOptions = [];
    const menteeUrl = 'http://localhost:8080/api/employees/mentors/' + topic + '/excludingUser/' + userId;
    $.getJSON(menteeUrl, function (data) {
        $.each(data, function (key, entry) {

            if (!durationOptions.includes(entry.mentorDuration)) {
                durationOptions.push(entry.mentorDuration);
                durationDropdown.append($('<option></option>').attr('value', entry.mentorDuration).text(entry.mentorDuration));
            }

            // change this to loop through availability
            // check what times on monday are available
            if (entry.availability.monday10 == true) {
                document.getElementById("monday10").disabled = false;
            }
            if (entry.availability.monday11 == true) {
                document.getElementById("monday11").disabled = false;
            }
            if (entry.availability.monday12 == true) {
                document.getElementById("monday12").disabled = false;
            }
            if (entry.availability.monday14 == true) {
                document.getElementById("monday14").disabled = false;
            }
            if (entry.availability.monday15 == true) {
                document.getElementById("monday15").disabled = false;
            }
            if (entry.availability.monday16 == true) {
                document.getElementById("monday16").disabled = false;
            }
            // check what times on tuesday are available
            if (entry.availability.tuesday10 == true) {
                document.getElementById("tuesday10").disabled = false;
            }
            if (entry.availability.tuesday11 == true) {
                document.getElementById("tuesday11").disabled = false;
            }
            if (entry.availability.tuesday12 == true) {
                document.getElementById("tuesday12").disabled = false;
            }
            if (entry.availability.tuesday14 == true) {
                document.getElementById("tuesday14").disabled = false;
            }
            if (entry.availability.tuesday15 == true) {
                document.getElementById("tuesday15").disabled = false;
            }
            if (entry.availability.tuesday16 == true) {
                document.getElementById("tuesday16").disabled = false;
            }
            // check what times on wednesday are available
            if (entry.availability.wednesday10 == true) {
                document.getElementById("wednesday10").disabled = false;
            }
            if (entry.availability.wednesday11 == true) {
                document.getElementById("wednesday11").disabled = false;
            }
            if (entry.availability.wednesday12 == true) {
                document.getElementById("wednesday12").disabled = false;
            }
            if (entry.availability.wednesday14 == true) {
                document.getElementById("wednesday14").disabled = false;
            }
            if (entry.availability.wednesday15 == true) {
                document.getElementById("wednesday15").disabled = false;
            }
            if (entry.availability.wednesday16 == true) {
                document.getElementById("wednesday16").disabled = false;
            }
            // check what times on thursday are available
            if (entry.availability.thursday10 == true) {
                document.getElementById("thursday10").disabled = false;
            }
            if (entry.availability.thursday11 == true) {
                document.getElementById("thursday11").disabled = false;
            }
            if (entry.availability.thursday12 == true) {
                document.getElementById("thursday12").disabled = false;
            }
            if (entry.availability.thursday14 == true) {
                document.getElementById("thursday14").disabled = false;
            }
            if (entry.availability.thursday15 == true) {
                document.getElementById("thursday15").disabled = false;
            }
            if (entry.availability.thursday16 == true) {
                document.getElementById("thursday16").disabled = false;
            }
            // check what times on friday are available
            if (entry.availability.friday10 == true) {
                document.getElementById("friday10").disabled = false;
            }
            if (entry.availability.friday11 == true) {
                document.getElementById("friday11").disabled = false;
            }
            if (entry.availability.friday12 == true) {
                document.getElementById("friday12").disabled = false;
            }
            if (entry.availability.friday14 == true) {
                document.getElementById("friday14").disabled = false;
            }
            if (entry.availability.friday15 == true) {
                document.getElementById("friday15").disabled = false;
            }
            if (entry.availability.friday16 == true) {
                document.getElementById("friday16").disabled = false;
            }
        })
    });

}

function getAvailabilitiesByTopicAndDuration(duration, userId) {
    let topicChoice = document.getElementById("topic-filter").value;
    if (duration === "any") {
        getMentorsByTopic(topicChoice);
    } else {
        let times = document.getElementsByClassName("resetToDisabled");
        for (let i = 0; i < times.length; i++) {
            times[i].disabled = true;
        }
        const menteeUrl = 'http://localhost:8080/api/employees/mentors/' + topicChoice + '/duration/' + duration + '/excludingUser/' + userId;
        $.getJSON(menteeUrl, function (data) {
            $.each(data, function (key, entry) {


                // change this to loop through availability
                // check what times on monday are available
                if (entry.availability.monday10 == true) {
                    document.getElementById("monday10").disabled = false;
                }
                if (entry.availability.monday11 == true) {
                    document.getElementById("monday11").disabled = false;
                }
                if (entry.availability.monday12 == true) {
                    document.getElementById("monday12").disabled = false;
                }
                if (entry.availability.monday14 == true) {
                    document.getElementById("monday14").disabled = false;
                }
                if (entry.availability.monday15 == true) {
                    document.getElementById("monday15").disabled = false;
                }
                if (entry.availability.monday16 == true) {
                    document.getElementById("monday16").disabled = false;
                }
                // check what times on tuesday are available
                if (entry.availability.tuesday10 == true) {
                    document.getElementById("tuesday10").disabled = false;
                }
                if (entry.availability.tuesday11 == true) {
                    document.getElementById("tuesday11").disabled = false;
                }
                if (entry.availability.tuesday12 == true) {
                    document.getElementById("tuesday12").disabled = false;
                }
                if (entry.availability.tuesday14 == true) {
                    document.getElementById("tuesday14").disabled = false;
                }
                if (entry.availability.tuesday15 == true) {
                    document.getElementById("tuesday15").disabled = false;
                }
                if (entry.availability.tuesday16 == true) {
                    document.getElementById("tuesday16").disabled = false;
                }
                // check what times on wednesday are available
                if (entry.availability.wednesday10 == true) {
                    document.getElementById("wednesday10").disabled = false;
                }
                if (entry.availability.wednesday11 == true) {
                    document.getElementById("wednesday11").disabled = false;
                }
                if (entry.availability.wednesday12 == true) {
                    document.getElementById("wednesday12").disabled = false;
                }
                if (entry.availability.wednesday14 == true) {
                    document.getElementById("wednesday14").disabled = false;
                }
                if (entry.availability.wednesday15 == true) {
                    document.getElementById("wednesday15").disabled = false;
                }
                if (entry.availability.wednesday16 == true) {
                    document.getElementById("wednesday16").disabled = false;
                }
                // check what times on thursday are available
                if (entry.availability.thursday10 == true) {
                    document.getElementById("thursday10").disabled = false;
                }
                if (entry.availability.thursday11 == true) {
                    document.getElementById("thursday11").disabled = false;
                }
                if (entry.availability.thursday12 == true) {
                    document.getElementById("thursday12").disabled = false;
                }
                if (entry.availability.thursday14 == true) {
                    document.getElementById("thursday14").disabled = false;
                }
                if (entry.availability.thursday15 == true) {
                    document.getElementById("thursday15").disabled = false;
                }
                if (entry.availability.thursday16 == true) {
                    document.getElementById("thursday16").disabled = false;
                }
                // check what times on friday are available
                if (entry.availability.friday10 == true) {
                    document.getElementById("friday10").disabled = false;
                }
                if (entry.availability.friday11 == true) {
                    document.getElementById("friday11").disabled = false;
                }
                if (entry.availability.friday12 == true) {
                    document.getElementById("friday12").disabled = false;
                }
                if (entry.availability.friday14 == true) {
                    document.getElementById("friday14").disabled = false;
                }
                if (entry.availability.friday15 == true) {
                    document.getElementById("friday15").disabled = false;
                }
                if (entry.availability.friday16 == true) {
                    document.getElementById("friday16").disabled = false;
                }
            })
        });
    }
}

// ensures only one time slot is selected
$(document).ready(function () {
    $('.resetToDisabled').click(function () {
        $('.resetToDisabled').not(this).prop('checked', false);
    });
});


function getOdysseysByMentee(userId) {
    populateTopicDropdown(userId);
    const menteeOdysseysURL = 'http://localhost:8080/api/odysseys/getOdysseysByMentee/' + userId;
    $.getJSON(menteeOdysseysURL, function (data) {
        if (!data.length) {
            document.getElementById("mentee-page-odyssey-title").innerHTML = "";
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
                odysseyTypeTitle.innerHTML = "Mentee";
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
                odysseyPartnerLabel.innerHTML = "Mentor:";
                odysseyPartner.appendChild(odysseyPartnerLabel);
                var odysseyPartnerTitle = document.createElement("h2");
                odysseyPartnerTitle.innerHTML = entry.mentor.firstName;
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

                odysseyCard.appendChild(odysseyCardMain);

                var odysseyMoreDetails = document.createElement("div");
                odysseyMoreDetails.className = "odyssey-more-details";
                odysseyMoreDetails.id = "odysseyMoreDetails"+entry.id;
                odysseyMoreDetails.style.display = "none";

                for (let i = 0; i < entry.odysseyMeetings.length; i++) {
                    var odysseyMeeting = document.createElement("div");
                    odysseyMeeting.className = "odyssey-meeting";

                    var meetingHeader = document.createElement("h2");
                    meetingHeader.innerHTML = "Meeting " + (i + 1);
                    odysseyMeeting.appendChild(meetingHeader);

                    var meetingDate = document.createElement("h4");
                    meetingDate.innerHTML = "Date: " + entry.odysseyMeetings[i].date;
                    odysseyMeeting.appendChild(meetingDate);

                    var meetingDayAndTime = document.createElement("h4");
                    meetingDayAndTime.innerHTML = "Time and Day: " + entry.odysseyMeetings[i].time + " " + entry.odysseyMeetings[i].day;
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
                        editMeetingNoteModal(entry.odysseyMeetings[i].id, entry.odysseyMeetings[i].meetingNote);
                    };

                    if (entry.odysseyMeetings[i].isCompleted) {
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

                var hideMoreDetailsButton = document.createElement("button");
                hideMoreDetailsButton.className = "btn btn-danger btn-sm more-details-button margin-top";
                hideMoreDetailsButton.innerHTML = "X";
                hideMoreDetailsButton.onclick = function () {
                    displayMoreDetails(entry.id);
                };

                odysseyMoreDetails.appendChild(hideMoreDetailsButton);

                odysseyCard.appendChild(odysseyMoreDetails);

                // Add card to list
                document.getElementById("odyssey-list-by-mentee").appendChild(odysseyCard);
            })
        }
    });
}

function displayMoreDetails(odysseyId) {
    if (document.getElementById("odysseyMoreDetails"+odysseyId).style.display === "none") {
        document.getElementById("odysseyMoreDetails"+odysseyId).style.display = "block";
    } else {
        document.getElementById("odysseyMoreDetails"+odysseyId).style.display = "none";
    }
}

function editMeetingNoteModal(meetingId, currentNote) {
    // create modal with post for a meetings notes
    $('#edit-meeting-note-modal').modal('show');
    var action = document.getElementById("edit-meeting-note-form");
    action.action = "../api/odysseyMeetings/" + meetingId + "/note";

    if (currentNote != null || currentNote !== "") {
        var existingNote = document.getElementById("meetingNote");
        existingNote.innerHTML = currentNote;
    }
    // disable button if meeting is not complete
}


