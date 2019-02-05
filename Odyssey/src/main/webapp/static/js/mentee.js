// List all mentors with details
let mentorList = $('#mentor-list');
const url = 'http://localhost:8080/api/employees/mentors';
$.getJSON(url, function (data) {
    $.each(data, function (key, entry) {
        mentorList.append($('<div>'));
        mentorList.append($('<p></p>').attr('value', entry.topic.name).text("Topic: " + entry.topic.name));
        mentorList.append($('<p></p>').attr('value', entry.mentorDuration).text("Duration: " + entry.mentorDuration));
        mentorList.append($('<p>Mentor Availability</p>'));
        mentorList.append($('<p></p>').attr('value', entry.availability.monday).text("Monday: " + entry.availability.monday));
        mentorList.append($('<p></p>').attr('value', entry.availability.tuesday).text("Tuesday: " + entry.availability.tuesday));
        mentorList.append($('<p></p>').attr('value', entry.availability.wednesday).text("Wednesday: " + entry.availability.wednesday));
        mentorList.append($('<p></p>').attr('value', entry.availability.thursday).text("Thursday: " + entry.availability.thursday));
        mentorList.append($('<p></p>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
        mentorList.append($('<button onclick="createOdyssey(mentor,mentee,topic)"></button>').text("Create Odyssey")); //
        mentorList.append($('</div>'));
    })
});


function getMentorsByTopic(value) {
    let mentorByTopicList = $('#mentor-by-topic-list');

    $.getJSON(topic, url, function (data) {
    const url = 'http://localhost:8080/api/employees/mentorsByTopic/' + value;
    $.each(data, function (key, entry) {
        mentorList.append($('<div>'));
        mentorList.append($('<p></p>').attr('value', entry.topic.name).text("Topic: " + entry.topic.name));
        mentorList.append($('<p></p>').attr('value', entry.mentorDuration).text("Duration: " + entry.mentorDuration));
        mentorList.append($('<p>Mentor Availability</p>'));
        mentorList.append($('<p></p>').attr('value', entry.availability.monday).text("Monday: " + entry.availability.monday));
        mentorList.append($('<p></p>').attr('value', entry.availability.tuesday).text("Tuesday: " + entry.availability.tuesday));
        mentorList.append($('<p></p>').attr('value', entry.availability.wednesday).text("Wednesday: " + entry.availability.wednesday));
        mentorList.append($('<p></p>').attr('value', entry.availability.thursday).text("Thursday: " + entry.availability.thursday));
        mentorList.append($('<p></p>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
        mentorList.append($('<button></button>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
        mentorList.append($('</div>'));
    })
});
}

// template function for creating an odyssey
function createOdyssey(mentor, mentee, topic) {
    const createOdysseyUrl = 'http://localhost:8080/api/odyssey/create/' + mentor + mentee + topic;
}

