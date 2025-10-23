package registerationlogin.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import registerationlogin.dto.QuotationDTO;

import registerationlogin.service.QuotationService;
import registerationlogin.service.UserService;

@Controller
public class QuotationController {

	private QuotationService service;
    private UserService userService;
    private String fileLocation;
	
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
public String uploadFile(Model model, MultipartFile file) throws IOException {
    InputStream in = file.getInputStream();
    File currDir = new File(".");
    String path = currDir.getAbsolutePath();
    fileLocation = path.substring(0, path.length() - 1) + file.getOriginalFilename();
    FileOutputStream f = new FileOutputStream(fileLocation);
    int ch = 0;
    while ((ch = in.read()) != -1) {
        f.write(ch);
    }
    f.flush();
    f.close();
    model.addAttribute("message", "File: " + file.getOriginalFilename() 
      + " has been uploaded successfully!");
    return "excel";
}    
}
