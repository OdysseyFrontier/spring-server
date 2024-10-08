package com.enjoyTrip.OdysseyFrontiers.hotplace.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceHitDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.service.HotPlaceService;
import com.enjoyTrip.OdysseyFrontiers.util.jwt.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    
    @Value("${file.path.upload-hotplace}")
    private String uploadHotPlacePath;
    
    
    @GetMapping("/sido")
    public ResponseEntity<?> getSidos() {
        try {
            List<Sido> sidos = hotPlaceService.listSidos();
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
            List<Gugun> guguns = hotPlaceService.listGuguns(sidoCode);
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
    
    @GetMapping("/image/{filename}")
    public ResponseEntity<?> getImage(@PathVariable("filename") String filename) {
        try {
            // Define the path to the image
            File imageFile = new File(uploadHotPlacePath + filename);

            // Check if the file exists
            if (!imageFile.exists()) {
                return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
            }

            // Read the file content into a byte array
            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());

            // Determine the file's MIME type
            String mimeType = Files.probeContentType(imageFile.toPath());

            // Create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(mimeType));

            // Return the image as a ResponseEntity
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }
    
    
    @PostMapping(value = "/write")
	public ResponseEntity<?> writeHotPlace( @RequestPart("sidocode") String sidocode,
			@RequestPart("hotplaceDto") String hotplaceDtoString, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
        
    	System.out.println(sidocode);
    	int sidoNum = 1;
    	try {
			sidoNum = hotPlaceService.getSidoCode(sidocode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	HotPlaceDto hotPlaceDto = objectMapper.readValue(hotplaceDtoString, HotPlaceDto.class);
        hotPlaceDto.setSidoCode(sidoNum);
    	
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        hotPlaceDto.setCreatedTime(today);
        
        System.out.println(hotPlaceDto);
    	log.info("writeHotPlace hotPlaceDto - {}", hotPlaceDto);
		try {
			int contendId = hotPlaceService.writeHotPlace(hotPlaceDto);
			

			
			File folder = new File(uploadHotPlacePath);
			if (!folder.exists())
				folder.mkdirs();
			if (!folder.getParentFile().exists())
				folder.getParentFile().mkdirs();

			
			
//			byte[] bytes = file.getBytes();
//			Files.write(uploadHotPlacePath, bytes);
			String fileName = contendId + "_" + file.getOriginalFilename();
			file.transferTo(new File(uploadHotPlacePath , ""+fileName));
			
			System.out.println(uploadHotPlacePath + fileName);
			hotPlaceDto.setFirstImage("http://localhost/hotplace/image/" + fileName );
			hotPlaceService.setFilePath(hotPlaceDto);
			return new ResponseEntity<>(contendId, HttpStatus.CREATED);
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
    
 // 수정할 글 얻기
    @GetMapping("/modify/{contendId}")
	public ResponseEntity<HotPlaceDto> getModifyHotPlace(
			@PathVariable("contendId") int contendId)
			throws Exception {
		log.info("getModifyHotPlace - 호출 : " + contendId);
		return new ResponseEntity<HotPlaceDto>(hotPlaceService.getModifyHotPlace(contendId), HttpStatus.OK);
	}
    
    // 글 수정
    @PutMapping("/modify")
  public ResponseEntity<?> modify(@RequestPart("sidocode") String sidocode,
			@RequestPart("hotplaceDto") String hotplaceDtoString, @RequestParam("file") MultipartFile file) throws JsonMappingException, JsonProcessingException{

    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	int sidoNum = 1;
    	try {
			sidoNum = hotPlaceService.getSidoCode(sidocode);
		} catch (Exception e) {
			e.printStackTrace();
		}


    	HotPlaceDto hotPlaceDto = objectMapper.readValue(hotplaceDtoString, HotPlaceDto.class);
        hotPlaceDto.setSidoCode(sidoNum);
        
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        hotPlaceDto.setModifiedTime(today);
        
        System.out.println(hotPlaceDto);
    	log.info("modifyHotPlace hotPlaceDto - {}", hotPlaceDto);
		try {

			String fileName = hotPlaceDto.getContentId() + "_" + file.getOriginalFilename();
			file.transferTo(new File(uploadHotPlacePath , ""+fileName));
			
			System.out.println(uploadHotPlacePath + fileName);
			hotPlaceDto.setFirstImage("http://localhost/hotplace/image/" + fileName );

			int result = hotPlaceService.modifyHotPlcae(hotPlaceDto);
			if (result == 0) {
		          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
  }
    
    
    
    @DeleteMapping("/{contendId}")
    public ResponseEntity<?> delete(@PathVariable("contendId") int contendId) throws Exception {

        hotPlaceService.deleteHotPlace(contendId);


        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
    }
}
