$(document).ready(
    function() {

        $("#carForm").submit(function(event) {
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {

            var formData = {
                brand : $("#brand").val(),
                model : $("#model").val(),
                build : $("#build").val(),
                kilometers : $("#kilometers").val()
            };

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "submit",
                data : JSON.stringify(formData),
                dataType : 'json',
                success : function(result) {
                    if (result.status === "success") {
                        $("#postResultDiv").html(
                            "<div class='msg msg-success z-depth-3'>"
                            + result.data.brand
                            + " "
                            + result.data.model
                            + " was successfully saved!"
                            + "</div>");
                    } else {
                        $("#postResultDiv").html("<div class='msg msg-error z-depth-3'>Error</div>");
                    }
                    console.log(result);
                },
                error : function(e) {
                    $("#postResultDiv").html("<div class='msg msg-error z-depth-3'>Error</div>");
                    // alert("Error!");
                    // console.log("ERROR: ", e);
                }
            });

        }

    });