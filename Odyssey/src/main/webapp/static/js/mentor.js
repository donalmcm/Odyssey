let mentorDropdown = $('#topic-dropdown');

mentorDropdown.empty();

mentorDropdown.append('<option selected="true" disabled>Choose Topic</option>');
mentorDropdown.prop('selectedIndex', 0);

const topicMentorUrl = '/api/topics';

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
        const employeeURL = '/api/employees/' + userId;
        var mentorTopic, mentorDuration;
        var mentorAvailability = [];
        $.getJSON(employeeURL, function (data) {
            document.getElementById("topic-value").innerHTML = data.topic.name;
            document.getElementById("duration-value").innerHTML = data.mentorDuration;
            mentorAvailability = checkAvailability(data);
            for (var i = 0; i < mentorAvailability.length; i++) {
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
    const mentorOdysseysUrl = '/api/odysseys/getOdysseysByMentor/' + userId;
    $.getJSON(mentorOdysseysUrl, function (data) {
        if (!data.length) {
            document.getElementById("mentor-page-odyssey-title").innerHTML = "";
        } else {
            $.each(data, function (key, entry) {

                var odysseyCard = document.createElement("div");
                odysseyCard.className = "odyssey-card";

                var odysseyCardMain = document.createElement("div");
                odysseyCardMain.className = "odyssey-card-main";
                if (!entry.active) {
                    // set text to active or not active
                }

                // Type - Mentor
                var odysseyType = document.createElement("div"); // left inner div
                odysseyType.className = "col-md-2 odyssey-type";
                var odysseyTypeTitle = document.createElement("h1");
                odysseyTypeTitle.innerHTML = "Mentor";
                odysseyType.appendChild(odysseyTypeTitle);

                // Review with button
                var reviews = document.createElement("div");
                reviews.className = "reviews";
                if (!entry.complete) {
                    reviews.style.display = "none";
                }
                var reviewsLabel = document.createElement("h4");
                reviewsLabel.innerHTML = "For Reviews :";
                reviews.appendChild(reviewsLabel);
                var reviewsButton = document.createElement("button");
                reviewsButton.className = "btn btn-primary btn-lg more-details-button";
                reviewsButton.innerHTML = "Click Here";
                reviewsButton.onclick = function () {
                    displayReviews(entry.id);
                };
                reviews.appendChild(reviewsButton);

                odysseyType.appendChild(reviews);

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
                odysseyPartnerLabel.innerHTML = "Mentee:";
                odysseyPartner.appendChild(odysseyPartnerLabel);
                var odysseyPartnerTitle = document.createElement("h2");
                odysseyPartnerTitle.innerHTML = entry.mentee.firstName;
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
                odysseyMoreDetails.id = "odysseyMoreDetails" + entry.id;
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

                // Review section
                var odysseyReviews = document.createElement("div");
                odysseyReviews.className = "odyssey-reviews";
                odysseyReviews.id = "odysseyReviews" + entry.id;
                odysseyReviews.style.display = "none";

                for (let i = 0; i < entry.odysseyReviews.length; i++) {
                    var odysseyReview = document.createElement("div");
                    odysseyReview.className = "odyssey-review";

                    var reviewHeader = document.createElement("h2");
                    odysseyReview.appendChild(reviewHeader);
                    if (entry.odysseyReviews[i].submitted) {
                        var punctuality = document.createElement("h4");
                        punctuality.innerHTML = "Punctuality: " + entry.odysseyReviews[i].punctuality + "/5 ";
                        odysseyReview.appendChild(punctuality);

                        var attendance = document.createElement("h4");
                        attendance.innerHTML = "Attendance: " + entry.odysseyReviews[i].attendance + "/5 ";
                        odysseyReview.appendChild(attendance);

                        // Only display for mentee's
                        var courseMaterial = document.createElement("h4");
                        courseMaterial.innerHTML = "Course Material: " + entry.odysseyReviews[i].courseMaterial + "/5 ";
                        odysseyReview.appendChild(courseMaterial);

                        // Only display for mentor's
                        var menteeEngagement = document.createElement("h4");
                        menteeEngagement.innerHTML = "Mentee Engagement: " + entry.odysseyReviews[i].menteeEngagement + "/5 ";
                        odysseyReview.appendChild(menteeEngagement);

                        var rating = document.createElement("h4");
                        rating.innerHTML = "rating: " + entry.odysseyReviews[i].rating + "/5 ";
                        odysseyReview.appendChild(rating);

                        var overallExperience = document.createElement("div");
                        var overallExperienceLabel = document.createElement("h4");
                        overallExperienceLabel.innerHTML = "Overall Experience: ";
                        overallExperience.appendChild(overallExperienceLabel);
                        var overallExperienceText = document.createElement("p");
                        overallExperienceText.innerHTML = entry.odysseyReviews[i].overallExperience;
                        overallExperience.appendChild(overallExperienceText);

                        let reviewButton = document.createElement("button");
                        reviewButton.className = "btn btn-primary btn-lg reviews-button";
                        reviewButton.onclick = function () {
                            editReviewModal(entry.odysseyReviews[i].id, entry.odysseyReviews[i].punctuality,
                                entry.odysseyReviews[i].attendance, entry.odysseyReviews[i].courseMaterial,
                                entry.odysseyReviews[i].menteeEngagement, entry.odysseyReviews[i].rating,
                                entry.odysseyReviews[i].overallExperience, entry.odysseyReviews[i].menteeReview,
                                entry.odysseyReviews[i].mentorReview);
                        };

                        reviewButton.innerHTML = "Edit Review";

                        if (entry.odysseyReviews[i].mentorReview) {
                            reviewHeader.innerHTML = "Mentor Review";
                            courseMaterial.style.display = "none";
                        } else {
                            reviewHeader.innerHTML = "Mentee Review";
                            menteeEngagement.style.display = "none";
                            reviewButton.style.display = "none";

                        }
                        overallExperience.appendChild(reviewButton);

                        odysseyReview.appendChild(overallExperience);
                    } else {
                        var noReview = document.createElement("h4");
                        noReview.innerHTML = "No review has been left";
                        odysseyReview.appendChild(noReview);

                        let reviewButton = document.createElement("button");
                        reviewButton.className = "btn btn-primary btn-lg reviews-button";
                        reviewButton.onclick = function () {
                            editReviewModal(entry.odysseyReviews[i].id, entry.odysseyReviews[i].punctuality,
                                entry.odysseyReviews[i].attendance, entry.odysseyReviews[i].courseMaterial,
                                entry.odysseyReviews[i].menteeEngagement, entry.odysseyReviews[i].rating,
                                entry.odysseyReviews[i].overallExperience, entry.odysseyReviews[i].menteeReview,
                                entry.odysseyReviews[i].mentorReview);
                        };
                        reviewButton.innerHTML = "Add Review";
                        if (entry.odysseyReviews[i].mentorReview) {
                            reviewHeader.innerHTML = "Mentor Review";

                        } else {
                            reviewHeader.innerHTML = "Mentee Review";
                            reviewButton.style.display = "none";
                        }

                        odysseyReview.appendChild(reviewButton);
                    }

                    // last line
                    odysseyReviews.appendChild(odysseyReview);
                }

                var hideReviewsButton = document.createElement("button");
                hideReviewsButton.className = "btn btn-danger btn-sm review-button margin-top";
                hideReviewsButton.innerHTML = "X";
                hideReviewsButton.onclick = function () {
                    displayReviews(entry.id);
                };

                odysseyReviews.appendChild(hideReviewsButton);

                odysseyCard.appendChild(odysseyReviews);

                // Add card to list
                document.getElementById("odyssey-list-by-mentor").appendChild(odysseyCard);
            })
        }
    });
}

function displayMoreDetails(odysseyId) {
    if (document.getElementById("odysseyMoreDetails" + odysseyId).style.display === "none") {
        document.getElementById("odysseyMoreDetails" + odysseyId).style.display = "block";
    } else {
        document.getElementById("odysseyMoreDetails" + odysseyId).style.display = "none";
    }
}

function displayReviews(odysseyId) {
    if (document.getElementById("odysseyReviews" + odysseyId).style.display === "none") {
        document.getElementById("odysseyReviews" + odysseyId).style.display = "block";
    } else {
        document.getElementById("odysseyReviews" + odysseyId).style.display = "none";
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

function editReviewModal(reviewId, currentPunctuality, currentAttendance, currentCourseMaterial, currentMenteeEngagement,
                         currentRating, currentOverallExperience, menteeReview, mentorReview) {
    // create modal with post for a meetings notes
    $('#odyssey-review-modal').modal('show');
    var action = document.getElementById("odyssey-review-form");
    action.action = "../api/reviews/edit/" + reviewId;

    if (menteeReview) {
        document.getElementById("mentee-engagement-section").style.display = "none";
    } else {
        document.getElementById("course-material-section").style.display = "none";
    }

    if (currentPunctuality !== 0) {
        document.getElementById("punctuality").value = currentPunctuality;
    }
    if (currentAttendance !== 0) {
        document.getElementById("attendance").value = currentAttendance;
    }
    if (currentCourseMaterial !== 0) {
        document.getElementById("courseMaterial").value = currentCourseMaterial;
    }
    if (currentMenteeEngagement !== 0) {
        document.getElementById("menteeEngagement").value = currentMenteeEngagement;
    }
    if (currentRating !== 0) {
        document.getElementById("rating").value = currentRating;
    }
    if (currentOverallExperience !== 0) {
        document.getElementById("overallExperience").innerHTML = currentOverallExperience;
    }
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
