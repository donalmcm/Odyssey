function getEmployeeStats(userId) {
    const odysseyCountWhereMentorURL = 'http://localhost:8080/api/employees/getOdysseyCountWhereMentor/' + userId;
    const odysseyCountWhereMenteeURL = 'http://localhost:8080/api/employees/getOdysseyCountWhereMentee/' + userId;
    const mentorRatingURL = 'http://localhost:8080/api/employees/getMentorRating/' + userId;
    const menteeRatingURL = 'http://localhost:8080/api/employees/getMenteeRating/' + userId;

    // Get mentor rating
    $.getJSON(mentorRatingURL, function (data) {
        if(data.mentorRating === 0) {
            document.getElementById("mentor-rating").innerHTML = " Rating: You have not yet been rated";
        } else {
            document.getElementById("mentor-rating").innerHTML = "Rating: "+data.mentorRating;
        }
    });

    // Get mentee rating
    $.getJSON(menteeRatingURL, function (data) {
        if(data.menteeRating === 0) {
            document.getElementById("mentee-rating").innerHTML = " Rating: You have not yet been rated";
        } else {
            document.getElementById("mentee-rating").innerHTML = "Rating: "+data.menteeRating;
        }
    });

    $.getJSON(odysseyCountWhereMentorURL, function (data) {
        document.getElementById("mentor-odyssey-count").innerHTML = data.odysseyCount;
        document.getElementById("mentor-hour-count").innerHTML = data.meetingHours;

        var topics = document.getElementById("mentor-topic-list");
        for (var i = 0; i < data.topics.length; i++) {
            var topic = document.createElement("h1");
            topic.innerHTML = data.topics[i];
            topics.appendChild(topic);
        }
    });

    $.getJSON(odysseyCountWhereMenteeURL, function (data) {
        document.getElementById("mentee-odyssey-count").innerHTML = data.odysseyCount;
        document.getElementById("mentee-hour-count").innerHTML = data.meetingHours;

        var topics = document.getElementById("mentee-topic-list");
        for (var i = 0; i < data.topics.length; i++) {
            var topic = document.createElement("h1");
            topic.innerHTML = data.topics[i];
            topics.appendChild(topic);
        }
    });
}
