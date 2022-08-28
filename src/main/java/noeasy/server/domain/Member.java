package noeasy.server.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noeasy.server.domain.dto.MemberDto;
import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "noeasy.server.util.RandomGenerator")
    @Column
    private String id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String pw;

    @NotNull
    private String nickname;

    @NotNull
    private String type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="team_id")
    private Team team;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Participant> participantList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Voter> voterList;


    private Integer birthYear;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    public Member(MemberDto memberDto, Team team){
        this.email = memberDto.getEmail();
        this.pw = memberDto.getPw();
        this.nickname = memberDto.getNickname();
        this.type = memberDto.getType();
        this.team = team;
        this.birthYear = memberDto.getBirthYear();
        this.roles = memberDto.getRoles();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getPassword();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
