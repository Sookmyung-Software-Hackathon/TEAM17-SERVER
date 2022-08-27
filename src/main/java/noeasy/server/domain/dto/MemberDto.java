package noeasy.server.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.Team;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberDto {
    @NotNull
    private String email;

    private String pw;

    @NotNull
    private String nickname;

    @NotNull
    private String type;

    @NotNull
    @JsonProperty("birth_year")
    private Integer birthYear;

    private Team team;

    private List<String> roles = new ArrayList<>();

    @Getter
    public static class LoginDto{
        @NotNull
        private String email;

        @NotNull
        private String pw;
    }

    @Getter
    @Setter
    public static class SignUpDto{
        @NotNull
        private String email;

        @NotNull
        private String pw;

        @NotNull
        private String nickname;

        @NotNull
        private Integer birthYear;

        @NotNull
        @JsonProperty(value="team_id")
        private String teamId;
    }
}
