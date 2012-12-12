package net.dirong.turbo.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import net.dirong.turbo.util.SecurityHelper;

/**
 * @author almozavr ;)
 */
public abstract class PreferenceHelper {

    private SharedPreferences prefs;

    protected PreferenceHelper(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    protected String getStringValue(String key, boolean useDecryption) {
        String value = prefs.getString(key, null);
        if (useDecryption && value != null)
            return SecurityHelper.decodeString(value);
        else
            return value;
    }

    protected String getStringValue(String key, boolean useDecryption, String defaultStr) {
        String value = prefs.getString(key, defaultStr);
        if (useDecryption && value != null)
            return SecurityHelper.decodeString(value);
        else
            return value;
    }

    protected long getLongValue(String key, boolean useDecryption) {
        String stringValue = getStringValue(key, useDecryption);
        if (stringValue == null)
            return 0L;
        else
            return Long.valueOf(stringValue);
    }

    protected int getIntValue(String key, boolean useDecryption) {
        String stringValue = getStringValue(key, useDecryption);
        if (stringValue == null)
            return 0;
        else
            return Integer.valueOf(stringValue);
    }

    protected boolean getBooleanValue(String key, boolean useDecryption) {
        return getBooleanValue(key, useDecryption, false);
    }

    protected boolean getBooleanValue(String key, boolean useDecryption, boolean defValue) {
        String stringValue = getStringValue(key, useDecryption);
        if (stringValue == null)
            return defValue;
        else
            return Boolean.valueOf(stringValue);
    }

    protected <U> void storeValue(String key, U value, boolean useEncryption) {
        String resultValue;
        if (useEncryption)
            resultValue = SecurityHelper.encodeString(String.valueOf(value));
        else
            resultValue = String.valueOf(value);
        prefs.edit().putString(key, resultValue).commit();
    }

    protected <U> void removeValue(String key) {
        prefs.edit().remove(key).commit();
    }

}
