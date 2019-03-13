let dropdown = $('#topic-dropdown');

dropdown.empty();

dropdown.append('<option selected="true" disabled>Choose Topic</option>');
dropdown.prop('selectedIndex', 0);

const topicMentorUrl = 'http://localhost:8080/api/topics';

// Populate dropdown with list of topics
$.getJSON(topicMentorUrl, function (data) {
    $.each(data, function (key, entry) {
        dropdown.append($('<option></option>').attr('value', entry.name).text(entry.name));
    })
});

$(document).ready(function(){
    $("#become-mentor").click(function(){
        $("#become-mentor-modal").modal();
    });
});
