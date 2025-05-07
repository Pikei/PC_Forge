package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.api.constants.UrlPath;
import com.pc_forge.backend.controller.exceptions.ConfigurationDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.controller.service.ConfigurationService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.configuration.ConfigurationBody;
import com.pc_forge.backend.view.response.configuration.CompatibilityResponse;
import com.pc_forge.backend.view.response.configuration.ConfigurationResponse;
import com.pc_forge.backend.view.response.configuration.ConfigurationShortResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kontroler obsługujący żądania związane z konfiguracjami. Przetwarza żądania pobrania wszystkich lub konkretnej konfiguracji,
 * tworzenia nowych i usuwanie wybranych oraz dodawania produktów z konfiguracji do koszyka.
 */
@RestController
@RequestMapping(UrlPath.CONFIGURATIONS)
public class ConfigurationController {
    /**
     * Serwis obsługujący logikę związaną z konfiguracjami
     */
    private final ConfigurationService configurationService;

    /**
     * Konstruktor wstrzykujący zależność do serwisu konfiguracji
     *
     * @param configurationService Serwis konfiguracji
     */
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Zwraca wszystkie zapisane przez użytkownika konfiguracje
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @return Odpowiedź zawierająca listę uproszczonych informacji o wszystkich konfiguracjach zapisanych przez użytkownika.
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     */
    @GetMapping(UrlPath.ALL)
    public ResponseEntity<List<ConfigurationShortResponse>> getAllConfigurations(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(configurationService.getAllConfigurations(user));
    }

    /**
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param name Nazwa konfiguracji przekazana jako wartość pozyskana z adresu URL
     * @return Odpowiedź zawierająca informację o konkretnej konfiguracji zapisanej przez użytkownika.
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     */
    @GetMapping(UrlPath.CONFIG + "/{name}")
    public ResponseEntity<ConfigurationResponse> getConfiguration(@AuthenticationPrincipal User user, @PathVariable String name) {
        try {
            return ResponseEntity.ok(configurationService.getConfiguration(user, name));
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Dodaje produkty zawarte w wybranej konfiguracji do koszyka
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param name Nazwa konfiguracji przekazana jako wartość pozyskana z adresu URL
     * @return Odpowiedź ze statusem HTTP 200 (OK) w przypadku powodzenia.
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     * W przypadku, jeśli podana została nieistniejąca nazwa konfiguracji użytkownika,
     * zwracana jest odpowiedź ze statusem HTTP 404 (NOT_FOUND).
     */
    @PostMapping(UrlPath.ADD_TO_CART + "/{name}")
    public ResponseEntity<Void> addConfigurationToCart(@AuthenticationPrincipal User user, @PathVariable String name) {
        try {
            configurationService.addConfigurationToCart(user, name);
            return ResponseEntity.ok().build();
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Usuwa wybraną konfigurację
     *
     * @param user Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param name Nazwa konfiguracji przekazana jako wartość pozyskana z adresu URL
     * @return Odpowiedź ze statusem HTTP 200 (OK) w przypadku powodzenia.
     * Jeśli wskazana konfiguracja nie istnieje, zostanie zwrócona odpowiedź ze statusem HTTP 404 (NOT_FOUND).
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     */
    @PostMapping(UrlPath.REMOVE + "/{name}")
    public ResponseEntity<Void> removeConfiguration(@AuthenticationPrincipal User user, @PathVariable String name) {
        try {
            configurationService.deleteConfiguration(user, name);
            return ResponseEntity.ok().build();
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param user          Obiekt zalogowanego użytkownika, wstrzykiwany przez Spring Security
     * @param configuration Ciało żądania zawierające informacje o konfiguracji
     * @return Odpowiedź zawierająca wiadomość typu {@link CompatibilityResponse},
     * która określa czy udało się zapisać konfigurację, lub jakie produkty są niekompatybilne.
     * Jeśli udało się zapisać konfigurację, zwracana odpowiedź ma status HTTP 200 (OK).
     * Jeśli produkty są niekompatybilne, konfiguracja o podanej nazwie już istnieje lub podano id nieistniejącego produktu,
     * zwrócona odpowiedź będzie miała status HTTP 400 (BAD_REQUEST).
     * Jeśli użytkownik nie jest uwierzytelniony, Spring Security zwróci odpowiedź ze statusem HTTP 403 (FORBIDDEN).
     */
    @PostMapping(UrlPath.SAVE)
    public ResponseEntity<CompatibilityResponse> saveConfiguration(@AuthenticationPrincipal User user, @Valid @RequestBody ConfigurationBody configuration) {
        try {
            var response = configurationService.saveOrEditConfiguration(user, configuration);
            if (response.getCompatible()) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().body(response);
        } catch (ClassCastException | ProductDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
