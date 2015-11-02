package beadsan.api;

import beadsan.dto.DesignDto;
import beadsan.entity.TrnDesign;
import beadsan.security.BeadsanUserDetails;
import beadsan.service.DesignService;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bead")
public class DesignRestController {

    @Autowired
    DesignService designService;

    @Autowired
    protected Mapper mapper;

    Logger logger = LoggerFactory.getLogger(DesignRestController.class);

    @RequestMapping(method = RequestMethod.GET)
    Page<DesignDto> getDesigns(@AuthenticationPrincipal BeadsanUserDetails userDetail,
                               @RequestParam("curPage") int curPage,
                               @RequestParam("itemsPerPage") int itemsPerPage) {
        Page<DesignDto> designs = designService.findDesignsByUserId(userDetail.getUserInfo().getUserId(),
                curPage, itemsPerPage);

        return designs;
    }

    @RequestMapping(value = "designName/{designName}", method = RequestMethod.GET)
    Map<String, Boolean> hasDuplicated(@AuthenticationPrincipal BeadsanUserDetails userDetail,
                                       @PathVariable("designName") String designName) {

        TrnDesign trnDesign = designService.findDesignsByUserIdAndDesignName(
                userDetail.getUserInfo().getUserId(), designName);

        Map rtn = new HashMap<String, Boolean>();
        rtn.put("result", new Boolean(trnDesign != null));
        return rtn;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<TrnDesign> create(
            @AuthenticationPrincipal BeadsanUserDetails userDetail,
            @RequestBody DesignDto designDto,
            UriComponentsBuilder uriBuilder) {

        TrnDesign trnDesign = mapper.map(designDto, TrnDesign.class);
        trnDesign.getMstUserId().setMstUserId(userDetail.getUserInfo().getUserId());
        TrnDesign created = designService.save(trnDesign);
        URI location = uriBuilder.path("api/bead/designName/{designName}")
                .buildAndExpand(created.getName()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<TrnDesign> update(
            @AuthenticationPrincipal BeadsanUserDetails userDetail,
            @RequestBody DesignDto designDto,
            UriComponentsBuilder uriBuilder) {

        TrnDesign trnDesign = mapper.map(designDto, TrnDesign.class);
        trnDesign.getMstUserId().setMstUserId(userDetail.getUserInfo().getUserId());
        TrnDesign updated = designService.save(trnDesign);

        URI location = uriBuilder.path("api/bead/designName/{designName}")
                .buildAndExpand(updated.getName()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(null, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "designName/{designName}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDesign(@AuthenticationPrincipal BeadsanUserDetails userDetail,
                      @PathVariable("designName") String designName) {

        designService.deleteDesignByName(
                userDetail.getUserInfo().getUserId(), designName);
    }

    @RequestMapping(value = "find", method = RequestMethod.GET)
    ResponseEntity<Page<DesignDto>> findDesigns(@AuthenticationPrincipal BeadsanUserDetails userDetail,
                                                @RequestParam(value = "designName", required = false) String designName,
                                                @RequestParam(value = "tag", required = false) String tag,
                                                @RequestParam("curPage") int curPage,
                                                @RequestParam("itemsPerPage") int itemsPerPage) {
        logger.info("findDesigns start");

        if (designService.countDesignsByDesignNameAndTag(designName, tag) > 200) {
            // 検索結果が多すぎ
            return new ResponseEntity<>(null, null, HttpStatus.FORBIDDEN);
        }

        Page<DesignDto> designs = designService.findDesignsByDesignNameAndTag(
                userDetail.getUserInfo().getUserId(), designName, tag, curPage, itemsPerPage);

        logger.info("findDesigns end");
        return new ResponseEntity<>(designs, null, HttpStatus.OK);
    }


}
