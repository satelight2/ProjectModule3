package com.ra.model;

public class Header {
    public static void accounts(){
        String header = String.format(TableForm.accounts.column,
                "Mã tài khoản",
                "Tên tài khoản",
                "Mật khẩu",
                "Quyền hạn",
                "Mã nhân viên",
                "Trạng thái");
        printSeparatorForHeader(header);
    }
    public static void billDetails(){
        String header = String.format(TableForm.billDetails.column,
                "Mã phiếu chi tiết",
                "Mã phiếu",
                "Mã sản phẩm",
                "Số lượng",
                "Giá");
        printSeparatorForHeader(header);
    }
    public static void bills(){
        String header = String.format(TableForm.bills.column,
                "Mã phiếu",
                "Mã code",
                "Loại phiếu",
                "Mã nhân viên tạo",
                "Ngày tạo",
                "Mã nhân viên duyệt",
                "Ngày duyệt",
                "Trạng thái");
        printSeparatorForHeader(header);
    }
    public static void employees(){
        String header = String.format(TableForm.employees.column,
                "Mã nhân viên",
                "Tên nhân viên",
                "Ngày-tháng-năm sinh",
                "Địa chỉ e-mail",
                "Số điện thoại",
                "Địa chỉ",
                "Trạng thái");
        printSeparatorForHeader(header);
    }
    public static void products(){
        String header = String.format(TableForm.products.column,
                "Mã sản phẩm",
                "Tên sản phẩm",
                "Nhà sản xuất",
                "Ngày tạo",
                "Lô",
                "Số lượng",
                "Trạng thái");
        printSeparatorForHeader(header);
    }
    public static void printSeparator(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("_"); // Sử dụng ký tự "_" thay vì "="
        }
        System.out.print("\n"); // Thay đổi từ System.out.println() thành System.out.print("\n")
    }
    public static void printSeparatorForHeader(String header){
        System.out.println(header);
        int headerLength = header.length();
        printSeparator(headerLength);
    }




}
