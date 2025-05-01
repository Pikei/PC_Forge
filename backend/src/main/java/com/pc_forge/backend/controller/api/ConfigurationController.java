package com.pc_forge.backend.controller.api;

import com.pc_forge.backend.controller.exceptions.ConfigurationAlreadyExists;
import com.pc_forge.backend.controller.exceptions.ConfigurationDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.ProductDoesNotExistException;
import com.pc_forge.backend.controller.exceptions.ProductNotCompatibleException;
import com.pc_forge.backend.controller.service.ConfigurationService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.view.body.configuration.ConfigurationBody;
import com.pc_forge.backend.view.response.configuration.ConfigurationResponse;
import com.pc_forge.backend.view.response.configuration.ConfigurationShortResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ConfigurationController {
    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ConfigurationShortResponse>> getAllConfigurations(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(configurationService.getAllConfigurations(user));
    }

    @GetMapping("config/{name}")
    public ResponseEntity<ConfigurationResponse> getConfiguration(@AuthenticationPrincipal User user, @PathVariable String name) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            return ResponseEntity.ok(configurationService.getConfiguration(user, name));
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add-to-cart/{name}")
    public ResponseEntity<Void> addConfigurationToCart(@AuthenticationPrincipal User user, @PathVariable String name) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            configurationService.addConfigurationToCart(user, name);
            return ResponseEntity.ok().build();
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/remove/{name}")
    public ResponseEntity<Void> removeConfiguration(@AuthenticationPrincipal User user, @PathVariable String name) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            configurationService.deleteConfiguration(user, name);
            return ResponseEntity.ok().build();
        } catch (ConfigurationDoesNotExistException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Void> addConfigurationToCart(@AuthenticationPrincipal User user, @Valid @RequestBody ConfigurationBody configuration) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        try {
            configurationService.addConfiguration(user, configuration);
            return ResponseEntity.ok().build();
        } catch (ConfigurationAlreadyExists | ProductDoesNotExistException | ProductNotCompatibleException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
