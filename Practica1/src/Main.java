import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static String[][] products = new String[20][3];
    static String[][] coupons = new String[20][2];
    static String[][] productsClient = new String[20][3];
    static Scanner sc = new Scanner(System.in);
    static boolean flag;
    static String productName;
    static String productPrice;
    static int contP = 0;
    static String couponCode;
    static String discountPercentage;
    static int contC = 0;
    static int contV = 0;
    static double subTotal = 0;
    static double total = 0;
    static double disPer;
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
        /*while (!userL.equals(user) || !passL.equals(password)) {
            System.out.println("======= POR FAVOR INGRESAR TUS CREDENCIALES =======");
            System.out.println("INGRESAR USUARIO:");
            userL = sc.nextLine();
            System.out.println("INGRESAR CONTRASEÑA:");
            passL = sc.nextLine();
            if(!userL.equals(user) && !passL.equals(password)) {
                System.out.println("DATOS INCORRECTOS");
            }
        }*/
        System.out.println("======= BIENVENIDO USUARIO "+user+" =======");

        // MENU
        do {
            System.out.println("\n======= MENU =======");
            System.out.println("| 1 | AGREGAR PRODUCTOS");
            System.out.println("| 2 | MOSTRAR PRODUCTOS");
            System.out.println("| 3 | AGREGAR CUPONES DE DESCUENTO");
            System.out.println("| 4 | MOSTRAR CUPONES DE DESCUENTO");
            System.out.println("| 5 | REALIZAR VENTAS");
            System.out.println("| 6 | GENERAR REPORTES");
            System.out.println("| 7 | CERRAR EL PROGRAMA");
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
                    flag = false;
                    break;
                case "3":
                    System.out.println("======= AGREGAR CUPONES DE DESCUENTO =======");
                    AddCoupons();
                    break;
                case "4":
                    System.out.println("======= MOSTRAR CUPONES DE DESCUENTO =======");
                    ShowCoupons();
                    break;
                case "5":
                    System.out.println("======= REALIZAR VENTAS =======");
                    RealizarVentas();
                    break;
                case "6":
                    System.out.println("======= GENERAR REPORTES =======");
                    GenerarReportes();
                    break;
                case "7":
                    System.out.println("======= CERRANDO PROGRAMA =======");
                    System.exit(0);
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
                    products[contP][2] = "0";
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
        System.out.println("| No. |==== PRODUCTO ====|==== PRECIO ====|");
        for (int i = 0; i < products.length; i++){
                if(products[i][0] != null) {
                    System.out.println("| "+(i+1)+" |     "+products[i][0]+ "     |     Q."+products[i][1]+"     |");
                    System.out.println("|===================================|");
                }
        }
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
        String option;
        String resp;
        String nameClient = "";
        String nit = "";
        String code;
        boolean flagV = true;
        boolean flagR = true;
        boolean flagC = true;

        do {
            System.out.println("\n======= MENU DE VENTAS =======");
            System.out.println("| 1 | REALIZAR VENTA");
            System.out.println("| 2 | REGRESAR AL MENU PRINCIPAL");
            System.out.println("INGRESE UNA OPCIÓN");
            option = sc.nextLine();
            switch (option) {
                case "1":
                    if(nameClient == "") {
                        System.out.println("INGRESE SU NIT");
                        nit = sc.nextLine();

                        if(nit.length() == 0) {
                            nit = "CF";
                            nameClient = "C/F";
                        } else {
                            System.out.println("INGRESE EL NOMBRE DEL CLIENTE");
                            nameClient = sc.nextLine();
                        }
                    }

                    AddProductClient();
                    do {
                        System.out.println("¿DESEAS AGREGAR OTRO PRODUCTO? (si/no)");
                        resp = sc.nextLine();

                        switch (resp.toLowerCase()) {
                            case "si":
                                AddProductClient();
                                flagR = false;
                                break;
                            case "no":
                                flagR = true;
                                flagV = false;

                                System.out.println("SUBTOTAL: Q."+subTotal);
                                ApplyDiscount();
                                System.out.println("\n======= GENERANDO FACTURA =======");
                                Bill(nit, nameClient);
                                break;
                            default:
                                flagR = false;
                                System.out.println("OPCIÓN NO VALIDA\n");
                        }

                    } while(flagR == false);
                    break;
                case "2":
                    flagV = true;
                    flag = false;
                    break;
                default:
                    flagV = false;
                    System.out.println("OPCIÓN NO VALIDA\n");
            }

        } while( flagV == false);

    }

    public static void AddProductClient(){
        String numberProduct;
        String amount;
        boolean flagN;
        boolean result = true;

        do {
            System.out.println("======= PRODUCTOS DISPONIBLES =======");
            flagN = true;
            ShowProducts();
            System.out.println("INGRESA EL NUMERO DEl PRODUCTO");
            numberProduct = sc.nextLine();
            result = true;
            if(ValidateNumberProduct(numberProduct) != true) {
                flagN = false;
            } else {
                System.out.println("INGRESA LA CANTIDAD QUE DESEAS");
                amount = sc.nextLine();
                try {
                    if(Integer.parseInt(amount) <= 0) {
                        System.out.println("CANTIDAD INGRESADA NO VALIDA\n");
                        flagN = false;
                        result = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("DEBES INGRESAR UN NUMERO\n");
                    flagN = false;
                    result = false;
                }

                if(result == true) {
                    ProductSale(numberProduct, amount);
                }
            }

        } while(flagN == false);
    }

    public static void ProductSale(String number, String amount) {
        int num = Integer.parseInt(number)-1;
        int amountP = Integer.parseInt(amount);

        for (int i = 0; i < products.length; i++) {
            if(products[i][0] != null) {

                if(i == num) {
                    productsClient[contV][0] = products[i][0];
                    productsClient[contV][1] = amount;
                    productsClient[contV][2] = Integer.toString(Integer.parseInt(products[i][1])*amountP);
                    products[i][2] = Integer.toString(Integer.parseInt(products[i][2]) + amountP);
                    subTotal = subTotal + Integer.parseInt(products[i][1])*amountP;
                }

            }
        }
        contV++;
    }

    public static void ShowProductsClient() {
        System.out.println("CANT.   PRODUCTO          TOTAL");
        for(int i = 0; i < productsClient.length; i++) {
            if( productsClient[i][0] != null ) {
                System.out.println(productsClient[i][1]+" "+productsClient[i][0]+"      "+productsClient[i][2]);
            }
        }
    }

    public static void ApplyDiscount() {
        String code = "";
        double percentage = 0;
        double discount = 0;
        boolean flagD = false;

        do {
            System.out.println("INGRESE CÓDIGO DE DESCUENTO");
            code = sc.nextLine();

            for (int i = 0; i < coupons.length; i++) {
                if( coupons[i][0] != null) {
                    if(coupons[i][0].equalsIgnoreCase(code)) {
                        flagD = true;
                        percentage = Double.parseDouble(coupons[i][1]);
                    }
                }
            }

            if(flagD == false && code.length() > 0) {
                System.out.println("CODIGO NO VALIDO");
            }

            if(code.length() == 0) {
                flagD = true;
            }

        } while(flagD == false);

        if(code != null) {
            discount = subTotal*(percentage/100);
            total = subTotal-discount;
        } else {
          total = subTotal;
        }

        disPer = percentage;

    }

    public static void Bill(String nit, String name) {
        System.out.println("======= SUPER-25 =======");
        System.out.println("CAJERO: JOSÉ DAVID GÓNGORA OLMEDO");
        System.out.println("CLIENTE: " +name);
        System.out.println("NIT: "+nit);
        System.out.println(LocalDateTime.now());
        ShowProductsClient();
        System.out.println("SUBTOTAL                "+subTotal);
        System.out.println("DESCUENTO               "+disPer+"%");
        System.out.println("TOTAL                   "+total);

    }
    public static boolean ValidateNumberProduct(String number) {
        int cont = 0;
        int numberP = 0;
        boolean result = true;

        try {
            numberP = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            result = false;
        }

        if(result == true ) {
            for (int i= 0; i < products.length; i++) {
                if(products[i][0] != null ) {
                    cont++;
                }
            }

            if(numberP <= 0 || numberP > cont) {
                System.out.println("NUMERO INGRESADO NO VALIDO\n");
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println("DEBES INGRESAR UN NUMERO\n");
            return false;
        }

    }

    public static void GenerarReportes() {
        System.out.println("Metodo generar reportes");

    }
}