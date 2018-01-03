package com.a1141705068qq.main;

/**
 * Created by Administrator on 2017/12/10.
 */

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1141705068qq.class_one.R;
import com.a1141705068qq.main.adapter.LeftMenuAdapter;
import com.a1141705068qq.main.adapter.RightDishAdapter;
import com.a1141705068qq.main.imp.ShopCartImp;
import com.a1141705068qq.main.model.Dish;
import com.a1141705068qq.main.model.DishMenu;
import com.a1141705068qq.main.model.ShopCart;
import com.a1141705068qq.main.util.HttpUtil;
import com.a1141705068qq.main.util.Utility;
import com.a1141705068qq.main.wiget.FakeAddImageView;
import com.a1141705068qq.main.wiget.PointFTypeEvaluator;
import com.a1141705068qq.main.wiget.ShopCartDialog;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Shop_Activity extends Activity implements LeftMenuAdapter.onItemSelectedListener,ShopCartImp,ShopCartDialog.ShopCartDialogImp{
    private final static String TAG = "Shop_Activity";
    private RecyclerView leftMenu;//左侧菜单栏
    private RecyclerView rightMenu;//右侧菜单栏
    private TextView headerView;
    private LinearLayout headerLayout;//右侧菜单栏最上面的菜单
    private LinearLayout bottomLayout;
    private DishMenu headMenu;
    private LeftMenuAdapter leftAdapter;
    private RightDishAdapter rightAdapter;
    private ArrayList<DishMenu> dishMenuList;//数据源
    private boolean leftClickType = false;//左侧菜单点击引发的右侧联动
    private ShopCart shopCart;
    //    private FakeAddImageView fakeAddImageView;
    private ImageView shoppingCartView;
    private FrameLayout shopingCartLayout;
    private TextView totalPriceTextView;
    private TextView totalPriceNumTextView;
    private Button pay_right_away;
    private RelativeLayout mainLayout;
    private int res_id;
    private List<com.a1141705068qq.main.gson.Dish> mdishes;

    public double jiage;
    public int shuliang;
    public String [] name__;
    public String [] price__;
    public String [] account__;
    private Map<Dish,Integer> shoppingSinglepass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mdishes=new ArrayList<>();
        Intent intent=getIntent();
        res_id=intent.getIntExtra("res_id",1);
        sendRequest(res_id);
    }


    private void initView(){
        mainLayout = (RelativeLayout)findViewById(R.id.main_layout);
        leftMenu = (RecyclerView)findViewById(R.id.left_menu);
        rightMenu = (RecyclerView)findViewById(R.id.right_menu);
        headerView = (TextView)findViewById(R.id.right_menu_tv);
        headerLayout = (LinearLayout)findViewById(R.id.right_menu_item);
//        fakeAddImageView = (FakeAddImageView)findViewById(R.id.right_dish_fake_add);
        bottomLayout = (LinearLayout)findViewById(R.id.shopping_cart_bottom);
        shoppingCartView = (ImageView) findViewById(R.id.shopping_cart);
        shopingCartLayout = (FrameLayout) findViewById(R.id.shopping_cart_layout);
        totalPriceTextView = (TextView)findViewById(R.id.shopping_cart_total_tv);
        totalPriceNumTextView = (TextView)findViewById(R.id.shopping_cart_total_num);
        pay_right_away = (Button)findViewById(R.id.pay_right_away);
        leftMenu.setLayoutManager(new LinearLayoutManager(this));
        rightMenu.setLayoutManager(new LinearLayoutManager(this));

        rightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if( recyclerView.canScrollVertically(1)==false) {//无法下滑
                    showHeadView();
                    return;
                }
                View underView = null;
                if(dy>0)
                    underView = rightMenu.findChildViewUnder(headerLayout.getX(),headerLayout.getMeasuredHeight()+1);
                else
                    underView = rightMenu.findChildViewUnder(headerLayout.getX(),0);
                if(underView!=null && underView.getContentDescription()!=null ){
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    DishMenu menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if(leftClickType || !menu.getMenuName().equals(headMenu.getMenuName())) {
                        if (dy> 0 && headerLayout.getTranslationY()<=1 && headerLayout.getTranslationY()>= -1 * headerLayout.getMeasuredHeight()*4/5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        }
                        else if(dy<0 && headerLayout.getTranslationY()<=0 && !leftClickType) {
                            headerView.setText(menu.getMenuName());
                            int dealtY = underView.getBottom() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        }
                        else{
                            headerLayout.setTranslationY(0);
                            headMenu = menu;
                            headerView.setText(headMenu.getMenuName());
                            for (int i = 0; i < dishMenuList.size(); i++) {
                                if (dishMenuList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if(leftClickType)leftClickType=false;
                            Log.e(TAG, "onScrolled: "+menu.getMenuName() );
                        }
                    }
                }
            }
        });

        shopingCartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCart(view);
            }
        });

        pay_right_away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shopCart!=null && shopCart.getShoppingAccount()>0){
                    Intent payintent = new Intent(Shop_Activity.this,PayActivity.class);
                    payintent.putExtra("send_adress","这里");
                    payintent.putExtra("num_of_food",shuliang);//红圈上的数字
                    payintent.putExtra("price_food",jiage);//黑栏目上的总价格
                    payintent.putExtra("name_restaurant",getnameof_restaurant());
                    payintent.putExtra("singlemessage",(Serializable)shoppingSinglepass);
                    startActivity(payintent);
                    //finish();
                }
                else{Toast.makeText(Shop_Activity.this,"您未点餐",Toast.LENGTH_SHORT).show();}
            }
        });
    }

    public void TouchButton(){
        pay_right_away.performClick();
    }

    private String getnameof_restaurant(){
        Intent intent = getIntent();
        String data_name_restaurant = intent.getStringExtra("name_restaurant");
        return data_name_restaurant;
    }

    private void initData(List<com.a1141705068qq.main.gson.Dish> dishes){
        shopCart = new ShopCart();
        dishMenuList = new ArrayList<>();
        ArrayList<Dish> dishs1 = new ArrayList<>();
        for(com.a1141705068qq.main.gson.Dish dish:dishes){
            dishs1.add(new Dish(dish.getDis_name(),dish.getDis_price(),10,dish.getDis_picture()));
        }
        DishMenu breakfast = new DishMenu("分类一",dishs1);

        ArrayList<Dish> dishs2 = new ArrayList<>();
        for(com.a1141705068qq.main.gson.Dish dish:dishes){
            dishs2.add(new Dish(dish.getDis_name(),dish.getDis_price(),10,dish.getDis_picture()));
        }
        DishMenu launch = new DishMenu("分类二",dishs2);

        ArrayList<Dish> dishs3 = new ArrayList<>();
        for(com.a1141705068qq.main.gson.Dish dish:dishes){
            dishs3.add(new Dish(dish.getDis_name(),dish.getDis_price(),10,dish.getDis_picture()));
        }
        DishMenu evening = new DishMenu("分类三",dishs3);

        dishMenuList.add(breakfast);
        dishMenuList.add(launch);
        dishMenuList.add(evening);
    }

    private void initAdapter(){
        leftAdapter = new LeftMenuAdapter(this,dishMenuList);
        rightAdapter = new RightDishAdapter(this,dishMenuList,shopCart);
        rightMenu.setAdapter(rightAdapter);
        leftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(this);
        rightAdapter.setShopCartImp(this);
        initHeadView();
    }

    private void initHeadView(){
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        headerLayout.setContentDescription("0");
        headerView.setText(headMenu.getMenuName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","___leftAdapter.removeItemSelectedListener___");
        leftAdapter.removeItemSelectedListener(this);
    }

    private void showHeadView(){
        headerLayout.setTranslationY(0);
        View underView = rightMenu.findChildViewUnder(headerView.getX(),0);
        if(underView!=null && underView.getContentDescription()!=null){
            int position = Integer.parseInt(underView.getContentDescription().toString());
            DishMenu menu = rightAdapter.getMenuOfMenuByPosition(position+1);
            headMenu = menu;
            headerView.setText(headMenu.getMenuName());
            for (int i = 0; i < dishMenuList.size(); i++) {
                if (dishMenuList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onLeftItemSelected(int position, DishMenu menu) {
        int sum=0;
        for(int i = 0;i<position;i++){
            sum+=dishMenuList.get(i).getDishList().size()+1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) rightMenu.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum,0);
        leftClickType = true;
    }

    @Override
    public void add(View view,int position) {
        int[] addLocation = new int[2];
        int[] cartLocation = new int[2];
        int[] recycleLocation = new int[2];
        view.getLocationInWindow(addLocation);
        shoppingCartView.getLocationInWindow(cartLocation);
        rightMenu.getLocationInWindow(recycleLocation);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0];
        startP.y = addLocation[1]-recycleLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1]-recycleLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final FakeAddImageView fakeAddImageView = new FakeAddImageView(this);
        mainLayout.addView(fakeAddImageView);
        fakeAddImageView.setImageResource(R.drawable.ic_add_circle_blue_700_36dp);
        fakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        fakeAddImageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(fakeAddImageView, "mPointF",
                new PointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                fakeAddImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                fakeAddImageView.setVisibility(View.GONE);
                mainLayout.removeView(fakeAddImageView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        ObjectAnimator scaleAnimatorX = new ObjectAnimator().ofFloat(shoppingCartView,"scaleX", 0.6f, 1.0f);
        ObjectAnimator scaleAnimatorY = new ObjectAnimator().ofFloat(shoppingCartView,"scaleY", 0.6f, 1.0f);
        scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
        scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
        animatorSet.setDuration(800);
        animatorSet.start();

        showTotalPrice();
    }

    @Override
    public void remove(View view,int position) {
        showTotalPrice();
    }



    private void showTotalPrice(){
        if(shopCart!=null && shopCart.getShoppingTotalPrice()>0){
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("￥ "+shopCart.getShoppingTotalPrice());
            jiage = shopCart.getShoppingTotalPrice();//获取总价格
            totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText(""+shopCart.getShoppingAccount());
            shuliang = shopCart.getShoppingAccount();//获取总数量
            shoppingSinglepass = shopCart.getShoppingSingleMap();
            //shishi = shopCart.getDishAccount();
            //shoppingSinglee_xcha = shopCart.getShoppingSingleMaptwo();
        }else {
            totalPriceTextView.setVisibility(View.GONE);
            totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    private void showCart(View view) {
        if(shopCart!=null && shopCart.getShoppingAccount()>0){
            ShopCartDialog dialog = new ShopCartDialog(this,shopCart,R.style.cartdialog);
            Window window = dialog.getWindow();
            dialog.setShopCartDialogImp(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount =0.5f;
            window.setAttributes(params);

            dialog.getPay_right_way_two().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TouchButton();
                }
            });
        }
    }

    @Override
    public void dialogDismiss() {
        showTotalPrice();
        rightAdapter.notifyDataSetChanged();
    }

    public void sendRequest(int id){
        String dishUrl="http://67.216.210.216/dishes.php?id="+id;
        HttpUtil.sendOkHttpRequest(dishUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Shop_Activity.this,"获取菜品数据失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText=response.body().string();
                List<com.a1141705068qq.main.gson.Dish> dishes= Utility.handleDishResponse(responseText);
                settle(dishes);
            }
        });
    }

    public void settle(final List<com.a1141705068qq.main.gson.Dish> dishes){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mdishes=dishes;
                initData(mdishes);
                initView();
                initAdapter();
            }
        });
    }
}
