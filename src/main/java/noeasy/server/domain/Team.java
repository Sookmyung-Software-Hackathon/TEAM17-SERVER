package noeasy.server.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import noeasy.server.domain.dto.TeamRequestDto;
import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Team extends Timestamped {

    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "noeasy.server.util.RandomGenerator")
    @Column
    private String id;

    @Column(length = 24)
    String name;

    @Column
    double temperature = 0.0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    List<Member> memberList;

    public Team(TeamRequestDto teamRequestDto) {
        this.name = teamRequestDto.getName();
    }
}
