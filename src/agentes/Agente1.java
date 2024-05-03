package agentes;

import contenedor.Mensaje;
import estudiante.Estudiante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Agente1 extends Agent {
    //establecer comportamientos del agente
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    /*
    Los comportamientos son 3:
    simplebehaviour > creado para un unico proposito
    cyclebehaviour > creado para que este en escucha miesntras el agente este activo
    Behaviour > creado para analizar un comportamiento apartir de un bandera que se lo establece en done()
    */

    public class Comportamiento extends Behaviour {

        @Override
        public void action() {
            String msd = "este es un mensaje de agente 1 a agente 2";
            String msd2 = "este es el mensaje del agente 1 a agente 3";
            //envio de mensajes inform: solo info request: que te de una info
            Mensaje.enviarMensaje(ACLMessage.INFORM,"Agente2",
                    getAgent(),"COD-01-02",msd,null,true);
            Mensaje.enviarMensaje(ACLMessage.INFORM, "Agente3",
                    getAgent(), "COD-01-03  ",msd2,
                    null, true);

            ACLMessage acl = blockingReceive();
            System.out.println(acl.getContent());

        }

        @Override
        public boolean done() {
            return true;
        }
    }
}
