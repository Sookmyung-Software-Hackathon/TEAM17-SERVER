package noeasy.server.domain;

import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "noeasy.server.util.RandomGenerator")
    @Column
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    private Boggle boggle;

    public Participant(Member member, Boggle boggle) {
        this.member = member;
        this.boggle = boggle;
    }
}
