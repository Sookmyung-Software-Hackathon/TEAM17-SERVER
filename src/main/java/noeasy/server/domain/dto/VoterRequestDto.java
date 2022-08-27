package noeasy.server.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VoterRequestDto {
    private Long voteId;
    private Long voteItemId;
}
