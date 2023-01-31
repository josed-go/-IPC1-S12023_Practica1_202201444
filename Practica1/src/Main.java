import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String user = "cajero_202201444";
        String password = "ipc1_202201444";
        String userL = "";
        String passL = "";
        String option = "";
        boolean flag;
        System.out.println("\n" +
                "   _____ _    _ _____  ______ _____      ___  _____ \n" +
                "  / ____| |  | |  __ \\|  ____|  __ \\    |__ \\| ____|\n" +
                " | (___ | |  | | |__) | |__  | |__) |_____ ) | |__  \n" +
                "  \\___ \\| |  | |  ___/|  __| |  _  /______/ /|___ \\ \n" +
                "  ____) | |__| | |    | |____| | \\ \\     / /_ ___) |\n" +
                " |_____/ \\____/|_|    |______|_|  \\_\\   |____|____/ \n" +
                "                                                    \n" +
                "                                                    \n");

        // LOGIN
        while (!userL.equals(user) || !passL.equals(password)) {
            System.out.println("======= POR FAVOR INGRESAR TUS CREDENCIALES =======");
            System.out.println("INGRESAR USUARIO:");
            userL = sc.nextLine();
            System.out.println("INGRESAR CONTRASEÑA:");
            passL = sc.nextLine();
            if(!userL.equals(user) && !passL.equals(password)) {
                System.out.println("DATOS INCORRECTOS");
            }
        }
        System.out.println("======= BIENVENIDO USUARIO "+user+" =======\n");

        // MENU
        do {
            System.out.println("======= MENU =======");
            System.out.println("| 1 | AGREGAR PRODUCTOS");
            System.out.println("| 2 | AGREGAR CUPONES DE DESCUENTO");
            System.out.println("| 3 | REALIZAR VENTAS");
            System.out.println("| 4 | GENERAR REPORTES");
            System.out.println("INGRESE UNA OPCIÓN");
            option = sc.nextLine();
            flag = true;
            switch (option) {
                case "1":
                    System.out.println("======= AGREGAR PRODUCTOS =======");
                    AgregarProductos();
                    break;
                case "2":
                    System.out.println("======= AGREGAR CUPONES DE DESCUENTO =======");
                    AgregarCupones();
                    break;
                case "3":
                    System.out.println("======= REALIZAR VENTAS =======");
                    RealizarVentas();
                    break;
                case "4":
                    System.out.println("======= GENERAR REPORTES =======");
                    GenerarReportes();
                    break;
                default:
                    flag = false;
                    System.out.println("OPCIÓN NO VALIDA\n");
            }
        } while(flag == false);

    }

    public static void AgregarProductos() {
        System.out.println("Metodo agregar productos");
    }
    public static void AgregarCupones() {
        System.out.println("Metodo agregar cupones");
    }

    public static void RealizarVentas() {
        System.out.println("Metodo realizar ventas");
    }

    public static void GenerarReportes() {
        System.out.println("Metodo generar reportes");
    }
}