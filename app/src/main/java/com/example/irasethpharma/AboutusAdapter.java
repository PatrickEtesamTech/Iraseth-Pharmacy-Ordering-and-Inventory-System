package com.example.irasethpharma;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AboutusAdapter extends PagerAdapter
{

    Context ctx;
    String[] text = new String[] {"Ever since its establishment in July 2013, Iraseth Pharma Inc., has been one of the country's rapidly growing trading companies in terms of Healthcare Solutions. The Company strives to uphold its steadfast commitment to deliver quality and safe healthcare products and services through the use of latest state of the art medical supplies and equipment's. By efficient use of research, we aim to introduce unparalleled technology development to enable us to be at par with the standards of the global market.\n" +
            "\n" +
            "We take pride in contributing to the society by creating a safe working environment not only for every patient but for the entire healthcare team. A strong healthcare system would necessarily equate to a healthy options including access to basic and most importantly, safe healthcare services should be at everyone's disposal.\n" +
            "To that end, we look forward to your continued support and patronage.  \n", "IRASETH PHARMA INCORPORATED \n" +
            "\n" +
            "Is a Philippine-based company providing integrated medical and diagnostics solutions for the country's foremost hospitals and clinical laboratories. Established in 2013, we are an importer, indentor and distributor of medical and diagnostic devices as well as laboratories supplies and consumables. We deliver innovative solutions at cost, servicing both local governments and private sector.\n" +
            "\n" +
            "We deliver innovative solutions at cost, servicing both local governments and private sectors.\n" +
            "\n" +
            "Our proficiency lies beyond our commitment in providing quality healthcare products emphasizing the importance of needle stick injury cases' eradication.\n" +
            "\n" +
            "We strive to innovate, search for solutions and push the boundaries of science at par with the highest degree of excellence as our answer to the global thrust for wellness.",

            "MISSION\n" +"\n"+"To provide access to quality pharmaceutical and medical device products with innovative and safety features that adhere to high standards of quality and service, hence CREATING A SAFE WORKING ENVIRONMENT IN HOSPITAL setting and community.\n" +
                    "\n"+
                    "VISION\n" + "\n"+ "We envision ourselves as a prime provider of high quality medical devices and pharmaceutical products that would substantially UPGRADE THE QUALITY OF HEALTH SERVICES for Filipino people.\n" +
                    "\n" +
                    "We also aim to ERADICATE ALL CASES OF NEEDLE STICK INJURIES incurred in hospital settings and promote the early prevention and screening of diabetes and communicable diseases in the Philippines.\n",

            "OUR COMMITMENT\n" + "\n" +
                    "To make a difference by being a responsible organization that BUILDS AND SUPPORT A SAFE WORKING ENVIRONMENT for \u200Bevery healthcare provider, patient and community."};

    AboutusAdapter(Context ctx){
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        TextView textView = new TextView(ctx);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setPadding(10,10,10,10);
        textView.setText(text[position]);
        container.addView(textView, 0);

        return textView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((TextView) object);
    }
}
