import java.util.Scanner;

public class Main {
    static String[][] products = new String[20][2];
    static String[][] coupons = new String[20][2];
    static Scanner sc = new Scanner(System.in);
    static boolean flag;
    static String productName;
    static String productPrice;
    static int contP = 0;
    static String couponCode;
    static String discountPercentage;
    static int contC = 0;
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
        System.out.println("======= BIENVENIDO USUARIO "+user+" =======");

        // MENU
        do {
            System.out.println("\n======= MENU =======");
            System.out.println("| 1 | AGREGAR PRODUCTOS");
            System.out.println("| 2 | MOSTRAR PRODUCTOS");
            System.out.println("| 3 | AGREGAR CUPONES DE DESCUENTO");
            System.out.println("| 4 | REALIZAR VENTAS");
            System.out.println("| 5 | GENERAR REPORTES");
            System.out.println("INGRESE UNA OPCIÓN");
            option = sc.nextLine();
            flag = true;
            switch (option) {
                case "1":
                    System.out.println("======= AGREGAR PRODUCTO =======");
                    AddProduct();
                    break;
                case "2":
                    System.out.println("======= MOSTRAR PRODUCTOS =======");
                    ShowProducts();
                    break;
                case "3":
                    System.out.println("======= AGREGAR CUPONES DE DESCUENTO =======");
                    AddCoupons();
                    break;
                case "4":
                    System.out.println("======= REALIZAR VENTAS =======");
                    RealizarVentas();
                    break;
                case "5":
                    System.out.println("======= GENERAR REPORTES =======");
                    GenerarReportes();
                    ShowCoupons();
                    break;
                default:
                    flag = false;
                    System.out.println("OPCIÓN NO VALIDA\n");
            }
        } while(flag == false);

    }

    public static void AddProduct() {
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

        if(contP != products.length) {
            System.out.println("INGRESA EL NOMBRE DEL PRODUCTO");
            productName = sc.nextLine();
            System.out.println("INGRESA EL PRECIO DEL PRODUCTO");
            productPrice = sc.nextLine();

            if(ValidateProductName(productName) != true) {
                if(ValidateProductPrice(productPrice) == true) {
                    products[contP][0] = productName;
                    products[contP][1] = productPrice;
                    System.out.println("======= PRODUCTO AGREGADO =======");
                    contP++;
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
    public static void ShowProducts() {
        System.out.println("|==== PRODUCTO ====|==== PRECIO ====|");
        for (int i = 0; i < products.length; i++){
                if(products[i][0] != null) {
                    System.out.println("|     "+products[i][0]+ "     |     "+products[i][1]+"     |");
                    System.out.println("|===================================|\n");
                }
        }
        flag = false;
    }

    public static void AddCoupons() {
        String resp;
        boolean flagC = true;
        boolean flagO = true;
        do {
            System.out.println("¿DESEAR AGREGAR UN CUPÓN?");
            resp = sc.nextLine();
            flagC = true;

            switch(resp.toLowerCase()) {
                case "si":
                    MethodAddCoupon();
                    do {
                        System.out.println("¿DESEAS AGREGAR OTRO CUPÓN?");
                        resp = sc.nextLine();
                        flagO = true;
                        switch (resp.toLowerCase()) {
                            case "si":
                                MethodAddCoupon();
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

                    break;
                case "no":
                    flag = false;
                    break;
                default:
                    flagC = false;
                    System.out.println("OPCIÓN NO VALIDA\n");
            }
        } while(flagC == false);
    }

    // METODO PARA AGREGAR CUPONES
    public static void MethodAddCoupon() {

        if (contC != coupons.length) {
            System.out.println("INGRESA EL CÓDIGO DEL CUPÓN (4 CARACTERES)");
            couponCode = sc.nextLine();
            System.out.println("INGRESA EL PORCENTAJE DE DESCUENTO (0-100)%");
            discountPercentage = sc.nextLine();

            if(ValidateCouponCode(couponCode) != true) {
                if(ValidateDiscountPercentage(discountPercentage) == true) {
                    coupons[contC][0] = couponCode.toUpperCase();
                    coupons[contC][1] = discountPercentage;
                    System.out.println("======= CUPÓN AGREGADO =======");
                    contC++;
                }
            }

        } else {
            System.out.println("LIMITE DE CUPONES ALCANZADO");
        }
    }

    // METODO PARA VALIDAR EL CODIGO DEL CUPON
    public static boolean ValidateCouponCode(String couponCode) {
        boolean flag = false;

        if(couponCode.length() == 4) {
            for (int i = 0; i < coupons.length; i++) {
                if (coupons[i][0] != null) {
                    if (coupons[i][0].equalsIgnoreCase(couponCode)) {
                        flag = true;
                        System.out.println("YA EXISTE UN CUPÓN CON ESE CÓDIGO");
                    }
                }
            }
        } else {
            System.out.println("EL CÓDIGO DEBE SER DE 4 CARACTERES");
            flag = true;
        }
        return flag;
    }

    // METODO PARA VALIDAR EL PORCENTAJE INGRESADO
    public static boolean ValidateDiscountPercentage(String discountPercentage) {
        boolean flag = false;
        boolean result = true;
        double discountP = 0;

        try {
            discountP = Double.parseDouble(discountPercentage);
        } catch (NumberFormatException e) {
            result = false;
        }

        if(result == true) {

            if (discountP >= 1 && discountP <= 100) {
                flag = true;
            } else {
                System.out.println("PORCENTAJE NO VALIDO");
                flag = false;
            }

        } else {
            System.out.println("CUPÓN NO AGREGADO");
            System.out.println("POR FAVOR INGRESE UN NÚMERO");
            flag = false;
        }
        return flag;
    }

    // METODO PARA MOSTRAR TODOS LOS CUPONES
    public static void ShowCoupons() {
        System.out.println("|==== CÓDIGO ====|==== DESCUENTO ====|");
        for (int i = 0; i < coupons.length; i++){
            if(coupons[i][0] != null) {
                System.out.println("|       "+coupons[i][0]+ "     |        "+coupons[i][1]+"%         |");
                System.out.println("|===================================|");
            }
        }
        flag = false;
    }

    public static void RealizarVentas() {
        System.out.println("Metodo realizar ventas");
    }

    public static void GenerarReportes() {
        System.out.println("Metodo generar reportes");
    }
}