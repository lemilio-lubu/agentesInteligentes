package agentes;

import contenedor.Mensaje;
import estudiante.Estudiante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.util.Logger;

import java.util.ArrayList;
import java.util.logging.Level;

public class Agente3 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    boolean terminar = false;
    ArrayList<String> al = new ArrayList<>();

    class Comportamiento extends Behaviour {

        @Override
        public void action() {
            ACLMessage msj = blockingReceive();

            if (msj.getConversationId().equals("COD-01-03")) { //verifica que agente envio el mensaje
                al.add(0, msj.getContent());
            } else {
                if (msj.getConversationId().equals("COD-0h-03")) {  //verifica que agente envio el mensaje
                    try {
                        Estudiante estudiante = (Estudiante) msj.getContentObject(); //obtiene el objeto pasado en agente hijo
                        al.add(1, estudiante.getNombre());
                    } catch (UnreadableException ex) {
                        Logger.getLogger(Agente3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (al.size() == 2) { //verifica si se recibio los dos mensajes
                    Mensaje.enviarMensaje(ACLMessage.REQUEST, "Agente1",
                            getAgent(), "COD-03-01", "Hola recibi " + al.get(0) + " , " + al.get(1),
                            null, true);
                    terminar = true;
                }

            }
        }

        @Override
        public boolean done() {
            return terminar;
        }

    }
    }

