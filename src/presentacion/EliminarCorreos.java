package presentacion;

import funciones.cadenas;
import funciones.funciones;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class EliminarCorreos {
    public static void leercorreoaEliminar() {
        //se establecen las cadenas con informacion del servidor,usuario receptor y emisor del email y puerto de conexion
        String servidor = "mail.tecnoweb.org.bo";
        //String servidor="172.20.172.254";
        String usuario = "grupo09sa";
        String contraseña = "grup009grup009";
        String comando = "";
        String linea = "";
        int puerto = 110;
        cadenas s = new cadenas();
        funciones f = new funciones();

        try {
            //se establece conexion abriendo un socket especificando el servidor y puerto SMTP
            Socket socket = new Socket(servidor, puerto);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
            // Escribimos datos en el canal de salida establecido con el puerto del protocolo SMTP del servidor
            if (socket != null && entrada != null && salida != null) {
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "USER " + usuario + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "PASS " + contraseña + "\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

                comando = "STAT \r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                String res = entrada.readLine();
                System.out.println("S : " + res);
                String e;
                int u=res.indexOf("K");
                int c=1;
                int nro=Integer.parseInt(res.charAt(u+2)+"");
                while(c<=nro){
                    System.out.println("vuelta"+c);
                    comando = "DELE "+c+"\n";
                    System.out.print("C : " + comando);
                    salida.writeBytes(comando);    
                    c++;
                }
                

                comando = "QUIT\r\n";
                System.out.print("C : " + comando);
                salida.writeBytes(comando);
                System.out.println("S : " + entrada.readLine() + "\r\n");

            }
            // Cerramos los flujos de salida y de entrada y el socket cliente
            salida.close();
            entrada.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println(" S : no se pudo conectar con el servidor indicado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stu{}:_[]{][
        leercorreoaEliminar();
    }

}
