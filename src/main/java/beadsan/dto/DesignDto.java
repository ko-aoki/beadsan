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

//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getPaletteCd() {
//		return paletteCd;
//	}
//	public void setPaletteCd(String paletteCd) {
//		this.paletteCd = paletteCd;
//	}
//	public String getDesign() {
//		return design;
//	}
//	public void setDesign(String design) {
//		this.design = design;
//	}
//	public String getUpdateDate() {
//		return updateDate;
//	}
//	public void setUpdateDate(String updateDate) {
//		this.updateDate = updateDate;
//	}
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
