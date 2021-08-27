const dropdowns = document.querySelectorAll(".dropdown");

dropdowns.forEach(dropdown => {
	
	dropdown.addEventListener('click',() => {
		const showMenu = dropdown.querySelector('.dropdown__menu');
		if(showMenu.classList.contains('show')) {
			showMenu.classList.remove('show');
		} else {
			showMenu.classList.add('show');
		}
	})
})