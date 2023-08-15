package com.kolaykafe.userproducer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolaykafe.userproducer.dto.UserDTO;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class DTODeserializer implements Deserializer<UserDTO> {
    @Override
    public UserDTO deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (data == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");
            return mapper.readValue(new String(data, "UTF-8"), UserDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }
}
