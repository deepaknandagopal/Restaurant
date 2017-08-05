package com.example.andriod.restaurant;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Deepak on 7/14/2017.
 */

public class Menu {

        private String menu;
        private HashMap<String, Integer> subMenuPrice = new HashMap<>();

        public Menu() {
        }

        public Menu(String menu,HashMap<String,Integer> subMenuPrice) {
            this.menu = menu;
            this.subMenuPrice = subMenuPrice;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public HashMap<String,Integer> getSubMenuPrice() {
            return subMenuPrice;
        }

        public void setSubMenuPrice(HashMap<String,Integer> subMenuPrice) {
            this.subMenuPrice = subMenuPrice;
        }

}
