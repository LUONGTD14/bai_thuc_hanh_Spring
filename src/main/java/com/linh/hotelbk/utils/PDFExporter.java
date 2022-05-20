package com.linh.hotelbk.utils;

import com.linh.hotelbk.entity.BookingFormEntity;
import com.linh.hotelbk.entity.ServiceEntity;
import com.linh.hotelbk.entity.UsedServiceEntity;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Set;

public class PDFExporter {

    public static void export(HttpServletResponse response, BookingFormEntity bookingForm) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        //Set font
        Font fontHeader = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20);
        Font fontTitle = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18);
        Font fontDetail = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 16);
        Font fontTotal = new Font(BaseFont.createFont("C:\\Windows\\Fonts\\arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 18);
        fontTotal.setColor(Color.RED);

        // Set Header
        Paragraph header = new Paragraph("Hóa đơn đặt phòng "+bookingForm.getRoom().getRoomName(), fontHeader);
        header.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(header);

        //Set customer info
        Paragraph customerTitle = new Paragraph("Thông tin khách đặt phòng\n", fontTitle);
        customerTitle.setAlignment(Paragraph.ALIGN_LEFT);
        customerTitle.setSpacingBefore(30);
        document.add(customerTitle);

        Paragraph customerInfo = new Paragraph();
        customerInfo.setFont(fontDetail);
        customerInfo.setAlignment(Paragraph.ALIGN_LEFT);
        customerInfo.setSpacingBefore(15);
        customerInfo.add("      Họ và tên:       "+bookingForm.getCustomerName()+"\n\n");
        customerInfo.add("      Email:           "+bookingForm.getCustomerEmail()+"\n\n");
        customerInfo.add("      Số điện thoại:   "+bookingForm.getPhoneNumber());
        document.add(customerInfo);

        //Set room price
        int totalRoomPrice = bookingForm.getRoom().getPrice() * bookingForm.getBookingDays();
        Paragraph roomPrice = new Paragraph("Tổng tiền đặt phòng:  "+totalRoomPrice+" $", fontTitle);
        roomPrice.setAlignment(Paragraph.ALIGN_LEFT);
        roomPrice.setSpacingBefore(30);
        document.add(roomPrice);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Paragraph roomPriceInfo = new Paragraph();
        roomPriceInfo.setFont(fontDetail);
        roomPriceInfo.setAlignment(Paragraph.ALIGN_LEFT);
        roomPriceInfo.setSpacingBefore(15);
        roomPriceInfo.add("      Giá thuê phòng:     "+bookingForm.getRoom().getCurrency()+"\n\n");
        roomPriceInfo.add("      Ngày check-in:      "+simpleDateFormat.format(bookingForm.getCheckInAt())+"\n\n");
        roomPriceInfo.add("      Ngày check-out:     "+simpleDateFormat.format(bookingForm.getCheckOutAt())+"\n\n");
        roomPriceInfo.add("      Số ngày thuê phòng: "+bookingForm.getBookingDays()+" ngày");
        document.add(roomPriceInfo);

        //Set service fee
        int totalServiceFee = bookingForm.getUsedServices().stream().mapToInt(i -> i.getService().getPrice() * i.getQuantity()).sum();
        Paragraph serviceFee= new Paragraph("Tổng tiền dịch vụ:  "+totalServiceFee+" $", fontTitle);
        serviceFee.setAlignment(Paragraph.ALIGN_LEFT);
        serviceFee.setSpacingBefore(30);
        document.add(serviceFee);

        Set<UsedServiceEntity> usedServiceSet = bookingForm.getUsedServices();
        Paragraph serviceFeeInfo = new Paragraph();
        serviceFeeInfo.setFont(fontDetail);
        serviceFeeInfo.setAlignment(Paragraph.ALIGN_LEFT);
        serviceFeeInfo.setSpacingBefore(15);
        for (UsedServiceEntity usedService : usedServiceSet){
            ServiceEntity service = usedService.getService();
            int usedServicePrice = usedService.getQuantity()*service.getPrice();
            serviceFeeInfo.add("       "+service.getServiceName()+" :   "+service.getPrice()+" $  x"+usedService.getQuantity()+"    =   "+usedServicePrice+" $\n\n");
        }
        document.add(serviceFeeInfo);

        //Set total to be paid
        Paragraph totalFee = new Paragraph("Tổng tiền đã trả:  "+(totalServiceFee + totalRoomPrice)+" $", fontTotal);
        totalFee.setAlignment(Paragraph.ALIGN_LEFT);
        totalFee.setSpacingBefore(30);
        document.add(totalFee);

        document.close();
    }
}
