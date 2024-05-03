package contenedor;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    clase creada con el proposito de configurar el protocolo de comunicacion de los agentes
 */
public class Mensaje {
    // metodo estatico para no depender de la instancia de un mensaje
    public static void enviarMensaje(int tipo, String receptor, Agent emisor,
                                     String codigoConversionId, String contenidoString,
                                     Serializable contenidoObject, boolean isContenentString){

        //creo el cuerpo del mensaje
        ACLMessage acl = new ACLMessage(tipo); // se crea el mensaje con un propotsito(tipo: INFORM, REQUEST...)
        AID id = new AID();
        id.setLocalName(receptor); //mira si esta en el local el receptor y lo agrega
        acl.addReceiver(id);//agregar el id receptor
        acl.setSender(emisor.getAID()); //agregar el id emisor
        acl.setLanguage(FIPANames.ContentLanguage.FIPA_SL); // establece el protocolo del FIPA
        acl.setConversationId(codigoConversionId); //codigo de comunicacion de los agentes > tipo wtsp
        establecerPorTipoDeContenido(contenidoString, contenidoObject, isContenentString, acl);

        emisor.send(acl);//el emisor envia el mensaje

    }

    private static void establecerPorTipoDeContenido(String contenidoString, Serializable contenidoObject, boolean isContenentString, ACLMessage acl) {
        if(isContenentString){
            acl.setContent(contenidoString);
        }else{
            try {
                acl.setContentObject(contenidoObject);
            } catch (IOException e) {
                Logger.getLogger(Mensaje.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }


}
