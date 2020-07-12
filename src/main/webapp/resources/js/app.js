document.addEventListener("DOMContentLoaded", function() {

    // var checkboxs = document.querySelectorAll(".answer");
    // var checkboxsInputs = document.querySelectorAll(".answer input");

    $(function () {
        var answer = $(".answer");
        var answerInput = $(".answer input");



        answer.on("click", function() {
            console.log("kliknieto mnie"+this.parentNode)
            answerInput.checked = false;
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