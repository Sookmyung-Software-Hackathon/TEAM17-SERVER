package noeasy.server.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VoteDto {

    @NotNull
    private String title;

    @JsonProperty("vote_content")
    private List<String> voteContent;

    public static class HomeDto{
        @NotNull
        private String title;

        private String expired;
        private List<VoteItemDto>


    }
}
