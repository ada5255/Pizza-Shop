public class Main {
    private int cCount; //will be initialized with 0

    //Add scanner as private static
    private static Scanner scnr =  new Scanner(System.in);

    public static void main(String[] args) {
        Main main = new Main();
        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();
        Order order1 = new Order(1);
        Transaction trans1 = new Transaction(1, order1, PaymentType.cash);
        Menu menu1 = new Menu(1, "Plain");
        Menu menu2 = new Menu(2, "Meat");
        Menu menu3 = new Menu(3, "Extra");
        Menu menu4 = new Menu(4, "Veg");
        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);
        oList.add(order1);
        tList.add(trans1);
        userAction = getAction(PROMPT_ACTION);
        while (userAction != EXIT_CODE) {
            switch (userAction) {
                case CUST_CODE:
                    cList.add(main.addCustomer());
                    break;
                case CUST_PRNT:
                    Customer.printCustomer(cList);
                    break;
                case MENU_CODE:
                    Menu.listMenu(mList);
                    break;
                case ORDE_CODE:
                    System.out.print("Enter customer ID : " );
                    int cid = scnr.nextInt();
                    ArrayList<Menu> cMenu = selectMenu(mList);
                    Order.addOrders(order1, cList.get(cid), cMenu);
                    break;
                case TRAN_CODE:
                    Transaction.listTransactions(tList);
                    break;
            }
            userAction = getAction(PROMPT_ACTION);
        }
    }

    public static ArrayList<Menu> selectMenu(ArrayList<Menu> menus){
        System.out.println("Select menu (by ID): (Press 0 to finalize)");
        for (Menu menu : menus)
            System.out.println("'" + menu.getmenuId() + "' for " + menu.getmenuItem());
        int flag;
        ArrayList<Menu> menus1 = new ArrayList<>();
        while(true) {
            flag = scnr.nextInt();
            if(flag == 0)
                break;
            menus1.add(menus.get(flag-1));
        }
        return menus1;
    }

    public static char getAction(String prompt) {
        //removed scanner object from here
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer() {
        Customer cust = new Customer(cCount++);
        //removed scanner object from here
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }
}
