package beadsan.api;

import beadsan.dto.DesignDto;
import beadsan.entity.MstUser;
import beadsan.entity.TrnDesign;
import beadsan.entity.TrnFavoriteDesign;
import beadsan.security.BeadsanUserDetails;
import beadsan.service.DesignService;
import beadsan.service.FavoriteDesignService;
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

@RestController
@RequestMapping("/api/favorite")
public class FavoriteDesignRestController {
	
	@Autowired
	FavoriteDesignService favoriteDesignService;

	@Autowired
	DesignService designService;

	@Autowired
	protected Mapper mapper;

	@RequestMapping(method = RequestMethod.GET)
	Page<DesignDto> getFavorites(@AuthenticationPrincipal BeadsanUserDetails userDetail,
								 @RequestParam("curPage") int curPage,
								 @RequestParam("itemsPerPage") int itemsPerPage) {

		Page<DesignDto> favorites = favoriteDesignService.findFavoriteDesignsByUserId(userDetail.getUserInfo().getUserId(),
				curPage, itemsPerPage);

		return favorites;
	}

	@RequestMapping(value = "{designId}", method = RequestMethod.POST)
	ResponseEntity<TrnFavoriteDesign> create(
			@AuthenticationPrincipal BeadsanUserDetails userDetail,
			@PathVariable("designId") Integer designId,
			UriComponentsBuilder uriBuilder) {

		TrnFavoriteDesign trnFavoriteDesign = new TrnFavoriteDesign();

		MstUser mstUser = new MstUser();
		mstUser.setMstUserId(userDetail.getUserInfo().getUserId());
		trnFavoriteDesign.setMstUserId(mstUser);

		DesignDto designDto = designService.find(designId);
		TrnDesign trnDesign = mapper.map(designDto, TrnDesign.class);
		trnDesign.getMstUserId().setMstUserId(userDetail.getUserInfo().getUserId());
		trnFavoriteDesign.setTrnDesignId(trnDesign);

		TrnFavoriteDesign created = favoriteDesignService.save(trnFavoriteDesign);
		URI location = uriBuilder.path("api/favorite/{id}")
				.buildAndExpand(created.getTrnFavoriteDesignId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{designId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@AuthenticationPrincipal BeadsanUserDetails userDetail,
					  @PathVariable("designId") Integer designId) {
		favoriteDesignService.delete(userDetail.getUserInfo().getUserId(), designId);
	}

}
