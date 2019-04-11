// Populate table with list of employees with id, both names and email
let odysseyEmployeeList = $('#odyssey-list');

function getOdysseys(userId) {
    const adminEmployeeUrl = 'http://localhost:8080/api/odysseys/getEmployeeOdysseys/'+userId;
    $.getJSON(adminEmployeeUrl, function (data) {
        $.each(data, function (key, entry) {
            odysseyEmployeeList.append($('<tr>'));
            odysseyEmployeeList.append($('<td></td>').attr('value', entry.topic.name).text(entry.topic.name));
            odysseyEmployeeList.append($('<td></td>').attr('value', entry.mentor.firstName).text(entry.mentor.firstName));
            odysseyEmployeeList.append($('<td></td>').attr('value', entry.mentee.firstName).text(entry.mentee.firstName));
            odysseyEmployeeList.append($('<td></td>').attr('value', entry.percentageCompleteOfOdyssey).text(entry.percentageCompleteOfOdyssey));
            odysseyEmployeeList.append($('</tr>'));

        })
    });
}

