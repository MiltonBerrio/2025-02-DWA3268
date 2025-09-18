package tallerjdbc;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private int edad;
    private EstadoCivil estadoCivil;

    public Estudiante(int id, String nombre, String apellido, String correo, int edad, EstadoCivil estadoCivil) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
    }

    public Estudiante(String nombre, String apellido, String correo, int edad, EstadoCivil estadoCivil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
    }


    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public int getEdad() { return edad; }
    public EstadoCivil getEstadoCivil() { return estadoCivil; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }

    @Override
    public String toString() {
        return id + " | " + nombre + " " + apellido + " | " + correo + " | " + edad + " | " + estadoCivil;
    }
}
