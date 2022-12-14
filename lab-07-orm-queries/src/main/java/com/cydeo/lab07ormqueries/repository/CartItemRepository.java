package com.cydeo.lab07ormqueries.repository;


import com.cydeo.lab07ormqueries.Entity.CartItem;
import com.cydeo.lab07ormqueries.enums.CartState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    //Write a derived query to get count cart items
    Integer countAllBy();
    //or
    Integer countCartItemBy();
    //Write a derived query to get cart items for specific cart state

    List<CartItem> findAllByCartCartState(CartState state);

    //Write a native query to get cart items for specific cart state and product name
    @Query(value =  "SELECT * FROM cart_item ci  JOIN cart c  ON ci.cart_id=c.id JOIN product p ON ci.product_id=p.id" +
            " where c.cart_state=?1 AND p.name=?2",nativeQuery = true)
    List<CartItem> retrieveCartItemsByCartStateAndProductName(@Param("cart_state") String cart_state, @Param("name") String name);
    //Write a native query to get cart items for specific cart state and without discount
    @Query(value="select * from cart_item ci join cart c on ci.cart_id=c.id where c.cart_state=?1 AND c.discount_id is null",nativeQuery = true)
    List<CartItem> retrieveCartItemsByCartStateWithoutDiscount(@Param("cart_state") String cart_state);

    //Write a native query to get cart items for specific cart state and with specific Discount type

    @Query(value =  "SELECT * FROM cart_item ci  JOIN cart c  ON ci.cart_id=c.id JOIN discount d ON c.discount_id=d.id" +
            " where c.cart_state=?1 AND d.discountType=?2",nativeQuery = true)
    //enum usually placed here as a string, in this case discountType it is
    List<CartItem> retrieveCartItemsByCartStateAndDiscountType(@Param("cart_state") String cartState,@Param("discount_type") String discountType);

}
