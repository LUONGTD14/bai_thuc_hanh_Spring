package com.linh.hotelbk.controller.web;

import com.linh.hotelbk.entity.RoomEntity;
import com.linh.hotelbk.entity.RoomTypeEntity;
import com.linh.hotelbk.service.IRoomService;
import com.linh.hotelbk.service.IRoomTypeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller(value = "Room Controller for Client")
@AllArgsConstructor
@Slf4j
@Api(tags = "Controller")
public class RoomController {

    private final IRoomService roomService;
    private final IRoomTypeService roomTypeService;

    @GetMapping(path = "/room")
    public ModelAndView getRoomPage(HttpServletRequest request) throws ParseException {
        ModelAndView mv = new ModelAndView("web/phong");
        // Get all parameter from request
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int currentPage = (request.getParameter("page") == null) ? 1 : Integer.parseInt(request.getParameter("page"));
        String sortBy = (request.getParameter("sort") == null)? "CreateAt" : request.getParameter("sort");
        String sortDir = (request.getParameter("sortDir") == null) ? "asc" : request.getParameter("sortDir");
        Long roomTypeId = (request.getParameter("roomTypeId") == null || request.getParameter("roomTypeId").equals("")) ? null : Long.parseLong(request.getParameter("roomTypeId"));
        Integer price = (request.getParameter("price") == null || request.getParameter("price").equals("")) ? null : Integer.parseInt(request.getParameter("price"));
        Date checkInDate = (request.getParameter("checkInDate") == null || request.getParameter("checkInDate").equals("")) ? null : simpleDateFormat.parse(request.getParameter("checkInDate"));
        Date checkOutDate = (request.getParameter("checkOutDate") == null || request.getParameter("checkOutDate").equals("")) ? null : simpleDateFormat.parse(request.getParameter("checkOutDate"));
        RoomTypeEntity roomType = (roomTypeId == null) ? null : roomTypeService.findById(roomTypeId);

        Page<RoomEntity> roomPage = roomService.findAll(currentPage, 6, roomType, price, checkInDate, checkOutDate, sortBy, sortDir);

        int totalPages = roomPage.getTotalPages();
        long totalItems = roomPage.getTotalElements();

        String sortName = "";
        if(sortBy.equals("CreateAt")) sortName = "Mặc định";
        else if(sortBy.equals("RoomName") && sortDir.equals("asc")) sortName = "Tên (A-Z)";
        else if(sortBy.equals("RoomName") && sortDir.equals("desc")) sortName = "Tên (Z-A)";
        else if(sortBy.equals("Price") && sortDir.equals("asc")) sortName = "Giá (Thấp-Cao)";
        else if(sortBy.equals("Price") && sortDir.equals("desc")) sortName = "Giá (Cao-Thấp)";


        mv.addObject("sortBy", sortBy);
        mv.addObject("sortDir", sortDir);
        mv.addObject("sortName", sortName);
        mv.addObject("totalItems", totalItems);
        mv.addObject("totalPages", totalPages);
        mv.addObject("currentPage", currentPage);
        mv.addObject("roomList", roomPage.getContent());
        mv.addObject("roomTypeList", roomTypeService.findAll());
        return mv;
    }
}
