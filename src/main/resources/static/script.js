// Agregar clase 'active' al enlace del menú que el usuario está viendo
document.querySelectorAll('nav ul li a').forEach(link => {
    link.addEventListener('click', () => {
        document.querySelectorAll('nav ul li a').forEach(item => item.classList.remove('active'));
        link.classList.add('active');
    });
});
 

// Validación de formularios
document.querySelector('#form-contacto').addEventListener('submit', (e) => {
    const email = document.querySelector('#email').value;
    const mensaje = document.querySelector('#mensaje').value;

    if (!email || !mensaje) {
        e.preventDefault(); // Evitar que se envíe el formulario
        alert('Por favor, completa todos los campos.');
    } else if (!/\S+@\S+\.\S+/.test(email)) {
        e.preventDefault();
        alert('Por favor, ingresa un correo válido.');
    }
});