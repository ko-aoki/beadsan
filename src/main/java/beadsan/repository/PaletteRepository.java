package beadsan.repository;

import beadsan.entity.MstPalette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ko-aoki on 2015/08/27.
 */
public interface PaletteRepository extends JpaRepository<MstPalette, Integer> {

    @Query("select m from MstPalette m where m.paletteCd =:paletteCd")
    MstPalette selectByPaletteCd(@Param("paletteCd") String paletteCd);
}
