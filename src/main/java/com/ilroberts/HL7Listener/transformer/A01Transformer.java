package com.ilroberts.HL7Listener.transformer;

import ca.uhn.hl7v2.model.v26.datatype.XPN;
import ca.uhn.hl7v2.model.v26.message.ADT_A01;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.hl7.fhir.dstu3.model.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class A01Transformer {
    private final Logger log = LoggerFactory.getLogger(A01Transformer.class);

    public Patient transform(ADT_A01 message) {
        log.info("transforming A01 message");

        Patient patient = new Patient();

        XPN xpn[] = message.getPID().getPatientName();
        String firstName = xpn[0].getGivenName().getValue();
        String surname = xpn[0].getFamilyName().getSurname().getValue();
        log.info(String.format("First name: %s Surname: %s", firstName, surname));

        HumanName name = new HumanName();
        name.setFamily(surname);
        StringType stringType = new StringType(firstName);
        name.setGiven(Collections.singletonList(stringType));
        patient.setName(Collections.singletonList(name));

        return patient;
    }
}
