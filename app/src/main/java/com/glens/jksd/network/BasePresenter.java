package com.glens.jksd.network;

import rx.subscriptions.CompositeSubscription;

/**
 * 定义一个Presenter的基础类，后续具体功能类继承于它
 */
public class BasePresenter implements Presenter {

    //声明一个CompositeSubscription对象，注意是protected修饰符，便于子类进行调用
    protected CompositeSubscription mCompositeSubscription;

    @Override
    public void onCreate() {
        //在基础类中对CompositeSubscription进行初始化，子类中就不用再写一次
        //子类如果需要对onCreate进行重写，记得先调用super.onCreate();
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onDestroy() {
        //释放CompositeSubscription，否则会造成内存泄漏
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void BindPresentView(PresentView presentView) {
        //与具体视图进行绑定，留个子类进行扩展
    }
}
