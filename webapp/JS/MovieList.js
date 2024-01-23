let formInput = document.getElementById("modificaFilm");
let btn_input = document.getElementById("btn-modifica");

btn_input.addEventListener("click",italoSvevo)

function italoSvevo(){
	if(formInput.className == "nascosto"){
		formInput.classList.remove('nascosto');
		formInput.classList.add('visibile');
	} else {
		formInput.classList.remove('visibile');
		formInput.classList.add('nascosto');
	}
}