$(document).ready(
    function() {

        $("#carForm").submit(function(event) {
            event.preventDefault();
            ajaxUpdate();
        });

        function ajaxUpdate() {

            var formData = {
                brand : $("#brand").val(),
                model : $("#model").val(),
                build : $("#build").val(),
                kilometers : $("#kilometers").val()
            };

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "check",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(response) {
                    if (response.status === "success") {
                        $("#postResultDiv").html(
                            "" + response.data.brand + " " + response.data.model
                            + "The car was successfully updated!" + "<br>" + "</p>");
                    } else {
                        $("#postResultDiv").html("<strong>Error</strong>");
                    }
                    console.log(response);
                },
                error : function(e) {
                    alert("Error!");
                    console.log("ERROR: ", e);
                }
            });

        }

    });