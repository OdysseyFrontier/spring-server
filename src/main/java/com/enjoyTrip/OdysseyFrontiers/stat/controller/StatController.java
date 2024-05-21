package com.enjoyTrip.OdysseyFrontiers.stat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enjoyTrip.OdysseyFrontiers.stat.model.dto.StatDto;
import com.enjoyTrip.OdysseyFrontiers.stat.model.service.StatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stat")
@RequiredArgsConstructor
public class StatController {
	private final StatService statService;

//    @GetMapping("/sido")
//    public ResponseEntity<?> getSidos() {
//        try {
//            List<Sido> sidos = attractionService.listSidos();
//            System.out.println(sidos);
//            if (sidos != null && !sidos.isEmpty()) {
//                String result = objectMapper.writeValueAsString(sidos);
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//        	return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

	@GetMapping("/popular")
    public ResponseEntity<?> getPopularAttractions() {
    	 try {
    		 List<StatDto> list = statService.getPopularAttractions();
           System.out.println(list);
           if (list != null && !list.isEmpty()) {
               return new ResponseEntity<>(list, HttpStatus.OK);
           } else {
               return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
           }
       } catch (Exception e) {
       	return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
	
	@GetMapping("/registration-stats")
    public ResponseEntity<?> getMemberRegistrationStats() {
		try {
   		 List<StatDto> list = statService.getMemberRegistrationStats();
          System.out.println(list);
          if (list != null && !list.isEmpty()) {
              return new ResponseEntity<>(list, HttpStatus.OK);
          } else {
              return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
          }
      } catch (Exception e) {
      	return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
	
	@GetMapping("/region-count")
    public ResponseEntity<?> getAttractionCountByRegion() {
		try {
	   		 List<StatDto> list = statService.getAttractionCountByRegion();
	          System.out.println(list);
	          if (list != null && !list.isEmpty()) {
	              return new ResponseEntity<>(list, HttpStatus.OK);
	          } else {
	              return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	          }
	      } catch (Exception e) {
	      	return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	      }
    }
	
	@GetMapping("/time")
    public ResponseEntity<?> getActivityByTime() {
		try {
	   		 List<StatDto> list = statService.getActivityByTime();
	          System.out.println(list);
	          if (list != null && !list.isEmpty()) {
	              return new ResponseEntity<>(list, HttpStatus.OK);
	          } else {
	              return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	          }
	      } catch (Exception e) {
	      	return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	      }
    }
	
}
