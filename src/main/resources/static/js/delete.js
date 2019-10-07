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
                        $('#getResultDiv ul').empty();
                        var thisCar = "Removed car: "
                            + result.data.brand + " "
                            + result.data.model + "<br>"
                            + result.data.build + "<br>"
                            + result.data.kilometers + "km<br><hr>";

                        $('#getResultDiv').append(thisCar);

                        console.log("Success: ", result);
                    } else {
                        $("#getResultDiv").html("<strong>Error</strong>");
                        console.log("Fail: ", result);
                    }
                },
                error : function(e) {
                    $("#getResultDiv").html("<strong>Error</strong>");
                    console.log("ERROR: ", e);
                }
            });
        }
    });