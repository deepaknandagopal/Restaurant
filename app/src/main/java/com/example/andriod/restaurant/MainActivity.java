package com.example.andriod.restaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.TestMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static ArrayList<Menu> menu = new ArrayList<Menu>();
    private ArrayList<OrderedItem> orderedItems = new ArrayList<OrderedItem>();
    private  HashMap<String,Integer> subMenuPrice = new HashMap<String,Integer>();
    private LinearLayout mLinearLayout;
    private  TableLayout mUpperTable;
    private TableLayout mLowerTable;
    private int displayWidth;
    private int displayHeight;
    private ListView mListView;
    private OrderedItemAdapter orderedItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the layout instance
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mUpperTable = (TableLayout) findViewById(R.id.tableLayout1);
        mLowerTable = (TableLayout) findViewById(R.id.tableLayout2);

        //Display Width and Height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayHeight = displayMetrics.heightPixels;
        displayWidth = displayMetrics.widthPixels;
        String disWidHeight = displayWidth + " "+ displayHeight;
        Log.v(TAG,disWidHeight);


        //Adding the menu details
        menu.add(new Menu("drinks",new HashMap<String,Integer>() {
                {
                    put("coke",6);
                    put("pepsi",7);
                    put("fanta",5);
                }
            }
        ));
        menu.add(new Menu("food"
                ,new HashMap<String,Integer>() {
            {
                put("friedrice",15);
                put("noodles",20);
                put("french fires",5);
            }
        }
        ));
        menu.add(new Menu("wine"
                ,new HashMap<String,Integer>() {
            {
                put("RedWine",10);
                put("Bud",6);
                put( "whiteWine",20);
            }
        }
        ));
        menu.add(new Menu("apero"
                ,new HashMap<String,Integer>() {
            {
                put("Cheese",3);
                put("Cake",4);
                put("BlackForest",10);
            }
        }
        ));
        menu.add(new Menu("alcohol"
                ,new HashMap<String,Integer>() {
            {
                put("Beer",6);
                put("Gin",23);
                put("Tequila",22);
                put("Vodka",24);
                put("Cava",19);
                put("Whisky",25);
            }
        }
        ));


        //Inflating the menu Items
        int halfScreenWidth = displayWidth / 2;
        int noOfButtonsInRow = 0;
        for(int i=0;i<menu.size();i++)
        {
            int buttonsInRow = 0;
            int numRows = mUpperTable.getChildCount();
            TableRow row = null;
            int viewWidth = getViewWidth(mLinearLayout);

            if(numRows == 1 && viewWidth+100>=halfScreenWidth)
            {
                row = (TableRow) mUpperTable.getChildAt(numRows - 1);
                noOfButtonsInRow = row.getChildCount();
            }
            if (numRows > 0) {
                row = (TableRow) mUpperTable.getChildAt(numRows - 1);
                buttonsInRow = row.getChildCount();

            }
            if (numRows == 0 || noOfButtonsInRow==buttonsInRow) {
                row = new TableRow(this);
                mUpperTable.addView(row);
            }
            Log.v(TAG,"Width:"+viewWidth+"halfscreen Width"+halfScreenWidth);
            Button b = new Button(this);
            b.setText(menu.get(i).getMenu());
            b.setTextSize(10.0f);
            //b.setBackgroundColor(Color.rgb(0xfd, 0xde, 0xa5)); //fddea5
            //b.setFocusable(true);
            b.setTextColor(Color.WHITE);
            b.setId(i);
            b.setOnClickListener(btnclick);
            b.setTag(menu.get(i).getMenu());
            row.addView(b, 170, 150);
        }
        int widths = getViewWidth(mLinearLayout);
        Log.v("act",Integer.toString(widths));


        orderedItemAdapter = new OrderedItemAdapter(this, orderedItems);

        ListView listView = (ListView) findViewById(R.id.lv);
        listView.setAdapter(orderedItemAdapter);
        inflateSubmenu(0,"drinks");
    }


    View.OnClickListener btnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String itemTag = view.getTag().toString();
            if(itemTag== null)
            {
                return;
            }
            mLowerTable.removeAllViews();
            for(int i=0;i<menu.size();i++)
            {
                if(itemTag.equals(menu.get(i).getMenu()))
                {
                    inflateSubmenu(i, menu.get(i).getMenu());
                }
            }

        }
    };

    //To Inflate the Submenu items after the button pressed
    public void inflateSubmenu(int index,String menuItem)
    {
        int halfScreenWidth = displayWidth / 2;
        int noOfButtonsInRow = 0;
        subMenuPrice = menu.get(index).getSubMenuPrice();
        ArrayList<String> keyList = new ArrayList<String>(subMenuPrice.keySet());

        for(int i=0;i<keyList.size();i++)
        {
            int buttonsInRow = 0;
            int numRows = mLowerTable.getChildCount();
            TableRow row = null;
            int viewWidth = getViewWidth(mLowerTable);

            if(numRows == 1 && viewWidth+100>=halfScreenWidth)
            {
                row = (TableRow) mLowerTable.getChildAt(numRows - 1);
                noOfButtonsInRow = row.getChildCount();
            }
            if (numRows > 0) {
                row = (TableRow) mLowerTable.getChildAt(numRows - 1);
                buttonsInRow = row.getChildCount();

            }
            if (numRows == 0 || noOfButtonsInRow==buttonsInRow) {
                row = new TableRow(this);
                mLowerTable.addView(row);
            }
            Log.v(TAG,"Width:"+viewWidth+"halfscreen Width"+halfScreenWidth);
            Button b = new Button(this);
            b.setText(keyList.get(i));
            b.setId(i);
            b.setTag(keyList.get(i));
            String key = keyList.get(i);
            Log.v(TAG,subMenuPrice.get(key).toString());
            b.setTextSize(12.0f);
            b.setOnClickListener(subbtnclick);
            row.addView(b, 170, 150);
        }
    }

    View.OnClickListener subbtnclick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String itemTag = view.getTag().toString();
            if(itemTag== null) {
                return;
            }

            String mprice = subMenuPrice.get(itemTag).toString();

            String mQuantity = " ";

            boolean updatecheck = false;

            for(int i=0;i<orderedItems.size();i++)
            {
                if(orderedItems.get(i).getItemName() == itemTag)
                {
                    mQuantity = orderedItems.get(i).getQuantity();
                    int q=2;
                    if(!mQuantity.equals(" ")) {
                        q= Integer.parseInt(mQuantity);
                        q = q + 1;
                    }
                    orderedItems.get(i).setQuantity(Integer.toString(q));
                    updatecheck = true;
                }
            }

            if(!updatecheck)
            {
                orderedItems.add(new OrderedItem(itemTag,mprice," "));
            }

            int total = 0;
            for(int i=0;i<orderedItems.size();i++)
            {
                String price = orderedItems.get(i).getPrice();
                String quantity = orderedItems.get(i).getQuantity();
                int p =Integer.parseInt(price);
                if(!quantity.equals(" "))
                {
                    int q =Integer.parseInt(quantity);
                    p = p*q;
                }
                total = total + p;
            }
            TextView mTotalPrice = (TextView) findViewById(R.id.totalPrice);
            mTotalPrice.setText("INR : "+total);
            orderedItemAdapter.notifyDataSetChanged();
        }
    };


    //To get the measured width of the View after the child elements are inflated
    public static int getViewWidth(View view) {
        WindowManager wm =
                (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int deviceWidth;

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
            Point size = new Point();
            display.getSize(size);
            deviceWidth = size.x;
        } else {
            deviceWidth = display.getWidth();
        }
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
        return view.getMeasuredWidth();
    }
}
