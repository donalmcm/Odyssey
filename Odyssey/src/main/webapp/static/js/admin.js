function loadAdminPage(isAdmin) {
// Populate table with list of employees with id, both names and email
    if (isAdmin !== true) {
        document.getElementById("admin-page-title").innerHTML = "NOT AUTHORIZED";
        document.getElementById("admin-page-title").style.color = 'red';
        document.getElementById("admin-page-content").style.display = "none";
    } else {
        document.getElementById("admin-page-title").innerHTML = "Administration";
        let adminEmployeeList = $('#employee-list');
        const adminEmployeeUrl = 'http://odyssey-aws.eu-west-1.elasticbeanstalk.com/api/employees';
        $.getJSON(adminEmployeeUrl, function (data) {
            $.each(data, function (key, entry) {
                var tr = document.createElement('tr');

                // First name
                var firstName = document.createElement('td');
                firstName.innerHTML = entry.firstName;
                tr.append(firstName);

                // Last name
                var lastName = document.createElement('td');
                lastName.innerHTML = entry.lastName;
                tr.append(lastName);

                // Email
                var email = document.createElement('td');
                email.innerHTML = entry.email;
                tr.append(email);

                // Title
                var title = document.createElement('td');
                title.innerHTML = entry.title;
                tr.append(title);

                adminEmployeeList.append(tr);
            })
        });

        // Populate table with list of topics with id, topic name - subtopic yet to be added
        let adminTopicList = $('#topic-list');
        const employeeUrl = 'http://odyssey-aws.eu-west-1.elasticbeanstalk.com/api/topics';
        $.getJSON(employeeUrl, function (data) {
            $.each(data, function (key, entry) {
                var tr = document.createElement('tr');

                // Topic name
                var topicName = document.createElement('td');
                topicName.innerHTML = entry.name;
                tr.append(topicName);

                // Subtopic name
                var subTopic = document.createElement('td');
                subTopic.innerHTML = "Not yet assigned";
                tr.append(subTopic);

                adminTopicList.append(tr);
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
