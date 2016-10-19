package com.li.rr.mvp.view.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.li.rr.base.activity.BaseSwipeBackActivity;
import com.li.rr.minterface.INetServie;
import com.li.rr.minterface.ITest;
import com.li.rr.mvp.bean.FileModel;
import com.li.rr.mvp.bean.netbean.MovieModel;
import com.li.rr.mvp.bean.netbean.SubjectsModel;
import com.li.rr.util.ApiConstants;
import com.li.rr.util.LinuxUtils;
import com.li.rr.util.RxBusUtil;
import com.li.rr.util.file.FileUtils;
import com.li.rr.util.http.HttpMethod;
import com.li.rr.util.http.RetrofitUtils;
import com.li.rr.widget.roundimageview.RoundedImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.InjectView;
import rr.li.com.rxjavaretrofit.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/5/23.
 */
public class ActivityAbout extends BaseSwipeBackActivity implements View.OnClickListener, ITest {
    @InjectView(R.id.tv_test)
    TextView tv_test;
    @InjectView(R.id.btn_openWIfiDebug)
    Button btn_openWIfiDebug;
    @InjectView(R.id.btn_closeWIfiDebug)
    Button btn_closeWIfiDebug;
    @InjectView(R.id.btn_getMovie)
    Button btn_getMovie;
    @InjectView(R.id.riv_testimage)
    RoundedImageView riv_testimage;
    @InjectView(R.id.btn_rx_operation)
    Button btn_rx_operation;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        getSupportActionBar().setTitle("关于");
        return false;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void setListener() {
        btn_openWIfiDebug.setOnClickListener(this);
        btn_closeWIfiDebug.setOnClickListener(this);
        tv_test.setOnClickListener(this);
        btn_getMovie.setOnClickListener(this);
        btn_rx_operation.setOnClickListener(this);
    }

    @Override
    protected void doBusiness(Context context) {
        RxBusUtil.getDefault().register(this);
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_about_us;
    }


    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }


    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_test:
                FileUtils.getInstance().test(ActivityAbout.this);
                break;
            case R.id.btn_openWIfiDebug:
                LinuxUtils.getInstance().connectWifiDeBug("5555");
                break;
            case R.id.btn_closeWIfiDebug:
//                LinuxUtils.getInstance().disconnectWifiDeBug("5555");
//                FileUtils.getInstance().getmediaInfoObservable(ActivityAbout.this).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<String>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onNext(String s) {
//                                Toast.makeText(ActivityAbout.this,s,Toast.LENGTH_SHORT).show();
//                            }
//                        });
                FileUtils.getInstance().getFilePermssion(ActivityAbout.this);
                break;
            case R.id.btn_getMovie:
//                Toast.makeText(this, "===========", Toast.LENGTH_SHORT).show();
//                HttpMethod.getInstance().getMovie(new Subscriber<MovieModel>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(MovieModel movieModel) {
//                        Toast.makeText(ActivityAbout.this,movieModel.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }, 0, 10);
//                FileModel f=new FileModel();
//                f.setName("咋说大沙发沙发");
//                RxBusUtil.getDefault().post(123,f);
//                f.setName("asdasdasdasd");
//                RxBusUtil.getDefault().post(321,f);

                Observable.create(new Observable.OnSubscribe<Drawable>() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        File f = HttpMethod.getInstance().download("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p1910813120.jpg");
                        Drawable drawable = BitmapDrawable.createFromPath(f.getAbsolutePath());
                        subscriber.onNext(drawable);
                        subscriber.onCompleted();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Drawable>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(Drawable drawable) {
                                riv_testimage.setImageDrawable(drawable);
                            }
                        });
                Test test = new Test();
                Observable<String> observable = test.valueObservable();
                test.setName("LMW666");
                observable.subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(ActivityAbout.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_rx_operation:

//                getMovie();
//                getIpInfo();
//                demo1();
//                demo2();
//                demo3();
//                demo4();
//                demo5(this);
//                demo7();
//                demo8();
//                demo9();
//                demo10();
//                demo11();
//                demo12();
                break;
        }
    }

    @RxBusUtil.Subscribe(code = 123)
    public void getFileModel(FileModel fileModel) {
        Toast.makeText(this, fileModel.getName(), Toast.LENGTH_SHORT).show();
    }

    @RxBusUtil.Subscribe(code = 321)
    public void getFileMode321(FileModel fileModel) {
        Toast.makeText(this, fileModel.getName(), Toast.LENGTH_SHORT).show();
    }
    public void getMovie() {
        RetrofitUtils.getInstance().retrofitCtreate(ApiConstants.Urls.DOUBAN_BASEURL, INetServie.class)
                .getMovieModel(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<MovieModel<SubjectsModel>>() {
                    @Override
                    public void call(MovieModel<SubjectsModel> movieModel) {
                        SubjectsModel subjectsModel = movieModel.getSubjects();
                        Toast.makeText(ActivityAbout.this, movieModel.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }


    public <T> void demo1(List<T> list) {
        Observable.from(list)
                .flatMap(new Func1<T, Observable<?>>() {
                    @Override
                    public Observable<?> call(T t) {
                        List<T> tList = new ArrayList<T>();
                        tList.add(t);
                        return Observable.from(tList);
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Toast.makeText(ActivityAbout.this, ((T) o).toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }

    public <T> void demo2(List<List<T>> lists) {
        Observable.from(lists)
                .flatMap(new Func1<List<T>, Observable<?>>() {
                    @Override
                    public Observable<?> call(List<T> list) {
                        return Observable.from(list);
                    }
                })

                .flatMap(new Func1<Object, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(Object o) {
                        return Observable.just(o);
                    }
                })
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Toast.makeText(ActivityAbout.this, ((T) o).toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }


    private void testDemo3() {
        List<List<List<MovieModel>>> listList = new ArrayList<>();
        List<List<MovieModel>> lists = new ArrayList<>();
        List<MovieModel> list = new ArrayList<>();
        MovieModel movieModel = new MovieModel();
        movieModel.setTitle("LLLLLLLLLLLL");
        list.add(movieModel);
        movieModel = new MovieModel();
        movieModel.setTitle("MMMMMMMMMMM");
        list.add(movieModel);
        lists.add(list);
        listList.add(lists);
        demo3(listList);
    }

    //如果使用for循环也可以实现此功能，但是为嵌套结构，使用RxJava就变成了链式结构
    public <T> void demo3(List<List<List<T>>> lists) {
        Observable.from(lists)
                //剥开最外层List 获取List<List<T>>作为元素，但发现还不是原子元素，需要继续分离
                .flatMap(new Func1<List<List<T>>, Observable<List<T>>>() {
                    @Override
                    public Observable<List<T>> call(List<List<T>> lists) {
                        return Observable.from(lists);
                    }
                })
                //继续分离
                .flatMap(new Func1<List<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(List<T> list) {
                        return Observable.from(list);
                    }
                })
                //直到发现原子元素使用Just 否则一直使用from
                .flatMap(new Func1<T, Observable<T>>() {
                    @Override
                    public Observable<T> call(T o) {
                        return Observable.just(o);
                    }
                })
                .subscribe(new Action1<T>() {
                    @Override
                    public void call(T o) {
                        Toast.makeText(ActivityAbout.this, ((T) o).toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }


    private void testDemo3Hash() {
        List<List<List<HashMap<String, String>>>> hashLists = new ArrayList<>();
        List<List<HashMap<String, String>>> hashList = new ArrayList<>();
        List<HashMap<String, String>> hash = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "LMW");
        hashMap.put("2", "LMW");
        hashMap.put("3", "LMW");
        hashMap.put("4", "LMW");
        hashMap.put("5", "LMW");
        hash.add(hashMap);
        hashList.add(hash);
        hashLists.add(hashList);
        demo3Hash(hashLists);
    }

    //如果使用for循环也可以实现此功能，但是为嵌套结构，使用RxJava就变成了链式结构
    public <K, V> void demo3Hash(List<List<List<HashMap<K, V>>>> lists) {
        Observable.from(lists)
                //剥开最外层List 获取List<List<T>>作为元素，但发现还不是原子元素，需要继续分离
                .flatMap(new Func1<List<List<HashMap<K, V>>>, Observable<List<HashMap<K, V>>>>() {
                    @Override
                    public Observable<List<HashMap<K, V>>> call(List<List<HashMap<K, V>>> lists) {
                        return Observable.from(lists);
                    }
                })
                //继续分离
                .flatMap(new Func1<List<HashMap<K, V>>, Observable<HashMap<K, V>>>() {
                    @Override
                    public Observable<HashMap<K, V>> call(List<HashMap<K, V>> list) {
                        return Observable.from(list);
                    }
                })
                //直到发现原子元素使用Just 否则一直使用from
                .flatMap(new Func1<HashMap<K, V>, Observable<HashMap<K, V>>>() {
                    @Override
                    public Observable<HashMap<K, V>> call(HashMap<K, V> o) {
                        return Observable.just(o);
                    }
                })
                .subscribe(new Action1<HashMap<K, V>>() {
                    @Override
                    public void call(HashMap<K, V> o) {
                        Toast.makeText(ActivityAbout.this, o.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        ;
    }


    //    public void demo1() {
//        Observable.just("String")
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this, s, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    public void demo2() {
//        Observable.just("通过just传进的参数是字符串")
//                .map(new Func1<String, Object>() {
//                    @Override
//                    public Object call(String s) {
//                        Toast.makeText(ActivityAbout.this, s, Toast.LENGTH_SHORT).show();
//                        s = "1";
//                        return Integer.parseInt(s) + 1;
//                    }
//                })
//                .subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        Toast.makeText(ActivityAbout.this, "我出来的时候却是一个数字(对象的变换)" + o, Toast.LENGTH_SHORT).show();
//                    }
//                })
//        ;
//    }
//    public void demo3(){
//        Observable.just("字符串哦")
//                .map(new Func1<String, Integer>() {
//                    @Override
//                    public Integer call(String s) {
//                        return s.hashCode();
//                    }
//                })
//        .subscribe(new Action1<Integer>() {
//            @Override
//            public void call(Integer integer) {
//                Toast.makeText(ActivityAbout.this , "hashCode："+integer , Toast.LENGTH_LONG).show();
//            }
//        })
//        ;
//    }
//
//    public void demo4(){
//        List<Object> objects=new ArrayList<>();
//        for (int i=0 ; i<100 ; i++){
//            objects.add(i);
//        }
//        Observable.from(objects)
//                .subscribe(new Action1<Object>() {
//                    @Override
//                    public void call(Object o) {
//                        Toast.makeText(ActivityAbout.this , ""+o , Toast.LENGTH_LONG).show();
//                    }
//                });
//    }
//
//    ITest iTest;
//    public void demo5(ITest iTest){
//        iTest.query("asd").flatMap(new Func1<List<String>, Observable<?>>() {
//            @Override
//            public Observable<?> call(List<String> strings) {
//                return Observable.from(strings);
//            }
//        })
//        .subscribe(new Action1<Object>() {
//            @Override
//            public void call(Object o) {
//                Toast.makeText(ActivityAbout.this , ""+o , Toast.LENGTH_LONG).show();
//            }
//        })
//        ;
//    }
//
//
//
//
//    //与demo5类似 实现方式不同
//    public void demo6(){
//        List<List<String>> objects=new ArrayList<>();
//        List<String> list=new ArrayList<>();
//        list.add("123");
//        list.add("456");
//        objects.add(list);
//
//        Observable.from(objects).flatMap(new Func1<List<String>, Observable<String>>() {
//            @Override
//            public Observable<String> call(List<String> strings) {
//                return Observable.from(strings);
//            }
//        })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this , "====="+s , Toast.LENGTH_LONG).show();
//                    }
//                })
//        ;
//    }
//    //与demo5 、 6 类似
//    public void demo7(){
//        List<String> list=new ArrayList<>();
//        list.add("真复杂");
//        Observable.from(list).flatMap(new Func1<String, Observable<String>>() {
//            @Override
//            public Observable<String> call(String s) {
//                ArrayList<String> strings=new ArrayList<String>();
//                strings.add(s);
//                return Observable.from(strings);
//            }
//        })
//        .subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Toast.makeText(ActivityAbout.this , "demo7"+s , Toast.LENGTH_LONG).show();
//            }
//        })
//        ;
//    }
//
//
//
//
//    //利用接口实现 demo7增强
//    public void demo8(){
//        iTest=this;
//        List<List<String>> lists=new ArrayList<>();
//        List<String> list=new ArrayList<>();
//        iTest.query("太扯淡了")
//                .flatMap(new Func1<List<String>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(List<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//        .flatMap(new Func1<Object, Observable<String>>() {
//            @Override
//            public Observable<String> call(Object o) {
//                return iTest.getTitle(o+"");
//            }
//        })
//        .subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Toast.makeText(ActivityAbout.this , "demo8"+s , Toast.LENGTH_LONG).show();
//            }
//        })
//        ;
//    }
    //demo5 demo8
    @Override
    public Observable<List<String>> query(String url) {
        List<List<String>> lists = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(url);
        lists.add(list);
        return Observable.from(lists);
    }

    //demo8
    @Override
    public Observable<String> getTitle(String url) {
        List<String> list = new ArrayList<>();
        list.add(url);
        return Observable.from(list);
    }

//    //与Demo8类似 未用接口实现
//    public void demo9(){
//        List<List<String>> lists=new ArrayList<>();
//        List<String> list=new ArrayList<>();
//        list.add("123");
//        list.add("456");
//        lists.add(list);
//        Observable.from(lists)
//                .flatMap(new Func1<List<String>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(List<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//                .flatMap(new Func1<Object, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(Object o) {
//                        List<String> s=new ArrayList<String>();
//                        s.add(""+o);
//                        return Observable.from(s);
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this , "demo9"+s , Toast.LENGTH_LONG).show();
//                    }
//                })
//        ;
//    }
//
//
//    //与Demo8类似 添加过滤
//    public void demo10(){
//        List<List<String>> lists=new ArrayList<>();
//        List<String> list=new ArrayList<>();
//        list.add("123");
//        list.add("456");
//        list.add("789");
//        lists.add(list);
//        Observable.from(lists)
//                .flatMap(new Func1<List<String>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(List<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//                .flatMap(new Func1<Object, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(Object o) {
//                        List<String> s=new ArrayList<String>();
//                        s.add(""+o);
//                        return Observable.from(s);
//                    }
//                })
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        if("123".equals(s)){
//                            return false;
//                        }else {
//                            return true;
//                        }
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this , "demo9"+s , Toast.LENGTH_LONG).show();
//                    }
//                })
//        ;
//    }
//
//    //与Demo8类似 添加过滤限制最大数量
//    public void demo11(){
//        List<List<String>> lists=new ArrayList<>();
//        List<String> list=new ArrayList<>();
//        list.add("123");
//        list.add("456");
//        list.add("789");
//        lists.add(list);
//        Observable.from(lists)
//                .flatMap(new Func1<List<String>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(List<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//                .flatMap(new Func1<Object, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(Object o) {
//                        List<String> s=new ArrayList<String>();
//                        s.add(""+o);
//                        return Observable.from(s);
//                    }
//                })
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        if("123".equals(s)){
//                            return false;
//                        }else {
//                            return true;
//                        }
//                    }
//                })
//                .take(1)
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this , "demo9"+s , Toast.LENGTH_LONG).show();
//                    }
//                })
//        ;
//    }
//    //与Demo8类似 添加过滤限制最大数量并且在subscribe执行之前(输出每个元素之前)添加一个动作
//    public void demo12(){
//        List<List<String>> lists=new ArrayList<>();
//        List<String> list=new ArrayList<>();
//        list.add("123");
//        list.add("456");
//        list.add("789");
//        lists.add(list);
//        Observable.from(lists)
//                .flatMap(new Func1<List<String>, Observable<?>>() {
//                    @Override
//                    public Observable<?> call(List<String> strings) {
//                        return Observable.from(strings);
//                    }
//                })
//                .flatMap(new Func1<Object, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(Object o) {
//                        List<String> s=new ArrayList<String>();
//                        s.add(""+o);
//                        return Observable.from(s);
//                    }
//                })
//                .filter(new Func1<String, Boolean>() {
//                    @Override
//                    public Boolean call(String s) {
//                        if("123".equals(s)){
//                            return false;
//                        }else {
//                            return true;
//                        }
//                    }
//                })
//                .take(1)
//                .doOnNext(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this , "demo9打印之前做的事情"+s , Toast.LENGTH_LONG).show();
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Toast.makeText(ActivityAbout.this , "demo9"+s , Toast.LENGTH_LONG).show();
//                    }
//                })
//        ;
//    }

}
