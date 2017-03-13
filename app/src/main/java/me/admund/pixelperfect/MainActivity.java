package me.admund.pixelperfect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.coffees_list_view) ListView coffeeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        coffeeListView.setAdapter(new CoffeeAdapter());
    }

    class CoffeeAdapter extends BaseAdapter {

        public String[] coffeeList = {"Extra Espresso", "Cafe Latte", "Chocolate", "Moka", "Caffè Americano", "Cappuccino", "Macchiato"};

        @Override
        public int getCount() {
            return coffeeList.length;
        }

        @Override
        public Object getItem(int position) {
            return coffeeList[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_list_item, parent, false);
            }

            TextView coffeeNameTextView = ButterKnife.findById(convertView, R.id.coffee_name_text);
            coffeeNameTextView.setText(coffeeList[position]);

            TextView orderTextView = ButterKnife.findById(convertView, R.id.order_text);
            boolean canOrder = position % 2 == 0;
            orderTextView.setText(canOrder ? "Order for free!" : "You need 40 points");
            orderTextView.setTextColor(canOrder ? getColor(R.color.order_green) : getColor(R.color.order_grey));

            return convertView;
        }
    }
}
