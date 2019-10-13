$(document).ready(

    function () {
        $("#registrationForm").submit(function (event) {
            event.preventDefault();
            saveUser();
        });

        function saveUser() {

            var formData = {
                username : $("#username").val(),
                email : $("#email").val(),
                password : $("#password").val(),
                confirmPassword : $("#confirmPassword").val()
            };

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "signup",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status === "success") {
                        $("#registrationResultDiv").html(
                            "Nice to meet you, "
                            + result.data.username
                            + "<br>"
                            + "You are successfully signed up!"
                        );
                    }
                    else {
                        $("#registrationResultDiv").html(
                            "<strong>Error</strong>"
                        );
                    }
                    console.log(result);
                },
                error : function(e) {
                    alert("Bad request!");
                    console.log("ERROR: ", e);
                }
            })

        }

    }

);