package com.kolaykafe.userproducer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolaykafe.userproducer.dto.UserDTO;
import org.apache.commons.lang.SerializationException;
import org.apache.kafka.common.serialization.Serializer;


public class DTOSerializer implements Serializer<UserDTO> {
    @Override
    public byte[] serialize(String topic, UserDTO data) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            if (data == null){
                System.out.println("Null received at serializing");
                return null;
            }
            System.out.println("Serializing...");
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error when serializing MessageDto to byte[]");
        }
    }
}
