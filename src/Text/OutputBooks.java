package Text;

import BasicClass.Book;
import BasicClass.BookList;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class OutputBooks {
    private static BookList bookList;
    private static FileOutputStream fos;
    private static BufferedOutputStream bos;
    private static DataOutputStream dos;

    public OutputBooks() throws Exception{
        bookList = new BookList();

        fos = new FileOutputStream("Books.txt");
        bos = new BufferedOutputStream(fos);
        dos = new DataOutputStream(bos);

        dos.writeBytes("Book ID     Name    Price\n");
        for(Book book : bookList){
            String bookId = book.getBookId();
            String bookName = book.getBookName();
            float bookPrice = book.getBookPrice();
            dos.writeBytes(bookId + "   " + bookName + "    " + String.valueOf(bookPrice) + "\n");
        }
        dos.close();
        bos.close();
        fos.close();
        System.out.println("Output success.");
    }
}
