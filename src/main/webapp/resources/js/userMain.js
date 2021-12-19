document.addEventListener("DOMContentLoaded", function (event) {


    const Btns = document.getElementById("Tab").querySelectorAll(".btn-success");
    const Forms = document.querySelectorAll(".clear");
    console.log(Btns);
    console.log(Forms);


    Btns.forEach(function (el, i){
        let counter = 0;
        Btns[i].addEventListener("click", function (event){
            if (counter%2===0) {
                Forms[i].classList.remove("d-none");
            } else if (counter%2!=0) {
                Forms[i].classList.add("d-none");
            }
            counter++;
        })
    })

});
