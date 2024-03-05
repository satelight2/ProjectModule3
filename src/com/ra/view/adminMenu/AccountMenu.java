package com.ra.view.adminMenu;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.model.ConstStatus;
import com.ra.model.Header;
import com.ra.model.PermissionType;
import com.ra.model.TableForm;
import com.ra.service.impl.AccountServiceImpl;
import com.ra.service.impl.EmployeeServiceImpl;
import com.ra.util.Console;
import com.ra.util.font.MessageCustom;

import static com.ra.model.Header.printSeparator;

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
                        Header.accounts();
                        for(Account account : accountService.findAll()){
                            printAccount(account);
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
                            employee = employeeService.findAny(empID);
                            if (employee == null) {
                                MessageCustom.objectNotExist();
                            }
                            account.setEmpId(empID);
                        } while (employee == null);
                        System.out.println("Nhập trạng thái tài khoản: ");
                        System.out.println("0. Block");
                        System.out.println("1. Active");
                        System.out.println("Lựa chọn của bạn là: ");
                        if(Integer.parseInt(Console.scanner.nextLine()) == 0){
                            account.setAccStatus(false);
                        }else {
                            account.setAccStatus(true);
                        }


                        if( accountService.addAccount(account) != null){
                            MessageCustom.createdSuccess();
                        }else {
                            MessageCustom.createdFailure();
                        }

                        break;
                    case 3:
                        System.out.println("3.Cập nhật trạng thái tài khoản");
                        System.out.println("Nhập username hoặc tên nhân viên: ");
                        String keyUpdate = Console.scanner.nextLine();
                        Account updateAcc = accountService.findByUsernameOrEmployeeName(keyUpdate);
                        if(updateAcc != null){
                            Header.accounts();
                            printAccount(updateAcc);
                            System.out.println(updateAcc.toString());
                            System.out.println("Nhập trạng thái tài khoản: ");
                            System.out.println("0. Block");
                            System.out.println("1. Active");
                            System.out.println("Lựa chọn của bạn là: ");
                            int choice = Integer.parseInt(Console.scanner.nextLine());
                            if(choice == 1) {
                                updateAcc.setAccStatus(true);
                                if(accountService.updateAcc(updateAcc) != null){
                                    MessageCustom.updateSuccess();
                                    System.out.println("Tài khoản đã được active");
                                }
                            }else if(choice == 0){
                                updateAcc.setAccStatus(false);
                                if(accountService.updateAcc(updateAcc) != null){
                                    MessageCustom.updateSuccess();
                                    System.out.println("Tài khoản đã bị block");
                                }
                            }
                        }
                        else
                        {
                            MessageCustom.objectNotExist();
                        }
                        break;
                    case 4:
                        System.out.println("4. Tìm kiếm tài khoản");
                        System.out.println("Nhập username hoặc tên nhân viên: ");
                        String key = Console.scanner.nextLine();
                        Account account1 = accountService.findByUsernameOrEmployeeName(key);
                        if(account1 != null){
                            Header.accounts();
                            printAccount(account1);
                        }else {
                            MessageCustom.objectNotExist();
                        }
                        break;
                    case 5:
                        check = false;
                        break;
                    default:
                        MessageCustom.choiceFailure();
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 6");

            }
        } while (check);
    }
    public static void printAccount(Account acc){
        String info = String.format(TableForm.accounts.column,
                acc.getAccId(),
                acc.getUserName(),
                acc.getPassword(),
                acc.isPermission() == (PermissionType.USER) ? "User":"Admin",
                acc.getEmpId(),
                acc.isAccStatus() == (ConstStatus.AccountStt.ACTIVE)?"Hoạt động":"Không hoạt động");
        System.out.println(info);
        int headerLength = info.length();
        printSeparator(headerLength);
    }
}
