$(document).ready(
    function() {

        $("#getCars").click(function(event) {
            event.preventDefault();
            ajaxGet();
        });

        function ajaxGet() {
            $.ajax({
                type : "GET",
                url : "cars/all",
                success : function(result) {
                    if (result.status === "success") {
                        $('#getResultDiv ul').empty();
                        // var custList = "";
                        $.each(result.data,
                            function(i, car) {
                                var thisCar = i + ": "
                                    + car.brand + " "
                                    + car.model + "<br>"
                                    + car.build + "<br>"
                                    + car.kilometers + "km<br><hr>";

                                $('#getResultDiv .list-group').append(thisCar)
                            });
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