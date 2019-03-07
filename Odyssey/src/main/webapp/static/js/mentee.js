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
        var inputElement = document.createElement('input');
        inputElement.type = "button";
        inputElement.addEventListener('click', function(){
            myFunction(entry.topic.name,entry.mentorDuration,entry.id,entry.availability);
        });
        mentorList.append(inputElement);
        mentorList.append($('</div>'));
    })
});

function myFunction(topic,mentorDuration,mentorId,availability) {

    document.getElementById("mentorId").value = mentorId;
    document.getElementById("topicId").value = topic;
    document.getElementById("mentorDuration").value = mentorDuration;
    document.getElementById("userId").value = 1;

    let dropdown = $('#availability-dropdown');
    dropdown.empty();
    dropdown.append('<option selected="true" disabled>Choose Day</option>');
    dropdown.prop('selectedIndex', 0);

    if (availability.monday == true) {
        dropdown.append($('<option></option>').attr('value', "monday").text("Monday"));
    } if (availability.tuesday == true) {
        dropdown.append($('<option></option>').attr('value', "tuesday").text("Tuesday"));
    } if (availability.wednesday == true) {
        dropdown.append($('<option></option>').attr('value', "wednesday").text("Wednesday"));
    } if (availability.thursday == true) {
        dropdown.append($('<option></option>').attr('value', "thursday").text("Thursday"));
    } if (availability.friday == true) {
        dropdown.append($('<option></option>').attr('value', "friday").text("Friday"));
    }

    $('#odyssey-modal').modal('show');

}

