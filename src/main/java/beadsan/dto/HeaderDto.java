package beadsan.dto;

import lombok.Data;

/**
 * Created by ko-aoki on 2015/08/02.
 */
@Data
public class HeaderDto {

    private String nickname;
    private Boolean auth;

    public Boolean isAuth() {
        return this.auth;
    }

}
