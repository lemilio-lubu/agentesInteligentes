package contenedor;

import agentes.Agente1;
import agentes.Agente2;
import agentes.Agente3;
import agentes.AgenteH;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Contenedor {
    AgentController agenteControlador;
    AgentContainer contenedorPrincipal;

    public void iniciarContenedor() {

        jade.core.Runtime runtime = jade.core.Runtime.instance(); //entorno de ejecucion de los agentes
        Profile profile = new ProfileImpl(null,1099, null); //configuracion del entorno en el que se ejecutan los agentes
        contenedorPrincipal = runtime.createMainContainer(profile); //creacion del contenedor con las configuraciones del perfil
        agregarAgentes(); //metodo privado que se ejecutar despues de crear el contenedor

    }

    private void agregarAgentes() {
        try {
            //ojo sapo: debes siempre que haces un agente iniciarlo
            contenedorPrincipal.createNewAgent("Agente3", Agente3.class.getName(),null).start();
            contenedorPrincipal.createNewAgent("Agente2", Agente2.class.getName(),new Object[]{this}).start();
            contenedorPrincipal.createNewAgent("Agente1", Agente1.class.getName(),null).start();

        } catch (StaleProxyException e) {
            Logger.getLogger(Contenedor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void crearHijoNuevo(String alias, Object[] conocimiento) {
        try {
            contenedorPrincipal.createNewAgent(alias, AgenteH.class.getName(), conocimiento).start();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }
    }
}
