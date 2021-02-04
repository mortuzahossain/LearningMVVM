package io.github.mortuzahossain.mvvm.utils;
/*
 * Created by mortuza on 4/2/21 | 4:57 PM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class IntentUtils {


    public static void callToPhone(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "Can not find any way to call.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void sendEmail(Context context, String name, String email, String subject,
                                 String mainBody) {
        Intent putExtra = new Intent("android.intent.action.SENDTO")
                .setData(new Uri.Builder().scheme("mailto").build())
                .putExtra("android.intent.extra.EMAIL",
                        new String[]{name + " <" + email + ">"})
                .putExtra("android.intent.extra.SUBJECT", subject)
                .putExtra("android.intent.extra.TEXT", mainBody);
        ComponentName resolveActivity = putExtra.resolveActivity(context.getPackageManager());
        ComponentName unflattenFromString = ComponentName.unflattenFromString("com.android.fallback/.Fallback");
        if (resolveActivity != null && !resolveActivity.equals(unflattenFromString)) {
            try {
                context.startActivity(Intent.createChooser(putExtra, "Send email with"));
                return;
            } catch (ActivityNotFoundException unused) {
                Toast.makeText(context, "Couldn't find an email app and account", Toast.LENGTH_LONG).show();
            }
        }
    }

    public static void sendSMS(Context context, String number) {
        Uri uri = Uri.parse("smsto:" + number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        context.startActivity(intent);
    }
}
