package noeasy.server.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 계정이 존재하지 않습니다."),
    MEMBER_DUPLICATED(HttpStatus.CONFLICT, "이미 해당 계정의 유저가 존재합니다."),

    VOTE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 투표가 존재하지 않습니다."),

    BOGGLE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 보글이 존재하지 않습니다."),

    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰이 존재하지 않습니다."),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "액세스 토큰이 만료되었습니다."),
    TOKEN_INVALID(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다."),
    TOKEN_NOT_EXPIRED(HttpStatus.BAD_REQUEST, "액세스 토큰이 만료되지 않았습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
