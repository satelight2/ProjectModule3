package com.ra.view.adminMenu;

import com.ra.manager.ReportManager;
import com.ra.util.Console;

public class StatisticMenu {
    public static void statisticMenu(){

        ReportManager reportManager = new ReportManager();
        boolean check = true;
        do {
            System.out.println("******************STATISTIC MANAGEMENT****************");
            System.out.println("Chọn chức năng: ");
            System.out.println("1. Thống kê chi phí theo ngày, tháng, năm");
            System.out.println("2. Thống kê chi phí theo khoảng thời gian");
            System.out.println("3. Thống kê doanh thu theo ngày, tháng, năm");
            System.out.println("4. Thống kê doanh thu theo khoảng thời gian");
            System.out.println("5. Thống kê số nhân viên theo từng trạng thái");
            System.out.println("6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian");
            System.out.println("7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian");
            System.out.println("8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian");
            System.out.println("9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian");
            System.out.println("10. Thoát");
            System.out.println("Mời bạn chọn từ 1 - 10");
            System.out.print("Lựa chọn của bạn là: ");
            try {
                int choose = Integer.parseInt(com.ra.util.Console.scanner.nextLine());
                switch (choose) {
                    case 1:
                        System.out.println("1. Thống kê chi phí theo ngày, tháng, năm");
                        System.out.println("Nhập ngày tháng năm cần thống kê (yyyy-MM-dd): ");
                        String date = Console.scanner.nextLine();
                        reportManager.CostStatisticsByDate(date);
                        break;
                    case 2:
                        System.out.println("2. Thống kê chi phí theo khoảng thời gian");
                        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd): ");
                        String startDate = Console.scanner.nextLine();
                        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd): ");
                        String endDate = Console.scanner.nextLine();
                        reportManager.CostStatisticsByTimeRange(startDate, endDate);
                        break;
                    case 3:
                        System.out.println("3. Thống kê doanh thu theo ngày, tháng, năm");
                        System.out.println("Nhập ngày tháng năm cần thống kê (yyyy-MM-dd): ");
                        String dateRevenue = Console.scanner.nextLine();
                        reportManager.RevenueStatisticsByDate(dateRevenue);
                        break;
                    case 4:
                        System.out.println("4. Thống kê doanh thu theo khoảng thời gian");
                        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd): ");
                        String startDateRevenue = Console.scanner.nextLine();
                        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd): ");
                        String endDateRevenue = Console.scanner.nextLine();
                        reportManager.RevenueStatisticsByTimeRange(startDateRevenue, endDateRevenue);
                        break;
                    case 5:
                        System.out.println("5. Thống kê số nhân viên theo từng trạng thái");
                        reportManager.EmployeeCountByStatus();
                        break;
                    case 6:
                        System.out.println("6. Thống kê sản phẩm nhập nhiều nhất trong khoảng thời gian");
                        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd): ");
                        String startDateImport = Console.scanner.nextLine();
                        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd): ");
                        String endDateImport = Console.scanner.nextLine();
                        reportManager.MostImportedProductByTimeRange(startDateImport, endDateImport);
                        break;

                    case 7:
                        System.out.println("7. Thống kê sản phẩm nhập ít nhất trong khoảng thời gian");
                        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd): ");
                        String startDateImportLeast = Console.scanner.nextLine();
                        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd): ");
                        String endDateImportLeast = Console.scanner.nextLine();
                        reportManager.LeastImportedProductByTimeRange(startDateImportLeast, endDateImportLeast);
                        break;
                    case 8:
                        System.out.println("8. Thống kê sản phẩm xuất nhiều nhất trong khoảng thời gian");
                        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd): ");
                        String startDateExport = Console.scanner.nextLine();
                        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd): ");
                        String endDateExport = Console.scanner.nextLine();
                        reportManager.MostExportedProductByTimeRange(startDateExport, endDateExport);
                        break;
                    case 9:
                        System.out.println("9. Thống kê sản phẩm xuất ít nhất trong khoảng thời gian");
                        System.out.println("Nhập ngày bắt đầu (yyyy-MM-dd): ");
                        String startDateExportLeast = Console.scanner.nextLine();
                        System.out.println("Nhập ngày kết thúc (yyyy-MM-dd): ");
                        String endDateExportLeast = Console.scanner.nextLine();
                        reportManager.LeastExportedProductByTimeRange(startDateExportLeast, endDateExportLeast);
                        break;
                    case 10:
                        check = false;
                        break;
                    default:
                        System.out.println("Chọn sai mời chọn lại");
                }
            } catch (NumberFormatException e) {
                System.err.println("Lựa chọn phải là số nguyên từ 1 - 3");
            }
        } while (check);
    }
}
