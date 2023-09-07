package cl.felipe.obrestdatajpa.service;

import cl.felipe.obrestdatajpa.entities.Book;

public class BookPriceCalculator {
    public double calculatorPrice(Book book){
        double price = book.getPrecio();
        if (book.getPaginas()>300){
            price +=5;
        }
        //envio
        price+=2.99;
        return price;
    }
}
