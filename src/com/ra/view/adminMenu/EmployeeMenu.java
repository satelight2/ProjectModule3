package com.ra.view.adminMenu;

import com.ra.entity.Account;
import com.ra.entity.Employee;
import com.ra.service.impl.AccountServiceImpl;
import com.ra.service.impl.EmployeeServiceImpl;
import com.ra.util.Console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
                            employee.setEmpStatus(Integer.parseInt(Console.scanner.nextLine()));
                            if(employeeService.addProduct(employee) != null){
                                System.out.println("Thêm mới nhân viên thành công");
                            }else{
                                System.out.println("Thêm mới nhân viên thất bại");
                            }

                            break;
                        case 3:
                            System.out.println("Cập nhật thông tin nhân viên");
                            System.out.println("Nhập tên hoặc mã nhân viên cần cập nhật: ");
                            String updateKey = Console.scanner.nextLine();
                            Employee updateE = employeeService.findAny(updateKey);
                            if(updateE != null){
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
                                updateE.setEmpStatus(Integer.parseInt(Console.scanner.nextLine()));
                                if(employeeService.updateProduct(updateE) != null){
                                    System.out.println("Cập nhật thông tin nhân viên thành công");
                                }else{
                                    System.out.println("Cập nhật thông tin nhân viên thất bại");
                                }
                            }else{
                                System.out.println("Không tìm thấy nhân viên");
                            }

                            break;
                        case 4:
                            System.out.println("Cập nhật trạng thái nhân viên");
                            System.out.println("Nhập tên hoặc mã nhân viên cần cập nhật: ");
                            String updateStatusKey = Console.scanner.nextLine();
                            Employee updateStatusE = employeeService.findAny(updateStatusKey);
                            if(updateStatusE != null){
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
                                        System.out.println("Lựa chọn không hợp lệ");
                                        break;
                                }
                                if(employeeService.updateProduct(updateStatusE) != null){
                                    // neu EmpStatus = 1 hoac 2 thi cap nhat lai account
                                    if(updateStatusE.getEmpStatus() == 1 || updateStatusE.getEmpStatus() == 2){
                                        Account account = accountService.findAny(updateStatusE.getEmpId());

                                        if(account != null){
                                            account.setAccStatus(false);
                                            if(accountService.updateAcc(account) != null){
                                                System.out.println("Cập nhật trạng thái tài khoản thành công");
                                            }else{
                                                System.out.println("Cập nhật trạng thái tài khoản thất bại");
                                            }
                                        }else{
                                            System.out.println("Không tìm thấy tài khoản");
                                        }
                                    System.out.println("Cập nhật trạng thái nhân viên thành công");
                                }else{
                                    System.out.println("Cập nhật trạng thái nhân viên thất bại");
                                }
                            }
                            }else{
                                System.out.println("Không tìm thấy nhân viên");
                            }
                            break;
                        case 5:
                            System.out.println("Tìm kiếm nhân viên theo tên hoặc mã nhân viên");
                            System.out.println("Nhập tên hoặc mã nhân viên cần tìm kiếm: ");
                            String keyword = Console.scanner.nextLine();
                            Employee employee1 = employeeService.findAny(keyword);
                            if(employee1 != null){
                                System.out.println(employee1.toString());
                            }else{
                                System.out.println("Không tìm thấy nhân viên");
                            }
                            break;
                        case 6:
                            check = false;
                            break;
                        default:
                            System.out.println("Chọn sai mời chọn lại");
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
            System.out.println("Danh sách sản phẩm:");
            List<Employee> employeeList = employeeService.findPagination(page, PAGE_SIZE);
            for (Employee employee : employeeList) {
                System.out.println(employee.toString()); // In thông tin của từng sản phẩm
            }
            System.out.println("Trang " + page);
            System.out.println("1. Trang trước");
            System.out.println("2. Trang sau");
            System.out.println("3. Quay lại menu chính");
            System.out.print("Lựa chọn của bạn là: ");
            int choice = Console.scanner.nextInt();
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
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
};

