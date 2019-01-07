package sercandevops.com.veterineruygulamasi.Models;

public class LoginModel {

    private boolean tf;
    private String mailadres;
    private String parola;
    private String id;
    private String text;
    private String username;

    public boolean isTf() {
        return tf;
    }

    public void setTf(boolean tf) {
        this.tf = tf;
    }


    public String getMailadres() {
        return mailadres;
    }

    public void setMailadres(String mailadres) {
        this.mailadres = mailadres;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
