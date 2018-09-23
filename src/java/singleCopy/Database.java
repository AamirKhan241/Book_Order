package singleCopy;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Database DB=new Database();
    private Connection conn;
    
    private Database(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","book","22647");
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return conn;
    }
    public static Database getInstance(){
        return DB;
    }
    /*
        Tables creation commands
create table profiles(user_id int primary key,first_name varchar(50),last_name varchar(50),roles varchar(15),email varchar(50) unique not null,
password varchar(20) not null,contact_no varchar(15) not null,address varchar(500),status varchar(20) default 'ACTIVE');

create table Category(id int not null unique,Category_Name varchar(50) primary key,description varchar(200),image varchar(500));

create table Publisher(Publisher_id int not null unique,Publisher_Name varchar(50) primary key,Supplier_id int references profiles(user_id) unique);

create table Supplier_details(Serial_No int primary key,Supplier_Id int references profiles(user_id) ,email varchar(50) not null unique,
publisher_id int references publisher(Publisher_id));

create table books (serial_no int unique not null,name varchar(100) not null,publisher varchar(50) references publisher(publisher_name),   
Supplier int references profiles(user_id),Category varchar(50) references category(category_name),Store_Location varchar(100),
Copies varchar(5) default '0' not null,EOQ varchar(5) default '1',Edition varchar(4) not null,ISBN varchar(13) primary key,
Price varchar(5) not null,Author varchar(20) not null,image varchar(500) not null);

create table sales(sales_id int primary key,customer_id int references profiles(user_id),sale_date date default sysdate,
Invoice_No int references sales_report(invoice_no) , Amount varchar(10) not null,Book_ISBN varchar(13) references books(ISBN),
Copies varchar(5) not null);

create table sales_Report(Invoice_No int primary key,customer_id int references profiles(user_id),Amount_to_Pay varchar(10) not null,
reciever_Name varchar(70) not null,reciever_Address varchar(500) not null,reciever_Contact varchar(12) not null,Transaction_Mode varchar(10) not null,
amount_paid varchar(10) not null,Balance varchar(10) not null,Description varchar(100) ,status varchar(30) default 'Order Placed' not null );

create table cart(serial_no int primary key,customer_id int references profiles(user_id),Book_id varchar(13) references books(isbn),
quantity int not null);


create table profit (Transaction_No int primary key, sale_by int references profiles(user_id),sale_from int references profiles(user_id),
Profit_Amount varchar(10),transaction_date date default sysdate);


create table userquery (serial_no int primary key, name varchar(30), email varchar(50), subject varchar(50), message varchar(500), reply varchar(500) default ‘none’  );


create table purchase_report (purchase_note_no int primary key, amount_to_pay varchar (10), book_isbn varchar(13),	copies varchar (5), price varchar(5), 
amount_paid varchar(10), balance varchar(10), supplier_id int references profiles(user_id));


create table purchase (purchase_id int primary key, purchase_note_no int references purchase_report(purchase_note_no), supplier_id int references profiles(user_id), 
amount varchar(10), purchase_date date default sysdate);
    */
}
