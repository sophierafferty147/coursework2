
public class CUSTOMER
{
    // instance variables - replace the example below with your own
    private String customerid;
    private char ticketid;
    private int numoftickets;
    private char method;

    public CUSTOMER()
    {
        // initialise instance variables
        customerid = "";
        ticketid = 0;
        numoftickets = 0;
        method = 0;
    }

    public void readCustomerDetails(String dataItems)
    {
        String[] rowItems = dataItems.split(",");
        customerid = rowItems[0];
        ticketid = (rowItems[1]).charAt(0);
        numoftickets = Integer.parseInt(rowItems[2]);
        method = (rowItems[3]).charAt(0);
    }

    public String writeDetails()
    {
        String customerData = "";
        customerData = customerData.concat(customerid);
        customerData = customerData.concat(",");
        customerData = customerData;
        customerData = customerData.concat(",");
        customerData = customerData.concat(Integer.toString(numoftickets));
    }
}