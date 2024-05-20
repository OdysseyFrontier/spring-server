package com.enjoyTrip.OdysseyFrontiers.hotplace.controller;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.Gugun2;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceHitDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.Sido2;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.service.HotPlaceService;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/hotplace")
@RequiredArgsConstructor
public class HotPlaceController {
	private final HotPlaceService hotPlaceService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JWTUtil jwtUtil;
    
    @GetMapping("/sido")
    public ResponseEntity<?> getSidos() {
        try {
            List<Sido2> sidos = hotPlaceService.listSidos();
            System.out.println(sidos);
            if (sidos != null && !sidos.isEmpty()) {
                String result = objectMapper.writeValueAsString(sidos);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/gugun/{sidoCode}")
    public ResponseEntity<?> getGuguns(@PathVariable("sidoCode") int sidoCode) {
        try {
            List<Gugun2> guguns = hotPlaceService.listGuguns(sidoCode);
            if (guguns != null && !guguns.isEmpty()) {
                String result = objectMapper.writeValueAsString(guguns);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    @GetMapping("/list")
    public ResponseEntity<?> listBoard(@RequestParam Map<String, Object> map) throws Exception {
        // 핫플레이스의 리스트를 json으로 보내주는 코드
    	log.info("listArticle map - {}", map);
		try {
			List<HotPlaceDto> list = hotPlaceService.selectAttr(map);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			return ResponseEntity.ok().headers(header).body(list);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
//        return new ResponseEntity<>(boardService.listBoard(map), HttpStatus.OK);
    }
    
    @GetMapping("/{contentId}")
    public ResponseEntity<?> view(@PathVariable("contentId") int contentId,
                                  @RequestHeader("Authorization") String authorization) throws Exception {

        // 현재는 회원만 board 에 조회 수 증가 가능.
            Long memberId = jwtUtil.getUserId(authorization);       
            HotPlaceHitDto hotplaceHitDto = new HotPlaceHitDto(contentId, memberId);
            hotPlaceService.createOrUpdateHit(hotplaceHitDto);

            Map<String, Object> map = new HashMap<>();
            map.put("contentId", contentId);
            map.put("memberId", memberId);
            HotPlaceDto hotPlaceDto = hotPlaceService.getHotPlace(map);

        log.info("{}", hotPlaceDto);
        return new ResponseEntity<>(hotPlaceDto, HttpStatus.OK);
    }
    
    @PostMapping("/like")
	  public ResponseEntity<?> creatLike(@RequestBody Map<String, String> map) throws Exception {
    	System.out.println(map); 
    	hotPlaceService.createLike(map);
	
	      return new ResponseEntity<>(HttpStatus.CREATED);
	  }
    
    @DeleteMapping("/like")
    public ResponseEntity<?> delete(@RequestBody Map<String, String> map) throws Exception {
    	System.out.println("취소 누름");
    	System.out.println(map); 
        hotPlaceService.deleteLike(map);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
    }
}
