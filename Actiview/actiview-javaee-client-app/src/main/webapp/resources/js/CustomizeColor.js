function myFunction() {
	document.getElementById("para").style.backgroundColor = "yellow";
}

// Get the root element
var r = document.querySelector(':root');

function myFunction_set() {
	r.style.setProperty('--background-color', '#424242');
	r.style.setProperty('--primary-color', '#81c784');
	r.style.setProperty(' --secondary-color ', '#ffd54f');
	r.style.setProperty('text-color ', '#f5f5f5');
}

//With the color picker
input.onchange = () => {
	document.body.style.setProperty("--page-background-color", input.value);
};

