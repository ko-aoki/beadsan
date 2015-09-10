package beadsan.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ko-aoki on 2015/08/02.
 */
@Data
public class PageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private LoginDto loginDto;
    private HeaderDto headerDto;
}
