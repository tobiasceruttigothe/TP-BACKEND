package org.backend.notificaciones.service;

import org.backend.dtos.NotificacionesCreate;
import org.backend.entities.Empleado;
import org.backend.entities.Interesado;
import org.backend.entities.Notificacion;
import org.backend.entities.Vehiculo;
import org.backend.repository.NotificacionesRepository;

import org.backend.services.InteresadoService;
import org.backend.services.NotificacionesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//prueba unitaria para NotificacionesSerice, verifica que el metodo saveNotificacion funcione correctamente

public class NotificacionesServiceTest {
    //hacemos 2 mocks, uno para el repositorio y otro para el servicio de interesados
    private NotificacionesRepository notificacionesRepository;
    private InteresadoService interesadoService;
    private NotificacionesService notificacionesService;

    @BeforeEach
    void setup() {
        //creamos los mocks de las dependencias para que simulen los comporamientos de esas clases
        notificacionesRepository = mock(NotificacionesRepository.class);
        interesadoService = mock(InteresadoService.class);
        // creamos una instancia real de NotificacionesService pasandole el mock
        notificacionesService = new NotificacionesService(interesadoService);

        //instanciamos el servicio de notificaciones y le inyectamos el repositorio
        injectRepository(notificacionesService, notificacionesRepository);
    }

    @Test
    void testSaveNotification() {
        Empleado empleado = new Empleado();
        Interesado interesado = new Interesado();
        Vehiculo vehiculo = new Vehiculo();
        String comentario = "Comentario de prueba";

        //creamos objetos falsos para simular datos de entrada
        NotificacionesCreate dto = new NotificacionesCreate(empleado, vehiculo, interesado  , comentario);

        //creamos un dto de notificacion para pasar al metodo a testear y lo invocamos
        notificacionesService.saveNotification(dto);

        // ArgumentCaptor para capturar el argumento que fue pasado a notificacionesRepository.save(...)
        //verifica que ese metodo fue llamado
        ArgumentCaptor<Notificacion> captor = ArgumentCaptor.forClass(Notificacion.class);

        //captura la notificacion guardada
        verify(notificacionesRepository).save(captor.capture());

        Notificacion saved = captor.getValue();
        assertEquals("Comentario de prueba", saved.getComentario());
        assertEquals(dto.getEmpleado(), saved.getEmpleado());
        assertEquals(dto.getInteresado(), saved.getInteresado());
        assertEquals(dto.getVehiculo(), saved.getVehiculo());
        assertEquals(LocalDate.now(), saved.getFecha());

        //verificcamos que los valores de la notificacion guardada sean correctos
        verify(interesadoService, times(1)).restringirInteresado(dto.getInteresado().getId());
        //tambien se verifica que se llame a restringirInteresado
    }

   //este mettodo inyecta manualmente el mock NotificacionesRepository
   // dentro de la instancia de NotificacionesService, porque ese campo está anotado con @Autowired y no se pasa por constructor.
   // como en el test no usamos spring completo, necesitamos meterlo a mano
    private void injectRepository(NotificacionesService service, NotificacionesRepository repo) {
        try {
            var field = NotificacionesService.class.getDeclaredField("notificacionesRepository");
            field.setAccessible(true);
            field.set(service, repo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
