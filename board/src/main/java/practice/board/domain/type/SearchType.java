package practice.board.domain.type;

import lombok.Getter;

public enum SearchType {
    TITLE("제목"),
    CONTENT("내용"),
    HASHTAG("해시태그"),
    NICKNAME("닉네임"),
    ID("유저 ID");

    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
