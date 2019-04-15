function loadAdminPage(isAdmin) {
// Populate table with list of employees with id, both names and email
    if (isAdmin !== true) {
        document.getElementById("admin-page-title").innerHTML = "NOT AUTHORIZED";
        document.getElementById("admin-page-title").style.color = 'red';
        document.getElementById("admin-page-content").style.display = "none";
    } else {
        document.getElementById("admin-page-title").innerHTML = "Administration";
        let adminEmployeeList = $('#employee-list');
        const adminEmployeeUrl = 'http://localhost:8080/api/employees';
        $.getJSON(adminEmployeeUrl, function (data) {
            $.each(data, function (key, entry) {
                adminEmployeeList.append($('<tr>'));
                adminEmployeeList.append($('<td></td>').attr('value', entry.id).text(entry.id));
                adminEmployeeList.append($('<td></td>').attr('value', entry.firstName).text(entry.firstName));
                adminEmployeeList.append($('<td></td>').attr('value', entry.lastName).text(entry.lastName));
                adminEmployeeList.append($('<td></td>').attr('value', entry.email).text(entry.email));
                adminEmployeeList.append($('</tr>'));

            })
        });

        // Populate table with list of topics with id, topic name - subtopic yet to be added
        let adminTopicList = $('#topic-list');
        const employeeUrl = 'http://localhost:8080/api/topics';
        $.getJSON(employeeUrl, function (data) {
            $.each(data, function (key, entry) {
                adminTopicList.append($('<tr>'));
                adminTopicList.append($('<td></td>').attr('value', entry.name).text(entry.name));
                adminTopicList.append($('<td></td>').attr('value', "N/A").text("N/A"));
                adminTopicList.append($('</tr>'));

            })
        });

        // New employee modal
        $(document).ready(function () {
            $("#create-employee").click(function () {
                $("#create-employee-modal").modal();
            });
        });

        // New topic modal
        $(document).ready(function () {
            $("#create-topic").click(function () {
                $("#create-topic-modal").modal();
            });
        });
    }
}
