package com.a1141705068qq.main.wiget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.adapter.PopupDishAdapter;
import com.a1141705068qq.main.imp.ShopCartImp;
import com.a1141705068qq.main.model.Dish;
import com.a1141705068qq.main.model.ShopCart;


/**
 * Created by cheng on 16-12-22.
 */
public class ShopCartDialog extends Dialog implements View.OnClickListener,ShopCartImp {

    private LinearLayout linearLayout,bottomLayout,clearLayout;
    private FrameLayout shopingcartLayout;
    private ShopCart shopCart;
    private TextView totalPriceTextView;
    private TextView totalPriceNumTextView;
    private Button pay_right_away;
    private RecyclerView recyclerView;
    private PopupDishAdapter dishAdapter;
    private ShopCartDialogImp shopCartDialogImp;

    public int num_kind;//选的菜的种类数
    private static String[] __name = {"a", "b", "c", "d", "e"};
    private static String[] __price = {"a", "b", "c", "d", "e"};
    private static String[] __account = {"a", "b", "c", "d", "e"};

    public ShopCartDialog(Context context, ShopCart shopCart, int themeResId) {
        super(context,themeResId);
        this.shopCart = shopCart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_popupview);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        clearLayout = (LinearLayout)findViewById(R.id.clear_layout);
        shopingcartLayout = (FrameLayout)findViewById(R.id.shopping_cart_layout);
        bottomLayout = (LinearLayout)findViewById(R.id.shopping_cart_bottom);
        totalPriceTextView = (TextView)findViewById(R.id.shopping_cart_total_tv);
        totalPriceNumTextView = (TextView)findViewById(R.id.shopping_cart_total_num);
        pay_right_away = (Button)findViewById(R.id.pay_right_away);
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        shopingcartLayout.setOnClickListener(this);
        bottomLayout.setOnClickListener(this);
        clearLayout.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dishAdapter = new PopupDishAdapter(getContext(),shopCart);
        recyclerView.setAdapter(dishAdapter);
        dishAdapter.setShopCartImp(this);
        showTotalPrice();
        num_kind = dishAdapter.getItemCount();

        //获取数据
        /*for(int i = 0;i<num_kind;i++){
            //View view = recyclerView.getChildAt(0);
            View view = recyclerView.getLayoutManager().findViewByPosition(i);
            TextView _name = (TextView) view.findViewById(R.id.right_dish_name);
            TextView _price = (TextView) view.findViewById(R.id.right_dish_price);
            TextView _account = (TextView) view.findViewById(R.id.right_dish_account);
            __name[i] = _name.getText().toString();
            __price[i] = _price.getText().toString();
            __account[i] = _account.getText().toString();
        }*/
    }

    public String[] get__name(){
        return __name;
    }

    public String[] get__price(){
        return __price;
    }

    public String[] get__account(){
        return __account;
    }

    public int getNum_kind(){
        return num_kind;
    }

    @Override
    public void show() {
        super.show();
        animationShow(1000);
    }

    @Override
    public void dismiss() {
        animationHide(1000);
    }

    private void showTotalPrice(){
        if(shopCart!=null && shopCart.getShoppingTotalPrice()>0){
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("￥ "+shopCart.getShoppingTotalPrice());
            totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText(""+shopCart.getShoppingAccount());

        }else {
            totalPriceTextView.setVisibility(View.GONE);
            totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    private void animationShow(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linearLayout, "translationY",1000, 0).setDuration(mDuration)
        );
        animatorSet.start();
    }

    private void animationHide(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linearLayout, "translationY",0,1000).setDuration(mDuration)
        );
        animatorSet.start();

        if(shopCartDialogImp!=null){
            shopCartDialogImp.dialogDismiss();
        }

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ShopCartDialog.super.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.shopping_cart_bottom:
            case R.id.shopping_cart_layout:
                this.dismiss();
                break;
            case R.id.clear_layout:
                clear();
                break;
        }
    }

    @Override
    public void add(View view, int postion) {
        showTotalPrice();
    }

    @Override
    public void remove(View view, int postion) {
        showTotalPrice();
        if(shopCart.getShoppingAccount()==0){
            this.dismiss();
        }
    }

    public ShopCartDialogImp getShopCartDialogImp() {
        return shopCartDialogImp;
    }

    public void setShopCartDialogImp(ShopCartDialogImp shopCartDialogImp) {
        this.shopCartDialogImp = shopCartDialogImp;
    }

    public interface ShopCartDialogImp{
        public void dialogDismiss();
    }

    public void clear(){
        shopCart.clear();
        showTotalPrice();
        if(shopCart.getShoppingAccount()==0){
            this.dismiss();
        }
    }
}
