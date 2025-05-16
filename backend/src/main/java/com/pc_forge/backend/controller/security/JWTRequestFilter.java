package com.pc_forge.backend.controller.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.pc_forge.backend.controller.service.JWTService;
import com.pc_forge.backend.model.entity.user.User;
import com.pc_forge.backend.model.repository.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


/**
 * Klasa odpowiedzialna za filtrowanie żądań HTTP. Filtrowanie odbywa się raz na wysłane żądanie.
 * Podczas filtrowania żądań przetwarzany jest token JWT (JSON Web Token), zapisany w nagłówku żądania,
 * służący do autentykacji zalogowanych użytkowników.
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    /**
     * Serwis odpowiedzialny za tworzenie i weryfikację tokenów JWT
     */
    private final JWTService jwtService;

    /**
     * Repozytorium/DAO użytkownika, służące do pozyskiwania encji użytkownika na podstawie tokenu JWT
     */
    private final UserRepository userRepository;

    /**
     * Konstruktor klasy filtra żądań
     *
     * @param jwtService     serwis JWT
     * @param userRepository serwis użytkownika
     */
    public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * Metoda przetwarzająca przychodzące żądania HTTP, w celu ich filtracji.
     * Odczytuje token znajdujący się w nagłówku autoryzacji żądania, rozpoczynający się od "Bearer ".
     * Token jest deszyfrowany, a następnie pobierana jest z niego nazwa użytkownika,
     * służąca dalej do pobrania encji użytkownika z bazy danych. Obiekt ten jest później ustawiany w Spring Security.
     * W przypadku braku nagłówka autoryzacji w otrzymanym żądaniu lub jeśli nie zaczyna się on na "Bearer ",
     * żądanie przechodzi dalej przez kolejne filtry bez ustawionej autentykacji.
     *
     * @param request     otrzymane, filtrowane żądanie HTTP
     * @param response    przetwarzana odpowiedź HTTP
     * @param filterChain łańcuch filtrów żądania HTTP, pozwalający przekazać je dalej,
     *                    w przypadku braku odpowiedniego nagłówka
     * @throws ServletException wyjątek wyrzucany w przypadku błędów Servlet podczas przekazania żądania do dalszego filtrowania
     * @throws IOException      wyjątek wyrzucany w przypadku błędu wejścia/wyjścia, podczas przekazania żądania do dalszego filtrowania
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            try {
                String username = jwtService.getUsernameClaim(token);
                Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(username);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JWTDecodeException e) {
                System.out.println("JWT verification failed");
            } catch (TokenExpiredException e) {
                System.out.println("JWT token expired");
            }
        }
        filterChain.doFilter(request, response);
    }
}
