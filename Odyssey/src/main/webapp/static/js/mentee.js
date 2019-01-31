let mentorList = $('#mentor-list');


const url = 'http://localhost:8080/api/employees/mentors';

// Populate dropdown with list of topics
$.getJSON(url, function (data) {
    $.each(data, function (key, entry) {
        mentorList.append($('<div class="mentor-card" style="border: #2196F3 3px">'));
        mentorList.append($('<p></p>').attr('value', entry.topic.name).text("Topic: " + entry.topic.name));
        mentorList.append($('<p></p>').attr('value', entry.mentorDuration).text("Duration: " + entry.mentorDuration));
        mentorList.append($('<p>Mentor Availability</p>'));
        mentorList.append($('<p></p>').attr('value', entry.availability.monday).text("Monday: " + entry.availability.monday));
        mentorList.append($('<p></p>').attr('value', entry.availability.tuesday).text("Tuesday: " + entry.availability.tuesday));
        mentorList.append($('<p></p>').attr('value', entry.availability.wednesday).text("Wednesday: " + entry.availability.wednesday));
        mentorList.append($('<p></p>').attr('value', entry.availability.thursday).text("Thursday: " + entry.availability.thursday));
        mentorList.append($('<p></p>').attr('value', entry.availability.friday).text("Friday: " + entry.availability.friday));
        mentorList.append($('</div>'));
    })
});

const odyssey = 'http://localhost:8080';

// $.postJSON(odyssey, function(data)) {
//
// }