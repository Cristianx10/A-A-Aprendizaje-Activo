package FirebaseConexion;

public class Usuario {

    private String name;
    private String email;
    private String password;
    private String age;
    private String rol;

    public Usuario() {
    }

    public Usuario(String name, String email, String password, String age, String rol) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
