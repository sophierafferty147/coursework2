import javax.swing.JOptionPane;
import java.io.*;
import java.time.*;
public class CONCERT
{
    // arrray of customer objects
    private CUSTOMER customerList[];
    // number of customers to be calculated after reading file
    int noOfCustomers;
    FILEREADCSV customerFile;
    FILEWRITECSV resultFile;

    public CONCERT() throws IOException
    {
        // initialise instance variables
        customerFile = new FILEREADCSV();
        resultFile = new FILEWRITECSV();
    }

    public void processCustomer() throws IOException
    {
        setUpCustomerList();
        showyear();
        countmethod();
        counttotal();
        savefridaynightfile();
    }

    public void setUpCustomerList() throws IOException
    {
        // First user message
        System.out.println("Concert Sales: Customer update");
        System.out.println("** Preparing to read data file.");
        // read file, fetch data as String array containing the rows
        String[] dataRows = customerFile.readCSVtable();
        // calculate the number of customer rows, skip headings
        noOfCustomers = dataRows.length;
        // update user with number of rows with customer details
        System.out.println("**" + noOfCustomers + " rows read.\n\n");
        customerList = new CUSTOMER[noOfCustomers];
        for (int i = 0; i < noOfCustomers; i++)
        {
            customerList[i] = new CUSTOMER();
            customerList[i].readCustomerDetails(dataRows[i]);
        }
    }

    public void showyear()
    {
        System.out.println(Year.now().getValue() + "\n");
    }

    public void countmethod()
    {
        System.out.print("A report of which method was used the most : ");
        // start the count
        int countS = 0;
        int countW = 0;
        // loop for each item : member
        for (int i = 0; i < noOfCustomers; i++)
        {
            // decide if current item: member matches target: bmi
            if (customerList[i].getmethod() == 'S')
            {
                // add 1 to count: for OK bmi
                countS = countS + 1;
            }
            else if (customerList[i].getmethod() == 'W')
            {
                countW = countW + 1;
            }
        }

        if (countS > countW)
        {
            System.out.println("\n Most customers bought tickets in the school");
        }
        else if (countS < countW)
        {
            System.out.println("\n Most customers bought tickets on the website");
        }
        else
        {
            System.out.println("\n Same number of customers bought tickets in school and on the website");
        }
        // A blank line to separate this report from others.
        System.out.println();
    }

    public void counttotal()
    {
        // start count
        float total = 0.00f;
        for (int i = 0; i < noOfCustomers; i++)
        {
            if (customerList[i].getnight() == 'F')
            {
                total = total + customerList[i].getnumoftickets()*10;
            }
            else 
            {
                total = total + customerList[i].getnumoftickets()*5;
            }
        }
        System.out.print("total money raised for charity : £" + total );
    }

    public void savefridaynightfile() throws IOException
    {
        String fileContent = "";
        int count = 0;
        for (int i = 0; i < noOfCustomers; i++)
        {
            if(customerList[i].getnight() == 'F' )
            {
                count = count +1;
                if (count>1)
                {
                    fileContent = fileContent.concat("\n");
                }
                fileContent = fileContent.concat(customerList[i].writeDetails());

            }
            System.out.println("** Preparing to write Friday night file.");
            resultFile.writeCSVtable(fileContent);
            System.out.println("** File written and closed.");
        }
    }
}