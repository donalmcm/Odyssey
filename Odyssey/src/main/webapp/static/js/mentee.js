
function populateModal() {

    let formValues = document.getElementsByClassName("resetToDisabled");
    let selectedValue = "";
    for(let i=0;i<formValues.length;i++) {
        if(formValues[i].checked) {
            selectedValue = formValues[i].name;
        }
    }

    let topic = document.getElementById("topic-filter").value;
    let duration = document.getElementById("duration-dropdown").value;
    let day = selectedValue.slice(0,-2); // e.g. monday
    let time = selectedValue.slice(-2); // e.g. 10

    document.getElementById("topicId").value = topic;
    document.getElementById("mentorDuration").value = duration;
    document.getElementById("dayOfMeetings").value = day;
    document.getElementById("timeOfMeetings").value = time;

    $('#odyssey-modal').modal('show');

}

let menteeDropdown = $('#topic-filter');
menteeDropdown.empty();
menteeDropdown.append('<option selected="true" disabled>Choose Topic</option>');
menteeDropdown.prop('selectedIndex', 0);
const topicMenteeUrl = 'http://localhost:8080/api/topics';

// Populate dropdown with list of topics
$.getJSON(topicMenteeUrl, function (data) {
    $.each(data, function (key, entry) {
        //create input, add value, add name, add event listener and then append
        var dropDownElement = document.createElement('option');
        dropDownElement.value = entry.name;
        dropDownElement.text = entry.name;

        menteeDropdown.append(dropDownElement);
    })
});


let durationDropdown = $('#duration-dropdown');
durationDropdown.empty();
durationDropdown.append('<option selected="true" disabled>Choose Duration</option>');
durationDropdown.prop('selectedIndex', 0);

function getMentorsByTopic(topic) {

    // get all time slot buttons
    let times = document.getElementsByClassName("resetToDisabled");
    // reset dropdown
    durationDropdown.empty();
    durationDropdown.append('<option selected="true" disabled>Choose Duration</option>');
    durationDropdown.prop('selectedIndex', 0);
    durationDropdown.append($('<option></option>').attr('value', "any").text("Any"));
    // on change of topic reset all times to unavailable
    for(let i=0;i<times.length;i++) {
        times[i].disabled=true;
    }

    let durationOptions = [];
    const menteeUrl = 'http://localhost:8080/api/employees/mentors/'+topic;
    $.getJSON(menteeUrl, function (data) {
        $.each(data, function (key, entry) {

            if(!durationOptions.includes(entry.mentorDuration)) {
                durationOptions.push(entry.mentorDuration);
                durationDropdown.append($('<option></option>').attr('value', entry.mentorDuration).text(entry.mentorDuration));
            }

            // change this to loop through availability
            // check what times on monday are available
            if(entry.availability.monday10 == true) {
                document.getElementById("monday10").disabled = false;
            }
            if(entry.availability.monday11 == true) {
                document.getElementById("monday11").disabled = false;
            }
            if(entry.availability.monday12 == true) {
                document.getElementById("monday12").disabled = false;
            }
            if(entry.availability.monday14 == true) {
                document.getElementById("monday14").disabled = false;
            }
            if(entry.availability.monday15 == true) {
                document.getElementById("monday15").disabled = false;
            }
            if(entry.availability.monday16 == true) {
                document.getElementById("monday16").disabled = false;
            }
            // check what times on tuesday are available
            if(entry.availability.tuesday10 == true) {
                document.getElementById("tuesday10").disabled = false;
            }
            if(entry.availability.tuesday11 == true) {
                document.getElementById("tuesday11").disabled = false;
            }
            if(entry.availability.tuesday12 == true) {
                document.getElementById("tuesday12").disabled = false;
            }
            if(entry.availability.tuesday14 == true) {
                document.getElementById("tuesday14").disabled = false;
            }
            if(entry.availability.tuesday15 == true) {
                document.getElementById("tuesday15").disabled = false;
            }
            if(entry.availability.tuesday16 == true) {
                document.getElementById("tuesday16").disabled = false;
            }
            // check what times on wednesday are available
            if(entry.availability.wednesday10 == true) {
                document.getElementById("wednesday10").disabled = false;
            }
            if(entry.availability.wednesday11 == true) {
                document.getElementById("wednesday11").disabled = false;
            }
            if(entry.availability.wednesday12 == true) {
                document.getElementById("wednesday12").disabled = false;
            }
            if(entry.availability.wednesday14 == true) {
                document.getElementById("wednesday14").disabled = false;
            }
            if(entry.availability.wednesday15 == true) {
                document.getElementById("wednesday15").disabled = false;
            }
            if(entry.availability.wednesday16 == true) {
                document.getElementById("wednesday16").disabled = false;
            }
            // check what times on thursday are available
            if(entry.availability.thursday10 == true) {
                document.getElementById("thursday10").disabled = false;
            }
            if(entry.availability.thursday11 == true) {
                document.getElementById("thursday11").disabled = false;
            }
            if(entry.availability.thursday12 == true) {
                document.getElementById("thursday12").disabled = false;
            }
            if(entry.availability.thursday14 == true) {
                document.getElementById("thursday14").disabled = false;
            }
            if(entry.availability.thursday15 == true) {
                document.getElementById("thursday15").disabled = false;
            }
            if(entry.availability.thursday16 == true) {
                document.getElementById("thursday16").disabled = false;
            }
            // check what times on friday are available
            if(entry.availability.friday10 == true) {
                document.getElementById("friday10").disabled = false;
            }
            if(entry.availability.friday11 == true) {
                document.getElementById("friday11").disabled = false;
            }
            if(entry.availability.friday12 == true) {
                document.getElementById("friday12").disabled = false;
            }
            if(entry.availability.friday14 == true) {
                document.getElementById("friday14").disabled = false;
            }
            if(entry.availability.friday15 == true) {
                document.getElementById("friday15").disabled = false;
            }
            if(entry.availability.friday16 == true) {
                document.getElementById("friday16").disabled = false;
            }
        })
    });

}

function getAvailabilitiesByTopicAndDuration(duration){
    let topicChoice = document.getElementById("topic-filter").value;
    if(duration === "any") {
        getMentorsByTopic(topicChoice);
    }
    else {
        let times = document.getElementsByClassName("resetToDisabled");
        for(let i=0;i<times.length;i++) {
            times[i].disabled=true;
        }

        const menteeUrl = 'http://localhost:8080/api/employees/mentors/'+topicChoice+'/duration/'+duration;
        $.getJSON(menteeUrl, function (data) {
            $.each(data, function (key, entry) {


                // change this to loop through availability
                // check what times on monday are available
                if(entry.availability.monday10 == true) {
                    document.getElementById("monday10").disabled = false;
                }
                if(entry.availability.monday11 == true) {
                    document.getElementById("monday11").disabled = false;
                }
                if(entry.availability.monday12 == true) {
                    document.getElementById("monday12").disabled = false;
                }
                if(entry.availability.monday14 == true) {
                    document.getElementById("monday14").disabled = false;
                }
                if(entry.availability.monday15 == true) {
                    document.getElementById("monday15").disabled = false;
                }
                if(entry.availability.monday16 == true) {
                    document.getElementById("monday16").disabled = false;
                }
                // check what times on tuesday are available
                if(entry.availability.tuesday10 == true) {
                    document.getElementById("tuesday10").disabled = false;
                }
                if(entry.availability.tuesday11 == true) {
                    document.getElementById("tuesday11").disabled = false;
                }
                if(entry.availability.tuesday12 == true) {
                    document.getElementById("tuesday12").disabled = false;
                }
                if(entry.availability.tuesday14 == true) {
                    document.getElementById("tuesday14").disabled = false;
                }
                if(entry.availability.tuesday15 == true) {
                    document.getElementById("tuesday15").disabled = false;
                }
                if(entry.availability.tuesday16 == true) {
                    document.getElementById("tuesday16").disabled = false;
                }
                // check what times on wednesday are available
                if(entry.availability.wednesday10 == true) {
                    document.getElementById("wednesday10").disabled = false;
                }
                if(entry.availability.wednesday11 == true) {
                    document.getElementById("wednesday11").disabled = false;
                }
                if(entry.availability.wednesday12 == true) {
                    document.getElementById("wednesday12").disabled = false;
                }
                if(entry.availability.wednesday14 == true) {
                    document.getElementById("wednesday14").disabled = false;
                }
                if(entry.availability.wednesday15 == true) {
                    document.getElementById("wednesday15").disabled = false;
                }
                if(entry.availability.wednesday16 == true) {
                    document.getElementById("wednesday16").disabled = false;
                }
                // check what times on thursday are available
                if(entry.availability.thursday10 == true) {
                    document.getElementById("thursday10").disabled = false;
                }
                if(entry.availability.thursday11 == true) {
                    document.getElementById("thursday11").disabled = false;
                }
                if(entry.availability.thursday12 == true) {
                    document.getElementById("thursday12").disabled = false;
                }
                if(entry.availability.thursday14 == true) {
                    document.getElementById("thursday14").disabled = false;
                }
                if(entry.availability.thursday15 == true) {
                    document.getElementById("thursday15").disabled = false;
                }
                if(entry.availability.thursday16 == true) {
                    document.getElementById("thursday16").disabled = false;
                }
                // check what times on friday are available
                if(entry.availability.friday10 == true) {
                    document.getElementById("friday10").disabled = false;
                }
                if(entry.availability.friday11 == true) {
                    document.getElementById("friday11").disabled = false;
                }
                if(entry.availability.friday12 == true) {
                    document.getElementById("friday12").disabled = false;
                }
                if(entry.availability.friday14 == true) {
                    document.getElementById("friday14").disabled = false;
                }
                if(entry.availability.friday15 == true) {
                    document.getElementById("friday15").disabled = false;
                }
                if(entry.availability.friday16 == true) {
                    document.getElementById("friday16").disabled = false;
                }
            })
        });
    }
}

// ensures only one time slot is selected
$(document).ready(function(){
    $('.resetToDisabled').click(function() {
        $('.resetToDisabled').not(this).prop('checked', false);
    });
});



