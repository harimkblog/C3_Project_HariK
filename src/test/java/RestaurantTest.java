import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    private void setRestaurant() {
        LocalTime _5 =  LocalTime.of(5, 0, 0, 0);
        LocalTime _23 = LocalTime.of(23, 0, 0, 0);
        restaurant = new Restaurant("sona", "NYC", _5, _23);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        setRestaurant();
        LocalTime _6 =  LocalTime.of(6, 0, 0, 0);
        Restaurant r = Mockito.spy(restaurant);
        when(r.getCurrentTime()).thenReturn(_6);
        assertTrue(r.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        setRestaurant();
        LocalTime _4 =  LocalTime.of(4, 0, 0, 0);
        Restaurant r = Mockito.spy(restaurant);
        when(r.getCurrentTime()).thenReturn(_4);
        assertFalse(r.isRestaurantOpen());
    }

    @Test
    public void calculate_order_cost_test(){
        //WRITE UNIT TEST CASE HERE
        setRestaurant();
        restaurant.addToMenu("i1", 100);
        restaurant.addToMenu("i2", 200);
        restaurant.addToMenu("i3", 300);
        List<String> l1 = Arrays.asList("i1", "i2");
        List<String> l2 = Arrays.asList("i1", "i3");
        List<String> l3 = new ArrayList<>();
        List<String> l4 = Arrays.asList("i1", "i4");
        int r1 = restaurant.calcOrderCost(l1);
        int r2 = restaurant.calcOrderCost(l2);
        int r3 = restaurant.calcOrderCost(l3);
        assertEquals(300, r1);
        assertEquals(400, r2);
        assertEquals(0, r3);
        assertThrows(RuntimeException.class, ()->{restaurant.calcOrderCost(l4);});
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private void setRestaurantAddMenu() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        setRestaurantAddMenu();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        setRestaurantAddMenu();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        setRestaurantAddMenu();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}