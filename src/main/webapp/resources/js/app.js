document.addEventListener("DOMContentLoaded", function() {

    // var checkboxs = document.querySelectorAll(".answer");
    // var checkboxsInputs = document.querySelectorAll(".answer input");


    var inputs = $(".input");
    var placeToMoveInputs=$(".invisible");


    for (var i=0; i<inputs.length; i++)  {
        if (inputs[i].type == 'checkbox')   {
            inputs[i].checked = false;
        }
    }

    //inputs.appendTo(placeToMoveInputs);

    $(function () {
        var answer = $(".answer");
        var answerInput = $(".answer input");

        answer.on("click", function(){
            if(answerInput.checked = false)
                answerInput.checked=true;
            else if(answerInput.checked=true)
                answerInput.checked=false;
        });

})})

// $(function() {
//     var answer = $(".answer");
//     var answerInput = $(".answer input");
//
//     descriptionDT.hide();
//
//     descriptionDD.on("click", function() {
//         $(this).next().toggle();
//     });
//
// });