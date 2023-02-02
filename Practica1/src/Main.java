import java.util.Scanner;

public class Main {
    static String[][] products = new String[20][2];
    static Scanner sc = new Scanner(System.in);
    static boolean flag;
    static String productName;
    static String productPrice;
    static int contF = 0;
    public static void main(String[] args) {
        String user = "cajero_202201444";
        String password = "ipc1_202201444";
        String userL = "";
        String passL = "";
        String option = "";
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
                    System.out.println("======= AGREGAR PRODUCTO =======");
                    addProduct();
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
                    MostrarProductos();
                    break;
                default:
                    flag = false;
                    System.out.println("OPCIÓN NO VALIDA\n");
            }
        } while(flag == false);

    }

    public static void addProduct() {
        String resp;
        boolean flagP = true;
        boolean flagO = true;
        do {
            System.out.println("¿DESEAS INGRESAR UN PRODUCTO? (si/no)");
            resp = sc.nextLine();
            flagP = true;
            switch (resp.toLowerCase()) {
                case "si":
                    MethodAddProduct();
                    do {
                        System.out.println("¿DESEAS INGRESAR OTRO PRODUCTO? (si/no)");
                        resp = sc.nextLine();
                        flagO = true;
                        switch (resp.toLowerCase()) {
                            case "si":
                                MethodAddProduct();
                                flagO = false;
                                break;
                            case "no":
                                flag = false;
                                break;
                            default:
                                flagO = false;
                                System.out.println("OPCIÓN NO VALIDA\n");
                        }
                    } while(flagO == false);

                    if(resp.equalsIgnoreCase("no")){
                        flag=false; // PARA QUE AL SALIR DE AGREGAR PRODUCTOS REGRESE AL MENU
                    }
                    break;

                case "no":
                    flag = false;
                    break;

                default:
                    flagP = false;
                    System.out.println("OPCIÓN NO VALIDA\n");
            }
        } while(flagP == false);

    }

    public static void MethodAddProduct() {

        if(contF != products.length) {
            System.out.println("INGRESA EL NOMBRE DEL PRODUCTO");
            productName = sc.nextLine();
            System.out.println("INGRESA EL PRECIO DEL PRODUCTO");
            productPrice = sc.nextLine();

            if(ValidateProductName(productName) != true) {
                if(ValidateProductPrice(productPrice) == true) {
                    products[contF][0] = productName;
                    products[contF][1] = productPrice;
                    System.out.println("======= PRODUCTO AGREGADO =======");
                    contF++;
                }
            } else {
                System.out.println("YA EXISTE UN PRODUCTO CON ESE NOMBRE");
            }

        } else {
            System.out.println("LIMITE DE PRODUCTOS ALCANZADO");
        }
    }

    // METODO PARA VALIDAR SI YA EXISTE UN PRODUCTO CON EL NOMBRE INGRESADO
    public static boolean ValidateProductName(String productN) {
        boolean flag = false;
        for (int i = 0; i < products.length; i++) {
            if (products[i][0] != null) {
                if (products[i][0].equalsIgnoreCase(productN)) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    // METODO PARA VERIFICAR SI EL PRECIO INGRESADO ES VALIDO
    public static boolean ValidateProductPrice(String productPrice){
        boolean flag = false;
        boolean result = true;
        double price = 0;

        try {
            price = Double.parseDouble(productPrice);
        } catch (NumberFormatException e){
            result = false;
        }

        if (result == true) {

            if (price > 0) {
                flag = true;
            } else {
                System.out.println("NUMERO NO VALIDO");
                flag = false;
            }
        } else {
            System.out.println("PRODUCTO NO AGREGADO");
            System.out.println("POR FAVOR INGRESE UN NUMERO");
            flag = false;
        }

        return flag;
    }

    // METODO PARA VER LOS PRODUCTOS AGREGADOS
    public static void MostrarProductos() {
        for (int i = 0; i < products.length; i++){
            for (int j = 0; j < products[0].length; j++){
                System.out.println(products[i][j]);
            }
        }
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