import BasicClass.Sale;
import BasicClass.SaleList;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class Test {
    private static SaleList saleList;
    private static FileOutputStream fos;
    private static BufferedOutputStream bos;
    private static DataOutputStream dos;

    public static void main(String[] args) throws Exception{
        saleList = new SaleList();

        fos = new FileOutputStream("Sales.txt");
        bos = new BufferedOutputStream(fos);
        dos = new DataOutputStream(bos);

        dos.writeBytes("Sale ID     Book ID     Seller      Number\n");
        for(Sale sale : saleList){
            String saleId = sale.getSaleId();
            String bookId = sale.getBookId();
            String seller = sale.getSeller();
            int saleNum = sale.getSaleNum();
            dos.writeBytes(saleId + "   " + bookId + "     " + seller  + "     " + String.valueOf(saleNum) + "\n");
            System.out.println("Outputting...");
        }
        dos.close();
        System.out.println("Output succeed.");
    }
}