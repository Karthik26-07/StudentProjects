/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validateUser() {
    var name = document.getElementById('uname').value;
    var contactNo = document.getElementById('contactNo').value;
    var emailId = document.getElementById('emailId').value;
    var address = document.getElementById('address').value;
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    var CV = document.getElementById('docfile').value;

    if (name == "") {
        window.alert('Please provide Name');
        return false;
    }
    else if (contactNo == "") {
        window.alert('Please provide Contact Number');
        return false;
    }
    else if (contactNo.length != 10) {
        window.alert('Please provide valid Contact Number');
        return false;
    }
    else if (emailId == "") {
        window.alert('Please provide EmailID');
        return false;
    }
    else if (!/(.+)@(.+){2,}\.(.+){2,}/.test(emailId)) {
        window.alert('Please provide Valid EmailID');
        return false;
    }
    else if (address == "") {
        window.alert('Please provide Address');
        return false;
    }
    else if (username == "") {
        window.alert('Please provide Username');
        return false;
    }
    else if (password == "") {
        window.alert('Please provide Password');
        return false;
    }
    else if (CV === "No file chosen") {
        window.alert('Please provide CV');
        return false;
    }
  
    return true;
}

function aptitude() {
    var type = document.getElementById('type').value;
    var Question = document.getElementById('Question').value;
    var option1 = document.getElementById('option1').value;
    var option2 = document.getElementById('option2').value;
    var option3 = document.getElementById('option3').value;
    var option4 = document.getElementById('option4').value;
    var correctAns = document.getElementById('correctAns').value;
    if (type == "") {
        window.alert('Please select question type');
        return false;
    }
    else if (Question == "") {
        window.alert('Please provide Question');
        return false;
    }
    else if (option1 == "") {
        window.alert('Please provide Option1');
        return false;
    }
    else if (option2 == "") {
        window.alert('Please provide Option2');
        return false;
    } else if (option3 == "") {
        window.alert('Please provide Option3');
        return false;
    } else if (option4 == "") {
        window.alert('Please provide Option4');
        return false;
    } else if (correctAns == "") {
        window.alert('Please provide correct option');
        return false;
    }

    return true;


}


function personality() {
    var Question = document.getElementById('Question').value;
    var Openness = document.getElementById('Openness').value;
    var Conscientiousness = document.getElementById('Conscientiousness').value;
    var Extraversion = document.getElementById('Extraversion').value;
    var Agreebleness = document.getElementById('Agreebleness').value;
    var Neuroticism = document.getElementById('Neuroticism').value;
    if (Question == "") {
        window.alert('Please provide the question');
        return false;
    }
    else if (Openness == "") {
        window.alert('Please select Openness to experience');
        return false;
    }
    else if (Conscientiousness == "") {
        window.alert('Please select Conscientiousness');
        return false;
    }
    else if (Extraversion == "") {
        window.alert('Please select Extraversion');
        return false;
    }
    else if (Agreebleness == "") {
        window.alert('Please select Agreebleness');
        return false;
    }
    else if (Neuroticism == "") {
        window.alert('Please select Neuroticism');
        return false;
    }
    return true;

}


// function jobdetails() {
//     var Designation = document.getElementById('designation').value;
//     var Salary = document.getElementById('salary').value;
//     var Place = document.getElementById('place').value;

//     if (Designation == "") {
//         window.alert('Please provide Designation');
//         return false;
//     }
//     else if (Salary == "") {
//         window.alert('Please provide Salary');
//         return false;
//     }  if (Place == "") {
//         window.alert('Please provide Place');
//         return false;
//     }
//     return true;
// }

// function jobrequirement() {
//     var Experience = document.getElementById('Experience').value;
//     var Qualifiction = document.getElementById('Qualifiction').value;
//     var Skills = document.getElementById('keySkills').value;

//     if (Experience == "") {
//         window.alert('Please provide Experience');
//         return false;
//     }
//     else if (Qualifiction == "") {
//         window.alert('Please provide Qualification');
//         return false;
//     } if (Skills == "") {
//         window.alert('Please provide Key Skills');
//         return false;
//     }
//     return true;
// }