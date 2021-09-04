const tbodyDetails = document.querySelector('#tbody-details');
const formOrderDetail = document.querySelector('#form-detail');
const inputProduct = document.querySelector('#product');
const inputQuantity = document.querySelector('#quantity');
const btnFinish = document.querySelector('#finish-order');

const modifyForm = () => {
    const nameProduct = inputProduct.options[inputProduct.selectedIndex].text;
    getProductByName(nameProduct)
        .then(data => {
            const labelQuantity = document.querySelector('label[for=quantity]');
            labelQuantity.textContent = `Cantidad (maxima: ${data.stock})`;
            inputQuantity.max = parseInt(data.stock);
        });
}

const showButtonFinish = () => {
    if(tbodyDetails.querySelectorAll('tr').length > 0) {
        btnFinish.style.display = 'block';
    } else {
        btnFinish.style.display = 'none';
    }
}
showButtonFinish();

const addRowOrderDetail = (detail) => {
    if(document.querySelector(`#row-${detail.idOrderDetail}`) !== null) {
        const tr = document.querySelector(`#row-${detail.idOrderDetail}`);
        tr.children[2].innerHTML = detail.quantity;
        tr.children[3].innerHTML = detail.quantity * detail.product.price;
    } else {
        const tr = tbodyDetails.insertRow();
        tr.setAttribute('id',`row-${detail.idOrderDetail}`);
        tr.innerHTML = `
        <td>${detail.product.name}</td>
        <td>${detail.product.price}</td>
        <td>${detail.quantity}</td>
        <td>${detail.quantity*detail.product.price}</td>
        <td>
            <button class='btn btn--delete delete-details' data-id='${detail.idOrderDetail}'>
                Eliminar
            </button>
        </td>`;
        tbodyDetails.appendChild(tr);
    }
    modifyForm();
    showButtonFinish();
}

const deleteRowOrderDetail = (btnDelete) => {
    Swal.fire({
        title: '¿Estas seguro?',
        text: "¡No podrás revertir esto!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, Eliminelo'
    }).then((result) => {
        if (result.isConfirmed) {
            deleteOrderDetail(btnDelete.dataset.id)
                .then(isDeleted => {
                    if(isDeleted) {
                        document.querySelector(`#row-${btnDelete.dataset.id}`).remove();
                        modifyForm();
                        showButtonFinish();
                        Swal.fire(
                            'Eliminado!',
                            'Se ha borrado correctamente',
                            'success'
                        )
                    }
                });
        }
    })
}
//===============================

//cambiar un stock al escoger producto
inputProduct.addEventListener('change', () => {
    if(inputProduct.value !== "") {
        modifyForm();
    }
});

//agregar detalle de la orden al enviar formulario
formOrderDetail.addEventListener('submit', (e) => {
    e.preventDefault();
    let data = new FormData(formOrderDetail);
    let jsonOrderDetail = {
        order: {idOrder: data.get('idOrder')},
        product: {idProduct: data.get('idProduct')},
        quantity: data.get('quantity')
    }
    registerOrderDetail(jsonOrderDetail)
        .then(detail => {
            addRowOrderDetail(detail);
        });
});

//eliminar un detalle de producto
tbodyDetails.addEventListener('click', e => {
    if(e.target.matches('.delete-details')) {
        deleteRowOrderDetail(e.target);
    }
})

btnFinish.addEventListener('click', (e) => {
    e.preventDefault();
    Swal.fire({
        title: '¿Estas seguro?',
        text: "¡No podrás revertir esto!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Si, Finalizar'
    }).then((result) => {
        if (result.isConfirmed) {
            location.href = btnFinish.getAttribute('href');
        }
    })
})


//===============================
