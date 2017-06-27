package xxx.functions.manager;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 标题：Activity管理类
 * 作者：kisen
 * 版本：
 * 创建时间：on 2017/5/31 15:03.
 */
public class ActivityManager {

    private static Map<String, ActivityManager> instanceMap;
    private Stack<Activity> activityStack;
    private String key;

    private ActivityManager(String key) {
        this.key = key;
    }

    static ActivityManager getScreenManager(String key) {
        key = "herbs_" + key;
        if (instanceMap == null) {
            instanceMap = new HashMap<>();
        }
        if (instanceMap.containsKey(key)) {
            return instanceMap.get(key);
        } else {
            ActivityManager manager = new ActivityManager(key);
            instanceMap.put(key, manager);
            return manager;
        }
    }

    //退出栈顶Activity
    public void popActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            //在从自定义集合中取出当前Activity时，也进行了Activity的关闭操作
            activityStack.remove(activity);
        }
    }

    //获得当前栈顶Activity
    public Activity currentActivity() {
        Activity activity = null;
        if (!activityStack.empty())
            activity = activityStack.lastElement();
        return activity;
    }

    //将当前Activity推入栈中
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if (!activityStack.contains(activity))
            activityStack.add(activity);
    }

    //清空栈，将activity置顶
    public void newRootActivity(Activity activity) {
        activityStack.remove(activity);
        clearStack();
        pushActivity(activity);
    }

    public void newStack(Activity activity) {
        newRootActivity(activity);
    }

    public void clearStack() {
        while (!activityStack.empty()) {
            Activity a = currentActivity();
            popActivity(a);
            destroyActivity(a);
        }
        activityStack.clear();
    }

    private void destroyActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }
}
