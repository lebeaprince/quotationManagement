package registerationlogin.controller;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import registerationlogin.dto.QuotationDTO;

import registerationlogin.service.QuotationService;
import registerationlogin.service.UserService;
import registerationlogin.entity.Enums.QuoatationState;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

@Controller
public class QuotationController {

    private QuotationService service;
    private UserService userService;
	
	public QuotationController(QuotationService srvc, UserService us) {
		this.service = srvc;
        this.userService = us;
	}

    //handler methods for getting list of quotations.
    @GetMapping("/quotation")
    public String quotations(Model model){
        List<QuotationDTO> dtoList = service.findAllQuotations();
        model.addAttribute("quotations",dtoList);
        return "quotation/list";
    }	

    private boolean hasRole(Authentication authentication, String roleName) {
        if (authentication == null) return false;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    private String requiredRoleFor(QuotationDTO dto) {
        double total = dto.getTotal();
        if (total > 1000000) {
            return "ROLE_SUPER";
        }
        if (total >= 500000) {
            return "ROLE_ADMIN";
        }
        return null; // auto-approved tier
    }

    @GetMapping("/quotation/{id}/review")
    public String reviewQuotation(@PathVariable("id") Long id, Model model) {
        QuotationDTO dto = service.findDtoById(id);
        if (dto == null) {
            return "redirect:/index?error=Quotation%20not%20found";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String requiredRole = requiredRoleFor(dto);
        boolean canApprove;
        if (requiredRole == null) {
            canApprove = false; // nothing to approve for auto-approve tier
        } else {
            canApprove = hasRole(auth, requiredRole);
        }
        model.addAttribute("quotation", dto);
        model.addAttribute("requiredRole", requiredRole);
        model.addAttribute("canApprove", canApprove);
        return "quotation/approval";
    }

    @PostMapping("/quotation/{id}/approve")
    public String approveQuotation(@PathVariable("id") Long id, Model model) {
        QuotationDTO dto = service.findDtoById(id);
        if (dto == null) {
            return "redirect:/index?error=Quotation%20not%20found";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String requiredRole = requiredRoleFor(dto);
        if (requiredRole == null) {
            // Below threshold; already auto-approved at creation. No action.
            return "redirect:/index?info=No%20approval%20required";
        }
        if (!hasRole(auth, requiredRole)) {
            return "redirect:/quotation/" + id + "/review?error=Insufficient%20permissions";
        }
        service.updateQuotationState(id, QuoatationState.Approved);
        return "redirect:/index?approved=" + id;
    }

    @PostMapping("/quotation/{id}/decline")
    public String declineQuotation(@PathVariable("id") Long id, Model model) {
        QuotationDTO dto = service.findDtoById(id);
        if (dto == null) {
            return "redirect:/index?error=Quotation%20not%20found";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String requiredRole = requiredRoleFor(dto);
        if (requiredRole == null) {
            // Below threshold; should not be declined via approval page
            return "redirect:/index?info=No%20approval%20required";
        }
        if (!hasRole(auth, requiredRole)) {
            return "redirect:/quotation/" + id + "/review?error=Insufficient%20permissions";
        }
        service.updateQuotationState(id, QuoatationState.Declined);
        return "redirect:/index?declined=" + id;
    }


@PostMapping("/uploadExcelFile")
public String uploadFile(Model model, @RequestParam("file") MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
        return "redirect:/index?uploadError=No%20file%20selected";
    }

    int successCount = 0;
    int skipCount = 0;

    try (InputStream in = file.getInputStream(); Workbook workbook = WorkbookFactory.create(in)) {
        Sheet sheet = workbook.getNumberOfSheets() > 0 ? workbook.getSheetAt(0) : null;
        if (sheet == null) {
            return "redirect:/index?uploadError=No%20sheets%20found";
        }

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            return "redirect:/index?uploadError=Missing%20header%20row";
        }

        Map<String, Integer> headerIndex = new HashMap<>();
        for (int c = headerRow.getFirstCellNum(); c < headerRow.getLastCellNum(); c++) {
            Cell cell = headerRow.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell == null) continue;
            String key = getString(cell);
            if (key != null && !key.isBlank()) {
                headerIndex.put(key.trim().toLowerCase(), c);
            }
        }

        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null || isRowEmpty(row)) {
                continue;
            }

            try {
                QuotationDTO dto = new QuotationDTO();

                // Required fields
                Long customerId = getLong(row, headerIndex.get("customer_id"));
                  
                java.sql.Date validUntil = getSqlDate(row, headerIndex.get("valid_until"));
 
                Double total = getDouble(row, headerIndex.get("total"));


                if (customerId == null || validUntil == null || total == null) {
                    skipCount++;
                    continue;
                }

                dto.setCustomerId(customerId);
                dto.setValidUntil(validUntil);
                dto.setTotal(total);

                // Optional fields
                java.sql.Date dateCreated = getSqlDate(row, headerIndex.get("date_created"));

                if (dateCreated != null) dto.setDateCreated(dateCreated);

                Double discount = getDouble(row, headerIndex.get("discount"));
           
                if (discount != null) dto.setDiscount(discount);

                Double vat = getDouble(row, headerIndex.get("vat"));

                if (vat != null) dto.setVAT(vat);

                String currency = getString(row, headerIndex.get("currency"));
       
                if (currency != null) dto.setCurrency(currency);

                String createdBy = getString(row, headerIndex.get("created_by"));
        
                if (createdBy != null) dto.setCreatedBy(createdBy);

                QuoatationState state = QuoatationState.Pending;
                // Auto-approve only when total is strictly below 500,000
                if (total < 500000) {
                    state = QuoatationState.Approved;
                }

                if (state != null) dto.setState(state);

                service.saveQuotation(dto);
                System.out.println("saveQuotation successful::::::::: ");

                successCount++;
            } catch (Exception ex) {
                skipCount++;
            }
        }
    } catch (Exception e) {
        return "redirect:/index?uploadError=Invalid%20file";
    }

    return "redirect:/index?uploaded=" + successCount + "&skipped=" + skipCount;
}

private static boolean isRowEmpty(Row row) {
    if (row == null) return true;
    for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
        Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell != null && cell.getCellType() != CellType.BLANK) {
            String str = getString(cell);
            if (str != null && !str.isBlank()) return false;
            if (cell.getCellType() == CellType.NUMERIC) return false;
            if (cell.getCellType() == CellType.BOOLEAN) return false;
        }
    }
    return true;
}

private static String getString(Row row, Integer index) {
    if (index == null) return null;
    return getString(row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL));
}

private static String getString(Cell cell) {
    if (cell == null) return null;
    return switch (cell.getCellType()) {
        case STRING -> cell.getStringCellValue().trim();
        case NUMERIC -> {
            if (DateUtil.isCellDateFormatted(cell)) {
                LocalDate d = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                yield d.toString();
            }
            double val = cell.getNumericCellValue();
            if (val == Math.rint(val)) {
                yield String.valueOf((long) val);
            }
            yield String.valueOf(val);
        }
        case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
        case FORMULA -> {
            try {
                yield cell.getStringCellValue().trim();
            } catch (IllegalStateException ex) {
                yield String.valueOf(cell.getNumericCellValue());
            }
        }
        default -> null;
    };
}

private static Double getDouble(Row row, Integer index) {
    if (index == null) return null;
    Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
    if (cell == null) return null;
    return switch (cell.getCellType()) {
        case NUMERIC -> cell.getNumericCellValue();
        case STRING -> {
            String s = cell.getStringCellValue().trim();
            if (s.isEmpty()) yield null;
            try { yield Double.parseDouble(s); } catch (NumberFormatException e) { yield null; }
        }
        case FORMULA -> {
            try { yield cell.getNumericCellValue(); } catch (IllegalStateException e) { yield null; }
        }
        default -> null;
    };
}

private static Long getLong(Row row, Integer index) {
    if (index == null) return null;
    Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
    if (cell == null) return null;
    return switch (cell.getCellType()) {
        case NUMERIC -> (long) Math.rint(cell.getNumericCellValue());
        case STRING -> {
            String s = cell.getStringCellValue().trim();
            if (s.isEmpty()) yield null;
            try { yield Long.parseLong(s); } catch (NumberFormatException e) { yield null; }
        }
        case FORMULA -> {
            try { yield (long) Math.rint(cell.getNumericCellValue()); } catch (IllegalStateException e) { yield null; }
        }
        default -> null;
    };
}

private static java.sql.Date getSqlDate(Row row, Integer index) {
    if (index == null) return null;
    Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
    if (cell == null) return null;
    if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
        return new java.sql.Date(cell.getDateCellValue().getTime());
    }
    String s = getString(cell);
    if (s == null || s.isBlank()) return null;
    for (String pattern : new String[] { "yyyy-MM-dd", "dd/MM/yyyy", "MM/dd/yyyy" }) {
        try {
            LocalDate ld = LocalDate.parse(s, DateTimeFormatter.ofPattern(pattern));
            return java.sql.Date.valueOf(ld);
        } catch (DateTimeParseException ignored) {}
    }
    try {
        LocalDate ld = LocalDate.parse(s);
        return java.sql.Date.valueOf(ld);
    } catch (DateTimeParseException ignored) {}
    return null;
}

private static QuoatationState getState(Row row, Integer index) {
    String s = getString(row, index);
    if (s == null) return null;
    for (QuoatationState v : QuoatationState.values()) {
        if (v.name().equalsIgnoreCase(s.trim())) return v;
    }
    return null;
}
}
