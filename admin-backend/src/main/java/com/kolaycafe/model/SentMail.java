package com.kolaycafe.model;

import com.mongodb.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Data
@Document("SentMails")
@AllArgsConstructor
public class SentMail {
    @Id
//    @NotNull
    private String id;
    @NotNull
    private String body;
    @NotNull
    private boolean status;
    @NotNull
    private String email;
    @Nullable
    private String error;
    @Nullable
    private Timestamp sentTime = Timestamp.from(ZonedDateTime.now().toInstant());
}
