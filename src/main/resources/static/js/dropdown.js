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
});

const messages = document.querySelectorAll('.msg');
messages.forEach(message => {
	const btnClose = message.querySelector('.msg__close');
	btnClose.addEventListener('click', () => {
		message.remove();
	})
});

const linkActive = () => {
	const navMainList = document.querySelector('#nav-main-list');
	if((/\/user\/.*/gi).test(location.href)) {
		navMainList.querySelector('.nav__link[href=\\/user\\/]')
			.classList.add('active');
	} else if((/\/menu\/.*/gi).test(location.href)) {
		navMainList.querySelector('.nav__link[href=\\/menu\\/]')
			.classList.add('active');
	}else if((/\/order\/.*/gi).test(location.href)) {
		navMainList.querySelector('.nav__link[href=\\/order\\/]')
			.classList.add('active');
	}
}
linkActive();