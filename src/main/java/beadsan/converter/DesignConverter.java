package beadsan.converter;

import beadsan.dto.DesignDto;
import beadsan.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dozer.CustomConverter;
import org.dozer.MappingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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
        dest.setDesignId(source.getTrnDesignId());
        dest.setName(sourceObj.getName());
        dest.setPaletteCd(sourceObj.getMstPaletteId().getPaletteCd());
        try {
            dest.setDesign(mapper.readValue(sourceObj.getDesign(), String[][].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collection<TrnTag> trnTags = sourceObj.getTrnTagCollection();
        String[] tags = new String[trnTags.size()];
        int cnt = 0;
        for(TrnTag trnTag : trnTags) {
            tags[cnt] = trnTag.getMstTagId().getName();
            cnt++;
        }
        dest.setTags(tags);

        return dest;
    }

    private Object designDtoToTrnDesign(Object destination, DesignDto source, ObjectMapper mapper) {

        TrnDesign dest = null;
        DesignDto sourceObj = source;
        // check to see if the object already exists
        if (destination == null) {
            dest = new TrnDesign();
        } else {
            dest = (TrnDesign) destination;
        }
        dest.setTrnDesignId(source.getDesignId());
        dest.setName(sourceObj.getName());
        try {
            dest.setDesign(mapper.writeValueAsString(sourceObj.getDesign()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        MstUser mstUser = new MstUser();
        dest.setMstUserId(mstUser);
        MstPalette mstPalette = new MstPalette();
        mstPalette.setPaletteCd(sourceObj.getPaletteCd());
        dest.setMstPaletteId(mstPalette);

        String[] tags = sourceObj.getTags();
        Collection<TrnTag> trnTags = new ArrayList<TrnTag>();
        for (String tag : tags) {
            TrnTag trnTag = new TrnTag();
            MstTag mstTag = new MstTag();
            mstTag.setName(tag);
            trnTag.setMstTagId(mstTag);
            trnTags.add(trnTag);
        }
        dest.setTrnTagCollection(trnTags);

        return dest;
    }
}