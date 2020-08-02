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


    var form = $(".form");
    $(function () {


        var confirm =$(".confirmYourChoice")

        confirm.on("click", function(){
            form.submit();
        });

    })



    var counter = document.querySelector(".counter");

    counter.textContent="1000";
    var timeLeft=parseInt(counter.innerHTML);


    function countdown(time) {
        var interval = setInterval(function() {
                //document.querySelector(".form");

                console.log(timeLeft);
                if(timeLeft>1) {
                    counter.textContent = (counter.textContent - 1).toString();
                    timeLeft-=1;
                }else if(timeLeft==1) {
                    //console.log("odkomentuj wysyłanie formularza");
                    form.submit();
                }
            },

            1000);

    }

    countdown(10);

});
