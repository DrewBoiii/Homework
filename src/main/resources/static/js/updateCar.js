$(document).ready(
    function() {

        $("#carForm").submit(function(event) {
            event.preventDefault();
            ajaxUpdate();
        });

        function ajaxUpdate() {

            var formData = {
                id : $("#id").val(),
                brand : $("#brand").val(),
                model : $("#model").val(),
                build : $("#build").val(),
                kilometers : $("#kilometers").val(),
                description : $("#description").val()
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
                    console.log(window.location);
                },
                error : function(e) {
                    console.log("Result status:" + e.status);
                    console.log(window.location);
                    console.log("ERROR: ", e);
                }
            });

        }

    });