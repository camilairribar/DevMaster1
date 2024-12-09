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

    const obtenerNoticias = async () => {
        try {
            const response = await fetch('http://localhost:8080/polo_de_salud/noticias/ListaNoticia');
            if (!response.ok) {
                throw new Error('Error al obtener las noticias');
            }

            const noticias = await response.json();

            noticias.forEach(noticia => {
                const noticiaCard = document.createElement('div');
                noticiaCard.classList.add('noticia-card');
                noticiaCard.innerHTML = `
                    <img src="${noticia.foto || 'image/default-image.jpg'}" alt="Imagen de la noticia" class="noticia-img">
                    <h3>${noticia.titulo || 'Título no disponible'}</h3>
                    <p>${noticia.contenido || 'Descripción no disponible'}</p>
                    <p><strong>Fecha de publicación:</strong> ${formatearFecha(noticia.fechaPublicacion)}</p>
                `;
                noticiasContainer.appendChild(noticiaCard);
            });
        } catch (error) {
            console.error(error);
            noticiasContainer.innerHTML = '<p>Error al cargar las noticias.</p>';
        }
    };
    const formatearFecha = (fechaISO) => {
        if (!fechaISO) return 'Fecha no disponible';
        const fecha = new Date(fechaISO);
        return isNaN(fecha.getTime()) 
            ? 'Fecha no válida'
            : fecha.toLocaleDateString('es-ES', {
                year: 'numeric',
                month: 'long',
                day: 'numeric',
            });
    };

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
// Función para verificar si el usuario está autenticado
function verificarSesion() {
    if (!localStorage.getItem("userLoggedIn")) {
        window.location.href = "iniciar_sesion.html"; // Redirige a la página de inicio de sesión si no está autenticado
    }
}

// Función para verificar si el usuario está autenticado
function verificarSesion() {
    // Verificar si el usuario tiene una sesión activa
    if (!localStorage.getItem("userLoggedIn")) {
        // Redirigir al login si no está autenticado
        window.location.href = "iniciar_sesion.html"; 
    }
}

// Función para cerrar sesión
function cerrarSesion() {
    // Eliminar el estado de sesión del almacenamiento local
    localStorage.removeItem("userLoggedIn");
    
    // Redirigir al inicio o a la página de login
    window.location.href = "index.html"; // O a la página de inicio de sesión si prefieres
}


// Crear Noticia
document.getElementById('formCrearNoticia').addEventListener('submit', function (event) {
    event.preventDefault();

    const titulo = document.getElementById('tituloNoticia').value.trim();
    const contenido = document.getElementById('contenidoNoticia').value.trim();
    const foto = document.getElementById('fotoNoticia').value.trim();
    const fechaPublicacion = document.getElementById('fechaPublicacionNoticia').value.trim();

    if (!titulo || !contenido || !foto || !fechaPublicacion) {
        alert('Por favor, completa todos los campos antes de enviar.');
        return;
    }

    const noticia = {
        titulo: titulo,
        contenido: contenido,
        foto: foto,
        fechaPublicacion: fechaPublicacion,
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
                document.getElementById('formCrearNoticia').reset();
            } else {
                response.text().then(text => {
                    alert(`Error al crear la noticia: ${text}`);
                });
            }
        })
        .catch(error => {
            console.error('Error al crear noticia:', error);
            alert('Error en la conexión con el servidor.');
        });
});

function cargarNoticias() {
    fetch('http://localhost:8080/polo_de_salud/noticias/ListaNoticia')
        .then(response => response.json())
        .then(data => {
            const tabla = document.getElementById('tablaNoticias').querySelector('tbody');
            tabla.innerHTML = ''; // Limpiar tabla antes de cargar

            data.forEach(noticia => {
                const fila = document.createElement('tr');
                fila.innerHTML = `
                    <td>${noticia.idNoticia}</td>
                    <td>${noticia.titulo}</td>
                `;
                tabla.appendChild(fila);
            });
        })
        .catch(error => {
            console.error('Error al cargar noticias:', error);
            alert('Error al cargar noticias.');
        });
}

// Eliminar Noticia
document.getElementById('btnEliminarNoticia').addEventListener('click', function () {
    const id = document.getElementById('idEliminarNoticia').value.trim();

    if (!id) {
        alert('Por favor, ingresa un ID para eliminar.');
        return;
    }

    fetch(`http://localhost:8080/polo_de_salud/noticias/EliminarNoticia/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                alert("Noticia eliminada exitosamente.");
                document.getElementById('idEliminarNoticia').value = '';
                cargarNoticias(); // Recargar noticias después de eliminar
            } else {
                response.text().then(text => {
                    alert(`Error al eliminar la noticia: ${text}`);
                });
            }
        })
        .catch(error => {
            console.error('Error al eliminar noticia:', error);
            alert('Error en la conexión con el servidor.');
        });
});

//Crear proyecto
document.getElementById('formCrearProyecto').addEventListener('submit', function (event) {
    event.preventDefault();

    const proyecto = {
        nombre: document.getElementById('tituloProyecto').value.trim(),
        descripcion: document.getElementById('descripcionProyecto').value.trim(),
        fechaPublicacion: document.getElementById('fechaPublicacion').value,
        fechaTermino: document.getElementById('fechaTermino').value,
        estado: document.getElementById('estadoProyecto').value,
        foto: document.getElementById('fotoProyecto').value.trim(),
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
                alert('Proyecto creado exitosamente.');
                document.getElementById('formCrearProyecto').reset();
            } else {
                return response.text().then(msg => { throw new Error(msg || 'Error al crear proyecto.'); });
            }
        })
        .catch(error => alert(`Error: ${error.message}`));
});

// Cargar y mostrar los proyectos disponibles
function cargarProyectos() {
    fetch('http://localhost:8080/polo_de_salud/proyectos/ListaProyecto', {
        method: 'GET',
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Error al cargar los proyectos");
            }
        })
        .then(proyectos => {
            const lista = document.getElementById('proyectosDisponibles');
            lista.innerHTML = ""; // Limpiar la lista antes de cargar
            proyectos.forEach(proyecto => {
                const item = document.createElement('li');
                item.textContent = `ID: ${proyecto.idProyecto}, Título: ${proyecto.nombre}`;
                lista.appendChild(item);
            });
        })
        .catch(error => console.error('Error al cargar proyectos:', error));
}

// Eliminar Proyecto
function eliminarProyecto() {
    const id = document.getElementById('idEliminarProyecto').value.trim();

    if (!id) {
        alert('Por favor, ingresa el ID del proyecto a eliminar.');
        return;
    }

    // Confirmar la eliminación
    const confirmacion = confirm(`¿Estás seguro de que deseas eliminar el proyecto con ID ${id}? Esta acción no se puede deshacer.`);

    if (confirmacion) {
        fetch(`http://localhost:8080/polo_de_salud/proyectos/EliminarProyecto/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.ok) {
                alert("Proyecto eliminado exitosamente.");
                cargarProyectos(); // Recargar la lista después de eliminar
            } else {
                alert("Error al eliminar el proyecto. Verifica el ID ingresado.");
            }
        })
        .catch(error => alert(`Error: ${error.message}`));
    } else {
        alert('Operación de eliminación cancelada.');
    }
}

//colaboradores
document.addEventListener("DOMContentLoaded", () => {
    const baseUrl = "http://localhost:8080/polo_de_salud/colaboradores";
    const listaColaboradoresUl = document.getElementById("colaboradores-lista");
    const crearForm = document.getElementById("crear-form");
    const eliminarForm = document.getElementById("eliminar-form");
    const buscarButton = document.getElementById("buscar-colaborador");
    const colaboradorInfoDiv = document.getElementById("colaborador-info");

    // Función para verificar si el usuario está logueado
    function verificarLogin() {
        const userLogueado = sessionStorage.getItem("user");
        if (!userLogueado) {
            alert("Debe iniciar sesión para acceder a esta página.");
            window.location.href = "login.html";
        }
    }

    // Verificar login al cargar la página
    verificarLogin();

    // Listar colaboradores
    function listarColaboradores() {
        fetch(`${baseUrl}/ListaColaborador`)
            .then(response => response.json())
            .then(data => {
                listaColaboradoresUl.innerHTML = "";
                data.forEach(colaborador => {
                    const li = document.createElement("li");
                    li.textContent = `${colaborador.idColaborador} - ${colaborador.nombreColaborador}`;
                    listaColaboradoresUl.appendChild(li);
                });
            })
            .catch(error => console.error("Error al listar colaboradores:", error));
    }

    // Crear colaborador
    crearForm.addEventListener("submit", event => {
        event.preventDefault();
        const nuevoColaborador = {
            nombreColaborador: document.getElementById("nombre").value,
            correoColaborador: document.getElementById("correo").value,
            contrasenaColaborador: document.getElementById("contrasena").value
        };

        fetch(`${baseUrl}/CrearColaborador`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(nuevoColaborador)
        })
            .then(response => {
                if (response.ok) {
                    alert("Colaborador creado exitosamente.");
                    listarColaboradores();
                } else {
                    alert("Error al crear colaborador.");
                }
            })
            .catch(error => console.error("Error al crear colaborador:", error));
    });

    // Buscar colaborador por ID para eliminar
    buscarButton.addEventListener("click", () => {
        const id = document.getElementById("eliminar-id").value;

        fetch(`${baseUrl}/${id}`)
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Colaborador no encontrado");
                }
            })
            .then(data => {
                colaboradorInfoDiv.textContent = `Nombre: ${data.nombreColaborador}`;
                eliminarForm.querySelector("button[type='submit']").disabled = false;
            })
            .catch(error => {
                colaboradorInfoDiv.textContent = "";
                eliminarForm.querySelector("button[type='submit']").disabled = true;
                alert(error.message);
            });
    });

    // Eliminar colaborador
    eliminarForm.addEventListener("submit", event => {
        event.preventDefault();
        const id = document.getElementById("eliminar-id").value;

        fetch(`${baseUrl}/EliminarColaborador/${id}`, {
            method: "DELETE"
        })
            .then(response => {
                if (response.ok) {
                    alert("Colaborador eliminado exitosamente.");
                    listarColaboradores();
                } else {
                    alert("Error al eliminar colaborador.");
                }
            })
            .catch(error => console.error("Error al eliminar colaborador:", error));
    });

    // Cargar lista inicial de colaboradores
    listarColaboradores();
});

// Esperar a que el documento cargue completamente
// Seleccionar el contenedor de carreras
const container = document.getElementById('carreras-container');

// Función para obtener las carreras desde el backend
function fetchCarreras() {
    fetch('http://localhost:8080/polo_de_salud/carreras/ListaCarrera')
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error al obtener las carreras: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            renderCarreras(data);
        })
        .catch(error => {
            console.error('Error:', error);
            container.innerHTML = `<p style="color: red;">Ocurrió un error al cargar las carreras. Intenta de nuevo más tarde.</p>`;
        });
}

// Función para renderizar las carreras en la página
function renderCarreras(carreras) {
    carreras.forEach(carrera => {
        // Crear tarjeta
        const card = document.createElement('div');
        card.classList.add('carrera-card');

        // Contenido de la tarjeta
        card.innerHTML = `
            <h3>${carrera.nombre}</h3>
            <p><strong>Facultad:</strong> ${carrera.facultad}</p>
            <p>${carrera.descripcion}</p>
        `;

        // Agregar la tarjeta al contenedor
        container.appendChild(card);
    });
}

// Cargar las carreras al cargar la página
d// Verificar si el usuario está autenticado
if (!localStorage.getItem("userLoggedIn")) {
    window.location.href = "iniciar_sesion.html"; // Redirigir si no está autenticado
}

// Función para cerrar sesión
function logout() {
    localStorage.removeItem("userLoggedIn"); // Eliminar sesión
    window.location.href = "index.html"; // Redirigir al inicio
}

// Crear Carrera
document.getElementById('formCrearCarrera').addEventListener('submit', function (event) {
    event.preventDefault();

    const nombre = document.getElementById('nombreCarrera').value.trim();
    const descripcion = document.getElementById('descripcionCarrera').value.trim();
    const facultad = document.getElementById('facultadCarrera').value.trim();

    if (!nombre || !descripcion || !facultad) {
        alert('Por favor, completa todos los campos antes de enviar.');
        return;
    }

    const carrera = {
        nombre: nombre,
        descripcion: descripcion,
        facultad: facultad
    };

    fetch('http://localhost:8080/polo_de_salud/carreras/CrearCarrera', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(carrera),
    })
        .then(response => {
            if (response.ok) {
                alert("Carrera creada exitosamente");
                document.getElementById('formCrearCarrera').reset();
            } else {
                response.text().then(text => {
                    alert(`Error al crear la carrera: ${text}`);
                });
            }
        })
        .catch(error => {
            console.error('Error al crear carrera:', error);
            alert('Error en la conexión con el servidor.');
        });
});

// Eliminar Carrera
document.getElementById('btnEliminarCarrera').addEventListener('click', function () {
    const id = document.getElementById('idEliminarCarrera').value.trim();

    if (!id) {
        alert('Por favor, ingresa un ID para eliminar.');
        return;
    }

    fetch(`http://localhost:8080/polo_de_salud/carreras/EliminarCarrera/${id}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                alert("Carrera eliminada exitosamente");
                document.getElementById('idEliminarCarrera').value = '';
            } else {
                response.text().then(text => {
                    alert(`Error al eliminar la carrera: ${text}`);
                });
            }
        })
        .catch(error => {
            console.error('Error al eliminar carrera:', error);
            alert('Error en la conexión con el servidor.');
        });
});

// Cargar Carreras
document.getElementById('btnCargarCarreras').addEventListener('click', function () {
    fetch('http://localhost:8080/polo_de_salud/carreras/ListaCarrera', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(carreras => {
            const listaCarreras = document.getElementById('listaCarreras');
            listaCarreras.innerHTML = ''; // Limpiar la lista

            carreras.forEach(carrera => {
                const li = document.createElement('li');
                li.textContent = `ID: ${carrera.idCarreras}, Nombre: ${carrera.nombre}, Facultad: ${carrera.facultad}`;
                listaCarreras.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error al cargar carreras:', error);
            alert('Error en la conexión con el servidor.');
        });
});
