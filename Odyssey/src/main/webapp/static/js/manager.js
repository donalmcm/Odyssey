let mentorList = $('#managers-team');


const url = 'http://localhost:8080/api/employees/byManager/';

// Populate dropdown with list of topics
$.getJSON(url, function (data) {
    $.each(data, function (key, entry) {
        mentorList.append($('<tr>'));
        mentorList.append($('<td></td>').attr('value', entry.id).text(entry.id));
        mentorList.append($('<td></td>').attr('value', entry.firstName).text(entry.firstName));
        mentorList.append($('<td></td>').attr('value', entry.lastName).text(entry.lastName));
        mentorList.append($('<td></td>').attr('value', entry.email).text(entry.email));
        mentorList.append($('</tr>'));

    })
});
