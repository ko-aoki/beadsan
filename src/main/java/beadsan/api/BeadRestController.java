package beadsan.api;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import beadsan.dto.DesignDto;
import beadsan.dto.PageDto;
import beadsan.entity.TrnDesign;
import beadsan.service.DesignService;

@RestController
@RequestMapping("/api/bead/")
public class BeadRestController {
	
	@Autowired
	DesignService designService;

	@RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
	PageDto<DesignDto> getLoginUserDesigns(@PathVariable Integer userId,
			@RequestParam("page") int curPage,
			@RequestParam("size") int pageSize) {
		PageDto<DesignDto> designs = designService.findDesignsByUserId(userId,
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
