

const inputName = document.querySelector('#name');
inputName.addEventListener('change', () => {
	getAutoComplete(inputName.value);
});

const searchProduct = document.querySelector('#searchProduct');
searchProduct.addEventListener('submit', (e) => {
	e.preventDefault();
	const tableProducts = document.querySelector('#tableProducts');
	for(var i = tableProducts.rows.length - 1; i > 0; i--) {
		tableProducts.deleteRow(i);
	}

	const nameProduct = document.querySelector('#nameProduct');
	getProductsByNameContaining(nameProduct.value)
	.then(products => {
		products.forEach(product => {
			addProductTable(product);
		})
	})
});

const formAdd = document.querySelector('#formAdd');
formAdd.addEventListener('submit', (e) => {
	e.preventDefault();
	const stockElement = document.querySelector('#stock');
	console.log(stockElement.value, typeof stockElement.value);
	if(stockElement.value === '0' || stockElement.value === "") {
		document.querySelector('#state').value =  false;
	} else {
		document.querySelector('#state').value =  true;
	}
	e.currentTarget.submit();
});


const deleteProducts = document.querySelectorAll('.delete-products');
deleteProducts.forEach(btnDelete => {
	btnDelete.addEventListener('click', (e) => {
		e.preventDefault();
		console.log(btnDelete.dataset.id);
		Swal.fire({
			title: '¿Estas seguro?',
			text: "¡No podrás revertir esto!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Si, eliminelo'
		}).then((result) => {
			if (result.isConfirmed) {
				window.location.href = btnDelete.getAttribute('href');
			}
		})
	});
})

//=====================================================================

const addProductTable = (product) => {
	const tableProducts = document.querySelector('#tableProducts');
	tableProducts.innerHTML += `<tr>
		<td>${product.name}</td>
		<td>${product.price}</td>
		<td>${product.type}</td>
		<td>
			<a href="/menu/form/${product.idProduct}" class="btn btn--update">
				<i class="fas fa-edit"></i><span>Editar</span>
			</a>
			<a th:href="/menu/delete/${product.idProduct}" class="btn btn--delete">
				<i class="fas fa-trash-alt"></i><span>Eliminar</span>
			</a>
		</td>
	</tr>`
}

const resetModal = () => {
	document.querySelector('#idProduct').value = '';
	document.querySelector('#name').value = '';
	document.querySelector('#price').value =  '';
	document.querySelector('#type').value =  '';
	document.querySelector('#stock').value =  '';
	document.querySelector('#state').value =  '';
}

const getAutoComplete = (name) => {
	getProductByName(name)
	.then(product => {
		document.querySelector('#idProduct').value =  !product?.idProduct ? '' : product.idProduct;
		document.querySelector('#price').value =  !product?.price ? '' : product.price;
		document.querySelector('#type').value =  !product?.type ? '' : product.type;
		document.querySelector('#stock').value =  !product?.stock ? '' : product.stock;
		document.querySelector('#state').value =  !product?.state ? '' : product.state;
	})
}	

// ===================================================================
