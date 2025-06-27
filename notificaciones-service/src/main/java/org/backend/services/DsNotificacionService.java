package org.backend.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

@Service
public class DsNotificacionService {
    private static final String WEBHOOK_URL = "https://discordapp.com/api/webhooks/1388286351685713920/eXJ6eokzOHzHkZ-_rrOeEgifnygTRx4wUJ_J7yDnjgzmlk0QUcYYF6y2MOzxKDQ4I7x7";

    public void sendMessage(String message) {
        try {
            if (message == null || message.trim().isEmpty()) {
                System.out.println("Mensaje vacío, no se envía a Discord");
                return;
            }

            // Escapar comillas y saltos de línea para formar JSON válido
            String escapedMessage = message
                    .replace("\\", "\\\\")  // primero escapamos backslashes
                    .replace("\"", "\\\"")  // luego comillas
                    .replace("\n", "\\n");  // y los saltos de línea

            String json = String.format("{\"content\":\"%s\"}", escapedMessage);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(WEBHOOK_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Discord: " + response.statusCode());
            System.out.println("Respuesta Discord: " + response.body());

        } catch (Exception e) {
            System.out.println("Error al enviar mensaje a Discord");
            e.printStackTrace();
        }
    }

}
