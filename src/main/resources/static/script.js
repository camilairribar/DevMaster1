// Manejo de la clase 'active' en el menú de navegación
document.querySelectorAll('nav ul li a').forEach(link => {
    link.addEventListener('click', () => {
        document.querySelectorAll('nav ul li a').forEach(item => item.classList.remove('active'));
        link.classList.add('active');
    });
});

// Cargar proyectos dinámicamente en la página "proyectos.html"
document.addEventListener('DOMContentLoaded', () => {
    const proyectosContainer = document.getElementById('proyectos-container');

    // Función para obtener proyectos desde el backend
    const obtenerProyectos = async () => {
        try {
            const response = await fetch('http://localhost:8080/polo_de_salud/proyectos/ListaProyecto');
            if (!response.ok) {
                throw new Error('Error al obtener los proyectos');
            }

            const proyectos = await response.json();

            // Renderizar los proyectos en el contenedor
            proyectos.forEach(proyecto => {
                const proyectoCard = document.createElement('div');
                proyectoCard.classList.add('proyecto-card');
                proyectoCard.innerHTML = `
                    <img src="${proyecto.foto}" alt="Imagen del proyecto" class="proyecto-img">
                    <h3>${proyecto.nombre}</h3>
                    <p>${proyecto.descripcion}</p>
                    <p><strong>Estado:</strong> ${proyecto.estado}</p>
                    <p><strong>Fecha Publicación:</strong> ${formatearFecha(proyecto.fechaPublicacion)}</p>
                    <p><strong>Fecha Término:</strong> ${formatearFecha(proyecto.fechaTermino)}</p>
                `;
                proyectosContainer.appendChild(proyectoCard);
            });
        } catch (error) {
            console.error(error);
            proyectosContainer.innerHTML = '<p>Error al cargar los proyectos.</p>';
        }
    };

    // Función para formatear fechas
    const formatearFecha = (fechaISO) => {
        const fecha = new Date(fechaISO);
        return fecha.toLocaleDateString('es-ES', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
        });
    };

    // Llamar a la función para obtener y mostrar los proyectos
    if (proyectosContainer) {
        obtenerProyectos();
    }
});


// Manejo de la clase 'active' en el menú de navegación
document.querySelectorAll('nav ul li a').forEach(link => {
    link.addEventListener('click', () => {
        document.querySelectorAll('nav ul li a').forEach(item => item.classList.remove('active'));
        link.classList.add('active');
    });
});

// Cargar noticias dinámicamente en la página "noticias.html"
document.addEventListener('DOMContentLoaded', () => {
    const noticiasContainer = document.getElementById('noticias-container');

    // Función para obtener noticias desde el backend
    const obtenerNoticias = async () => {
        try {
            const response = await fetch('http://localhost:8080/polo_de_salud/noticias/ListaNoticia');
            if (!response.ok) {
                throw new Error('Error al obtener las noticias');
            }

            const noticias = await response.json();

            // Renderizar las noticias en el contenedor
            noticias.forEach(noticia => {
                const noticiaCard = document.createElement('div');
                noticiaCard.classList.add('noticia-card');
                noticiaCard.innerHTML = `
                    <img src="${noticia.imagen || 'image/default-image.jpg'}" alt="Imagen de la noticia" class="noticia-img">
                    <h3>${noticia.titulo}</h3>
                    <p>${noticia.descripcion}</p>
                    <p><strong>Fecha de publicación:</strong> ${formatearFecha(noticia.fechaPublicacionNoticia)}</p>
                `;
                noticiasContainer.appendChild(noticiaCard);
            });
        } catch (error) {
            console.error(error);
            noticiasContainer.innerHTML = '<p>Error al cargar las noticias.</p>';
        }
    };

    // Función para formatear fechas
    const formatearFecha = (fechaISO) => {
        const fecha = new Date(fechaISO);
        return fecha.toLocaleDateString('es-ES', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
        });
    };

    // Llamar a la función para obtener y mostrar las noticias
    if (noticiasContainer) {
        obtenerNoticias();
    }
});

//contacto
document.getElementById('contact-form').addEventListener('submit', function (event) {
    event.preventDefault();  // Evitar el envío por defecto

    const nombre = document.getElementById('nombre').value.trim();
    const email = document.getElementById('email').value.trim();
    const mensaje = document.getElementById('mensaje').value.trim();

    // Validaciones
    if (!nombre || !email || !mensaje) {
        alert('Todos los campos son obligatorios.');
    } else if (!email.match(/^\S+@\S+\.\S+$/)) {
        alert('Por favor, introduce un correo válido.');
    } else {
        // Enviar el formulario si todo es válido
        fetch('http://localhost:8080/polo_de_salud/contacto/enviar-contacto', {
            method: 'POST',
            body: new URLSearchParams(new FormData(this)),
        })
        .then(response => response.json())
        .then(data => {
            // Crear el contenedor de la notificación
            const successMessage = document.createElement('div');
            successMessage.className = 'success-message';
            successMessage.textContent = data.message;

            // Mostrar el mensaje de éxito en el contenedor
            document.getElementById('notification-container').appendChild(successMessage);

            // Establecer un temporizador para que la notificación desaparezca después de 5 segundos
            setTimeout(() => {
                successMessage.remove();
            }, 5000);

            // Resetear el formulario después del envío
            this.reset();  
        })
        .catch(error => {
            console.error(error);
            alert('Ocurrió un error al enviar el formulario.');
        });
    }
});