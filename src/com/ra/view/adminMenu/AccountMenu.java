package com.ra.view.adminMenu;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.service.impl.AccountServiceImpl;
import com.ra.service.impl.EmployeeServiceImpl;
import com.ra.util.Console;

public class AccountMenu {
    public static void aMenu(){
        AccountServiceImpl accountService = new AccountServiceImpl();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        boolean check = true;
        do{
            System.out.println("******************ACCOUNT MANAGEMENT****************");
            System.out.println("Chọn chức năng: ");
            System.out.println("1. Danh sách tài khoản");
            System.out.println("2. Tạo tài khoản mới");
            System.out.println("3. Cập nhật trạng thái tài khoản");
            System.out.println("4. Tìm kiếm tài khoản");
            System.out.println("5. Thoát");
            System.out.println("Mời bạn chọn từ 1 - 5");
            System.out.print("Lựa chọn của bạn là: ");

            try {
                int choose = Integer.parseInt(Console.scanner.nextLine());
                switch (choose) {
                    case 1:
                        System.out.println("Danh sách tài khoản");
                        for(Account account : accountService.findAll()){
                            System.out.println(account.toString());
                        }
                        break;
                    case 2:
                        System.out.println("Tạo tài khoản mới");
                        Account account = new Account();
                        System.out.println("Nhập username: ");
                        account.setUserName(Console.scanner.nextLine());
                        System.out.println("Nhập password: ");
                        account.setPassword(Console.scanner.nextLine());
                        System.out.println("Nhập quyền truy cập:");
                        System.out.println("True :  Admin");
                        System.out.println("False :  User");
                        System.out.print("Lựa chọn của bạn là: ");
                        String permission = Console.scanner.nextLine();
                        if(permission.equals("true")){
                            account.setPermission(true);
                        }else {
                            account.setPermission(false);
                        }
                        System.out.println("Nhập mã nhân viên: ");
                        String empID;
                        Employee employee;
                        do {
                            empID = Console.scanner.nextLine();
                            employee = employeeService.findbyID(empID);
                            if (employee == null) {
                                System.out.println("Mã nhân viên không tồn tại. Vui lòng nhập lại:");
                            }
                            account.setEmpId(empID);
                        } while (employee == null);
                        System.out.println("Nhập trạng thái tài khoản: ");
                        System.out.println("0. Block");
                        System.out.println("1. Active");
                        System.out.println("Lựa chọn của bạn là: ");
                        if(Console.scanner.nextLine().equals("0")){
                            account.setAccStatus(false);
                        }else {
                            account.setAccStatus(true);
                        }


                        if( accountService.addAccount(account) != null){
                            System.out.println("Thêm tài khoản thành công");
                        }else {
                            System.out.println("Thêm tài khoản thất bại");
                        }

                        break;
                    case 3:
                        System.out.println("3.Cập nhật trạng thái tài khoản");
                        System.out.println("Nhập username hoặc tên nhân viên: ");
                        String keyUpdate = Console.scanner.nextLine();
                        Account updateAcc = accountService.findByUsernameOrEmployeeName(keyUpdate);
                        if(updateAcc != null){
                            System.out.println(updateAcc.toString());
                            System.out.println("Nhập trạng thái tài khoản: ");
                            System.out.println("0. Block");
                            System.out.println("1. Active");
                            System.out.println("Lựa chọn của bạn là: ");
                            int choice = Integer.parseInt(Console.scanner.nextLine());
                            if(choice == 1) {
                                updateAcc.setAccStatus(true);
                                if(accountService.updateAcc(updateAcc) != null){
                                    System.out.println("Cập nhật trạng thái tài khoản thành công");
                                    System.out.println("Tài khoản đã được active");
                                }
                            }else if(choice == 0){
                                updateAcc.setAccStatus(false);
                                if(accountService.updateAcc(updateAcc) != null){
                                    System.out.println("Cập nhật trạng thái tài khoản thành công");
                                    System.out.println("Tài khoản đã bị block");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("Không tìm thấy tài khoản");
                        }
                        break;
                    case 4:
                        System.out.println("4. Tìm kiếm tài khoản");
                        System.out.println("Nhập username hoặc tên nhân viên: ");
                        String key = Console.scanner.nextLine();
                        Account account1 = accountService.findByUsernameOrEmployeeName(key);
                        if(account1 != null){
                            System.out.println(account1.toString());
                        }else {
                            System.out.println("Không tìm thấy tài khoản");
                        }
                        break;
                    case 5:
                        check = false;
                        break;
                    default:
                        System.out.println("Chọn sai mời chọn lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 6");

            }
        } while (check);
    }
}
