package com.ilroberts.HL7Listener;

import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v26.datatype.XPN;
import ca.uhn.hl7v2.model.v26.message.ADT_A01;
import ca.uhn.hl7v2.model.v26.segment.MSH;
import com.ilroberts.HL7Listener.transformer.A01Transformer;
import org.hl7.fhir.dstu3.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("unused")
public class RespondACK {

    @Autowired
    A01Transformer a01Transformer;

    private final Logger log = LoggerFactory.getLogger(RespondACK.class);

    public Message process(Message in) throws Exception {
        log.info(in.printStructure());

        ADT_A01 adtMsg = (ADT_A01) in;
        MSH msh = adtMsg.getMSH();

        String messageType = msh.getMessageType().getMsg2_TriggerEvent().getValue();
        log.info(String.format("Message type = %s", messageType));

        switch (messageType) {
            case "A01":
                Patient patient = a01Transformer.transform(adtMsg);
                log.info("transformed patient name: " + patient.getName().get(0).getNameAsSingleString());
                break;
            default:
                log.info("no transformer for message type " + messageType);
        }

        Message out = in.generateACK();
        log.info(out.toString());
        return out;
    }
}

