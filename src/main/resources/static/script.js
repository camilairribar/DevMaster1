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

document.addEventListener('DOMContentLoaded', () => {
    const formularioLogin = document.getElementById('loginForm');
    const errorMessage = document.getElementById('error-message');
    
    // Función para autenticar colaborador
    const autenticarColaborador = async (correo, contrasena) => {
        try {
            // Realizar la petición POST al backend para autenticar
            const response = await fetch('http://localhost:8080/polo_de_salud/colaboradores/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',  // Asegúrate de establecer el tipo de contenido
                },
                body: JSON.stringify({
                    correo: correo,
                    contrasena: contrasena
                }),
            });

            // Comprobar si la respuesta no es exitosa
            if (response.status === 401) {
                const errorResponse = await response.json();
                const errorMessage = errorResponse.message || 'Credenciales incorrectas. Por favor intente de nuevo.';
                throw new Error(errorMessage);
            } else {
                throw new Error('Error desconocido. Intenta nuevamente más tarde.');
            }            

            // Si la autenticación fue exitosa
            const colaborador = await response.json();
            console.log(colaborador);
            window.location.href = '/dashboard';  // Redirigir al dashboard si el login es exitoso

        } catch (error) {
            console.error(error);
            errorMessage.textContent = error.message || 'Ocurrió un error al intentar iniciar sesión';
            errorMessage.style.display = 'block';
        }
    };

    // Evento que se ejecuta cuando el formulario es enviado
    formularioLogin.addEventListener('submit', (event) => {
        event.preventDefault(); // Evitar el envío del formulario tradicional

        // Obtener los valores de correo y contraseña del formulario
        const correo = document.getElementById('correo').value.trim();
        const contrasena = document.getElementById('contrasena').value.trim();

        // Validar que ambos campos no estén vacíos
        if (correo === "" || contrasena === "") {
            errorMessage.textContent = 'Por favor, ingresa tanto el correo como la contraseña.';
            errorMessage.style.display = 'block';
            return;
        }

        // Deshabilitar el botón de submit y cambiar el texto mientras se realiza la autenticación
        const submitButton = document.querySelector('button');
        submitButton.disabled = true;
        submitButton.textContent = "Iniciando sesión...";

        // Llamar a la función de autenticar
        autenticarColaborador(correo, contrasena).finally(() => {
            // Habilitar el botón nuevamente y restaurar el texto
            submitButton.disabled = false;
            submitButton.textContent = "Iniciar sesión";
        });
    });
});
/*
document.getElementById('formCrearNoticia').addEventListener('submit', function(event) {
    event.preventDefault();

    const autores = document.getElementById('autores').value.split(',').map(author => author.trim()); // Convertir el campo de autores en un array

    const noticia = {
        titulo: document.getElementById('tituloNoticia').value,
        descripcion: document.getElementById('descripcionNoticia').value,
        foto: document.getElementById('fotoNoticia').value,
        fechaPublicacion: document.getElementById('fechaPublicacionNoticia').value,
        autores: autores // Pasar el array de autores
    };

    fetch('http://localhost:8080/polo_de_salud/noticias/CrearNoticia', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(noticia),
    })
    .then(response => {
        if (response.ok) {
            alert("Noticia creada exitosamente");
        } else {
            alert("Error al crear la noticia");
        }
    })
    .catch(error => console.error('Error al crear noticia:', error));
});

document.getElementById('formCrearProyecto').addEventListener('submit', function(event) {
    event.preventDefault();

    const proyecto = {
        titulo: document.getElementById('tituloProyecto').value,
        descripcion: document.getElementById('descripcionProyecto').value,
        proyectosRel: document.getElementById('proyectosRel').value.split(',').map(id => id.trim()) // Convertir a lista de IDs
    };

    fetch('http://localhost:8080/polo_de_salud/proyectos/CrearProyecto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(proyecto),
    })
    .then(response => {
        if (response.ok) {
            alert("Proyecto creado exitosamente");
        } else {
            alert("Error al crear el proyecto");
        }
    })
    .catch(error => console.error('Error al crear proyecto:', error));
});
*/
document.getElementById('formCrearNoticiaGestionar').addEventListener('submit', function (event) {
    console.log("Evento submit del formulario de noticias activado")
    event.preventDefault();

    const titulo = document.getElementById('tituloNoticia').value.trim();
    const descripcion = document.getElementById('descripcionNoticia').value.trim();
    const foto = document.getElementById('fotoNoticia').value.trim();
    const fechaPublicacion = document.getElementById('fechaPublicacionNoticia').value.trim();
    const autores = document.getElementById('autores').value.split(',').map(author => author.trim());

    if (!titulo || !descripcion || !foto || !fechaPublicacion || autores.length === 0) {
        alert('Por favor, completa todos los campos antes de enviar.');
        return;
    }

    const noticia = { titulo, descripcion, foto, fechaPublicacion, autores };

    fetch('http://localhost:8080/polo_de_salud/noticias/CrearNoticia', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(noticia),
    })
        .then(response => {
            if (response.ok) {
                alert('Noticia creada exitosamente.');
                document.getElementById('formCrearNoticiaGestionar').reset(); // Reiniciar formulario
            } else {
                return response.text().then(msg => { throw new Error(msg || 'Error al crear noticia.'); });
            }
        })
        .catch(error => alert(`Error: ${error.message}`));
});
document.getElementById('formCrearProyecto').addEventListener('submit', function (event) {
    event.preventDefault();

    const titulo = document.getElementById('tituloProyecto').value.trim();
    const descripcion = document.getElementById('descripcionProyecto').value.trim();
    const proyectosRel = document.getElementById('proyectosRel').value.split(',').map(id => id.trim());

    if (!titulo || !descripcion) {
        alert('Por favor, completa todos los campos antes de enviar.');
        return;
    }

    const proyecto = { titulo, descripcion, proyectosRel };

    fetch('http://localhost:8080/polo_de_salud/proyectos/CrearProyecto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(proyecto),
    })
        .then(response => {
            if (response.ok) {
                alert('Proyecto creado exitosamente.');
                document.getElementById('formCrearProyecto').reset();
            } else {
                return response.text().then(msg => { throw new Error(msg || 'Error al crear proyecto.'); });
            }
        })
        .catch(error => alert(`Error: ${error.message}`));
});