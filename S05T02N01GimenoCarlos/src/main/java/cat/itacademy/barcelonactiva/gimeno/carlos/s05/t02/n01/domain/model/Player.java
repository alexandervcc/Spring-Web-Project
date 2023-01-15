package cat.itacademy.barcelonactiva.gimeno.carlos.s05.t02.n01.domain.model;

import java.util.Date;
import java.util.List;

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
public class Player {
    @Id
    private String id;
    private String nombre;
    private Date fechaRegistro;

    @DBRef
    @JsonIgnore
    private List<Games> listGames;
}
