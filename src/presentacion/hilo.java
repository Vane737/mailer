package presentacion;

import datos.POPcorreo;

public class hilo extends Thread {

    int c = 1;
    POPcorreo cl = new POPcorreo();

    public void run() {

        while (true) {
            try {
                System.out.println("verificacion #" + c);
                cl.leercorreo();
                c++;
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                // TODO: handle exception
                System.out.println("error");
            }
        }
    }

}
