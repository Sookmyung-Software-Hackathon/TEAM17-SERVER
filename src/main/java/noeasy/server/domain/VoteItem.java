package noeasy.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteItem {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="vote_id")
    private Vote vote;

    @NotNull
    private String content;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "voteItem")
    private List<Voter> voterList = List.of();

    public VoteItem(Vote vote, String content){
        this.vote = vote;
        this.content = content;
    }
}
