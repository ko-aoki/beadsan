package beadsan.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class DesignDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 図案ID
	 */
	private Integer designId;

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
	private String[][] design;

	/**
	 * ニックネーム
	 */
	private String authorNickname;

	/**
	 * お気に入りID(ログインユーザの)
	 */
	private Integer favoriteId;

	/**
	 * お気に入り数
	 */
	private Long favoriteCnt;

	/**
	 * お気に入り済み
	 */
	private Boolean favoriteOne;

	/**
	 * タグ
	 */
	private String[] tags;

}
