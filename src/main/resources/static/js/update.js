$(document).ready(
    function() {

        $("#carDetailsForm").submit(function(event) {
            event.preventDefault();
            ajaxUpdate();
        });

        function ajaxUpdate() {

            var formData = {
                id : $("#id").val(),
                brand : $("#brand").val(),
                model : $("#model").val(),
                build : $("#build").val(),
                kilometers : $("#kilometers").val()
            };

            var carId = $("#id").val();
            console.log("Car id is " + carId);

            $.ajax({
                type : "PUT",
                contentType : "application/json",
                url : "update",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status === "success") {
                        $("#putResultDiv").html(
                            "" + result.data.brand + " " + result.data.model
                            + " was successfully updated!" + "<br>" + "</p>");
                    } else {
                        $("#putResultDiv").html("<strong>Error</strong>");
                    }

                    console.log("Result status:" + result.status);

                    // Fill out the form
                    $("#id").val(result.id);
                    $("#brand").val(result.brand);
                    $("#model").val(result.model);
                    $("#build").val(result.build);
                    $("#kilometers").val(result.kilometers);

                    // Update the form
                    $('#carDetailsForm' + result.id).find("input[name='brand']").val(result.brand);
                    $('#carDetailsForm' + result.id).find("input[name='model']").val(result.model);
                    $('#carDetailsForm' + result.id).find("input[name='build']").val(result.build);
                    $('#carDetailsForm' + result.id).find("input[name='kilometers']").val(result.kilometers);

                    console.log("Result status:" + result.status);
                    console.log(window.location);
                },
                error : function(e) {
                    alert("Error!");

                    console.log("Result status:" + e.status);
                    console.log(window.location);
                    console.log("ERROR: ", e);
                }
            });

        }

    });