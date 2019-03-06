// List all mentors with details
//let mentorList = $('#mentor-list');
// const mentorUrl = 'http://localhost:8080/api/employees/mentors';
// $.getJSON(mentorUrl, function (data) {
//     $.each(data, function (key, entry) {
//         mentorList.append($('<div>'));
//         mentorList.append($('<p></p>').attr('value', entry.topic.name).text("Topic: " + entry.topic.name));
//         mentorList.append($('<p></p>').attr('value', entry.mentorDuration).text("Duration: " + entry.mentorDuration));
//         mentorList.append($('<p>Mentor Availability</p>'));
//         mentorList.append($('<p></p>').attr('value', entry.availability.monday).text("Monday: " + entry.availability.monday));
//         mentorList.append($('<p></p>').attr('value', entry.availability.tuesday).text("Tuesday: " + entry.availability.tuesday));
//         mentorList.append($('<p></p>').attr('value', entry.availability.wednesday).text("Wednesday: " + entry.availability.wednesday));
//         mentorList.append($('<p></p>').attr('value', entry.availability.thursday).text("Thursday: " + entry.availability.thursday));
//         mentorList.append($('<p></p>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
//         mentorList.append($('<button onclick="createOdyssey(mentor,mentee,entry.topic.name)"></button>').text("Create Odyssey")); //
//         mentorList.append($('</div>'));
//     })
// });
let mentorByTopicList = $('#mentor-list');


function getMentorsByTopic() {
    var topic = document.getElementById('topic-dropdown').value;
    topic = topic.replace(/['"]+/g, '');
    const url = 'http://localhost:8080/api/employees/mentorsByTopic/' + topic;
    $.getJSON(url, function (data) {
        $.each(data, function (key, entry) {
        mentorByTopicList.append($('<div>'));
        mentorByTopicList.append($('<p></p>').attr('value', entry.topic.name).text("Topic: " + entry.topic.name));
        mentorByTopicList.append($('<p></p>').attr('value', entry.mentorDuration).text("Duration: " + entry.mentorDuration));
        mentorByTopicList.append($('<p>Mentor Availability</p>'));
        mentorByTopicList.append($('<p></p>').attr('value', entry.availability.monday).text("Monday: " + entry.availability.monday));
        mentorByTopicList.append($('<p></p>').attr('value', entry.availability.tuesday).text("Tuesday: " + entry.availability.tuesday));
        mentorByTopicList.append($('<p></p>').attr('value', entry.availability.wednesday).text("Wednesday: " + entry.availability.wednesday));
        mentorByTopicList.append($('<p></p>').attr('value', entry.availability.thursday).text("Thursday: " + entry.availability.thursday));
        mentorByTopicList.append($('<p></p>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
        mentorByTopicList.append($('<button></button>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
        mentorByTopicList.append($('</div>'));
    })
});
}

// template function for creating an odyssey
function createOdyssey(mentor, mentee, topic) {
    const createOdysseyUrl = 'http://localhost:8080/api/odyssey/create/' + mentor + mentee + topic;
}

