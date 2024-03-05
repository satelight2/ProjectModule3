package com.ra.util.font;

import com.ra.model.PermissionType;
import com.ra.util.Storage;

public class MessageCustom {
    public static void welcome(){
        System.out.printf("Xin chào %s | Quyền hạn: %s \n", Storage.current_account.getUserName(), Storage.current_account.isPermission()==(PermissionType.USER)?"User" : Storage.current_account.isPermission()==(PermissionType.ADMIN) ? "Admin": "Chưa được phân quyền");
    }
    public static void listEmpty(){
        PrintForm.attention("Danh sách hiện chưa có dữ liệu nào");
    }
    public static void dataNotFound(){PrintForm.warning("Từ khóa bạn tìm kiếm không có kết quả phù hợp");}
    public static void objectNotExist(){
        PrintForm.warning("Đối tượng cần tìm không tồn tại");
    }
    public static void choiceFailure() {
        PrintForm.warning("Lựa chọn không phù hợp mời chọn lại !");
    }
    public static void dateFormatFailure() {
        PrintForm.warning("Định dạng ngày tháng không phù hợp");
    }

    public static void createdSuccess(){
        PrintForm.success("Tạo thành công !!!");
    }
    public static void createdFailure(){
        PrintForm.warning("Tạo thất bại");
    }
    public static void updateSuccess(){
        PrintForm.success("Cập nhật thành công");
    }
    public static void updateFailure(){
        PrintForm.warning("Cập nhật thất bại");
    }
    public static void outOfRange(){
        PrintForm.warning("Số lượng vượt quá giới hạn cho phép");
    }
}
