package registerationlogin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;


import registerationlogin.dto.QuotationDTO;

import registerationlogin.service.QuotationService;
import registerationlogin.service.UserService;

@Controller
public class QuotationController {

	private QuotationService service;
    private UserService userService;
    private static final String UPLOAD_DIR = System.getProperty("java.io.tmpdir") + File.separator + "uploads";
	
	public QuotationController(QuotationService srvc, UserService us) {
		this.service = srvc;
        this.userService = us;
	}

    //handler methods for getting list of quotations.
    @GetMapping("/quotation")
    public String quotations(Model model){
        List<QuotationDTO> dtoList = service.findAllQuotations();
        model.addAttribute("quotations",dtoList);
        return "quotation";
    }	


@PostMapping("/uploadExcelFile")
public String uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
        model.addAttribute("message", "No file selected");
        return "redirect:/index?uploadError";
    }

    // Ensure upload directory exists
    Files.createDirectories(Path.of(UPLOAD_DIR));

    // Build a safe destination path
    String originalFilename = new File(file.getOriginalFilename()).getName();
    Path destination = Path.of(UPLOAD_DIR, originalFilename);

    // Copy stream efficiently and close resources automatically
    try (InputStream in = file.getInputStream()) {
        Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
    }

    model.addAttribute("message", "File: " + originalFilename + " uploaded successfully");
    return "redirect:/index?uploadSuccess";
}    
}
