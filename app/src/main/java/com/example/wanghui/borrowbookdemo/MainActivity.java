package com.example.wanghui.borrowbookdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanghui.borrowbookdemo.model.Book;
import com.example.wanghui.borrowbookdemo.model.Reader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private EditText readName;
    private RadioGroup mradioGroup;
    private RadioButton maleRadio;
    private  RadioButton femaleRadio;
    private TextView  borrowTime;
    private CheckBox historyCheckBox;
    private  CheckBox  horrorCheckBox;
    private  CheckBox  artCheckBox;
    private SeekBar  ageSeekBar;
    private ImageView bookImageView;
    private  TextView mBookName;
    private  TextView mBookStyle;
    private  TextView mBookSuitableAge;
    private Button  searchButton;
    private  TextView  searchTip;
    private  Button nextButton;
    private List<Book> bookList;
    private Reader reader;
    private boolean isHistory;
    private boolean isArt;
    private boolean isHorror;
    private int age;
    private List<Book> bookResult;
    private int currentIndex;
    private String borrowTime1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initViews();
        //初始化数据
        initData();
        //添加监听器
        setListener();
    }

    private void setListener() {
     readName.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
             if(reader!=null){
                 reader.setName(s.toString());
             }
         }
     });
         mradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 switch (checkedId){
                     case  R.id.male:
                         reader.setSex("男");
                         break;
                     case  R.id.female:
                         reader.setSex("女");
                         break;
                      default:
                          break;
                 }
             }
         });
         borrowTime.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {

             }

             @Override
             public void afterTextChanged(Editable s) {
                    if(reader!=null){
                        //时间格式
                        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date  data=null;
                         borrowTime1 = s.toString();
                        try {
                         data=  format.parse(borrowTime1);
                            reader.setDataTime(format1.format(data));

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
             }
         });
         historyCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   isHistory = isChecked;
             }
         });
        horrorCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHorror = isChecked;
            }
        });
        artCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isArt= isChecked;
            }
        });

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                 age = seekBar.getProgress();
                 reader.setAge(age);
             Toast.makeText(MainActivity.this,"年龄："+age,Toast.LENGTH_SHORT).show();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 searchBook();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    currentIndex++;
                    if(currentIndex<bookResult.size()){
                        bookImageView.setImageResource(bookResult.get(currentIndex).getPic());
                        //设置searchTip中的内容
                        mBookName.setText(bookResult.get(currentIndex).getName());
                        mBookStyle.setText(bookResult.get(currentIndex).getStyle());
                        mBookSuitableAge.setText(bookResult.get(currentIndex).getSuitableAge()+"");
                        searchTip.setText("符合条件的书共有"+bookResult.size()+"本");
                        Toast.makeText(MainActivity.this,"姓名："+reader.getName()+",性别："+reader.getSex()+",年龄："+reader.getAge()+
                                ",借出时间："+reader.getDataTime(),Toast.LENGTH_SHORT).show();


                }else{
                    bookResult=new ArrayList<>();
                    Toast.makeText(MainActivity.this,"没有啦",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void searchBook() {
        //将resultBook清空
        if(bookResult == null)  bookResult = new ArrayList<>();
        bookResult.clear();
        currentIndex= 0;
        //遍历所有书籍
        for(int index = 0; index<bookList.size();index++){
            //将符合条件的书籍放在resultBook里面
            if(bookList.get(index).getSuitableAge()<age

             ){
                if(  bookList.get(index).isHistory()==isHistory
                        ||  bookList.get(index).isHorror()==isHorror
                        ||  bookList.get(index).isArt()==isArt){
                    bookResult.add(bookList.get(index));

                }
            }

        }
        //设置显示index = 0 的第一本数据
        if(currentIndex<bookResult.size()){
            bookImageView.setImageResource(bookResult.get(currentIndex).getPic());
            //设置searchTip中的内容
            mBookName.setText(bookResult.get(currentIndex).getName());
            mBookStyle.setText(bookResult.get(currentIndex).getStyle());
           mBookSuitableAge.setText(bookResult.get(currentIndex).getSuitableAge()+"");
           searchTip.setText("符合条件的书共有"+bookResult.size()+"本");
            Toast.makeText(MainActivity.this,"姓名："+reader.getName()+",性别："+reader.getSex()+",年龄："+reader.getAge()+
                    ",借出时间："+reader.getDataTime(),Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {

        //初始化bookList
        bookList = new ArrayList<>();
        bookList.add(new Book("人生感悟","人生/哲学",20,R.drawable.aa,true,false,true));
        bookList.add(new Book("边城","剧情/文艺",30,R.drawable.bb,true,true,true));
        bookList.add(new Book("sapir","未知",20,R.drawable.cc,false,false,true));
        bookList.add(new Book("光辉岁月","剧情/人生",40,R.drawable.dd,true,false,true));
        bookList.add(new Book("宋词三百首","诗词",10,R.drawable.ee,true,false,false));
        bookList.add(new Book("荷花","散文",20,R.drawable.f,true,false,true));
        bookList.add(new Book("中国古代文学","文学",30,R.drawable.ff,true,false,true));
        bookList.add(new Book("无花果","剧情",40,R.drawable.gg,false,true,true));
        bookList.add(new Book("古镇记忆","散文",35,R.drawable.hh,true,true,false));

         reader = new Reader();
          bookResult = new ArrayList<>();

    }

    private void initViews() {
        readName = findViewById(R.id.mEditName);
        mradioGroup = findViewById(R.id.radioGroup);
        maleRadio = findViewById(R.id.male);
        femaleRadio = findViewById(R.id.female);
        borrowTime = findViewById(R.id.borrowedTime);
        historyCheckBox = findViewById(R.id.isHistory);
        horrorCheckBox = findViewById(R.id.isHorror);
        artCheckBox = findViewById(R.id.isArt);
        ageSeekBar = findViewById(R.id.seekBar);
        bookImageView = findViewById(R.id.bookPic);
        mBookName = findViewById(R.id.bookName);
        mBookStyle = findViewById(R.id.bookStyle);
        mBookSuitableAge = findViewById(R.id.bookSuitableAge);
        searchButton= findViewById(R.id.search_button);
        searchTip = findViewById(R.id.showTip);
        nextButton = findViewById(R.id.next_button);



    }
}
