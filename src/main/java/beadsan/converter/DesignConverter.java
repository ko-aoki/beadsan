package beadsan.converter;

import beadsan.dto.DesignDto;
import beadsan.entity.MstPalette;
import beadsan.entity.MstUser;
import beadsan.entity.TrnDesign;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

import java.io.IOException;

/**
 * Created by ko-aoki on 2015/08/28.
 */
public class DesignConverter  implements CustomConverter {

    public Object convert(Object destination, Object source,
                          Class destClass, Class sourceClass) {

        ObjectMapper mapper = new ObjectMapper();
        if (source == null) {
            return null;
        }
        if (source instanceof DesignDto) {
            return designDtoToTrnDesign(destination, (DesignDto) source, mapper);
        } else if (source instanceof TrnDesign) {
            return trnDesignToDesignDto(destination, (TrnDesign) source, mapper);
        } else {
            throw new MappingException("Converter DesignConverter "
                    + destination + " and " + source);
        }
    }

    private Object trnDesignToDesignDto(Object destination, TrnDesign source, ObjectMapper mapper) {
        DesignDto dest;
        TrnDesign sourceObj = source;
        if (destination == null) {
            dest = new DesignDto();
        } else {
            dest = (DesignDto) destination;
        }
        dest.setName(sourceObj.getName());
        dest.setPaletteCd(sourceObj.getMstPaletteId().getPaletteCd());
        try {
            dest.setDesign(mapper.readValue(sourceObj.getDesign(), String[][].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    private Object designDtoToTrnDesign(Object destination, DesignDto source, ObjectMapper mapper) {

        TrnDesign dest = null;
        DesignDto souceObj = source;
        // check to see if the object already exists
        if (destination == null) {
            dest = new TrnDesign();
        } else {
            dest = (TrnDesign) destination;
        }
        dest.setName(souceObj.getName());
        try {
            dest.setDesign(mapper.writeValueAsString(souceObj.getDesign()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MstUser mstUser = new MstUser();
        dest.setMstUserId(mstUser);
        MstPalette mstPalette = new MstPalette();
        mstPalette.setPaletteCd(souceObj.getPaletteCd());
        dest.setMstPaletteId(mstPalette);
        return dest;
    }
}