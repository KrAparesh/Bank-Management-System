import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "<username>", "<password>");
            int choice;
            do {
                System.out.println("\n\n***** Banking Management System *****");
                System.out.println("1. Show Customer Records");
                System.out.println("2. Add Customer Record");
                System.out.println("3. Delete Customer Record");
                System.out.println("4. Update Customer Record");
                System.out.println("5. Show Account Details of a Customer");
                System.out.println("6. Show Loan Details of a Customer");
                System.out.println("7. Deposit Money to an Account");
                System.out.println("8. Withdraw Money from an Account");
                System.out.println("9. Exit");
                System.out.println("Enter your choice (1-9):");
                choice = Integer.parseInt(br.readLine());

                switch (choice) {
                    case 1:
                        Statement smt = con.createStatement();
                        ResultSet rs = smt.executeQuery("select * from customer");
                        System.out.println("Customer Records:");
                        while (rs.next()) {
                            String cust = rs.getString(1);
                            String name = rs.getString(2);
                            String phone = rs.getString(3);
                            String city = rs.getString(4);
                            System.out.println("Cust_no: " + cust);
                            System.out.println("Name: " + name);
                            System.out.println("Phone_No: " + phone);
                            System.out.println("City: " + city);
                            System.out.println("\n");
                        }
                        break;

                    case 2:
                        PreparedStatement ps = con.prepareStatement("insert into customer values(?,?,?,?)");
                        System.out.print("Enter customer no: ");
                        String num = br.readLine();
                        System.out.print("Enter name: ");
                        String name = br.readLine();
                        System.out.print("Enter number: ");
                        String number = br.readLine();
                        System.out.print("Enter city: ");
                        String city = br.readLine();
                        ps.setString(1, num);
                        ps.setString(2, name);
                        ps.setString(3, number);
                        ps.setString(4, city);
                        int count = ps.executeUpdate();
                        if (count > 0) {
                            System.out.println(count + " record inserted");
                        } else {
                            System.out.println("Insert failed");
                        }
                        break;

                    case 3:
                        PreparedStatement ps1 = con.prepareStatement("delete from customer where Cust_no=?");
                        System.out.print("Enter customer id you want to delete: ");
                        String sno = br.readLine();
                        ps1.setString(1, sno);
                        int count1 = ps1.executeUpdate();
                        if (count1 == 1) {
                            System.out.println(count1 + " record deleted");
                        } else {
                            System.out.println("Delete failed");
                        }
                        break;

                    case 4:
                        int choice2;
                        do {
                            System.out.println("\n\n***** Update Customer Record *****");
                            System.out.println("1. Update Name");
                            System.out.println("2. Update Number");
                            System.out.println("3. Update City");
                            System.out.println("4. Exit");
                            System.out.println("Enter your choice (1-4):");
                            choice2 = Integer.parseInt(br.readLine());

                            switch (choice2) {
                                case 1:
                                    PreparedStatement psl1 = con.prepareStatement("update customer set name=? where Cust_no=?");
                                    System.out.print("Enter the customer no: ");
                                    String value2 = br.readLine();
                                    System.out.print("Enter new name: ");
                                    String custname = br.readLine();
                                    psl1.setString(1, custname);
                                    psl1.setString(2, value2);
                                    int count2 = psl1.executeUpdate();
                                    if (count2 > 0) {
                                        System.out.println(count2 + " record updated");
                                    } else {
                                        System.out.println("Update failed");
                                    }
                                    break;

                                case 2:
                                    PreparedStatement psl2 = con.prepareStatement("update customer set phone_no=? where Cust_no=?");
                                    System.out.print("Enter the customer no: ");
                                    String value3 = br.readLine();
                                    System.out.print("Enter new phone number: ");
                                    String custphone = br.readLine();
                                    psl2.setString(1, custphone);
                                    psl2.setString(2, value3);
                                    count2 = psl2.executeUpdate();
                                    if (count2 > 0) {
                                        System.out.println(count2 + " record updated");
                                    } else {
                                        System.out.println("Update failed");
                                    }
                                    break;

                                case 3:
                                    PreparedStatement psl3 = con.prepareStatement("update customer set city=? where Cust_no=?");
                                    System.out.print("Enter the customer no: ");
                                    String value4 = br.readLine();
                                    System.out.print("Enter new city: ");
                                    String custcity = br.readLine();
                                    psl3.setString(1, custcity);
                                    psl3.setString(2, value4);
                                    count2 = psl3.executeUpdate();
                                    if (count2 > 0) {
                                        System.out.println(count2 + " record updated");
                                    } else {
                                        System.out.println("Update failed");
                                    }
                                    break;

                                case 4:
                                    System.out.println("Exiting update menu...");
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                            }
                        } while (choice2 != 4);
                        break;

                    case 5:
                        Statement smtxy = con.createStatement();
                        System.out.println("Enter the customer number: ");
                        String erc = br.readLine();
                        ResultSet rsx = smtxy.executeQuery("SELECT * FROM CUSTOMER INNER JOIN DEPOSITOR ON CUSTOMER.CUST_NO = DEPOSITOR.CUST_NO INNER JOIN ACCOUNT ON DEPOSITOR.ACCOUNT_NO = ACCOUNT.ACCOUNT_NO where CUSTOMER.cust_no = '" + erc + "'");
                        while (rsx.next()) {
                            System.out.println("Cust_no: " + rsx.getString(1));
                            System.out.println("Name: " + rsx.getString(2));
                            System.out.println("Phone: " + rsx.getLong(3));
                            System.out.println("City: " + rsx.getString(4));
                            System.out.println("Customer_Account: " + rsx.getString(5));
                            System.out.println("Account Type: " + rsx.getString(7));
                            System.out.println("Balance: " + rsx.getString(8));
                            System.out.println("Branch Code: " + rsx.getString(9));
                        }
                        break;

                    case 6:
                        PreparedStatement ps26 = con.prepareStatement("SELECT CUSTOMER.*, loan.loan_no, loan.amount, branch.branch_code, branch.branch_name, branch.branch_city FROM CUSTOMER INNER JOIN loan ON CUSTOMER.CUST_NO = loan.cust_no INNER JOIN branch ON loan.branch_code = branch.branch_code WHERE CUSTOMER.CUST_NO=?");
                        System.out.println("Enter the customer number: ");
                        String ercx = br.readLine();
                        ps26.setString(1, ercx);
                        ResultSet rs13 = ps26.executeQuery();
                        while (rs13.next()) {
                            System.out.println("Cust_no: " + rs13.getString("CUST_NO"));
                            System.out.println("Name: " + rs13.getString("name"));
                            System.out.println("Phone: " + rs13.getString("phone_no"));
                            System.out.println("City: " + rs13.getString("city"));
                            System.out.println("Loan No: " + rs13.getString("loan_no"));
                            System.out.println("Loan Amount: " + rs13.getDouble("amount"));
                            System.out.println("Branch Code: " + rs13.getString("branch_code"));
                            System.out.println("Branch Name: " + rs13.getString("branch_name"));
                            System.out.println("Branch City: " + rs13.getString("branch_city"));
                        }
                        break;

                    case 7:
                        PreparedStatement psDeposit = con.prepareStatement("UPDATE ACCOUNT SET balance = balance + ? WHERE account_no = ?");
                        System.out.println("Enter your account number: ");
                        String accountNoDeposit = br.readLine();
                        System.out.println("Enter the amount you want to deposit: ");
                        int depositAmount = Integer.parseInt(br.readLine());
                        psDeposit.setInt(1, depositAmount);
                        psDeposit.setString(2, accountNoDeposit);
                        int depositCount = psDeposit.executeUpdate();
                        if (depositCount > 0) {
                            System.out.println("Deposit successful. Updated account details:");
                            showAccountDetails(con, accountNoDeposit);
                        } else {
                            System.out.println("Deposit failed");
                        }
                        break;

                    case 8:
                        PreparedStatement psWithdraw = con.prepareStatement("UPDATE ACCOUNT SET balance = balance - ? WHERE account_no = ?");
                        System.out.println("Enter your account number: ");
                        String accountNoWithdraw = br.readLine();
                        System.out.println("Enter the amount you want to withdraw: ");
                        int withdrawAmount = Integer.parseInt(br.readLine());
                        psWithdraw.setInt(1, withdrawAmount);
                        psWithdraw.setString(2, accountNoWithdraw);
                        int withdrawCount = psWithdraw.executeUpdate();
                        if (withdrawCount > 0) {
                            System.out.println("Withdrawal successful. Updated account details:");
                            showAccountDetails(con, accountNoWithdraw);
                        } else {
                            System.out.println("Withdrawal failed");
                        }
                        break;

                    case 9:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                }
            } while (choice != 9);

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void showAccountDetails(Connection con, String accountNo) throws SQLException {
        PreparedStatement psAccount = con.prepareStatement("SELECT * FROM ACCOUNT WHERE account_no = ?");
        psAccount.setString(1, accountNo);
        ResultSet rsAccount = psAccount.executeQuery();
        if (rsAccount.next()) {
            System.out.println("Account No: " + rsAccount.getString("account_no"));
            System.out.println("Account Type: " + rsAccount.getString("type"));
            System.out.println("Balance: " + rsAccount.getString("balance"));
            System.out.println("Branch Code: " + rsAccount.getString("branch_code"));
        } else {
            System.out.println("Account not found");
        }
    }
}