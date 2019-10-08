GET: $(document).ready(
    function() {

        $("#deleteCar").click(function(event) {
            event.preventDefault();
            ajaxDelete();
        });

        function ajaxDelete() {
            $.ajax({
                type : "DELETE",
                url : "delete",
                success : function(result) {
                    if (result.status === "success") {
                        $('#getDeleteResultDiv ul').empty();

                        var thisCar = "Removed car: "
                            + result.data.brand + " "
                            + result.data.model + "<br>"
                            + result.data.build + "<br>"
                            + result.data.kilometers + "km<br><hr>";

                        $('#getDeleteResultDiv').append(thisCar);

                        console.log("Success: ", result);
                    } else {
                        $("#getDeleteResultDiv").html("<strong>Error</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error : function(e) {
                    $("#getDeleteResultDiv").html("<strong>Error</strong>");
                    console.log("ERROR: ", e);
                }
            });
        }
    });