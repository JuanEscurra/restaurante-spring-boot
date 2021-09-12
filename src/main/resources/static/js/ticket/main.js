const pagoInput =document.querySelector('#pago');
const importeInput = document.querySelector('#importe');
const vueltoInput = document.querySelector('#vuelto');

pagoInput.addEventListener('input', () => {
    console.log('evento');
    if(Number(pagoInput.value) >= Number(importeInput.value)) {
        pagoInput.classList.remove('error');
        vueltoInput.value = (pagoInput.value - importeInput.value).toFixed(2);
    } else {
        pagoInput.classList.add('error');
        vueltoInput.value = '0';
    }
})