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