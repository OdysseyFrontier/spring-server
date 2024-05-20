package com.enjoyTrip.OdysseyFrontiers.attraction.controller;

import java.util.List;
import java.util.Map;


import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionDetail;
import com.enjoyTrip.OdysseyFrontiers.util.AttractionCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.AttractionInfo;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.service.AttractionService;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.BoardService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/attraction")
@RequiredArgsConstructor
public class AttractionRestController {

    private final AttractionService attractionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

//    public AttractionRestController(AttractionService attractionService) throws ServletException {
//        this.attractionService = attractionService;
//    }

    @GetMapping("/{contentId}")
    public ResponseEntity<?> getAttractions(long contentId) {
        AttractionInfo attraction = attractionService.getAttraction(contentId);
        return new ResponseEntity<>(attraction,HttpStatus.OK);
    }
    @GetMapping("/sido")
    public ResponseEntity<?> getSidos() {
        try {
            List<Sido> sidos = attractionService.listSidos();
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
            List<Gugun> guguns = attractionService.listGuguns(sidoCode);
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

    @GetMapping("/search/{contentTypeId}/{sidoCode}/{gugunCode}/{keyword}/{nowLocLng}/{nowLocLat}")
    public ResponseEntity<?> getAttraction(
            @PathVariable int contentTypeId,
            @PathVariable int sidoCode,
            @PathVariable int gugunCode,
            @PathVariable String keyword,
            @PathVariable String nowLocLat,
            @PathVariable String nowLocLng) {
        try {
            double lat = Double.parseDouble(nowLocLat);
            double lng = Double.parseDouble(nowLocLng);
            contentTypeId = AttractionCategory.fromCode(contentTypeId);
            List<AttractionInfo> attrs = attractionService.listAttr(contentTypeId, sidoCode, gugunCode, keyword, lat, lng);
            if (attrs != null && !attrs.isEmpty()) {
                String result = objectMapper.writeValueAsString(attrs);
                System.out.println(result);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/search/{contentTypeId}")
    public ResponseEntity<?> getAttraction(@PathVariable("contentTypeId") int contentTypeId){
    	System.out.println("들어옴1");
    	try {
        	System.out.println("들어옴");
            contentTypeId = AttractionCategory.fromCode(contentTypeId);
            System.out.println(contentTypeId);
            List<AttractionInfo> attrs = attractionService.listAttr(contentTypeId);
            System.out.println("11");
            System.out.println(attrs);
            if (attrs != null && !attrs.isEmpty()) {
                System.out.println("123");
                String result = objectMapper.writeValueAsString(attrs);
                System.out.println(result);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {
        try {
            List<Map<String, Object>> allCategoriesWithCodes = AttractionCategory.getAllCategoriesWithCodes();

            System.out.println(allCategoriesWithCodes);
            if (!allCategoriesWithCodes.isEmpty()) {
                String result = objectMapper.writeValueAsString(allCategoriesWithCodes);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
    }


}
