package br.eti.heitor.JWTSecurity.security.entities;

public class AuthResponse {
    private String email;
    private String acessToken;

    public AuthResponse() {
    }
    
    public AuthResponse(String email, String acessToken) {
        this.email = email;
        this.acessToken = acessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcessToken() {
        return acessToken;
    }

    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }
    
    
    
    
}
