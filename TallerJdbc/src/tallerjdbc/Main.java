package tallerjdbc;

    import java.util.Scanner;

    public class Main {

        private static String leerNombre(Scanner sc, String campo) {
            String valor;
            while (true) {
                System.out.print(campo + ": ");
                valor = sc.nextLine();
                if (valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) break;
                System.out.println("Solo se permiten letras y espacios.");
            }
            return valor;
        }

        private static String leerCorreo(Scanner sc) {
            String correo;
            while (true) {
                System.out.print("Correo: ");
                correo = sc.nextLine();
                if (correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) break;
                System.out.println("Correo invalido. Ejemplo: usuario@dominio.com");
            }
            return correo;
        }

        private static int leerEdad(Scanner sc) {
            int edad = -1;
            while (true) {
                System.out.print("Edad: ");
                String entrada = sc.nextLine();
                try {
                    edad = Integer.parseInt(entrada);
                    if (edad > 0) break;
                    else System.out.println(" La edad debe ser mayor que 0.");
                } catch (NumberFormatException e) {
                    System.out.println(" Debe ingresar un número válido.");
                }
            }
            return edad;
        }

        private static EstadoCivil leerEstadoCivil(Scanner sc) {
            while (true) {
                System.out.print("Estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
                String estadoStr = sc.nextLine().toUpperCase();
                try {
                    return EstadoCivil.valueOf(estadoStr);
                } catch (IllegalArgumentException ex) {
                    System.out.println(" Estado civil invalido.");
                }
            }
        }

        public static void main(String[] args) {
            EstudianteDAO dao = new EstudianteDAO();
            Scanner sc = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\n===== MENU ESTUDIANTES =====");
                System.out.println("1. Insertar Estudiante");
                System.out.println("2. Actualizar Estudiante");
                System.out.println("3. Eliminar Estudiante");
                System.out.println("4. Consultar todos los estudiantes");
                System.out.println("5. Consultar Estudiante por email");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> {
                        String nombre = leerNombre(sc, "Nombre");
                        String apellido = leerNombre(sc, "Apellido");
                        String correo = leerCorreo(sc);
                        int edad = leerEdad(sc);
                        EstadoCivil estado = leerEstadoCivil(sc);
                        dao.insertar(new Estudiante(nombre, apellido, correo, edad, estado));
                    }
                    case 2 -> {
                        System.out.print("Correo del estudiante a actualizar: ");
                        String correo = sc.nextLine();
                        String nombre = leerNombre(sc, "Nuevo nombre");
                        String apellido = leerNombre(sc, "Nuevo apellido");
                        int edad = leerEdad(sc);
                        EstadoCivil estado = leerEstadoCivil(sc);
                        dao.actualizar(correo, new Estudiante(nombre, apellido, correo, edad, estado));
                    }
                    case 3 -> {
                        System.out.print("Correo del estudiante a eliminar: ");
                        String correo = sc.nextLine();
                        dao.eliminar(correo);
                    }
                    case 4 -> dao.listarTodos().forEach(System.out::println);
                    case 5 -> {
                        System.out.print("Correo del estudiante: ");
                        String correo = sc.nextLine();
                        Estudiante e = dao.buscarPorCorreo(correo);
                        if (e != null) System.out.println(e);
                        else System.out.println("⚠️ No encontrado");
                    }
                    case 6 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción invalida");
                }
            } while (opcion != 6);

            sc.close();
        }
    }