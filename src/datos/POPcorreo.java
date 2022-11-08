package datos;

import java.io.*;

import java.net.*;
import funciones.*;

public class POPcorreo {

    public void leercorreo() {
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

                
                if (4 != res.indexOf("0")) {
                    comando = "RETR 1\n";
                    System.out.print("C : " + comando);
                    salida.writeBytes(comando);
                    e = getMultiline(entrada);
                    
                    comando = "DELE 1\n";
                    System.out.print("C : " + comando);
                    salida.writeBytes(comando);
                    System.out.println("S : " + e);
                    System.out.println(" : " + e.length());

                    if (e.length() > 650) { //LISTUSU["*"]    //Todo el correo
                        f.EjecOperacion(s.ObtSubject(e), e); // GMAIL
                    } else {
                        f.EjecOperacion(s.ObtSubject2(e), e);   //Hotmail
                    }

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
// Permite leer multiples lineas de respuesta del Protocolo POP

    static protected String getMultiline(BufferedReader in) throws IOException {
        String lines = "";
        while (true) {
            String line = in.readLine();
            if (line == null) {
                // Server closed connection
                throw new IOException(" S : Server unawares closed the connection.");
            }
            if (line.equals(".")) {
                // No more lines in the server response
                break;
            }
            if ((line.length() > 0) && (line.charAt(0) == '.')) {
                // The line starts with a "." - strip it off.
                line = line.substring(1);
            }
            // Add read line to the list of lines
            lines = lines + "\n" + line;
        }
        return lines;
    }
}
