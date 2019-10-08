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
                    console.log(result);
                },
                error : function(e) {
                    alert("Error!");
                    console.log("ERROR: ", e);
                }
            });

        }

    });