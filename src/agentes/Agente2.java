package agentes;

import contenedor.Contenedor;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

public class Agente2 extends Agent {
    @Override
    protected void setup() {
        addBehaviour(new Comportamiento());
    }

    @Override
    protected void takeDown() {
        //creamos un contenedor con el conocimiento
        //System.out.println("Muerte de agente 2");
        Contenedor c = (Contenedor) getArguments()[0];
        //cramos el agente hijo
        c.crearHijoNuevo("AgenteH",new Object[]{c});
        System.out.println("Se creo el hijo de Agente 2");
    }

    public class Comportamiento extends Behaviour{

        @Override
        public void action() {
            ACLMessage acl = blockingReceive();
            System.out.println(acl.getContent());
            //matamos al agente padre
            doDelete();
        }

        @Override
        public boolean done() {
            return true;
        }
    }
}
