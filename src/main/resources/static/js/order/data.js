const registerOrderDetail = async(data) => {
    const response = await fetch(
        "/order/register/detail",
    {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    console.log(response);
    return response.json();
}

const deleteOrderDetail = async(id) => {
    const response = await fetch(`/order/detail/delete/${id}`);
    return response.status === 200;
}