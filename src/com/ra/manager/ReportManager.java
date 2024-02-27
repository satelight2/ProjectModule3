package com.ra.manager;

import com.ra.util.MySqlConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReportManager {
    public void CostStatisticsByDate(String date){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call CostStatisticsByDate(?)}");
            callableStatement.setString(1, date);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                double totalCost = resultSet.getDouble("TotalCost");
                System.out.println("Total cost on " + date + ": " + totalCost);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void CostStatisticsByTimeRange(String startDate, String endDate){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call CostStatisticsByTimeRange(?,?)}");
            callableStatement.setString(1, startDate);
            callableStatement.setString(2, endDate);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                double totalCost = resultSet.getDouble("TotalCost");
                System.out.println("Total cost from " + startDate + " to " + endDate + ": " + totalCost);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void RevenueStatisticsByDate(String date){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call RevenueStatisticsByDate(?)}");
            callableStatement.setString(1, date);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                double totalCost = resultSet.getDouble("TotalCost");
                System.out.println("Total cost on " + date + ": " + totalCost);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void RevenueStatisticsByTimeRange(String startDate, String endDate){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call RevenueStatisticsByTimeRange(?,?)}");
            callableStatement.setString(1, startDate);
            callableStatement.setString(2, endDate);
            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                double totalCost = resultSet.getDouble("TotalCost");
                System.out.println("Total cost from " + startDate + " to " + endDate + ": " + totalCost);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void EmployeeCountByStatus(){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call EmployeeCountByStatus()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int status = resultSet.getInt("Emp_Status");
                int count = resultSet.getInt("EmployeeCount");
                System.out.println("Number of employees with status " + status + ": " + count);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };
    public void MostImportedProductByTimeRange(String startDate, String endDate){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call MostImportedProductByTimeRange(?,?)}");
            callableStatement.setString(1, startDate);
            callableStatement.setString(2, endDate);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                String productID = resultSet.getString("Product_ID");
                String productName = resultSet.getString("Product_Name");
                int quantity = resultSet.getInt("TotalQuantity");
                System.out.println("Most imported product from " + startDate + " to " + endDate + ": " + productName + " with product ID " + productID + " and total quantity " + quantity);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void LeastImportedProductByTimeRange(String startDate, String endDate){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call LeastImportedProductByTimeRange(?,?)}");
            callableStatement.setString(1, startDate);
            callableStatement.setString(2, endDate);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                String productID = resultSet.getString("Product_ID");
                String productName = resultSet.getString("Product_Name");
                int quantity = resultSet.getInt("TotalQuantity");
                System.out.println("Most imported product from " + startDate + " to " + endDate + ": " + productName + " with product ID " + productID + " and total quantity " + quantity);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void MostExportedProductByTimeRange(String startDate, String endDate){
        Connection  conn = null;
        try{
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call MostExportedProductByTimeRange(?,?)}");
            callableStatement.setString(1, startDate);
            callableStatement.setString(2, endDate);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                String productID = resultSet.getString("Product_ID");
                String productName = resultSet.getString("Product_Name");
                int quantity = resultSet.getInt("TotalQuantity");
                System.out.println("Most exported product from " + startDate + " to " + endDate + ": " + productName + " with product ID " + productID + " and total quantity " + quantity);
            } else {
                System.out.println("No data found for the specified date.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void LeastExportedProductByTimeRange(String startDate, String endDate) {
        Connection conn = null;
        try {
            conn = new MySqlConnection().getConnection();
            CallableStatement callableStatement = conn.prepareCall("{call LeastExportedProductByTimeRange(?,?)}");
            callableStatement.setString(1, startDate);
            callableStatement.setString(2, endDate);

            ResultSet resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                String productID = resultSet.getString("Product_ID");
                String productName = resultSet.getString("Product_Name");
                int quantity = resultSet.getInt("TotalQuantity");
                System.out.println("Least exported product from " + startDate + " to " + endDate + ": " + productName + " with product ID " + productID + " and total quantity " + quantity);
            } else {
                System.out.println("No data found for the specified date.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
