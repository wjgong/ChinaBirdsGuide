package cn.org.scbirds.chinabirdsguide;

import android.media.Image;

/**
 * Created by gonggwen on 2017/5/10.
 */

public class Bird {
    // Labels Table name
    public static final String TABLE = "MainDATA";

    // Labels Table Columns names
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME_CN = "NAME_CN";
/*    public static final String KEY_NAME_EN = "name en";
    public static final String KEY_NAME_LA = "name la";
    public static final String KEY_MAIN_INFO = "main info";
    public static final String KEY_ORDER_CN = "order cn";
    public static final String KEY_ORDER_EN = "order en";
    public static final String KEY_FAMILY_CN = "family cn";
    public static final String KEY_FAMILY_EN = "family en";
    public static final String KEY_IMAGE_D = "image d";*/

    public int mBirdId;
    public String mNameCn;
}
