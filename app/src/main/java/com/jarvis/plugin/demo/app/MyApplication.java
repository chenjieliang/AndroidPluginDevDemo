package com.jarvis.plugin.demo.app;

import android.app.Application;
import android.content.res.Resources;

import com.jarvis.plugin.demo.core.Const;
import com.jarvis.plugin.demo.core.hook.GlobalActivityHookHelper;
import com.jarvis.plugin.demo.core.hook.HookInjectHelper;
import com.jarvis.plugin.demo.utils.AssetUtil;

import java.util.List;

public class MyApplication extends Application {

    private Resources newResource;

    public static String pluginPath = null;

    public static List<String> pluginPaths = null;

    @Override
    public void onCreate() {
        super.onCreate();
        pluginPath = AssetUtil.copyAssetToCache(this, Const.PLUGIN_FILE_NAME);
        //pluginPaths = AssetUtil.copyAssetToCache(this,Const.PLUGIN_FILE_NAME_LIST);

        //Hook第一次，绕过manifest检测
        GlobalActivityHookHelper.hook(this);

        //Hook第二次把插件的源文件class导入到系统的ClassLoader中
        //HookInjectHelper.injectPluginClass(this);
        HookInjectHelper.injectPluginListClass(this);

        //Hook第三次，加载插件资源包，让系统的Resources能够读取插件的资源
        newResource = HookInjectHelper.injectPluginResources(this);
        //newResource = HookInjectHelper.injectPluginListResources(this);
    }

    //重写资源管理器,资源管理器是每个Activity自带的，
    // 而Application的getResources则是所有Activity共有的
    //重写了它，就不必一个一个Activity去重写了
    @Override
    public Resources getResources() {
        return newResource == null ? super.getResources() : newResource;
    }
}
