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