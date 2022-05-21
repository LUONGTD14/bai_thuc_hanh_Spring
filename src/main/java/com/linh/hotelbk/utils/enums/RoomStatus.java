package com.linh.hotelbk.utils.enums;

public enum RoomStatus {
    RESERVED,
    AVAILABLE;

    @Override
    public String toString() {
        if (this == RESERVED)
            return "Đã được đặt";
        else
            return "Còn trống";
    }
}
