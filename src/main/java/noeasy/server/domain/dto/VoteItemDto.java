package noeasy.server.domain.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.Vote;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class VoteItemDto {

    @NotNull
    private Long id;

    @NotNull
    private Vote vote;

    public static class Vote

}
