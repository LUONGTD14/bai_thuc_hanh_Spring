package com.linh.hotelbk.controller.admin;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;
import com.linh.hotelbk.service.IBookingFormService;
import com.linh.hotelbk.utils.PDFExporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Set;

@Controller
@RequestMapping(path = "/admin")
@AllArgsConstructor
public class InvoiceController {

    private final IBookingFormService bookingFormService;

    @GetMapping(path = "/invoice/{id}")
    public ModelAndView getInvoicePage(@PathVariable Long id){
        ModelAndView mv = new ModelAndView("admin/invoice");
        BookingFormEntity bookingForm = bookingFormService.findById(id);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mv.addObject("bookingForm", bookingForm);
        Set<UsedServiceEntity> usedServiceSet = bookingForm.getUsedServices();
        int totalServiceFee = usedServiceSet.stream().mapToInt(i -> i.getService().getPrice()*i.getQuantity()).sum();
        mv.addObject("totalServiceFee", totalServiceFee);
        mv.addObject("usedServiceSet", usedServiceSet);
        mv.addObject("totalToBePaid", totalServiceFee + bookingForm.getBookingDays() * bookingForm.getRoom().getPrice());
        mv.addObject("checkInAt", simpleDateFormat.format(bookingForm.getCheckInAt()));
        mv.addObject("checkOutAt", simpleDateFormat.format(bookingForm.getCheckOutAt()));
        mv.addObject("totalServiceFee", totalServiceFee);
        return mv;
    }

    @GetMapping(path = "/invoice/export/{id}")
    public void exportPDF(HttpServletResponse response, @PathVariable Long id) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=invoice.pdf";
        response.setHeader(headerKey, headerValue);
        // Get Booking form
        BookingFormEntity bookingForm = bookingFormService.findById(id);
        PDFExporter.export(response, bookingForm);
    }
}
