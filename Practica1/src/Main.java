import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String user = "cajero_202201444";
        String password = "ipc1_202201444";
        String userL = "";
        String passL = "";
        System.out.println("\n" +
                "   _____ _    _ _____  ______ _____      ___  _____ \n" +
                "  / ____| |  | |  __ \\|  ____|  __ \\    |__ \\| ____|\n" +
                " | (___ | |  | | |__) | |__  | |__) |_____ ) | |__  \n" +
                "  \\___ \\| |  | |  ___/|  __| |  _  /______/ /|___ \\ \n" +
                "  ____) | |__| | |    | |____| | \\ \\     / /_ ___) |\n" +
                " |_____/ \\____/|_|    |______|_|  \\_\\   |____|____/ \n" +
                "                                                    \n" +
                "                                                    \n");


        while (!userL.equals(user) || !passL.equals(password)) {
            System.out.println("======= POR FAVOR INGRESAR TUS CREDENCIALES =======");
            System.out.println("INGRESAR USUARIO:");
            userL = sc.nextLine();
            System.out.println("INGRESAR CONTRASEÑA:");
            passL = sc.nextLine();
            if(!userL.equals(user) && !passL.equals(password)) {
                System.out.println("DATOS INCORRECTOS");
            }
        } // LOGIN

    }
}