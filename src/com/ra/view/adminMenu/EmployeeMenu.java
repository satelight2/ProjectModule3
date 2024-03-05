package com.ra.view.adminMenu;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.entity.Product;
import com.ra.model.ConstStatus;
import com.ra.model.Header;
import com.ra.model.TableForm;
import com.ra.service.impl.AccountServiceImpl;
import com.ra.service.impl.EmployeeServiceImpl;
import com.ra.util.Console;
import com.ra.util.font.MessageCustom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.ra.model.Header.printSeparator;

public class EmployeeMenu {
    private static final int PAGE_SIZE = 10;
    public static void eMenu(){
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        AccountServiceImpl accountService = new AccountServiceImpl();
            boolean check = true;
            do{
                System.out.println("******************EMPLOYEE MANAGEMENT****************");
                System.out.println("Chọn chức năng: ");
                System.out.println("1. Danh sách nhân viên");
                System.out.println("2. Thêm mới nhân viên");
                System.out.println("3. Cập nhật thông tin nhân viên");
                System.out.println("4. Cập nhật trạng thái nhân viên");
                System.out.println("5. Tìm kiếm nhân viên");
                System.out.println("6. Thoát");
                System.out.println("Mời bạn chọn từ 1 - 6");
                System.out.print("Lựa chọn của bạn là: ");

                try {
                    int choose = Integer.parseInt(Console.scanner.nextLine());
                    switch (choose) {
                        case 1:
                            System.out.println("Danh sách nhân viên sắp xếp theo tên nhân viên tăng dần");
                            showEmployeeList();
                            break;
                        case 2:
                            System.out.println("Thêm mới nhân viên");
                            Employee employee = new Employee();
                            System.out.println("Nhập mã nhân viên: ");
                            employee.setEmpId(Console.scanner.nextLine());
                            System.out.println("Nhập tên nhân viên: ");
                            employee.setEmpName(Console.scanner.nextLine());
                            System.out.println("Nhập ngày sinh (theo định dạng dd/MM/yyyy): ");
                            String birthDateString = Console.scanner.nextLine();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            Date birthDate = dateFormat.parse(birthDateString);
                            employee.setBirthOfDate(birthDate);
                            System.out.println("Nhập email nhân viên: ");
                            employee.setEmail(Console.scanner.nextLine());
                            System.out.println("Nhập số điện thoại nhân viên: ");
                            employee.setPhone(Console.scanner.nextLine());
                            System.out.println("Nhập địa chỉ nhân viên: ");
                            employee.setAddress(Console.scanner.nextLine());

                            System.out.println("Nhập trạng thái nhân viên: ");
                            System.out.println("0 : Hoạt động");
                            System.out.println("1 : Nghỉ chế độ ");
                            System.out.println("2 : Nghỉ việc ");
                            System.out.println("Lựa chọn của bạn là: ");
                            int status2 = Integer.parseInt(Console.scanner.nextLine());
                            switch (status2){
                                case 0:
                                    employee.setEmpStatus(0);
                                    break;
                                case 1:
                                    employee.setEmpStatus(1);

                                    break;
                                case 2:
                                    employee.setEmpStatus(2);
                                    break;
                                default:
                                    MessageCustom.choiceFailure();
                                    break;
                            }
                            if(employeeService.addProduct(employee) != null){
                               MessageCustom.createdSuccess();
                            }else{
                                MessageCustom.createdFailure();
                            }

                            break;
                        case 3:
                            System.out.println("Cập nhật thông tin nhân viên");
                            System.out.println("Nhập tên hoặc mã nhân viên cần cập nhật: ");
                            String updateKey = Console.scanner.nextLine();
                            Employee updateE = employeeService.findAny(updateKey);
                            if(updateE != null){
                                Header.employees();
                                printEmployeeInfo(updateE);
                                System.out.println("Nhập tên nhân viên: ");
                                updateE.setEmpName(Console.scanner.nextLine());
                                System.out.println("Nhập ngày sinh (theo định dạng dd/MM/yyyy): ");
                                String birthDateString1 = Console.scanner.nextLine();
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                                Date birthDate1 = dateFormat1.parse(birthDateString1);
                                updateE.setBirthOfDate(birthDate1);
                                System.out.println("Nhập email nhân viên: ");
                                updateE.setEmail(Console.scanner.nextLine());
                                System.out.println("Nhập số điện thoại nhân viên: ");
                                updateE.setPhone(Console.scanner.nextLine());
                                System.out.println("Nhập địa chỉ nhân viên: ");
                                updateE.setAddress(Console.scanner.nextLine());
                                System.out.println("Nhập trạng thái nhân viên: ");
                                System.out.println("0 : Hoạt động");
                                System.out.println("1 : Nghỉ chế độ ");
                                System.out.println("2 : Nghỉ việc ");
                                System.out.println("Lựa chọn của bạn là: ");
                                int status1 = Integer.parseInt(Console.scanner.nextLine());
                                switch (status1){
                                    case 0:
                                        updateE.setEmpStatus(0);
                                        break;
                                    case 1:
                                        updateE.setEmpStatus(1);

                                        break;
                                    case 2:
                                        updateE.setEmpStatus(2);
                                        break;
                                    default:
                                        MessageCustom.choiceFailure();
                                        break;
                                }
                                if(employeeService.updateProduct(updateE) != null){
                                    MessageCustom.updateSuccess();
                                }else{
                                    MessageCustom.updateFailure();
                                }
                            }else{
                                MessageCustom.objectNotExist();
                            }

                            break;
                        case 4:
                            System.out.println("Cập nhật trạng thái nhân viên");
                            System.out.println("Nhập tên hoặc mã nhân viên cần cập nhật: ");
                            String updateStatusKey = Console.scanner.nextLine();
                            Employee updateStatusE = employeeService.findAny(updateStatusKey);
                            if(updateStatusE != null){
                                Header.employees();
                                printEmployeeInfo(updateStatusE);
                                System.out.println("Nhập trạng thái nhân viên: ");
                                System.out.println("0 .Hoạt động");
                                System.out.println("1. Nghỉ chế độ");
                                System.out.println("2. Nghỉ việc");
                                System.out.println("Lựa chọn của bạn là: ");
                                int status = Integer.parseInt(Console.scanner.nextLine());
                                switch (status){
                                    case 0:
                                        updateStatusE.setEmpStatus(0);
                                        break;
                                    case 1:
                                        updateStatusE.setEmpStatus(1);

                                        break;
                                    case 2:
                                        updateStatusE.setEmpStatus(2);
                                        break;
                                    default:
                                        MessageCustom.choiceFailure();
                                        break;
                                }
                                if(employeeService.updateProduct(updateStatusE) != null){
                                    if(updateStatusE.getEmpStatus() == 1 || updateStatusE.getEmpStatus() == 2){
                                        Account account = accountService.findAny(updateStatusE.getEmpId());

                                        if(account != null){
                                            account.setAccStatus(false);
                                            if(accountService.updateAcc(account) != null){
                                                System.out.println("Account updated ! ");
                                                MessageCustom.updateSuccess();
                                            }else{
                                                System.out.println("Account updated failed ! ");
                                                MessageCustom.updateFailure();
                                            }
                                        }else{
                                           MessageCustom.objectNotExist();
                                        }

                                }else{
                                        MessageCustom.updateSuccess();
                                }
                            }
                            }else{
                                MessageCustom.objectNotExist();
                            }
                            break;
                        case 5:
                            System.out.println("Tìm kiếm nhân viên theo tên hoặc mã nhân viên");
                            System.out.println("Nhập tên hoặc mã nhân viên cần tìm kiếm: ");
                            String keyword = Console.scanner.nextLine();
                            Employee employee1 = employeeService.findAny(keyword);
                            if(employee1 != null){
                                Header.employees();
                                printEmployeeInfo(employee1);
                            }else{
                                MessageCustom.objectNotExist();
                            }
                            break;
                        case 6:
                            check = false;
                            break;
                        default:
                            MessageCustom.choiceFailure();
                    }
                } catch (NumberFormatException | ParseException e) {
                    System.err.println("Lựa chọn phải là số nguyên từ 1 - 6");

                }
            } while (check);
    }
    public  static void showEmployeeList() {
         EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        int page = 1;
        while (true) {
            System.out.println("Danh sách nhân viên:");
            List<Employee> employeeList = employeeService.findPagination(page, PAGE_SIZE);
            Header.employees();
            for (Employee employee : employeeList) {
                printEmployeeInfo(employee);
            }
            System.out.println("Trang " + page);
            System.out.println("1. Trang trước");
            System.out.println("2. Trang sau");
            System.out.println("3. Quay lại menu chính");
            System.out.print("Lựa chọn của bạn là: ");
            int choice = Integer.parseInt(Console.scanner.nextLine());
            switch (choice) {
                case 1:
                    if (page > 1) {
                        page--;
                    } else {
                        System.out.println("Bạn đã ở trang đầu tiên.");
                    }
                    break;
                case 2:
                    if (!employeeList.isEmpty()) {
                        page++;
                    } else {
                        System.out.println("Bạn đã ở trang cuối cùng.");
                    }
                    break;
                case 3:
                    return;
                default:
                    MessageCustom.choiceFailure();
            }
        }
    }
    public static void printEmployeeInfo(Employee e) {
        String info = String.format(TableForm.employees.column,
                e.getEmpId(),
                e.getEmpName(),
                e.getBirthOfDate(),
                e.getEmail(),
                e.getPhone(),
                e.getAddress(),
                e.getEmpStatus() == (ConstStatus.EmpStt.ACTIVE) ? "Hoạt động" : e.getEmpStatus() == (ConstStatus.EmpStt.SLEEP) ? "Nghỉ chế độ" : "Nghỉ việc");
        System.out.println(info);
        int headerLength = info.length();
        printSeparator(headerLength);
    }
};

