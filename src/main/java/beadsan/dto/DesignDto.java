package beadsan.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 図案のDTOクラス
 * @author ko-aoki
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesignDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 図案名称
	 */
	private String name;

	/**
	 * パレット種類
	 */
	private String paletteCd;

	/**
	 * 図案データ
	 */
	private String design;
	/**
	 * 更新日
	 */
	private String updateDate;

}
