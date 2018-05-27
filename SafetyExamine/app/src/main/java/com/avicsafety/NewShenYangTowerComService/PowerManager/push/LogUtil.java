package com.avicsafety.NewShenYangTowerComService.PowerManager.push;

/**
 * Created by 刘畅 on 2017/8/18.
 */

public class LogUtil {
    @SuppressWarnings("unchecked")
    public static String makeLogTag(Class cls) {
        return "Androidpn_" + cls.getSimpleName();
    }
}
