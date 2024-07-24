package com.adayazilim.sigorta.security;

import com.adayazilim.sigorta.entity.User;
import com.adayazilim.sigorta.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static com.adayazilim.sigorta.security.AuthConstants.TOKEN_PREFIX;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserDetailsServiceImpl userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
//        UserDetails userDetails = userService.loadUserByUsername(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());
//        String token = jwtUtil.generateToken(userDetails);
//
//        JSONObject jsonResponse = new JSONObject();
//        jsonResponse.put("token", TOKEN_PREFIX + token);
//
//        PrintWriter writer = res.getWriter();
//        writer.write(jsonResponse.toString());
//        writer.flush();
//    }


    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        UserDetails userDetails = userService.loadUserByUsername(
                ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername());

        String token = jwtUtil.generateToken(userDetails);
        res.addHeader("Access-Control-Expose-Headers", "Authorization");
        res.addHeader("Authorization", "Bearer " + token);

    }

}
