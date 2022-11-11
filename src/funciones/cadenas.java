package funciones;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import presentacion.hilo;

public class cadenas {
                                
    public String ObtSubject(String s) {
        int lon = s.length();
        int pos = s.indexOf("Subject: ");
        pos = pos + 8;      
        String acu = "";
        boolean sw = true;
        while (pos <= lon && sw) {
            if ((s.charAt(pos) != 'T') || (s.charAt(pos + 1) != 'o') || (s.charAt(pos + 2) != ':')) {
                acu = acu + s.charAt(pos);
                pos++;
            } else {
                sw = false;
                acu = acu.trim();
            }

        }
        if (acu.indexOf("=?UTF-8?B?") != -1) {
            acu = Decodificar(acu);
        }
        //System.out.print(acu);
        return acu; // LISTUSU["*"]
    }

    public String ObtSubject2(String s) {
        int lon = s.length();
        s = s.replaceAll("\n", "");
        int pos = s.indexOf("SUBJECT");
        pos = pos + 8;
        String acu = "";
        boolean sw = true;
        while (pos <= lon && sw) {
            if ((s.charAt(pos) != 'T') || (s.charAt(pos + 1) != 'o') || (s.charAt(pos + 2) != ':')) {
                acu = acu + s.charAt(pos);
                pos++;
            } else {
                sw = false;
                acu = acu.trim();
            }

        }
        System.out.print(">>>" + acu);
        acu = acu.replaceAll("\n", "");
        return acu;
    }
                            //"LISTUSU["*"]"
    public String ObtPatron(String s) {
        int ini = s.indexOf("[");//7
        int fin = s.indexOf("]");//11
        int i = 0;
        String acu = "";
        while (ini <= fin) {
            acu = acu + s.charAt(ini); // ["*"]
            ini++;
        }
        return acu.trim();// ["*"] 
    }

    public String ObtenerID(String patron) {
        String id = "";
        patron = patron.replace('"', ' ');
        patron = patron.replace('[', ' ');
        patron = patron.replace(']', ' ');
        patron = patron.trim();
        id = patron;
        System.out.print("id obtenido: " + patron);
        return id;
    }
                            // LISTUSU["*"]
    public String identOper(String cad) {
        int i = 0;
        String acu = "";
        boolean sw = true;
        while (i < cad.length() && sw) {
            if (cad.charAt(i) != '[') {
                acu = acu + cad.charAt(i);
                i++;
            } else {
                sw = false;
            }

        }
        return acu; // LISTUSU
    }
                // recibe todo el correo capturado
    public String ObtEmailDestino(String sub) {
        int lon = sub.length();
        int ini = sub.indexOf('<');
        int fin = sub.indexOf('>');
        ini = ini + 1;
        sub = sub.substring(ini, fin);
        return sub; // devuelve el correo destinatario
    }

    public String Decodificar(String s) {
        String[] parts = s.split("\n");
        String cad = "";
        int lon = parts.length;
        int i = 0;
        while (i < lon) {
            System.out.println("inicia: " + parts[i]);
            parts[i] = parts[i].trim();
            parts[i] = parts[i].replaceAll("\t", "");
            parts[i] = parts[i].replaceAll("=\\?UTF-8\\?B\\?", "");
            parts[i] = parts[i].replaceAll("\\?=", "");
            System.out.println("termina: " + parts[i]);
            byte[] decoded = Base64.getDecoder().decode(parts[i]);
            String acu = new String(decoded, StandardCharsets.UTF_8);
            cad = cad + acu;
            i++;
        }
        return cad;
    }
//    Calendar fecha_actual = new GregorianCalendar();
//        System.out.println(fecha_actual.get(Calendar.YEAR));
    
    public String getFechaHora(){
        String fecha = "";
        String hora = "";
        Calendar fecha_actual = new GregorianCalendar();
        fecha = fecha + fecha_actual.get(Calendar.YEAR);
        fecha += "-" + fecha_actual.get(Calendar.MONTH);
        fecha += "-" + fecha_actual.get(Calendar.DAY_OF_MONTH);
        hora = hora + fecha_actual.get(Calendar.HOUR_OF_DAY);
        hora += ":" + fecha_actual.get(Calendar.MINUTE);
        hora += ":" + fecha_actual.get(Calendar.SECOND);
        return fecha + " "  + hora;
    }
    
//    public static void main(String[] args) {
//        System.out.println(getFechaHora());
//    }
            
}
