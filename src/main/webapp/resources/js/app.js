document.addEventListener("DOMContentLoaded", function() {

    // var checkboxs = document.querySelectorAll(".answer");
    // var checkboxsInputs = document.querySelectorAll(".answer input");


    var inputs = $(".input");
    var submitButton = $(".submitButton");
    var placeToMoveInputs=$(".invisible");


    for (var i=0; i<inputs.length; i++)  {
        if (inputs[i].type == 'checkbox')   {
            inputs[i].checked = false;
        }
    }

    submitButton.appendTo(placeToMoveInputs);
    inputs.appendTo(placeToMoveInputs);

    $(function () {

        var answer = $("label");
        answer.addClass("label");

        answer.on("click", function(){
            this.classList.toggle("checkedLabel");
            answer.children()
        });

        answer.hover(function(){
            this.classList.toggle("hoverover");
        });
})

    $(function () {

        var form = $(".form");
        var confirm =$(".confirmYourChoice")

        confirm.on("click", function(){
            form.submit();
        });

    })
})
