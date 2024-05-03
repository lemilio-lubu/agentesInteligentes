package agentes;

import contenedor.Mensaje;
import estudiante.Estudiante;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class AgenteH extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }
    public class Comportamiento extends Behaviour {

        @Override
        public void action() {
            System.out.println("estoy aqui");
            String msd = "este es un mensaje de agente hijo a agente 3";
            Mensaje.enviarMensaje(ACLMessage.INFORM,"Agente3",
                    getAgent(),"COD-0h-03",null, new Estudiante("Elpepe","totito",12),false);

        }

        @Override
        public boolean done() {
            return true;
        }
    }
}
