package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Games {
    @Id
    private String id;
    private Integer value;
    private Boolean result;

    @DBRef
    @JsonIgnore
    private Player player;
}
