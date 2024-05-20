package com.enjoyTrip.OdysseyFrontiers.hotplace.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Gugun;
import com.enjoyTrip.OdysseyFrontiers.attraction.model.dto.Sido;
import org.springframework.stereotype.Service;

import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.Gugun2;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.HotPlaceHitDto;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.dto.Sido2;
import com.enjoyTrip.OdysseyFrontiers.hotplace.model.mapper.HotPlaceMapper;

@Service
public class HotPlaceServiceImpl implements HotPlaceService {
	private final HotPlaceMapper hotPlaceMapper;

    private HotPlaceServiceImpl(HotPlaceMapper hotPlaceMapper) {
        this.hotPlaceMapper = hotPlaceMapper;
    }
	
	@Override
	public List<Sido> listSidos() throws Exception {
		return hotPlaceMapper.listSidos();
	}

	@Override
	public List<Gugun> listGuguns(int sidoCode) throws Exception {
		return hotPlaceMapper.listGuguns(sidoCode);
	}

	@Override
	public int writeHotPlace(HotPlaceDto hotPlaceDto) throws Exception {
		int contendId =  hotPlaceMapper.writeHotPlaceInfo(hotPlaceDto);
		
		hotPlaceDto.setContentId(contendId);
		hotPlaceMapper.writeHotPlaceDetail(hotPlaceDto);
		hotPlaceMapper.writeHotPlaceDescription(hotPlaceDto);
		
		hotPlaceMapper.writeHotPlaceAttachments(hotPlaceDto);
		
		return contendId;
	}

	@Override
	public List<HotPlaceDto> selectAttr(Map<String, Object> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		param.put("sidoCode", map.get("sidoCode") == null ? "0" : map.get("sidoCode"));
		param.put("gugunCode", map.get("gugunCode") == null ? "0" : map.get("gugunCode"));
		param.put("contentTypeId", map.get("contentTypeId") == null ? "0" : map.get("contentTypeId"));
		
		
		
		List<HotPlaceDto> list = hotPlaceMapper.selectAttr(map);
        return list;
	}
	
	@Override
	public HotPlaceDto getHotPlace(Map<String, Object> map) throws Exception {
		return hotPlaceMapper.getHotPlace(map);
	}

	@Override
	public int createOrUpdateHit(HotPlaceHitDto hotplaceHitDto) throws Exception {
		int contentId = hotplaceHitDto.getContentId();
        Long memberId = hotplaceHitDto.getMemberId();
		
		int recentMemberCount = hotPlaceMapper.getRecentMemberHit(contentId, memberId);

	System.out.println(recentMemberCount);
        if (recentMemberCount == 0) {
            return hotPlaceMapper.createHit(contentId, memberId);
        } else {
            return hotPlaceMapper.updateHit(contentId, memberId);
        }
	}

	@Override
	public int modifyHotPlcae(HotPlaceDto hotPlaceDto) throws Exception {
//		HotPlaceDto preHotPlaceDto = hotPlaceMapper.getHotPlace(hotPlaceDto.getContentId());
//		
//		if(!hotPlaceDto.getHomepage().equals(preHotPlaceDto.getHomepage()) || !hotPlaceDto.getOverview().equals(preHotPlaceDto.getOverview())) {
//			hotPlaceMapper.modifyHotPlcaeDescription(hotPlaceDto);
//		}
		hotPlaceMapper.modifyHotPlcaeDescription(hotPlaceDto);
		hotPlaceMapper.modifyHotPlcaeInfo(hotPlaceDto);
		return hotPlaceMapper.modifyHotPlcaeDetail(hotPlaceDto);
	}

	@Override
	public void createLike(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		hotPlaceMapper.createLike(map);
	}

	@Override
	public void deleteLike(Map<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		hotPlaceMapper.deleteLike(map);
	}

	@Override
	public void deleteHotPlace(int contentId) throws Exception {
		hotPlaceMapper.deleteHotPlaceHit(contentId);
		hotPlaceMapper.deleteHotPlaceLike(contentId);
		
		hotPlaceMapper.deleteHotPlaceAttachments(contentId);
		hotPlaceMapper.deleteHotPlaceDescription(contentId);
		hotPlaceMapper.deleteHotPlaceDetail(contentId);
		hotPlaceMapper.deleteHotPlaceInfo(contentId);
		
	}

}
