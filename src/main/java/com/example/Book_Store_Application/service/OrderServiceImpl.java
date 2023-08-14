package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.OrderDTO;
import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.exception.UserCustomException;
import com.example.Book_Store_Application.model.Book;
import com.example.Book_Store_Application.model.Cart;
import com.example.Book_Store_Application.model.Order;
import com.example.Book_Store_Application.model.User;
import com.example.Book_Store_Application.repo.BookRepo;
import com.example.Book_Store_Application.repo.CartRepo;
import com.example.Book_Store_Application.repo.OrderRepo;
import com.example.Book_Store_Application.repo.UserRepo;
import com.example.Book_Store_Application.utility.EmailService;
import com.example.Book_Store_Application.utility.Jwt_Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService_Interface{
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private Jwt_Token jwt_token;
    @Autowired
    private EmailService emailService;


    @Override
    public ResponseDTO placeOrder(String token, OrderDTO orderDTO) {
        long userId = jwt_token.decodeToken(token);

        // Getting user data from the user repository based on the user ID
        User userData = userRepo.findById(userId).orElseThrow(() -> new UserCustomException("User ID :: " + userId + " not Registered.."));

        //  Getting the list of cart items for the user
        List<Cart> cartDataList = cartRepo.findAll().stream().filter(data-> data.getUser().getUserId() == userId).toList();

        if(!cartDataList.isEmpty()){
            // Getting a list of books from the cart items
            List<Book> bookList = cartRepo.findAll().stream().filter(data-> data.getUser().getUserId() == userId).map(Cart::getBook).toList();
            List<Order> orderDataList = new ArrayList<>();     // to return all orders if multiple orders are present
            double price = 0;
            Order orderData = null;
            for (int i = 0; i < bookList.size(); i++) {
                price = price + bookList.get(i).getBookQuantity() * bookList.get(i).getBookPrice();
                orderData = new Order(orderDTO, userData,cartDataList.get(i).getBook(), cartDataList.get(i).getBook().getBookPrice()*cartDataList.get(i).getQuantity(), cartDataList.get(i).getQuantity());
                orderRepo.save(orderData);
                // Remove the cart item from the cart repository
                cartRepo.deleteById(cartDataList.get(i).getCartId());
               // System.out.println("Cart ID Cleared :: " + cartDataList.get(i).getCartId());
                orderDataList.add(orderData);
                emailService.sendEmail(userData.getEmail(), "Order Details", "Hello " + userData.getFirstName() + ","
                        + "\n Your order has been placed successfully..\n\n\n Your Order details ::  \n\n Order Date :: " + orderDataList.get(i).getOrderDate() +
                        "\n Order Recipient :: "+ orderDataList.get(i).getUserId().getFirstName()+" "+orderDataList.get(i).getUserId().getLastName() +
                        "\n Recipient Address :: "+ orderDataList.get(i).getAddress() +
                        "\n Book Name :: "+ orderDataList.get(i).getBookId().getBookName() +", by "+ orderDataList.get(i).getBookId().getBookAuthor() +
                        "\n Book Description :: "+ orderDataList.get(i).getBookId().getBookDescription() +
                        "\n Book Price :: Rs "+ orderDataList.get(i).getBookId().getBookPrice() +
                        "\n Order Quantity :: "+orderDataList.get(i).getQuantity()+
                        "\n Total Order Price :: Rs"+ orderDataList.get(i).getTotalPrice() +
                        "\n\n Regards,\n\n People's Mart");
            }
            return new ResponseDTO("Order Placed SuccessFully..\n\n Total order Price :: " + price, orderDataList);
        }
        return new ResponseDTO("Cart is Empty..Add Books to place Order.. ",null);
    }




    @Override
    public String cancelOrder(String token, long orderId) {
        long userId = jwt_token.decodeToken(token);
        Order orderData = orderRepo.getOrderById(orderId);
        orderData.setCancel(true);
        orderRepo.save(orderData);
        emailService.sendEmail(orderData.getUserId().getEmail(), "Order Details", "Hello " + orderData.getUserId().getFirstName() + ","
                + "\n\n Your order is cancelled successfully..\n\n Your cancelled Order details :: " + orderData + "\n\n Regards,\n\n People's Mart");
        return "Order Cancelled";
    }

    @Override
    public ResponseDTO getAllOrders() {
        List<Order> orderDataList = orderRepo.findAll();
        List<Order> orderNotCancelled = new ArrayList<>();
        for(int i=0;i<orderDataList.size();i++){
            if(!orderDataList.get(i).isCancel()){
                orderNotCancelled.add(orderDataList.get(i));
            }
        }
        return new ResponseDTO("List of All Orders..", orderNotCancelled);
    }

    @Override
    public ResponseDTO getAllOrderForUser(String token) {
        long userId = jwt_token.decodeToken(token);
        List<Order> orderDataList = orderRepo.findAll().stream().filter(data-> data.getUserId().getUserId() == userId).collect(Collectors.toList());
        return new ResponseDTO("List of Orders By UserID :: " + userId, orderDataList);

    }
}
