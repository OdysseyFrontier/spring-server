package com.enjoyTrip.OdysseyFrontiers.board.controller;


import java.util.List;
import java.util.Map;

import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.dto.BoardHitDto;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.BoardService;
import com.enjoyTrip.OdysseyFrontiers.board.model.service.CommentService;
import com.enjoyTrip.OdysseyFrontiers.member.model.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpSession;

import static com.enjoyTrip.OdysseyFrontiers.util.constant.SessionConst.SESSION_MEMBER_INFO;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final Logger logger = LoggerFactory.getLogger(BoardController.class);
//	private final String UPLOAD_PATH = "/upload";

    @Value("${file.path}")
    private String uploadPath;

    @Value("${file.path.upload-images}")
    private String uploadImagePath;

    @Value("${file.path.upload-files}")
    private String uploadFilePath;

    private BoardService boardService;
    private CommentService commentService;

    public BoardController(BoardService boardService, CommentService commentService) {
        this.boardService = boardService;
        this.commentService = commentService;
    }

    @GetMapping("/write")
    public String write(@RequestParam Map<String, String> map, Model model, HttpSession session) {
        logger.debug("write call parameter {}", map);
        model.addAttribute("member", session.getAttribute(SESSION_MEMBER_INFO));
        model.addAttribute("pgno", map.get("pgno"));
        model.addAttribute("key", map.get("key"));
        model.addAttribute("word", map.get("word"));
        return "/board/write";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, @RequestParam(value = "upfile", required = false) MultipartFile[] files,
                        HttpSession session, RedirectAttributes redirectAttributes) throws Exception {

        logger.debug("write boardDto : {}", boardDto);
        MemberDto memberDto = (MemberDto) session.getAttribute(SESSION_MEMBER_INFO);
        boardDto.setMemberId(memberDto.getMemberId());

        System.out.println(boardDto);
//		FileUpload 관련 설정.
//		logger.debug("uploadPath : {}, uploadImagePath : {}, uploadFilePath : {}", uploadPath, uploadImagePath, uploadFilePath);
//		logger.debug("MultipartFile.isEmpty : {}", files[0].isEmpty());
//		if (!files[0].isEmpty()) {
////			String realPath = servletContext.getRealPath(UPLOAD_PATH);
////			String realPath = servletContext.getRealPath("/resources/img");
//			String today = new SimpleDateFormat("yyMMdd").format(new Date());
//			String saveFolder = uploadPath + File.separator + today;
//			logger.debug("저장 폴더 : {}", saveFolder);
//			File folder = new File(saveFolder);
//			if (!folder.exists())
//				folder.mkdirs();
//			if (!folder.getParentFile().exists())
//				folder.getParentFile().mkdirs();
//			logger.debug("폴더 경로 : {}", folder.getPath());
//
//			List<FileInfoDto> fileInfos = new ArrayList<FileInfoDto>();
//			for (MultipartFile mfile : files) {
//				FileInfoDto fileInfoDto = new FileInfoDto();
//				String originalFileName = mfile.getOriginalFilename();
//				if (!originalFileName.isEmpty()) {
//					String saveFileName = UUID.randomUUID().toString()
//							+ originalFileName.substring(originalFileName.lastIndexOf('.'));
//					fileInfoDto.setSaveFolder(today);
//					fileInfoDto.setOriginalFile(originalFileName);
//					fileInfoDto.setSaveFile(saveFileName);
//					logger.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", mfile.getOriginalFilename(), saveFileName);
//					mfile.transferTo(new File(folder, saveFileName));
//				}
//				fileInfos.add(fileInfoDto);
//			}
//			boardDto.setFileInfos(fileInfos);
//		}

        boardService.writeBoard(boardDto);

//		redirectAttributes.addAttribute("pgno", "1");
//		redirectAttributes.addAttribute("key", "");
//		redirectAttributes.addAttribute("word", "");

        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public String list(@RequestParam Map<String, String> map, Model model, HttpSession session) throws Exception {
        logger.debug("list parameter pgno : {}", map.get("pgno"));

//		테스트 설정
//		MemberDto tmp = new MemberDto();
//		tmp.setMemberId("ssafy1");
//		tmp.setStatus(MemberStatus.ACTIVE);
//		tmp.setMemberName("김싸피");
//		tmp.setMemberPassword("whtlgus");
//		session.setAttribute("memberInfo", tmp);

        List<BoardDto> list = boardService.listBoard(map);
        System.out.println(list);
//		PageNavigation pageNavigation = boardService.makePageNavigation(map);
        model.addAttribute("Boards", list);
//		model.addAttribute("navigation", pageNavigation);
        model.addAttribute("pgno", map.get("pgno"));
        model.addAttribute("key", map.get("key"));
        model.addAttribute("word", map.get("word"));
        model.addAttribute("orderBy", map.get("orderBy"));
        model.addAttribute("orderDir", map.get("orderDir"));

        logger.debug("key : {}, word : {}", map.get("key"), map.get("word"));
        logger.debug("orderBy : {}, orderDir : {}", map.get("orderBy"), map.get("orderDir"));
        logger.debug("page : {}", map.get("navigation"));
        return "/board/list";
    }

    @GetMapping("/view/{BoardNo}")
    public String view(@PathVariable int BoardNo, @RequestParam Map<String, String> map,
                       Model model, HttpSession session)
            throws Exception {
        logger.debug("view BoardNo : {}", BoardNo);

        // 현재는 회원만 board에 접근 가능.
        MemberDto memberDto = (MemberDto) session.getAttribute(SESSION_MEMBER_INFO);
        BoardHitDto boardHitDto = new BoardHitDto(BoardNo, memberDto.getMemberId());
        boardService.createOrUpdateHit(boardHitDto);

        BoardDto boardDto = boardService.getBoard(BoardNo);
        System.out.println(boardDto);


        model.addAttribute("Board", boardDto);
        model.addAttribute("pgno", map.get("pgno"));
        model.addAttribute("key", map.get("key"));
        model.addAttribute("word", map.get("word"));


        return "/board/view";
    }

    @GetMapping("/modify/{BoardNo}")
    public String modify(@PathVariable int BoardNo, @RequestParam Map<String, String> map, Model model)
            throws Exception {
        logger.debug("modify BoardNo : {}", BoardNo);
        BoardDto boardDto = boardService.getBoard(BoardNo);
        model.addAttribute("Board", boardDto);
        model.addAttribute("pgno", map.get("pgno"));
        model.addAttribute("key", map.get("key"));
        model.addAttribute("word", map.get("word"));
        return "/board/modify";
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, @RequestParam Map<String, String> map,
                         RedirectAttributes redirectAttributes) throws Exception {
        logger.debug("modify boardDto : {}", boardDto);
        boardService.modifyBoard(boardDto);
        redirectAttributes.addAttribute("pgno", map.get("pgno"));
        redirectAttributes.addAttribute("key", map.get("key"));
        redirectAttributes.addAttribute("word", map.get("word"));
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{BoardNo}")
    public String delete(@PathVariable int BoardNo, @RequestParam Map<String, String> map,
                         RedirectAttributes redirectAttributes) throws Exception {
        logger.debug("delete BoardNo : {}", BoardNo);
//		boardService.deleteBoard(BoardNo, servletContext.getRealPath(UPLOAD_PATH));
//		boardService.deleteBoard(BoardNo, uploadPath);
        boardService.deleteBoard(BoardNo);
        redirectAttributes.addAttribute("pgno", map.get("pgno"));
        redirectAttributes.addAttribute("key", map.get("key"));
        redirectAttributes.addAttribute("word", map.get("word"));
        return "redirect:/board/list";
    }

//	@GetMapping("/download")
//	public ModelAndView downloadFile(@RequestParam("sfolder") String sfolder, @RequestParam("ofile") String ofile,
//									 @RequestParam("sfile") String sfile) {
//		Map<String, Object> fileInfo = new HashMap<String, Object>();
//		fileInfo.put("sfolder", sfolder);
//		fileInfo.put("ofile", ofile);
//		fileInfo.put("sfile", sfile);
//		return new ModelAndView("fileDownLoadView", "downloadFile", fileInfo);
//	}

}
