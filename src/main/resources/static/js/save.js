$(document).ready(
    function() {

        // SUBMIT FORM
        $("#carForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            // PREPARE FORM DATA
            var formData = {
                brand : $("#brand").val(),
                model : $("#model").val(),
                build : $("#build").val(),
                kilometers : $("#kilometers").val()
            };

            // DO POST
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "saveCar",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status === "success") {
                        $("#postResultDiv").html(
                            "" + result.data.brand + " " + result.data.model
                            + "Post Successfully! <br>"
                            + "---> Congrats !!" + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong>Error</strong>");
                    }
                    console.log(result);
                },
                error : function(e) {
                    alert("Error!");
                    console.log("ERROR: ", e);
                }
            });

        }

    });