const tbodyOrders = document.querySelector('#tbody-orders');

tbodyOrders.addEventListener('click', (e) => {
    e.preventDefault();
    if(e.target.matches('.delete-order')) {
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
                location.href = e.target.href;
            }
        })
    } else if(e.target.matches('.update-order')) {
        location.href = e.target.href
    }
})