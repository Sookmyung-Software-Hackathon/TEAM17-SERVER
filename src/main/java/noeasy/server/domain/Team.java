package noeasy.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noeasy.server.domain.dto.TeamRequestDto;
import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Team extends Timestamped {

    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "com.noeasy.server.util.RandomGenerator")
    @Column
    private String id;

    @Column(length = 24)
    String name;

    @Column
    double temperature = 0.0;

    public Team(TeamRequestDto teamRequestDto) {
        this.name = teamRequestDto.getName();
    }
}
