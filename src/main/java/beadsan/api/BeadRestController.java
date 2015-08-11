package beadsan.api;

import beadsan.dto.DesignDto;
import beadsan.dto.PaginationDto;
import beadsan.entity.TrnDesign;
import beadsan.security.BeadsanUserDetails;
import beadsan.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/bead")
public class BeadRestController {
	
	@Autowired
	DesignService designService;

	@RequestMapping(method = RequestMethod.GET)
	PaginationDto<DesignDto> getLoginUserDesigns(@AuthenticationPrincipal BeadsanUserDetails userDetail,
			@RequestParam("page") int curPage,
			@RequestParam("size") int pageSize) {
		PaginationDto<DesignDto> designs = designService.findDesignsByUserId(userDetail.getUserInfo().getUserId(),
				curPage, pageSize);

		return designs;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<TrnDesign> postDesign(@RequestBody TrnDesign design,
			UriComponentsBuilder uriBuilder) {
		TrnDesign created = designService.create(design);
		URI location = uriBuilder.path("api/customers/{id}")
				.buildAndExpand(created.getTrnDesignId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	TrnDesign putDesign(@PathVariable Integer id,
			@RequestBody TrnDesign design) {
		design.setTrnDesignId(id);
		return designService.update(design);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteDesing(@PathVariable Integer id) {
		designService.delete(id);
	}

}
