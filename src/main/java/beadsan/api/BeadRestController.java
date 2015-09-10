package beadsan.api;

import beadsan.dto.DesignDto;
import beadsan.entity.TrnDesign;
import beadsan.security.BeadsanUserDetails;
import beadsan.service.DesignService;
import org.dozer.Mapper;
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
public class BeadRestController {
	
	@Autowired
	DesignService designService;

	@Autowired
	protected Mapper mapper;

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
//	BooleanDto hasDuplicated(@AuthenticationPrincipal BeadsanUserDetails userDetail,
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
			UriComponentsBuilder uriBuilder
	) {

		TrnDesign trnDesign = mapper.map(designDto, TrnDesign.class);
		trnDesign.getMstUserId().setMstUserId(userDetail.getUserInfo().getUserId());
		TrnDesign created = designService.save(trnDesign);
		URI location = uriBuilder.path("api/bead/{id}")
				.buildAndExpand(created.getTrnDesignId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	ResponseEntity<TrnDesign> update(
			@AuthenticationPrincipal BeadsanUserDetails userDetail,
			@RequestBody DesignDto designDto
	) {

		TrnDesign trnDesign = mapper.map(designDto, TrnDesign.class);
		trnDesign.getMstUserId().setMstUserId(userDetail.getUserInfo().getUserId());
		TrnDesign created = designService.save(trnDesign);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(created, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteDesing(@PathVariable Integer id) {
		designService.delete(id);
	}

}
