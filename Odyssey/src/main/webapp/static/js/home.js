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
                var odysseyTypeTitleDiv = document.createElement("div");
                var odysseyTypeTitle = document.createElement("h1");
                odysseyTypeTitleDiv.appendChild(odysseyTypeTitle);
                odysseyType.appendChild(odysseyTypeTitleDiv);

                // Review with button
                var reviews = document.createElement("div");
                reviews.className = "reviews";
                if(!entry.complete) {
                reviews.style.display = "none"
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

                // More Details section
                var odysseyMoreDetails = document.createElement("div");
                odysseyMoreDetails.className = "odyssey-more-details";
                odysseyMoreDetails.id = "odysseyMoreDetails"+entry.id;
                odysseyMoreDetails.style.display = "none";

                var odysseyMoreDetailsTitle = document.createElement("h1");
                odysseyMoreDetailsTitle.innerHTML = "More Details";
                odysseyMoreDetails.appendChild(odysseyMoreDetailsTitle);

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

                // Attaching more details to odyssey card
                odysseyCard.appendChild(odysseyMoreDetails);

                // Odyssey Review section
                var odysseyReviews = document.createElement("div");
                odysseyReviews.className = "odyssey-reviews";
                odysseyReviews.id = "odysseyReviews"+entry.id;
                odysseyReviews.style.display = "none";

                var odysseyReviewsTitle = document.createElement("h1");
                odysseyReviewsTitle.innerHTML = "Reviews";
                odysseyReviews.appendChild(odysseyReviewsTitle);

                // Odyssey Review - mentor review
                var mentorReview = document.createElement("div");
                mentorReview.className = "mentor-review";

                var mentorReviewHeader = document.createElement("h2");
                mentorReviewHeader.innerHTML = "Mentor Review";
                mentorReviewHeader.appendChild(mentorReview);

                if(entry.mentorReview != null) {
                    var menteePunctuality = document.createElement("h4");
                    menteePunctuality.innerHTML = "Mentee Punctuality: " + entry.mentorReview.punctuality + "/5";
                    mentorReview.appendChild(menteePunctuality);

                    var menteeAttendance = document.createElement("h4");
                    menteeAttendance.innerHTML = "Mentee Attendance: " + entry.mentorReview.attendance + "/5";
                    mentorReview.appendChild(menteeAttendance);

                    var menteeEngagement = document.createElement("h4");
                    menteeEngagement .innerHTML = "Mentee Engagement: " + entry.mentorReview.menteeEngagement + "/5";
                    mentorReview.appendChild(menteeEngagement );

                    var mentorRating = document.createElement("h4");
                    menteeEngagement .innerHTML = "Odyssey Rating: " + entry.mentorReview.rating + "/5";
                    mentorReview.appendChild(menteeEngagement );

                    var mentorOverallExperience = document.createElement("div");
                    var mentorOverallExperienceLabel = document.createElement("h4");
                    mentorOverallExperienceLabel.innerHTML = "Overall Experience: ";
                    mentorOverallExperience.appendChild(mentorOverallExperienceLabel);

                    var mentorOverallExperienceText = document.createElement("p");
                    mentorOverallExperienceText.innerHTML = entry.mentorReview.overallExperience;
                    mentorOverallExperience.appendChild(mentorOverallExperienceText);

                    var mentorEditReviewButton = document.createElement("button");
                    if (userName === entry.mentee.firstName) {
                        mentorEditReviewButton.style.display = "none";
                    }
                    mentorEditReviewButton.className = "btn btn-success btn-lg more-details-button";
                    mentorEditReviewButton.innerHTML = "Edit Review";
                    mentorEditReviewButton.onclick = function () {
                        createReviewModal('mentor',entry.mentorReview.id,'edit');
                    };

                    mentorOverallExperience.appendChild(mentorCreateReviewButton);
                    mentorReview.appendChild(mentorOverallExperience);
                } else {
                    var noMentorReview = document.createElement("h4");
                    noMentorReview.innerHTML = "Mentor review not yet submitted";
                    mentorReview.appendChild(noMentorReview);

                    var mentorCreateReviewButtonDiv = document.createElement("div");
                    var mentorCreateReviewButton = document.createElement("button");
                    if (userName === entry.mentee.firstName) {
                        mentorCreateReviewButton.style.display = "none";
                    }
                    mentorCreateReviewButton.className = "btn btn-success btn-lg more-details-button margin-top";
                    mentorCreateReviewButton.innerHTML = "Create Review";
                    mentorCreateReviewButton.onclick = function () {
                        createReviewModal('mentor',undefined,'create');
                    };
                    mentorCreateReviewButtonDiv.appendChild(mentorCreateReviewButton);

                    noMentorReview.appendChild(mentorCreateReviewButtonDiv);
                    mentorReview.appendChild(noMentorReview);
                }

                // Attach mentor review to list of reviews
                odysseyReviews.appendChild(mentorReview);

                // Odyssey Review - mentee review
                var menteeReview = document.createElement("div");
                menteeReview.className = "mentee-review";

                var menteeReviewHeader = document.createElement("h2");
                menteeReviewHeader.innerHTML = "Mentee Review";
                menteeReviewHeader.appendChild(menteeReview);


                if(entry.menteeReview != null) {
                    var mentorPunctuality = document.createElement("h4");
                    mentorPunctuality.innerHTML = "Mentor Punctuality: " + entry.menteeReview.punctuality + "/5";
                    menteeReview.appendChild(mentorPunctuality);

                    var mentorAttendance = document.createElement("h4");
                    mentorAttendance.innerHTML = "Mentor Attendance: " + entry.menteeReview.attendance + "/5";
                    menteeReview.appendChild(mentorAttendance);

                    var courseMaterial = document.createElement("h4");
                    courseMaterial .innerHTML = "Course Material: " + entry.menteeReview.courseMaterial + "/5";
                    menteeReview.appendChild(menteeEngagement );

                    var menteeRating = document.createElement("h4");
                    menteeRating .innerHTML = "Odyssey Rating: " + entry.menteeReview.rating + "/5";
                    menteeReview.appendChild(menteeRating);

                    var menteeOverallExperience = document.createElement("div");
                    var menteeOverallExperienceLabel = document.createElement("h4");
                    menteeOverallExperienceLabel.innerHTML = "Overall Experience: ";
                    menteeOverallExperience.appendChild(menteeOverallExperienceLabel);

                    var menteeOverallExperienceText = document.createElement("p");
                    menteeOverallExperienceText.innerHTML = entry.menteeReview.overallExperience;
                    menteeOverallExperience.appendChild(menteeOverallExperienceText);

                    var menteeEditReviewButton = document.createElement("button");
                    if (userName === entry.mentor.firstName) {
                        menteeEditReviewButton.disabled = true;
                    }
                    menteeEditReviewButton.className = "btn btn-success btn-lg more-details-button";
                    menteeEditReviewButton.innerHTML = "Edit Review";
                    menteeEditReviewButton.onclick = function () {
                        createReviewModal('mentee',entry.menteeReview.id,'edit');
                    };

                    menteeOverallExperience.appendChild(menteeCreateReviewButton);
                    menteeReview.appendChild(menteeOverallExperience);
                } else {
                    var noMenteeReview = document.createElement("h4");
                    noMenteeReview.innerHTML = "Mentee review not yet submitted";
                    menteeReview.appendChild(noMenteeReview);

                    var menteeCreateReviewButtonDiv = document.createElement("div");
                    var menteeCreateReviewButton = document.createElement("button");
                    if (userName === entry.mentor.firstName) {
                        menteeCreateReviewButton.disabled = true;
                    }
                    menteeCreateReviewButton.className = "btn btn-success btn-lg more-details-button margin-top";
                    menteeCreateReviewButton.innerHTML = "Create Review";
                    menteeCreateReviewButton.onclick = function () {
                        createReviewModal('mentee',undefined,'create');
                    };
                    menteeCreateReviewButtonDiv.appendChild(menteeCreateReviewButton);

                    noMenteeReview.appendChild(menteeCreateReviewButtonDiv);
                    menteeReview.appendChild(noMenteeReview);
                }
                // Attach mentee review to reviews
                odysseyReviews.appendChild(menteeReview);

                var hideReviewsButton = document.createElement("button");
                hideReviewsButton.className = "btn btn-danger btn-sm more-details-button margin-top";
                hideReviewsButton.innerHTML = "X";
                hideReviewsButton.onclick = function () {
                    displayReviews(entry.id);
                };

                odysseyReviews.appendChild(hideReviewsButton);

                odysseyCard.appendChild(odysseyReviews);

                // Add card to list
                document.getElementById("odyssey-list").appendChild(odysseyCard);
            })
        }
    });
}

function displayReviews(odysseyId) {
    if (document.getElementById("odysseyReviews"+odysseyId).style.display === "none") {
        document.getElementById("odysseyReviews"+odysseyId).style.display = "block";
    } else {
        document.getElementById("odysseyReviews"+odysseyId).style.display = "none";
    }
}
function displayMoreDetails(odysseyId) {
    if (document.getElementById("odysseyMoreDetails"+odysseyId).style.display === "none") {
        document.getElementById("odysseyMoreDetails"+odysseyId).style.display = "block";
    } else {
        document.getElementById("odysseyMoreDetails"+odysseyId).style.display = "none";
    }
}

function createReviewModal(userType,reviewId,type) {
    if(userType === 'mentor') {
        document.getElementById("course-material-section").style.display = "none";
    } else {
        document.getElementById("mentee-engagement-section").style.display = "none";
    }
    $('#odyssey-review-modal').modal('show');

    var action = document.getElementById("odyssey-review-form");
    if(type === 'create') {
        action.action = "../api/reviews/create";
    } else {
        action.action = "../api/reviews/edit/"+reviewId;
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


