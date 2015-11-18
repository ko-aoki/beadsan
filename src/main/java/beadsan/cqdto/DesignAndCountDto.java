package beadsan.cqdto;

import beadsan.entity.TrnDesign;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ko-aoki on 2015/11/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class DesignAndCountDto {

    /** 図案 */
    private TrnDesign trnDesign;

    /** 図案お気に入り件数 */
    private long favoriteCount;
}
